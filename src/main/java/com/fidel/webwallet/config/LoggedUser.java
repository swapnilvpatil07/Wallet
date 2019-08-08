/**
 * 
 */
package com.fidel.webwallet.config;

import java.util.ArrayDeque;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Swapnil
 *
 */
@Configuration
public class LoggedUser extends ArrayDeque<String> {

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public LoggedUser userSession() {
		return new LoggedUser();
	}
}
