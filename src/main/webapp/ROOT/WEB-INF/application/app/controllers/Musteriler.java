package controllers;

import java.util.List;

import models.Musteri;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;
import controllers.Secure.Security;

@With(Secure.class)
public class Musteriler extends Controller {

	public static void yeni() {
		render();
	}

	public static void bul(String term) {

		List<Musteri> musteriler = Musteri.find("upper(isim) like upper(?) and active=true", term+"%").fetch();
		renderJSON(musteriler);

	}

	public static void ara(String musteri) {

		List<Musteri> musteriler = Musteri.find("upper(isim) like upper(?) and active=true", musteri+"%").fetch();
		render("Musteriler/musteriler.html", musteriler);

	}

	public static void ekle(@Required(message = "İsim gerekli") String isim,
			String telefon, String fax, String adres) {

		if (Musteri.find("byIsimAndActive", isim, true).first() != null) {
			validation.addError("isim", isim + " isimli bir müşteri mevcut!");
		}

		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			yeni();
		}

		Musteri musteri = new Musteri();
		musteri.isim = isim;
		musteri.telefon = telefon;
		musteri.fax = fax;
		musteri.adres = adres;
		musteri.save();
		musteriler();

	}

	public static void guncelle(@Required(message = "Id gerekli") Long id,
			@Required(message = "İsim gerekli") String isim, String telefon,
			String fax, String adres) {

		Musteri musteri = Musteri.findById(id);

		musteri.isim = isim;
		musteri.telefon = telefon;
		musteri.fax = fax;
		musteri.adres = adres;
		musteri.save();
		musteriler();

	}

	public static void sil(@Required(message = "İsim gerekli") Long id) {

		Musteri musteri = Musteri.findById(id);
		musteri.active = false;
		musteri.save();
		musteriler();
	}

	public static void musteriler() {

		if (Security.isConnected()) {
			renderArgs.put("kullanici", Security.connected());
		}

		List<Musteri> musteriler = Musteri.find("byActive", true).fetch();
		render(musteriler);
	}

	public static void musteri(Long id) {
		Musteri musteri = Musteri.findById(id);
		flash("musteriid", musteri.id);
		flash("isim", musteri.isim);
		flash("telefon", musteri.telefon);
		flash("fax", musteri.fax);
		flash("adres", musteri.adres);
		render(musteri);
	}

}
