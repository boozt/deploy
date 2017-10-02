package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

@Entity
public class Kullanici extends Model {

	@Required
	public String kullaniciAdi;

	@Required
	public String sifre;

	public String email;

	public Boolean admin = false;

	public Boolean active = true;

	@Override
	public String toString() {
		return "Kullanici [kullaniciAdi=" + kullaniciAdi + ", sifre=" + sifre
				+ ", email=" + email + ", admin=" + admin + ", active="
				+ active + "]";
	}

}
