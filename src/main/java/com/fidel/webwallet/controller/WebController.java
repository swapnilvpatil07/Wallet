/**
 * 
 */
package com.fidel.webwallet.controller;

import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.fidel.webwallet.commons.CommonUtils;
import com.fidel.webwallet.commons.Constants;
import com.fidel.webwallet.model.OrderDetails;
import com.fidel.webwallet.model.PayLoad;
import com.fidel.webwallet.model.RegUserInfo;
import com.fidel.webwallet.model.Response;
import com.fidel.webwallet.model.UserInfo;
import com.fidel.webwallet.service.OneTimePassword;
import com.fidel.webwallet.service.OrderDetailsService;
import com.fidel.webwallet.service.UserInfoService;

/**
 * @author Swapnil
 *
 */
@Controller
@RequestMapping(path = "/wallet")
public class WebController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private OrderDetailsService orderDetailsService;

	@Autowired
	private Response response;

	@Autowired
	private OneTimePassword oneTimePassword;

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.address}")
	private String serverAddress;

	@Value("${payment.server.port}")
	private String payServerPort;

	@Value("${payment.server.address}")
	private String payServerAddress;

	private int bal;

	@GetMapping(path = "/home")
	public String index(Model model) {
		return "index";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(path = "/secure/home")
	public String userHome(@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "errorMsg", required = false) String errorMsg, Model model, Principal principal) {
		List<UserInfo> userList;
		UserInfo user = new UserInfo();

		// get logged in user details
		if (!(userList = userInfoService.getUserByEmail(principal.getName())).isEmpty())
			user = userList.get(0);

		// get authenticated user
		UserInfo usrInfo = userInfoService.getAuthenticatedUserInfo();
		if (usrInfo != null) {

			// get order count
			long count = usrInfo.getOrderDetails().stream().filter(ord -> ord.getTxnSts().equals(1)).count();
			if (count != 0) {
				List<OrderDetails> list = new ArrayList<OrderDetails>(usrInfo.getOrderDetails().stream()
						.filter(ord -> ord.getTxnSts().equals(1)).collect(Collectors.toList()));
				Collections.sort(list);
				OrderDetails order = list.get(list.size() - 1);

				// set balance
				model.addAttribute(Constants.BALANCE, order.getBalance());
			} else {
				// set balance
				model.addAttribute(Constants.BALANCE, Constants.ZERO_BAL);
			}
		}

		// prepare response
		model.addAttribute("userInfo", user);
		model.addAttribute("orderDetails", user.getOrderDetails());
		model.addAttribute("status", status);
		model.addAttribute("errorMsg", errorMsg);

		// view to render
		return "index";
	}

	@GetMapping(path = "/payment")
	public String payment(Model model) {
		// view to render
		return "payment";
	}

	@PostMapping(path = "/user/register")
	public String userSignUp(RegUserInfo userIn, BindingResult result, WebRequest request, Errors errors, Model model) {

		// clear response
		model.addAttribute(Constants.RESPONSE, response.clear());

		// validation
		if (!userIn.validate().isEmpty()) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Fill out all the input fields.");
			model.addAttribute(Constants.RESPONSE, response);

			// view to render
			return "index";
		}

		// email id check
		if (userInfoService.isEmailAlreadyExist(userIn.getEmail())) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Email Id already registered.");
			model.addAttribute(Constants.RESPONSE, response);

			// view to render
			return "index";
		}

		// mobile number check
		if (userInfoService.isContactNumberAlreadyExist(userIn.getContact())) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Mobile number already registered.");
			model.addAttribute(Constants.RESPONSE, response);

			// view to render
			return "index";
		}

		if (!userInfoService.registerUser(userIn)) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Error while data persistance.");
			model.addAttribute(Constants.RESPONSE, response);

			// view to render
			return "index";
		}

		response.setStatus(Constants.SUCCESS);
		response.setErrorMsg("You are successfully register!");
		model.addAttribute(Constants.RESPONSE, response);

		UserInfo usrInfo = userInfoService.getAuthenticatedUserInfo();
		if (usrInfo != null) {
			long count = usrInfo.getOrderDetails().stream().count();
			if (count != 0) {
				Stream<OrderDetails> stream = usrInfo.getOrderDetails().stream();
				OrderDetails order = stream.skip(count - 1).findFirst().get();

				model.addAttribute(Constants.BALANCE, order.getBalance());
			} else {
				model.addAttribute(Constants.BALANCE, Constants.ZERO_BAL);
			}
		}

		// view to render
		return "index";
	}

	@GetMapping(path = "/login")
	public String userLogIn(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		model.addAttribute("userIn", new RegUserInfo());

		String errorMessge = null;
		if (error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if (logout != null) {
			errorMessge = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMessge", errorMessge);
		model.addAttribute("loginflg", true);

		UserInfo usrInfo = userInfoService.getAuthenticatedUserInfo();
		if (usrInfo != null) {
			long count = usrInfo.getOrderDetails().stream().count();
			if (count != 0) {
				Stream<OrderDetails> stream = usrInfo.getOrderDetails().stream();
				OrderDetails order = stream.skip(count - 1).findFirst().get();
				model.addAttribute(Constants.BALANCE, order.getBalance());
			} else {
				model.addAttribute(Constants.BALANCE, Constants.ZERO_BAL);
			}
		}

		// view to render
		return "index";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/update-profile")
	public @ResponseBody ResponseEntity<?> hello(@RequestParam(value = "fname") String fname,
			@RequestParam(value = "lname") String lname, @RequestParam(value = "dob") String dob,
			@RequestParam(value = "mobileno") String mobileno, @RequestParam(value = "emailid") String emailid,
			Principal principal, Model model) {
		List<UserInfo> list;
		UserInfo user = null;
		if (!(list = userInfoService.getUserByEmail(principal.getName())).isEmpty()) {
			if (list.size() == 1) {
				user = list.get(0);
				user.setAddress("");
				if (!user.getContactNo().equals(mobileno)) {
					user.setContactNo(mobileno);
					user.setCntVerified(Constants.NON_VERIFIED);
				}
				user.setDob(dob);
				user.setEmailId(emailid);
				user.setEmailVerified(Constants.NON_VERIFIED);
				user.setfName(fname);
				user.setlName(lname);
			} else {
				response.setStatus(Constants.ERROR);
				response.setErrorMsg("Dupplicate data.");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} else {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Data not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		boolean sts = userInfoService.save(user);
		if (sts) {
			// response.setData(userInfoService.getAuthenticatedUserInfo());
			response.setStatus(Constants.SUCCESS);
			response.setErrorMsg("You have successfully updated your profile!");
		} else {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Error.");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "qrcode/{id}", method = RequestMethod.GET)
	public void qrcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();

		UserInfo info = userInfoService.findById(id, true);
		if (info != null) {
			outputStream.write(CommonUtils.getQRCodeImage(info.toString(), 220, 200));
			outputStream.flush();
			outputStream.close();
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(path = "/secure/init-txn")
	public String initTxn(@RequestParam("txnAmt") String txnAmt, Model model, HttpServletResponse response)
			throws Exception {

		UserInfo usrInfo = userInfoService.getAuthenticatedUserInfo();
		Integer ordId = orderDetailsService.getNextOrdNo();

		PayLoad payLoad = new PayLoad();
		payLoad.setTxnAmount(txnAmt);
		payLoad.setmId(Constants.MID);
		payLoad.setCustId(usrInfo.getCustId().toString());
		payLoad.setEmail(usrInfo.getEmailId());
		payLoad.setMobileNo(usrInfo.getContactNo());
		payLoad.setOrderId(ordId.toString());
		payLoad.setChannelId(Constants.CH_WEB);
		payLoad.setReqType(Constants.ADD_MONEY);
		payLoad.setCallBckUrl("http://" + serverAddress + ":" + serverPort + "/wallet/txnStatus");
		payLoad.setServerUrl("http://" + payServerAddress + ":" + payServerPort + "/api/v1/net-pay");

		model.addAttribute("payload", payLoad);

		// view to render
		return "init-txn";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(path = "/processTransaction")
	public String processTransaction(@RequestParam(name = "ordId") String orderId, PayLoad load, Model model,
			HttpServletResponse response) throws Exception {
		model.addAttribute("payload", load);

		// view to render
		return "payment";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(path = "/txnStatus")
	public String txnHandler(@RequestParam("status") String status, @RequestParam("message") String message,
			PayLoad load, Model model) throws Exception {
		OrderDetails orderData = null;
		UserInfo usrInfo = userInfoService.getAuthenticatedUserInfo();
		OrderDetails ord = orderDetailsService.getOrder(load.getOrderId());

		long count0 = usrInfo.getOrderDetails().stream().filter(order -> order.getTxnSts().equals(1)).count();

		if (count0 != 0) {
			Stream<OrderDetails> stream1 = usrInfo.getOrderDetails().stream()
					.filter(order -> order.getTxnSts().equals(1));
			orderData = stream1.skip(count0 - 1).findFirst().get();

			List<OrderDetails> list = new ArrayList<OrderDetails>(usrInfo.getOrderDetails());
			Collections.sort(list);
			orderData = list.get(list.size() - 1);

		} else {
			orderData = new OrderDetails();
		}

		if (ord.getOrderId() != 0) {
			if (load.getReqType().equals(Constants.ADD_MONEY)) {
				bal = ((orderData.getBalance() == null) ? 0 : orderData.getBalance())
						+ Integer.parseInt(load.getTxnAmount());
				ord.setBalance(bal);
			} else {
				ord.setBalance(orderData.getBalance());
			}
			ord.setTxnAmt(Double.parseDouble(load.getTxnAmount()));
			ord.setTxnDate(CommonUtils.getDateTime());
			ord.setTxnSts((status.equals(Constants.SUCCESS)) ? 1 : 0);
			ord.setTxnType(Constants.ADD_MONEY);

			// boolean sts = orderDetailsService.updateTxn(ord);
			SortedSet<OrderDetails> ordList = usrInfo.getOrderDetails();
			ordList.add(ord);

			usrInfo.setOrderDetails(ordList);
			userInfoService.save(usrInfo);
		}

		return "redirect:/wallet/secure/home?status=" + status + "&errorMsg=" + message;
	}

	@GetMapping(path = "/otp")
	public ResponseEntity<Response> sendOtp(@RequestParam(name = "mobileno", required = true) String number,
			Model model) {
		boolean sts;
		try {
			sts = oneTimePassword.send(number);
		} catch (Exception e) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (!sts) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Error while generating OTP.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		response.setStatus(Constants.SUCCESS);
		response.setErrorMsg("OTP has been sent to mobile number +91" + number);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/verify-otp")
	public ResponseEntity<Response> verifyOtp(@RequestParam("otp") String otp, Model model) {
		boolean sts = oneTimePassword.verify(otp);
		if (!sts) {
			response.setStatus(Constants.ERROR);
			response.setErrorMsg("Invalid OTP.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		UserInfo usrInfo = userInfoService.getAuthenticatedUserInfo();
		if (usrInfo.getCntVerified().equals("0")) {
			usrInfo.setCntVerified("1");
		}

		userInfoService.updateVarifiedUser(usrInfo.getCustId());

		response.setStatus(Constants.SUCCESS);
		response.setErrorMsg("Mobile number has been verified succesfully.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
