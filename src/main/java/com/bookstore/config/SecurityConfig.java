package com.bookstore.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bookstore.dao.entity.User;
import com.bookstore.dao.repository.UserRepository;
import com.bookstore.service.impl.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getUserDetailsService());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.logout()
		.logoutUrl("/logout").and().cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/user/login").permitAll()
		.antMatchers("/books/add","/books/remove","/book/update").hasRole("ADMIN")
        .and().httpBasic();

	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new MyUserDetailsService();
	}

	@PostConstruct
	public void init() {
		if (userRepository.findAll().size() == 0) {
			User user = new User();
			user.setUserName("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRoles("ROLE_ADMIN,ROLE_USER");
			user.setActive(true);
			userRepository.save(user);
		}
	}

}
