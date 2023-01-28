package com.example.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;

public class AuthorizationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    /**
     * Loads user from data store and creates UserDetails object based on principal and/or credential.
     *
     * Role name needs to have "ROLE_" prefix.
     *
     * @param token instance of PreAuthenticatedAuthenticationToken
     * @return UserDetails object which contains role information for the given user.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        final String principal = (String)token.getPrincipal();
        final String credential = token.getCredentials().toString();
        final String authorities = token.getAuthorities().toString();

        System.out.println("principal: " + principal);
        System.out.println("credential: " + credential);
        System.out.println("authorities: " + authorities);


       // TODO this is only for illustration purpose. Should retrieve user from data store and determine user roles
       if (principal.equals("torsho")) {
           // TODO some user lookup and then create User object with roles
           return new User("torsho", "p", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
       } else {
           return new User("normal-user", "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
       }
    }
}