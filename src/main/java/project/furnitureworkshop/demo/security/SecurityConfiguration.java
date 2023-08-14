package project.furnitureworkshop.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

//    @Bean
//    public InMemoryUserDetailsManager users() {
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("client")
//                .password("shlepok")
//                .roles("CLIENT")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password("chacha")
//                .roles("ADMIN")
//                .authorities("ADMIN")
//                .build();
//        return  new InMemoryUserDetailsManager(user, admin);
//
//    }
}
