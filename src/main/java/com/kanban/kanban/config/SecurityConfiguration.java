package com.kanban.kanban.config;

import com.kanban.kanban.services.UserServices.CustomSuccessHandler;
import com.kanban.kanban.services.UserServices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(c -> c.disable())

                .authorizeHttpRequests(request -> request.requestMatchers("/admin-page", "/ToDo/**")
                        .hasAuthority("ADMIN").requestMatchers("/user-page").hasAuthority("USER")
                        .requestMatchers(
                                "/registration"
                                , "/css/**"
                                , "/mp4/**"
                                , "/pnj/**"
                                , "/"
                                , "/aboutus"
                                , "/contact"
                                , "/impressum"
                                , "/showcontactform"
                                , "/sendcontactform"
                                , "/projects/typepro"
                                , "/projects/pingpong"
                                , "/contactform"
                                , "/projects/**"
                                , "index.css"
                                , "aboutus.css"
                                , "contactus.css"
                                , "contactform.css"
                                , "profile.css"
                                , "typepro.css"
                                , "pingpong.css"
                                , "impressum.css"
                                , "/css**"
                                , "/jpg/**"
                                , "/png/**"
                                , "/ttf/**"
                                , "/viewfotos/**"
                                , "/fotos/**"
                                , "/userlogs/**"
                                , "/nav/**"
                                , "/static/**"
                                , "/registration**"
                                , "/registration*"
                                , "/contactform/**"
                                , "/fonts/**"



                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler).permitAll())

                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll());

        return http.build();

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }


}
