package controllers;

import static play.modules.pdf.PDF.renderPDF;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.CamTipi;
import models.Is;
import models.Islem;
import models.IslemTanim;
import models.Musteri;
import models.Siparis;
import models.SiparisHareketleri;
import play.data.validation.Required;
import play.modules.paginate.ValuePaginator;
import play.modules.pdf.PDF.Options;
import play.modules.pdf.PDF.PDFDocument;
import play.mvc.Controller;
import play.mvc.With;
import utils.SiparisDurumu;
import controllers.Secure.Security;
import dto.IsDto;
import dto.SiparisDto;

@With(Secure.class)
public class Siparisler extends Controller {

	public static void yeni(Integer sOncelik, String sCamTipi, String[] sIslemler) {

		SiparisDto siparis = SiparisDto.getSiparisComplex(sOncelik, sCamTipi, sIslemler);
		render(siparis);
	}

	public static void siparisBul(Long id) {

		Siparis siparisModel = Siparis.findById(id);
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		render("Siparisler/yeni.html", siparis);
	}

	public static void pdf() {

		Siparis siparisModel = Siparis.findById(3L);
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		PDFDocument doc = new PDFDocument();
		List<SiparisDto> siparisler = new ArrayList<SiparisDto>();
		siparisler.add(siparis);

		Options options = new Options();
		options.HEADER = "<span style='font-size: 8pt;font-style:italic;color: #666;'>Saray Cam Isleme</span><span style=\" color: rgb(141, 172, 38);float: right;font-size: 8pt;\">Page <pagenumber>/<pagecount></span>";
		options.FOOTER = "<span style='font-size: 8pt;font-style:italic;color: #666;'>Saray Cam Isleme</span><span style=\" color: rgb(141, 172, 38);float: right;font-size: 8pt;\">Page <pagenumber>/<pagecount></span>";
		options.filename = 3 + ".pdf";

		renderPDF("Siparisler/pdf.html", siparisler, doc, options);
	}
	
	public static void pdfMulti(String ids) {

		if (ids!=null) {
			PDFDocument doc = new PDFDocument();
			List<SiparisDto> siparisler = new ArrayList<SiparisDto>();
			Options options = new Options();
			options.HEADER = "<span style='font-size: 8pt;font-style:italic;color: #666;'>Saray Cam Isleme</span><span style=\" color: rgb(141, 172, 38);float: right;font-size: 8pt;\">Page <pagenumber>/<pagecount></span>";
			options.FOOTER = "<span style='font-size: 8pt;font-style:italic;color: #666;'>Saray Cam Isleme</span><span style=\" color: rgb(141, 172, 38);float: right;font-size: 8pt;\">Page <pagenumber>/<pagecount></span>";
			options.filename = "siparisler.pdf";

			String[] idStr = ids.split(",");
			for (String id: idStr) {
				
				try {
					Long idLong= Long.parseLong(id);
					Siparis siparisModel = Siparis.findById(idLong);
					siparisler.add(SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null));    
                    
                } catch (Throwable e) {
	                // TODO: handle exception
                }
				
			}
			renderPDF("Siparisler/pdf.html", siparisler, doc, options);
		}
		
		siparisler();
	}
	

	public static void durumGuncelleBugun(Long id, SiparisDurumu siparisDurumu, int diff, String aciklama) throws ParseException {

		Siparis siparisModel = Siparis.findById(id);

		/*
		SiparisHareketleri siparisHareketleri = new SiparisHareketleri();
		siparisHareketleri.aciklama = aciklama;
		siparisHareketleri.eskiDurum = siparisModel.durum;
		siparisHareketleri.yeniDurum = siparisDurumu.name();
		siparisHareketleri.kullanici = Security.connected();
		siparisHareketleri.siparis = siparisModel;
		siparisHareketleri.tarih = Utils.getToday();
		siparisHareketleri.create();
		*/
		
		siparisModel.durum = siparisDurumu.name().toString();
		siparisModel.save();
		//IsAkis.bugun(diff, null, null);
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		render("Islemler/durumGuncelleBugun.html", siparis);
	}

	public static void durumGuncelleBugunYeni(Long id, SiparisDurumu siparisDurumu, int diff, String aciklama) throws ParseException {

		Siparis siparisModel = Siparis.findById(id);

		SiparisHareketleri siparisHareketleri = new SiparisHareketleri();
		siparisHareketleri.aciklama = aciklama;
		siparisHareketleri.eskiDurum = siparisModel.durum;
		siparisHareketleri.yeniDurum = siparisDurumu.name();
		siparisHareketleri.kullanici = Security.connected();
		siparisHareketleri.siparis = siparisModel;
		siparisHareketleri.tarih = Utils.getToday();
		siparisHareketleri.create();

		siparisModel.durum = siparisDurumu.name().toString();
		siparisModel.save();
		Siparisler.siparisBul(id);
	}

	public static void durumGuncelleSiparis(Long id, SiparisDurumu siparisDurumu, int diff) throws ParseException {

		Siparis siparisModel = Siparis.findById(id);
		siparisModel.durum = siparisDurumu.name().toString();
		siparisModel.save();
		Siparisler.siparisBul(id);
	}

	public static void siparisEkleDegistir(Long id, String musteri, Integer oncelik, String baslangictarihi, String teslimtarihi,
	        String aciklama) throws ParseException {

		validation.required(musteri).message("Müşteri gerekli");
		validation.required(oncelik).message("Öncelik gerekli");
		validation.required(baslangictarihi).message("Başlangıç gerekli");
		validation.required(teslimtarihi).message("Teslim gerekli");

		Musteri musteriModel = Musteri.find("byIsimAndActive", musteri, true).first();
		if (musteriModel == null) {
			musteriModel = new Musteri();
			musteriModel.isim = musteri;
			musteriModel.telefon = "";
			musteriModel.fax = "";
			musteriModel.adres = "";
			musteriModel.save();
		}

		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			SiparisDto siparis = SiparisDto.getSiparisComplex(oncelik, null, null);
			siparis.setId(id != null ? String.valueOf(id) : "");
			siparis.setAciklama(aciklama);
			siparis.setBaslangictarihi(baslangictarihi);
			siparis.setMusteri(musteri);
			siparis.setTeslimtarihi(teslimtarihi);
			render("Siparisler/yeni.html", siparis);
		}

		// id varsa edit yoksa yeni
		Siparis siparisModel = id == null ? new Siparis() : (Siparis) Siparis.findById(new Long(id));
		siparisModel.musteri = musteriModel;
		siparisModel.aciklama = aciklama;
		siparisModel.active = true;
		siparisModel.baslangicTarihi = Utils.sdf.parse(baslangictarihi);
		siparisModel.teslimTarihi = Utils.sdf.parse(teslimtarihi);
		siparisModel.durum = SiparisDurumu.BASLAMADI.name();
		siparisModel.girisTarihi = Utils.getToday();
		siparisModel.oncelik = oncelik;
		siparisModel.active = true;
		siparisModel.save();

		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, oncelik, null, null);
		render("Siparisler/yeni.html", siparis);

	}

	public static void siparisSil(@Required(message = "Id gerekli") Long id) {

		Siparis siparis = Siparis.findById(id);
		siparis.active = false;
		siparis.save();
		siparisler();
	}

	public static void siparisSilMulti(String ids, String mus, String gt1, String gt2, String tt1, String tt2) throws ParseException {

		if (ids!=null) {
			String[] idStr = ids.split(",");
			for (String id: idStr) {
				
				try {
					Long idLong= Long.parseLong(id);
					Siparis siparis = Siparis.findById(idLong);
					siparis.active = false;
					siparis.save();    
                    
                } catch (Throwable e) {
	                // TODO: handle exception
                }
				
			}
		}
		
		bul(mus, gt1, gt2, tt1, tt2);

	}
	
	public static void isBul(Long isid) {

		Is isModel = Is.findById(isid);
		Siparis siparisModel = isModel.siparis;
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, isModel.camTipi.camTipi,
		        IsDto.getIslemler(isModel.islemler));
		siparis.setSeciliis(IsDto.getIsDto(isModel));
		render("Siparisler/yeni.html", siparis);
	}

	public static void isEkleDegistir(Long id, Long isid, String camtipi, String adet, String boy, String kalinlik, String en,
	        String cambizden, String renk, String isaciklama, String[] islemler, Integer sOncelik, String sCamTipi, String[] sIslemler)
	        throws ParseException {

		validation.required(camtipi).message("Cam Tipi gerekli");
		validation.isTrue("adet", adet != null).message("Adet gerekli");
		validation.isTrue("boy", boy != null).message("Boy gerekli");
		validation.isTrue("en", en != null).message("En gerekli");
		validation.isTrue("kalinlik", kalinlik != null).message("Kalinlik gerekli");
		validation.required(cambizden).message("Cam Bizden gerekli");

		Siparis siparisModel = Siparis.findById(id);

		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, camtipi, islemler);
			IsDto seciliIs = siparis.getSeciliis();
			seciliIs.setIsid(String.valueOf(isid));
			seciliIs.setAdet(adet);
			seciliIs.setBoy(boy);
			seciliIs.setCambizden(cambizden);
			seciliIs.setCamtipi(camtipi);
			seciliIs.setEn(en);
			seciliIs.setKalinlik(kalinlik);
			seciliIs.setRenk(renk);
			seciliIs.setIsaciklama(isaciklama);
			siparis.setSeciliis(seciliIs);
			render("Siparisler/yeni.html", siparis);
		}

		if (isid == null) {

			Is seciliIs = new Is();
			seciliIs.aciklama = isaciklama;
			seciliIs.camTipi = CamTipi.find("byCamTipi", camtipi).first();
			seciliIs.adet = adet;
			seciliIs.boy = boy;
			seciliIs.en = en;
			seciliIs.kalinlik = kalinlik;
			seciliIs.renk = renk;
			seciliIs.camBizden = cambizden.equalsIgnoreCase("Evet");
			seciliIs.siparis = siparisModel;
			seciliIs.save();

			if (islemler != null) {
				for (String islem : islemler) {
					Islem islemModel = new Islem();
					islemModel.islemTanim = (IslemTanim) IslemTanim.find("byIslem", islem).first();
					islemModel.durum = SiparisDurumu.BASLAMADI.name();
					islemModel.is = seciliIs;
					islemModel.create();
					seciliIs.islemler.add(islemModel);

				}
				siparisModel.isler.add(seciliIs);
			}
			siparisModel.save();

		} else {

			Is seciliIs = Is.findById(isid);
			seciliIs.aciklama = isaciklama;
			seciliIs.camTipi = CamTipi.find("byCamTipi", camtipi).first();
			seciliIs.adet = adet;
			seciliIs.boy = boy;
			seciliIs.en = en;
			seciliIs.renk = renk;
			seciliIs.kalinlik = kalinlik;
			seciliIs.camBizden = cambizden.equalsIgnoreCase("Evet");
			seciliIs.save();

			if (islemler == null) {
				islemler = new String[0];
			}
			
			for (String islem : islemler) {
				boolean found = false;
				for (Islem islemModel : seciliIs.islemler) {
					if (islemModel.islemTanim.islem.equals(islem)) {
						found = true;
						break;
					}
				}
				if (!found) {
					Islem islemModel = new Islem();
					islemModel.islemTanim = (IslemTanim) IslemTanim.find("byIslem", islem).first();
					islemModel.is = seciliIs;
					islemModel.durum = SiparisDurumu.BASLAMADI.name();
					islemModel.save();
				}

			}

			// remove the erased ones
			for (Islem islem : seciliIs.islemler) {
				boolean found = false;
				for (String islemUsr : islemler) {
					if (islem.islemTanim.islem.equals(islemUsr)) {
						found = true;
						break;
					}
				}
				if (!found) {
					islem.delete();
				}

			}

		}

		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		render("Siparisler/yeni.html", siparis);

	}

	public static void isSil(Long isid) {

		Is seciliIs = Is.findById(isid);
		seciliIs.active = false;
		seciliIs.save();

		Siparis siparisModel = seciliIs.siparis;
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		render("Siparisler/yeni.html", siparis);

	}

	public static void siparisler() {

		/*
		 * List<Siparis> siparisModels = Siparis.find("byActive", true).fetch();
		 * List<SiparisDto> siparisler = SiparisDto.getSiparisDto(siparisModels,
		 * false, null, null, null);
		 */
		List<SiparisDto> siparisler = new ArrayList<SiparisDto>();
		render(siparisler);
	}

	public static void bul(String musteri, String giristarihi1, String giristarihi2, String teslimtarihi1, String teslimtarihi2)
	        throws ParseException {

		params.flash(); // add http parameters to the flash scope
		giristarihi1 = giristarihi1 == null || giristarihi1.isEmpty() ? "01-01-2010" : giristarihi1;
		giristarihi2 = giristarihi2 == null || giristarihi2.isEmpty() ? "31-12-2030" : giristarihi2;
		teslimtarihi1 = teslimtarihi1 == null || teslimtarihi1.isEmpty() ? "01-01-2010" : teslimtarihi1;
		teslimtarihi2 = teslimtarihi2 == null || teslimtarihi2.isEmpty() ? "31-12-2030" : teslimtarihi2;

	    List<Siparis> siparisModels = Siparis
		        .find("upper(musteri.isim) like upper(?)  and girisTarihi between ? and ?  and teslimTarihi between ? and ?  and active=1 order by girisTarihi desc",
		                "%" + musteri + "%", Utils.parse(Utils.sdf, giristarihi1), Utils.parse(Utils.sdf, giristarihi2), Utils.parse(Utils.sdf, teslimtarihi1),
		                Utils.parse(Utils.sdf, teslimtarihi2)).fetch(100);
		List<SiparisDto> siparisList = SiparisDto.getSiparisDto(siparisModels, false, null, null, null);
		ValuePaginator<SiparisDto> siparisler = new ValuePaginator<SiparisDto>(siparisList);

		render("Siparisler/siparisler.html", siparisler, giristarihi1, giristarihi2, teslimtarihi1, teslimtarihi2);
	}

	public static List<List<SiparisDto>> getSiparisByMonth(int diff) throws ParseException {

		List<List<SiparisDto>> siparisler = new ArrayList<List<SiparisDto>>();
		for (int i = 0; i < Utils.getCurrentMonthLength(diff); i++) {
			siparisler.add(new ArrayList<SiparisDto>());
		}

		List<Siparis> siparisModels = Siparis.find("teslimTarihi>= ? and baslangicTarihi <= ?  and active=1 order by oncelik desc",
		        Utils.getFirstDayOfCurrentMonth(diff), Utils.getLastDayOfCurrentMonth(diff)).fetch();

		for (Siparis siparisModel : siparisModels) {
			SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);

			int start = Utils.getDOMForCurrentMonthStart(siparisModel.baslangicTarihi, diff);
			int end = Utils.getDOMForCurrentMonthEnd(siparisModel.teslimTarihi, diff);
			for (int i = start - 1; i < end; i++) {
				siparisler.get(i).add(siparis);
			}
		}

		return siparisler;
	}

	public static List<SiparisDto> getSiparisForToday(int diff, String orderBy, String ascDesc) throws ParseException {

		if (orderBy == null) {
			orderBy = "oncelik";
		}
		if (ascDesc == null) {
			ascDesc = "desc";
		}

		List<SiparisDto> siparisler = new ArrayList<SiparisDto>();
		// diff-2 diff+1 timezone problemlerinin onune gecmek ve biraz daha genis caplı bir dataset almak icin
		List<Siparis> siparisModels = Siparis.find(
		        "teslimTarihi>= ? and baslangicTarihi <= ?  and active=1  order by " + orderBy + " "
		                + ascDesc, Utils.getToday(diff-1), Utils.getToday(diff+1)).fetch();

		for (Siparis siparisModel : siparisModels) {
			SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
			siparisler.add(siparis);
		}

		return siparisler;
	}

}
