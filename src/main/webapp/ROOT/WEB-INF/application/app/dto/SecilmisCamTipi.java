package dto;

import java.util.ArrayList;
import java.util.List;

import models.CamTipi;

public class SecilmisCamTipi {

	String camtipi;
	boolean secili;
	
	public SecilmisCamTipi(String camtipi, boolean secili) {
		this.camtipi = camtipi;
		this.secili = secili;
	}
	public String getCamtipi() {
		return camtipi;
	}
	public void setCamtipi(String camtipi) {
		this.camtipi = camtipi;
	}
	public boolean isSecili() {
		return secili;
	}
	public void setSecili(boolean secili) {
		this.secili = secili;
	}
	@Override
	public String toString() {
		return "SecilmisCamTipi [camtipi=" + camtipi + ", secili=" + secili
				+ "]";
	}

	public static List<SecilmisCamTipi> getSecilmisCamTipleri(String sCamTipi) {
		
		List<SecilmisCamTipi> ret = new ArrayList<SecilmisCamTipi>();
		List<CamTipi> camTipleri = CamTipi.findAll();
		for (CamTipi camTipi : camTipleri) {
			ret.add(new SecilmisCamTipi(camTipi.camTipi, 
					camTipi.camTipi.equalsIgnoreCase(sCamTipi)));
		}
		return ret;
	}
	
}
