package dto;

import java.util.ArrayList;
import java.util.List;

import utils.SiparisOnceligi;

import controllers.Utils;

import models.Is;
import models.Siparis;

public class SiparisDto {

	String id= "";
	String musteri = "";
	Integer sure;
	List<SecilmisOncelik> secilmisOncelik = new ArrayList<SecilmisOncelik>();
	String oncelik="";
	String baslangictarihi="";
	String teslimtarihi="";
	String aciklama="";
	String durum="";
	IsDto seciliis = new IsDto();
	List<IsDto> isler = new ArrayList<IsDto>();
	List<SiparisHareketDto> hareketler= new ArrayList<SiparisHareketDto>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	public String getOncelik() {
		return oncelik;
	}

	public void setOncelik(String oncelik) {
		this.oncelik = oncelik;
	}

	public IsDto getSeciliis() {
		return seciliis;
	}

	public void setSeciliis(IsDto seciliis) {
		this.seciliis = seciliis;
	}

	public String getMusteri() {
		return musteri;
	}

	public void setMusteri(String musteri) {
		this.musteri = musteri;
	}


	public Integer getSure() {
		return sure;
	}

	public void setSure(Integer sure) {
		this.sure = sure;
	}

	public List<SecilmisOncelik> getSecilmisOncelik() {
		return secilmisOncelik;
	}

	public void setSecilmisOncelik(List<SecilmisOncelik> secilmisOncelik) {
		this.secilmisOncelik = secilmisOncelik;
	}

	public String getBaslangictarihi() {
		return baslangictarihi;
	}

	public void setBaslangictarihi(String baslangictarihi) {
		this.baslangictarihi = baslangictarihi;
	}

	public String getTeslimtarihi() {
		return teslimtarihi;
	}

	public void setTeslimtarihi(String teslimtarihi) {
		this.teslimtarihi = teslimtarihi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public List<IsDto> getIsler() {
		return isler;
	}

	public void setIsler(List<IsDto> isler) {
		this.isler = isler;
	}
	
	public List<SiparisHareketDto> getHareketler() {
		return hareketler;
	}

	public void setHareketler(List<SiparisHareketDto> hareketler) {
		this.hareketler = hareketler;
	}

	@Override
	public String toString() {
		return "SiparisDto [id=" + id + ", musteri=" + musteri + ", sure="
				+ sure + ", secilmisOncelik=" + secilmisOncelik + ", oncelik="
				+ oncelik + ", baslangictarihi=" + baslangictarihi
				+ ", teslimtarihi=" + teslimtarihi + ", aciklama=" + aciklama
				+ ", durum=" + durum + ", seciliis=" + seciliis + ", isler="
				+ isler + ", hareketler=" + hareketler + "]";
	}

	public static SiparisDto getSiparisComplex(Integer sOncelik, String sCamTipi, String[] sIslemler) {

		if (sOncelik==null) sOncelik=-1;
		if (sCamTipi==null) sCamTipi="";
		if (sIslemler==null) sIslemler=new String[]{""};
		
		List<SecilmisOncelik> secilmisOncelik = SecilmisOncelik.getSecilmisOncelikler(sOncelik);
		List<SecilmisCamTipi> secilmisCamTipi = SecilmisCamTipi.getSecilmisCamTipleri(sCamTipi);
		List<SecilmisIslem> secilmisIslemler = SecilmisIslem.getSecilmisIslemler(sIslemler);
		
		IsDto seciliIs = new IsDto();
		seciliIs.setSecilmiscamtipi(secilmisCamTipi);
		seciliIs.setSecilmisislemler(secilmisIslemler);

		SiparisDto siparis = new SiparisDto();
		siparis.setSecilmisOncelik(secilmisOncelik);
		siparis.setSeciliis(seciliIs);
		
		return siparis;
	}
	
	public static List<SiparisDto> getSiparisDto(List<Siparis> siparisModels, boolean includeIslemler, Integer sOncelik, String sCamTipi, String[] sIslemler) {
		
		List<SiparisDto> ret = new ArrayList<SiparisDto>();
		for (Siparis siparisModel : siparisModels) {
			if (siparisModel.active) {
				ret.add(getSiparisDto(siparisModel, includeIslemler, sOncelik, sCamTipi, sIslemler));
			}
		}
		
		return ret;
	}
	
	public static SiparisDto getSiparisDto(Siparis siparisModel, boolean includeIslemler, Integer sOncelik, String sCamTipi, String[] sIslemler) {
		
		SiparisDto ret = getSiparisComplex(sOncelik, sCamTipi, sIslemler);
		ret.setId(String.valueOf(siparisModel.id));
		ret.setAciklama(siparisModel.aciklama);
		ret.setDurum(siparisModel.durum);
		ret.setBaslangictarihi(Utils.sdf.format(siparisModel.baslangicTarihi));
		ret.setMusteri(siparisModel.musteri.isim);
		ret.setOncelik(SiparisOnceligi.values()[siparisModel.oncelik].name());
		ret.setTeslimtarihi(Utils.sdf.format(siparisModel.teslimTarihi));
		if (includeIslemler) {
			ret.setIsler(IsDto.getIsDto(siparisModel.isler));
		//	ret.setHareketler(SiparisHareketDto.getSiparisHareketDto(siparisModel.siparisHareketleri));
		}
		return ret;
	}
	
	
}
