package jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import controllers.Utils;

import models.CamTipi;
import models.IslemTanim;
import models.Kullanici;
import models.Musteri;
import models.Siparis;
import models.SiparisHareketleri;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.SiparisDurumu;

@OnApplicationStart
public class Bootstrap extends Job {
	public void doJob() {
		
	//	TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));
	//	System.out.println("Did set the timezone to Europe/Istanbul");
		// Check if the database is empty

		if (CamTipi.count() == 0) {
			CamTipi camTipi1 = new CamTipi();
			camTipi1.camTipi = "Temperli";
			camTipi1.create();
			CamTipi camTipi2 = new CamTipi();
			camTipi2.camTipi = "Lamine";
			camTipi2.create();
			CamTipi camTipi3 = new CamTipi();
			camTipi3.camTipi = "Buzlu";
			camTipi3.create();
			CamTipi camTipi4 = new CamTipi();
			camTipi4.camTipi = "Düz";
			camTipi4.create();
			CamTipi camTipi5 = new CamTipi();
			camTipi5.camTipi = "Ayna";
			camTipi5.create();
			CamTipi camTipi6 = new CamTipi();
			camTipi6.camTipi = new String("Isıcam");
			camTipi6.create();
			CamTipi camTipi7 = new CamTipi();
			camTipi7.camTipi = "Temperli Lamine";
			camTipi7.create();
			CamTipi camTipi8 = new CamTipi();
			camTipi8.camTipi = "Telli Cam";
			camTipi8.create();
			CamTipi camTipi9 = new CamTipi();
			camTipi9.camTipi = "Yorglass";
			camTipi9.create();
			CamTipi camTipi10 = new CamTipi();
			camTipi10.camTipi = "Bronz Ayna";
			camTipi10.create();
			CamTipi camTipi11 = new CamTipi();
			camTipi11.camTipi = "Füme Ayna";
			camTipi11.create();
		}

		if (IslemTanim.count() == 0) {
			IslemTanim islem1 = new IslemTanim();
			islem1.islem = "Kesim";
			islem1.create();
			IslemTanim islem2 = new IslemTanim();
			islem2.islem = "Bizote";
			islem2.create();
			IslemTanim islem3 = new IslemTanim();
			islem3.islem = "Rodaj";
			islem3.create();
			IslemTanim islem4 = new IslemTanim();
			islem4.islem = "Delik";
			islem4.create();
			IslemTanim islem5 = new IslemTanim();
			islem5.islem = "Havşalı Delik";
			islem5.create();
			IslemTanim islem6 = new IslemTanim();
			islem6.islem = "Forma Rodaj";
			islem6.create();
			IslemTanim islem7 = new IslemTanim();
			islem7.islem = "Forma Bizote";
			islem7.create();
			IslemTanim islem8 = new IslemTanim();
			islem8.islem = "CNC Oyum";
			islem8.create();
			IslemTanim islem9 = new IslemTanim();
			islem9.islem = "Temper";
			islem9.create();
			IslemTanim islem10 = new IslemTanim();
			islem10.islem = "Laminasyon";
			islem10.create();
			IslemTanim islem11 = new IslemTanim();
			islem11.islem = "Kanal";
			islem11.create();
			IslemTanim islem12 = new IslemTanim();
			islem12.islem = "Lazer Yapıştırma";
			islem12.create();
			IslemTanim islem13 = new IslemTanim();
			islem13.islem = "Montaj";
			islem13.create();
			IslemTanim islem14 = new IslemTanim();
			islem14.islem = "Kumlama";
			islem14.create();
			IslemTanim islem15 = new IslemTanim();
			islem15.islem = "Logo Uygulaması";
			islem15.create();
			IslemTanim islem16 = new IslemTanim();
			islem16.islem = "Köşe Taş";
			islem16.create();
			IslemTanim islem17 = new IslemTanim();
			islem17.islem = "Köşe Keçe";
			islem17.create();
		}

		if (Kullanici.count() == 0) {
			Kullanici kullanici = new Kullanici();
			kullanici.kullaniciAdi = "boozt";
			kullanici.sifre = "y1g1t8aran";
			kullanici.email = "boraozturk@hotmail.com";
			kullanici.admin = true;
			kullanici.create();

			Kullanici kullanici2 = new Kullanici();
			kullanici2.kullaniciAdi = "isleme";
			kullanici2.sifre = "saraycam1";
			kullanici2.email = "boraozturk@hotmail.com";
			kullanici2.admin = false;
			kullanici2.create();

		}
		
		
/*
		Musteri musteri = new Musteri();
		musteri.isim = "Bot";
		musteri.active =true;
		musteri.create();
		
		
		for (int i=0; i<1000; i++) {
			Siparis siparis = new Siparis();
			siparis.aciklama = "";
			siparis.active = true;
			try {
				siparis.baslangicTarihi = Utils.sdf.parse("08-02-2013");
				siparis.teslimTarihi = Utils.sdf.parse("08-04-2013");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			siparis.durum = SiparisDurumu.BASLAMADI.name();
			siparis.girisTarihi=new Date();
			siparis.musteri = musteri;
			siparis.oncelik = 3;
			siparis.create();
		}
*/
	}
}
