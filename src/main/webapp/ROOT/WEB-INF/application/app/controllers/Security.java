package controllers;

import org.eclipse.jdt.core.dom.SuperConstructorInvocation;

import models.Kullanici;

public class Security extends Secure.Security {

	static boolean authenticate(String username, String password) {
		if (username==null || password==null || username.isEmpty() || password.isEmpty()) {
			return false;
		}
		
	    Kullanici kullanici = Kullanici.find("byKullaniciAdi", username).<Kullanici>first();
	    return (kullanici!=null && kullanici.sifre.equals(password));
	    
	}
	
	static boolean check(String profile) {
	    if("admin".equals(profile)) {
	        return Kullanici.find("byKullaniciAdi", connected()).<Kullanici>first().admin;
	    }
	    return false;
	}
    
}

