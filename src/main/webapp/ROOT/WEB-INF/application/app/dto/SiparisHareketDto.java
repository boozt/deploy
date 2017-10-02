package dto;

import java.util.ArrayList;
import java.util.List;

import controllers.Utils;

import models.SiparisHareketleri;

public class SiparisHareketDto {

	private String aciklama = "";
	private String eskiDurum = "";
	private String yeniDurum = "";
	private String tarih = "";
	private String kullanici = "";

	public SiparisHareketDto() {
	}

	public SiparisHareketDto(String aciklama, String eskiDurum,
			String yeniDurum, String tarih, String kullanici) {
		this.aciklama = aciklama;
		this.eskiDurum = eskiDurum;
		this.yeniDurum = yeniDurum;
		this.tarih = tarih;
		this.kullanici = kullanici;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getEskiDurum() {
		return eskiDurum;
	}

	public void setEskiDurum(String eskiDurum) {
		this.eskiDurum = eskiDurum;
	}

	public String getYeniDurum() {
		return yeniDurum;
	}

	public void setYeniDurum(String yeniDurum) {
		this.yeniDurum = yeniDurum;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getKullanici() {
		return kullanici;
	}

	public void setKullanici(String kullanici) {
		this.kullanici = kullanici;
	}
	
	public static List<SiparisHareketDto> getSiparisHareketDto(List<SiparisHareketleri> siparisHareketleri) {
		List<SiparisHareketDto> ret = new ArrayList<SiparisHareketDto>();
		for (SiparisHareketleri siparisHareketModel: siparisHareketleri) {
			ret.add(getSiparisHareketDto(siparisHareketModel));
		}
		return ret;
	}
	
	public static SiparisHareketDto getSiparisHareketDto(SiparisHareketleri siparisHareket) {
		SiparisHareketDto ret = new SiparisHareketDto();
		ret.setAciklama(siparisHareket.aciklama);
		ret.setEskiDurum(siparisHareket.eskiDurum);
		ret.setKullanici(siparisHareket.kullanici);
		ret.setTarih(Utils.format(Utils.sdfHms, siparisHareket.tarih));
		ret.setYeniDurum(siparisHareket.yeniDurum);
		return ret;
	}

	@Override
	public String toString() {
		return "SiparisHareketDto [aciklama=" + aciklama + ", eskiDurum="
				+ eskiDurum + ", yeniDurum=" + yeniDurum + ", tarih=" + tarih
				+ ", kullanici=" + kullanici + "]";
	}

}
