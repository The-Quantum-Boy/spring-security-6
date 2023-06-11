package com.sumit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
//                .anyRequest().authenticated() //endpoint level security
//                .anyRequest().permitAll()
//                .anyRequest().denyAll()
//                .anyRequest().hasAnyAuthority("read")
//                .anyRequest().hasAnyAuthority("read","write")
//                .anyRequest().hasRole("ADMIN")
//                .anyRequest().hasAnyRole("ADMIN","MANAGER")
//                .anyRequest().access("isAuthentication() and hasAuthority('read')")
                .requestMatchers("/demo").hasAuthority("read")
                .anyRequest().authenticated()
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds=new InMemoryUserDetailsManager();

        var user1= User.withUsername("sumit")
                .password(passwordEncoder().encode("1234"))
                .authorities("read")
//                .roles("ADMIN","MANAGER") //equivalent to the authorities named "ROLE_ADMIN"
                .build();

        var user2= User.withUsername("amit")
                .password(passwordEncoder().encode("12345"))
                .authorities("write")
//                .roles("MANAGER")
                .build();

        uds.createUser(user1);
        uds.createUser(user2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
