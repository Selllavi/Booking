package com.lahtachev.booking.config;

import com.lahtachev.booking.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Requires login with role ROLE_EMPLOYEE or ROLE_MANAGER.
        // If not, it will redirect to /admin/login.
        http.authorizeRequests().antMatchers( "/reserve","/cancelOrder","/sellerOrders", "/buyerOrders", "/newOffer", "/admin/userInfo")//
                .access("hasAnyRole('ROLE_USER', 'ROLE_SELLER', 'ROLE_ADMIN')");

        // Pages only for MANAGER
        http.authorizeRequests().antMatchers("/admin/usersList","/admin/viewUserOffersForAdmin","/admin/viewUserOrdersForAdmin","/admin/blockPage").access("hasRole('ROLE_ADMIN')");

        // When user login, role XX.
        // But access to the page requires the YY role,
        // An AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Configuration for Login Form.
        http.authorizeRequests().and().formLogin()//

                //
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/admin/login")//
                .defaultSuccessUrl("/admin/userInfo")//
                .failureUrl("/admin/login?error=true")//
                .usernameParameter("userName")//
                .passwordParameter("password")

                // Configuration for the Logout page.
                // (After logout, go to home page)
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");

    }
}