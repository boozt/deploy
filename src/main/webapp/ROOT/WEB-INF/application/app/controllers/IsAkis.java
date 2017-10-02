package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Siparis;

import play.mvc.Controller;
import play.mvc.With;
import dto.SiparisDto;

@With(Secure.class)
public class IsAkis extends Controller{
	
	public static void aylik(int diff) throws ParseException {
		String ayyil=Utils.getMonthYearTr(diff);
		String ayinilkgunu = Utils.getFirstWeekdayOfMonthEng(diff).toLowerCase();
		List<List<SiparisDto>> siparisler = Siparisler.getSiparisByMonth(diff);
		int previous = diff-1;
		int next = diff+1;
		render(ayyil, ayinilkgunu, siparisler, previous, next);		
	}
	
	public static void bugunTarih(String tarih, String orderBy, String ascDesc) throws ParseException {
		
		int diff=0;
		if (tarih!=null && !tarih.isEmpty()) {
			diff= Utils.daysBetweenToday(tarih);
		}
		List<SiparisDto> siparisler = Siparisler.getSiparisForToday(diff, orderBy, ascDesc);
		String bugun = Utils.getTodayStr(diff);
		int previous = diff-1;
		int next = diff+1;
		render("IsAkis/bugun.html", siparisler, bugun, previous, next, diff, orderBy, ascDesc);
	}
	
	public static void bugunId(Long id) throws ParseException {
		
		Siparis siparisModel = Siparis.findById(id);
		List<SiparisDto> siparisler = new ArrayList<SiparisDto>();
		SiparisDto siparis = SiparisDto.getSiparisDto(siparisModel, true, siparisModel.oncelik, null, null);
		siparisler.add(siparis);

		render("IsAkis/bugun.html", siparisler, "17-02-2014", -1, 1, 0, null, null);
	}
	
	public static void bugun(int diff, String orderBy, String ascDesc) throws ParseException {
		
		List<SiparisDto> siparisler = Siparisler.getSiparisForToday(diff, orderBy, ascDesc);
		String bugun = Utils.getTodayStr(diff);
		int previous = diff-1;
		int next = diff+1;
		render(siparisler, bugun, previous, next, diff, orderBy, ascDesc);
	}

}
