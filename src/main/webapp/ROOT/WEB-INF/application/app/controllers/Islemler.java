package controllers;

import java.text.ParseException;

import models.Is;
import models.Islem;
import models.IslemHareketleri;
import models.Siparis;
import play.mvc.Controller;
import play.mvc.With;
import utils.SiparisDurumu;
import controllers.Secure.Security;
import dto.SiparisDto;

@With(Secure.class)
public class Islemler extends Controller {

	public static void durumGuncelleBugun(Long id, SiparisDurumu islemDurumu, int diff, String aciklama) throws ParseException {

		Islem islemModel = Islem.findById(id);

		IslemHareketleri islemHareketleri = new IslemHareketleri();
		islemHareketleri.aciklama = aciklama;
		islemHareketleri.eskiDurum = islemModel.durum;
		islemHareketleri.yeniDurum = islemDurumu.name();
		islemHareketleri.kullanici = Security.connected();
		islemHareketleri.islem = islemModel;
		islemHareketleri.tarih = Utils.getToday();
		islemHareketleri.create();

		islemModel.durum = islemDurumu.name().toString();
		islemModel.save();

		Siparis siparisModel = islemModel.is.siparis;

		switch (islemDurumu) {

		case CALISILIYOR:
			if (islemModel.is.siparis.durum.equalsIgnoreCase(SiparisDurumu.BASLAMADI.name())) {
				islemModel.is.siparis.durum = SiparisDurumu.CALISILIYOR.name();
				islemModel.is.siparis.save();

				/*
				SiparisHareketleri siparisHareketleri = new SiparisHareketleri();
				siparisHareketleri.aciklama = aciklama;
				siparisHareketleri.eskiDurum = SiparisDurumu.BASLAMADI.name();
				siparisHareketleri.yeniDurum = islemDurumu.name();
				siparisHareketleri.kullanici = Security.connected();
				siparisHareketleri.siparis = siparisModel;
				siparisHareketleri.tarih = Utils.getToday();
				siparisHareketleri.create();
				*/
			}
			break;
		case TAMAMLANDI:
			boolean allDone = true;
			label1: for (Is is : siparisModel.isler) {
				for (Islem islem : is.islemler) {
					if (!islem.durum.equals(SiparisDurumu.TAMAMLANDI.name())) {
						allDone = false;
						break label1;
					}
				}
			}
			if (allDone) {
				islemModel.is.siparis.durum = SiparisDurumu.TAMAMLANDI.name();
				islemModel.is.siparis.save();
/*
				SiparisHareketleri siparisHareketleri2 = new SiparisHareketleri();
				siparisHareketleri2.aciklama = aciklama;
				siparisHareketleri2.eskiDurum = siparisModel.durum;
				siparisHareketleri2.yeniDurum = islemDurumu.name();
				siparisHareketleri2.kullanici = Security.connected();
				siparisHareketleri2.siparis = siparisModel;
				siparisHareketleri2.tarih = Utils.getToday();
				siparisHareketleri2.create();
*/
			}
			break;
		default:
			break;
		}
		//IsAkis.bugun(diff, null, null);
	//	String durum = islemDurumu.name();
	//	render(id, durum);
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		render(siparis);
	}

}
