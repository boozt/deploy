package dto;

import java.util.ArrayList;
import java.util.List;

import models.IslemHareketleri;
import controllers.Utils;

public class IslemHareketDto {

	private String aciklama = "";
	private String eskiDurum = "";
	private String yeniDurum = "";
	private String tarih = "";
	private String kullanici = "";

	public IslemHareketDto() {
	}

	public IslemHareketDto(String aciklama, String eskiDurum, String yeniDurum, String tarih, String kullanici) {
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

	public static List<IslemHareketDto> getIslemHareketDto(List<IslemHareketleri> IslemHareketleri) {
		List<IslemHareketDto> ret = new ArrayList<IslemHareketDto>();
		for (IslemHareketleri IslemHareketModel : IslemHareketleri) {
			ret.add(getIslemHareketDto(IslemHareketModel));
		}
		return ret;
	}

	public static IslemHareketDto getIslemHareketDto(IslemHareketleri IslemHareket) {
		IslemHareketDto ret = new IslemHareketDto();
		ret.setAciklama(IslemHareket.aciklama);
		ret.setEskiDurum(IslemHareket.eskiDurum);
		ret.setKullanici(IslemHareket.kullanici);
		ret.setTarih(Utils.format(Utils.sdfHms, IslemHareket.tarih));
		ret.setYeniDurum(IslemHareket.yeniDurum);
		return ret;
	}

	@Override
	public String toString() {
		return "IslemHareketDto [aciklama=" + aciklama + ", eskiDurum=" + eskiDurum + ", yeniDurum=" + yeniDurum + ", tarih=" + tarih
		        + ", kullanici=" + kullanici + "]";
	}

}
