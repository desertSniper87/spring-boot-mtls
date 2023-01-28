package com.example.securingweb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bouncycastle.cert.X509CertificateHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.cert.X509Certificate;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HandlerInterceptor() {
//			public boolean preHandle(HttpServletRequest request,
//									 HttpServletResponse response, Object handler)
//					throws Exception {
//
//
//				X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
//				if (certs != null && certs.length > 0) {
//					X509Certificate clientCert = certs[0];
//					System.out.println(clientCert);
//					// Use the clientCert object to access the certificate information
//					// such as the subject, issuer, and public key.
//				} else {
//					System.out.println("1");
//				}
//
////
////				Object object = SecurityContextHolder.getContext().getAuthentication().getCredentials();
////				if (object instanceof X509Certificate) {
////					X509Certificate x509Certificate = (X509Certificate) object;
////					//convert to bouncycastle if you want
////					System.out.println("Test");
////					X509CertificateHolder x509CertificateHolder =
////							new X509CertificateHolder(x509Certificate.getEncoded());
////					System.out.println(x509CertificateHolder);
////				}
////
////
//					return true;
//			}
//		});
//	}

}
