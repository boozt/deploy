package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="Iss")
public class Is extends Model {

	@ManyToOne(cascade=CascadeType.ALL)
	@Required
	public Siparis siparis;
	
	@ManyToOne
	@Required
	public CamTipi camTipi;

	@Required
	@Column(length=50)
	public String adet;
	
	@Column(length=50)
	public String kalinlik;
	
	@Column(length=50)
	public String boy;
	
	@Column(length=50)
	public String en;
	
	@Column(length=50)
	public String renk;

	public Boolean camBizden;
	
	@Column(length=500)
	public String aciklama;
	
	public Boolean active = true;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="is", fetch= FetchType.LAZY) 
	public List<Islem> islemler = new ArrayList<Islem>();

	@Override
	public String toString() {
		return "IsTanim [siparis=" + siparis + ", camTipi=" + camTipi
				+ ", adet=" + adet + ", kalinlik=" + kalinlik + ", boy=" + boy
				+ ", en=" + en + ", renk=" + renk + ", camBizden=" + camBizden
				+ ", aciklama=" + aciklama + ", active=" + active
				+ ", islemler=" + islemler + "]";
	}

}
