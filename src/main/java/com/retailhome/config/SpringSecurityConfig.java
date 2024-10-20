package com.retailhome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> {
            form.loginPage("/login")
                    .permitAll();
        }).authorizeRequests();
        return http.build();
    }

    @Bean
    @Description("In memory Userdetails service registered since DB doesn't have user table ")
    public UserDetailsService users()  {
        UserDetails user = User.builder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);

    }

    //@Bean
//    public BCryptPasswordEncoder passwordEncoder(){
   //     return new BCryptPasswordEncoder();
  //  }
}
