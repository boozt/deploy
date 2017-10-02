package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

@Entity
public class CamTipi extends Model {

    @Required
    public String camTipi;

	@Override
	public String toString() {
		return camTipi;
	}

	

    
    
    
}

