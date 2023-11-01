//package com.example.springsecuritycinemaproject.config;
//
//import com.example.springsecuritycinemaproject.service.UserDetailsServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider provider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userDetailsService());
//        return provider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(
//                        authorize -> authorize
//                                .requestMatchers("/user/registration").permitAll()
//                                .requestMatchers("/cinema/findAll").hasAnyAuthority("ADMIN", "USER")
//                                .requestMatchers("/cinema/save").hasAuthority("ADMIN"))
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//}
