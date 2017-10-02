package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

import org.hibernate.annotations.Index;

@Entity
public class Musteri extends Model {

    @Required
    @Column(unique=true)
    @Index(name = "idx_must_isim")
    public String isim;
    
    public String telefon;
    
    public String fax;
    
    public String adres;

    public Boolean active = true;

    @Override
	public String toString() {
		return "Musteri [isim=" + isim + ", telefon=" + telefon + ", fax="
				+ fax + ", adres=" + adres + ", active=" + active + "]";
	}
    
    
    
}

