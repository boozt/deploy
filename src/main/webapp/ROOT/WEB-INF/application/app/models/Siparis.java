package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Index;

@Entity
public class Siparis extends Model {

	@ManyToOne
	public Musteri musteri;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siparis", fetch= FetchType.LAZY)
	public List<Is> isler = new ArrayList<Is>();
	
	/*
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siparis", fetch= FetchType.LAZY)
	public List<SiparisHareketleri> siparisHareketleri = new ArrayList<SiparisHareketleri>();
	 */
	
	public String durum;

	public int oncelik;

	@Temporal(TemporalType.DATE)
	@Index(name = "idx_sip_giris_t")
	public Date girisTarihi;

	@Temporal(TemporalType.DATE)
	@Index(name = "idx_sip_bas_t")
	public Date baslangicTarihi;

	@Temporal(TemporalType.DATE)
	@Index(name = "idx_sip_tes_t")
	public Date teslimTarihi;

	@Column(length = 500)
	public String aciklama;

	public Boolean active = true;

	@Override
	public String toString() {
		return "Siparis [musteri=" + musteri + ", isler=" + isler + ", durum="
				+ durum + ", oncelik=" + oncelik + ", girisTarihi="
				+ girisTarihi + ", baslangicTarihi=" + baslangicTarihi
				+ ", teslimTarihi=" + teslimTarihi + ", aciklama=" + aciklama
				+ ", active=" + active + "]";
	}

}
