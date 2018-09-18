package org.cloudfoundry.samples.music.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by 721672 on 9/18/2018.
 */
@Configuration
@EnableWebSecurity( debug = true )
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
       /*httpSecurity.formLogin().disable()
               .anonymous().disable()
               .authorizeRequests().anyRequest().denyAll();*/
       httpSecurity.httpBasic().and().authorizeRequests().anyRequest().authenticated();
   }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // creating user in memory
                .withUser("user")
                .password("password").roles("USER")
                .and().withUser("admin")
                .password("password").authorities("ROLE_ADMIN");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }
}
