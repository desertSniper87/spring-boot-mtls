package com.example.securingweb;

//import com.example.securingweb.certificate.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Collections;

@Service
public class AuthorizationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
//    @Autowired
//    CertificateRepository certificateRepository;
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
        final String serialNumber = ((X509Certificate) token.getCredentials()).getSerialNumber().toString(16);
//        final String serialNumberHex = serialNumber.toString(16)

        System.out.println("principal: " + principal);
        System.out.println("credential: " + credential);
        System.out.println("authorities: " + authorities);


       // TODO this is only for illustration purpose. Should retrieve user from data store and determine user roles
       if (serialNumber.equals("6e4f0c70d92b322da14a26bd3baa707f")) {

//           certificateRepository.findBySerial()

           // TODO some user lookup and then create User object with roles
           return new User("Samidhya Sarker", "p", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
       } else {
           throw new UsernameNotFoundException("Certificate not found");
       }
    }
}