package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import models.Islem;
import models.IslemTanim;

public class SecilmisIslem {

	private Long id;
	private String islem;
	private boolean secili;
	private String durum;
	List<IslemHareketDto> islemHareketleri = new ArrayList<IslemHareketDto>();

	public SecilmisIslem(Long id, String islem, String durum, boolean secili) {
		this.id = id;
		this.islem = islem;
		this.secili = secili;
		this.durum = durum;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIslem() {
		return islem;
	}

	public void setIslem(String islem) {
		this.islem = islem;
	}

	public boolean isSecili() {
		return secili;
	}

	public void setSecili(boolean secili) {
		this.secili = secili;
	}

	public List<IslemHareketDto> getIslemHareketleri() {
    	return islemHareketleri;
    }

	public void setIslemHareketleri(List<IslemHareketDto> islemHareketleri) {
    	this.islemHareketleri = islemHareketleri;
    }

	public static List<SecilmisIslem> getSecilmisIslemler(String[] sIslemler) {
		List<String> asIslemler = Arrays.asList(sIslemler);
		List<SecilmisIslem> ret = new ArrayList<SecilmisIslem>();
		List<IslemTanim> islemler = IslemTanim.findAll();
		for (IslemTanim islem : islemler) {
			ret.add(new SecilmisIslem(islem.id, islem.islem, null, asIslemler
					.contains(islem.islem)));
		}
		return ret;
	}
	
	public static List<SecilmisIslem> getSecilmisIslemler(List<Islem> islemler) {
		
		List<SecilmisIslem> ret = new ArrayList<SecilmisIslem>();
		List<IslemTanim> islemTanimlari = IslemTanim.findAll();
		for (IslemTanim islemTanim : islemTanimlari) {
			ret.add(new SecilmisIslem(null, islemTanim.islem, null, false));
		}
		
		for (Islem islem: islemler) {
			for (SecilmisIslem secIslem : ret) {
				if (secIslem.islem.equals(islem.islemTanim.islem)) {
					secIslem.setId(islem.id);
					secIslem.setDurum(islem.durum);
					secIslem.secili=true;
					secIslem.setIslemHareketleri(IslemHareketDto.getIslemHareketDto(islem.islemHareketleri));
				}
				
			}
		}
		return ret;
	}

	@Override
    public String toString() {
	    return "SecilmisIslem [id=" + id + ", islem=" + islem + ", secili=" + secili + ", durum=" + durum + ", islemHareketleri="
	            + islemHareketleri + "]";
    }
	
	

}
