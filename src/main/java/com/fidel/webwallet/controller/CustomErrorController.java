
package com.fidel.webwallet.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Swapnil
 *
 */
@Controller
public class CustomErrorController implements ErrorController {

	/**
	 * 
	 */
	public CustomErrorController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/error")
	public String handleError(Model model, HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object errMsg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("errCd", statusCode);
				model.addAttribute("errMsg", errMsg);
				return "error/error-page";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("errCd", statusCode);
				model.addAttribute("errMsg", errMsg);
				return "error/error-page";
			}
			model.addAttribute("errCd", statusCode);
			model.addAttribute("errMsg", errMsg);
		}

		return "error/error-page";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
