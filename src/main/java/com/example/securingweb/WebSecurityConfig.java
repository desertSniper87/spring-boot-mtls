package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(siteminderFilter(), RequestHeaderAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .x509()
                .subjectPrincipalRegex("CN=(.*?)(?:,|$)").authenticationUserDetailsService(userDetailsServiceWrapper())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .userDetailsService(userDetailsService())
                .logout((logout) -> logout.permitAll());

//        http.addFilterAfter(new AfterX509AuthenticationFilter(), X509AuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("torsho")
                        .password("p")
                        .roles("USER")
                        .authorities(AuthorityUtils.createAuthorityList("USER"))
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean(name = "siteminderFilter")
    public RequestHeaderAuthenticationFilter siteminderFilter() {
        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();

        // set the header name which provides principal information
        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("x-actor-id");

        // set the header name which provides credential information
        requestHeaderAuthenticationFilter.setCredentialsRequestHeader("x-grantor-id");

        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());

        // do not throw exception when header is not present.
        // one use case is for actuator endpoints and static assets where security headers are not required.
        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);

        return requestHeaderAuthenticationFilter;
    }


    @Bean
    protected AuthenticationManager authenticationManager() {
        final List<AuthenticationProvider> providers = new ArrayList<>(1);
        providers.add(preAuthAuthProvider());
        return new ProviderManager(providers);
    }

    @Bean(name = "preAuthProvider")
    PreAuthenticatedAuthenticationProvider preAuthAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
        return provider;
    }

    @Bean
    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
        return new AuthorizationUserDetailsService();
    }


}
