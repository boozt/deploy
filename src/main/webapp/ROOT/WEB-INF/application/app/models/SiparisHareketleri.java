package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
public class SiparisHareketleri extends Model {

	@ManyToOne
	public Siparis siparis;

	@Column(length = 20)
	public String eskiDurum;
	
	@Column(length = 20)
	public String yeniDurum;
	
	@Column(length = 500)
	public String aciklama;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date tarih;
	
	@Column(length = 100)
	public String kullanici;

	@Override
	public String toString() {
		return "SiparisHareketleri [siparis=" + siparis + ", eskiDurum="
				+ eskiDurum + ", yeniDurum=" + yeniDurum + ", aciklama="
				+ aciklama + ", tarih=" + tarih + "]";
	}

	

}
