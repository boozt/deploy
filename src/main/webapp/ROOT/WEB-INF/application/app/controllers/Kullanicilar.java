package controllers;

import java.util.List;

import models.Kullanici;
import models.Musteri;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;
import controllers.Secure.Security;

@With(Secure.class)
public class Kullanicilar extends Controller {

	public static void yeni() {
		render();
	}

	public static void ekle(@Required(message = "Kullanıcı adı gerekli") String kullaniciadi,
							@Required(message = "Şifre gerekli") String sifre,
							String email,
							@Required(message = "Özel yetkili kullanici") Boolean admin) {

		if (Kullanici.find("byKullaniciAdiAndActive", kullaniciadi, true).first() != null) {
			validation.addError("kullaniciAdi", kullaniciadi + "  mevcut, başka deneyin!");
		}

		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			yeni();
		}

		Kullanici kullanici = new Kullanici();
		kullanici.kullaniciAdi = kullaniciadi;
		kullanici.sifre = sifre;
		kullanici.email = email;
		kullanici.admin = admin;
		kullanici.active = true;
		kullanici.save();
		kullanicilar();

	}

	public static void guncelle(
								@Required(message = "Id gerekli") Long id,
								@Required(message = "Kullanıcı adı gerekli") String kullaniciadi,
								@Required(message = "Şifre gerekli") String sifre,
								String email,
								@Required(message = "Özel yetkili kullanici") Boolean admin) {

		Kullanici kullanici = Kullanici.findById(id);

		if (!kullanici.kullaniciAdi.equals(kullaniciadi) && Kullanici.find("byKullaniciAdiAndActive", kullaniciadi, true).first() != null) {
			validation.addError("kullaniciAdi", kullaniciadi + "  mevcut, başka deneyin!");
		}
		
		kullanici.kullaniciAdi = kullaniciadi;
		kullanici.sifre = sifre;
		kullanici.email = email;
		kullanici.admin = admin;
		kullanici.save();
		kullanicilar();

	}

	public static void sil(@Required(message = "İsim gerekli") Long id) {

		Kullanici kullanici = Kullanici.findById(id);
		kullanici.active = false;
		kullanici.save();
		kullanicilar();
	}

	public static void kullanicilar() {

		if (Security.isConnected()) {
			renderArgs.put("kullanici", Security.connected());
		}

		List<Kullanici> kullanicilar = Kullanici.find("byActive", true).fetch();
		render(kullanicilar);
	}

	public static void kullanici(Long id) {
		Kullanici kullanici = Kullanici.findById(id);
		flash("kullaniciid", kullanici.id);
		flash("kullaniciadi", kullanici.kullaniciAdi);
		flash("sifre", kullanici.sifre);
		flash("email", kullanici.email);
		flash("admin", kullanici.admin);
		
		render(kullanici);
	}

}
