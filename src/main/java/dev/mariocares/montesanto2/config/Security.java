package dev.mariocares.montesanto2.config;

import dev.mariocares.montesanto2.service.implementation.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class Security {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                    .antMatchers("/", "/login", "/registro").permitAll()
                    .antMatchers("/Articulo/**").permitAll()
                    .antMatchers("/Biblia/**").permitAll()
                    .antMatchers("/img/**", "/js/**", "/css/**").permitAll()
                    .antMatchers("/Administracion/**").hasAuthority("Administrador")
                    .antMatchers("/aquiUsuario").hasAuthority("Usuario")
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/").failureUrl("/login?error=ohno")
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true).clearAuthentication(true).permitAll();
        httpSecurity.authenticationProvider(authenticationProvider());
        return httpSecurity.build();
    }
}
