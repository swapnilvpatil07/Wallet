package com.fidel.webwallet;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebWalletApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.address}")
	private String serverAddress;

	public static void main(String[] args) {
		SpringApplication.run(WebWalletApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			openHomePage();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private void openHomePage() throws URISyntaxException {
		System.out.println("serverAddress: " + serverAddress);
		String url = "https://" + serverAddress + ":" + serverPort + "/wallet/secure/home";
		URI homepage = new URI(url);

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(homepage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
