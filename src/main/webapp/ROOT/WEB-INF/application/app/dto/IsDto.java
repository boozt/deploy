package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.IslemTanimlari;

import models.Is;
import models.Islem;
import models.IslemTanim;

public class IsDto {

	String isid = "";
	List<SecilmisCamTipi> secilmiscamtipi = new ArrayList<SecilmisCamTipi>();
	String camtipi;
	String adet;
	String kalinlik;
	String boy;
	String en;
	String renk = "Renksiz";
	String cambizden = "Evet";
	List<SecilmisIslem> secilmisislemler = new ArrayList<SecilmisIslem>();
	String isaciklama = "";

	public List<SecilmisCamTipi> getSecilmiscamtipi() {
		return secilmiscamtipi;
	}

	public void setSecilmiscamtipi(List<SecilmisCamTipi> secilmiscamtipi) {
		this.secilmiscamtipi = secilmiscamtipi;
	}

	public String getCamtipi() {
		return camtipi;
	}

	public void setCamtipi(String camtipi) {
		this.camtipi = camtipi;
	}

	public String getIsid() {
		return isid;
	}

	public void setIsid(String isid) {
		this.isid = isid;
	}

	public String getAdet() {
		return adet;
	}

	public void setAdet(String adet) {
		this.adet = adet;
	}

	public String getBoy() {
		return boy;
	}

	public void setBoy(String boy) {
		this.boy = boy;
	}

	public String getKalinlik() {
		return kalinlik;
	}

	public void setKalinlik(String kalinlik) {
		this.kalinlik = kalinlik;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getCambizden() {
		return cambizden;
	}

	public void setCambizden(String cambizden) {
		this.cambizden = cambizden;
	}

	public List<SecilmisIslem> getSecilmisislemler() {
		return secilmisislemler;
	}

	public void setSecilmisislemler(List<SecilmisIslem> secilmisislemler) {
		this.secilmisislemler = secilmisislemler;
	}

	public String getIsaciklama() {
		return isaciklama;
	}

	public void setIsaciklama(String isaciklama) {
		this.isaciklama = isaciklama;
	}

	public String getRenk() {
		return renk;
	}

	public void setRenk(String renk) {
		this.renk = renk;
	}

	@Override
	public String toString() {
		return "IsDto [isid=" + isid + ", secilmiscamtipi=" + secilmiscamtipi
				+ ", camtipi=" + camtipi + ", adet=" + adet + ", kalinlik="
				+ kalinlik + ", boy=" + boy + ", en=" + en + ", renk=" + renk
				+ ", cambizden=" + cambizden + ", secilmisislemler="
				+ secilmisislemler + ", isaciklama=" + isaciklama + "]";
	}

	public static IsDto getIsDto(Is isModel) {
		IsDto ret = new IsDto();
		ret.setIsid(String.valueOf(isModel.id));
		ret.setAdet(isModel.adet);
		ret.setBoy(isModel.boy);
		ret.setCambizden(isModel.camBizden ? "Evet" : "Hayır");
		ret.setEn(isModel.en);
		ret.setKalinlik(isModel.kalinlik);
		ret.setRenk(isModel.renk);
		ret.setIsaciklama(isModel.aciklama);
		ret.setCamtipi(isModel.camTipi.camTipi);
		ret.setSecilmiscamtipi(SecilmisCamTipi
				.getSecilmisCamTipleri(isModel.camTipi.camTipi));
		ret.setSecilmisislemler(SecilmisIslem
				.getSecilmisIslemler(isModel.islemler));
		return ret;
	}

	public static List<IsDto> getIsDto(List<Is> isModeller) {
		List<IsDto> ret = new ArrayList<IsDto>();
		for (Is isModel : isModeller) {
			if (isModel.active) {
				ret.add(getIsDto(isModel));
			}
		}
		return ret;
	}

	public static String[] getIslemler(List<Islem> islemler) {
		List<String> tmp = new ArrayList<String>();
		for (Islem islem : islemler) {
			tmp.add(islem.islemTanim.islem);
		}
		String[] ret = new String[tmp.size()];
		tmp.toArray(ret);
		return ret;
	}

}
