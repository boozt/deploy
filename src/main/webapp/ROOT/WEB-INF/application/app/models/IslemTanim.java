package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

@Entity
public class IslemTanim extends Model {

    @Required
    public String islem;

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((islem == null) ? 0 : islem.hashCode());
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
		IslemTanim other = (IslemTanim) obj;
		if (islem == null) {
			if (other.islem != null)
				return false;
		} else if (!islem.equals(other.islem))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return islem;
	}
	
	


}

