package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Islem extends Model {

	@ManyToOne
	public Is is;
	
	@ManyToOne
	public IslemTanim islemTanim;
	
    @Required
    public String durum;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "islem", fetch= FetchType.LAZY)
	public List<IslemHareketleri> islemHareketleri = new ArrayList<IslemHareketleri>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((islemTanim == null) ? 0 : islemTanim.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Islem other = (Islem) obj;
		if (islemTanim == null) {
			if (other.islemTanim != null)
				return false;
		} else if (!islemTanim.equals(other.islemTanim))
			return false;
		return true;
	}

	

	


   

}

