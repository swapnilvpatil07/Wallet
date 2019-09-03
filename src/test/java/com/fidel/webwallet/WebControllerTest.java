/**
 * 
 */
package com.fidel.webwallet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Swapnil
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(WebController.class)
public class WebControllerTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#index(org.springframework.ui.Model)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/wallet/home")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/index.jsp"));
	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#userHome(java.lang.String, java.lang.String, org.springframework.ui.Model, java.security.Principal)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserHome() throws Exception {
		String errorMsg = "";
		String status = "";

		/*
		 * this.mockMvc.perform(post("/wallet/secure/home").param("status",
		 * status).param("errorMsg", errorMsg))
		 * .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.forwardedUrl(
		 * "/WEB-INF/jsp/index.jsp"));
		 */
	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#payment(org.springframework.ui.Model)}.
	 */
	@Test
	public void testPayment() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#userSignUp(com.fidel.webwallet.model.RegUserInfo, org.springframework.validation.BindingResult, org.springframework.web.context.request.WebRequest, org.springframework.validation.Errors, org.springframework.ui.Model)}.
	 */
	@Test
	public void testUserSignUp() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#userLogIn(java.lang.String, java.lang.String, org.springframework.ui.Model)}.
	 */
	@Test
	public void testUserLogIn() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#hello(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.security.Principal, org.springframework.ui.Model)}.
	 */
	@Test
	public void testHello() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#qrcode(java.lang.String, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testQrcode() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#initTxn(java.lang.String, org.springframework.ui.Model, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testInitTxn() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#processTransaction(java.lang.String, com.fidel.webwallet.model.PayLoad, org.springframework.ui.Model, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testProcessTransaction() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#payRequest(java.lang.String, java.lang.String, com.fidel.webwallet.model.PayLoad, org.springframework.ui.Model, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testPayRequest() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#txnHandler(java.lang.String, java.lang.String, com.fidel.webwallet.model.PayLoad, org.springframework.ui.Model)}.
	 */
	@Test
	public void testTxnHandler() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#sendOtp(java.lang.String, org.springframework.ui.Model)}.
	 */
	@Test
	public void testSendOtp() {

	}

	/**
	 * Test method for
	 * {@link com.fidel.webwallet.controller.WebController#verifyOtp(java.lang.String, org.springframework.ui.Model)}.
	 */
	@Test
	public void testVerifyOtp() {

	}

}
