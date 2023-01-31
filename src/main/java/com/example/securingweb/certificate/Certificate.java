//package com.example.securingweb.certificate;
//
//
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "certificate")
//public class Certificate {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//
//	@Column(name = "serial")
//	private String serial;
//
//	@Column(name = "certificate_plaintext")
//	private String certificate_plaintext;
//
//	public Certificate() {
//
//	}
//
//	public Certificate(String serial, String certificate_plaintext) {
//        this.serial = serial;
//        this.certificate_plaintext = certificate_plaintext;
//	}
//
//
//    public String getSerial() {
//        return serial;
//    }
//
//    public void setSerial(String serial) {
//        this.serial = serial;
//    }
//
//    public String getCertificate_plaintext() {
//        return certificate_plaintext;
//    }
//
//    public void setCertificate_plaintext(String certificate_plaintext) {
//        this.certificate_plaintext = certificate_plaintext;
//    }
//}