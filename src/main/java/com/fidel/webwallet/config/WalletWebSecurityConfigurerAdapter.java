
package com.fidel.webwallet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fidel.webwallet.repository.UserInfoRepository;

/**
 * @author Swapnil
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserInfoRepository.class)
@Configuration
@EnableWebSecurity
public class WalletWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/resources/**", "/wallet/user/register", "/wallet/home", "/wallet/login",
						"/wallet/qrcode/**")
				.permitAll().antMatchers("/js/*.js").permitAll().antMatchers("/css/**").permitAll()
				.antMatchers("**/fonts/**").permitAll().antMatchers("/webfonts/**").permitAll().anyRequest()
				.authenticated();

		http.formLogin().loginProcessingUrl("/wallet/secure/postReq").loginPage("/wallet/login")
				.defaultSuccessUrl("/wallet/secure/home").failureUrl("/wallet/login?error=true")
				.usernameParameter("email").passwordParameter("password");

		http.logout().deleteCookies("JSESSIONID").permitAll();

		http.authorizeRequests().and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);
	}

	/*
	 * @Bean public AuthenticationManager customAuthenticationManager() throws
	 * Exception { return authenticationManager();
	 * 
	 * }
	 */

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {

				return rawPassword.toString().equals(encodedPassword);
			}

			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}
		});
	}
}
