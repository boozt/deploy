package dto;

import java.util.ArrayList;
import java.util.List;

import utils.SiparisOnceligi;

public class SecilmisOncelik {

	String oncelik;
	int value;
	boolean secili;

	public SecilmisOncelik(String oncelik, int value, boolean secili) {
		this.oncelik = oncelik;
		this.value = value;
		this.secili = secili;
	}

	public String getOncelik() {
		return oncelik;
	}

	public void setOncelik(String oncelik) {
		this.oncelik = oncelik;
	}

	public boolean isSecili() {
		return secili;
	}

	public void setSecili(boolean secili) {
		this.secili = secili;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SecilmisOncelik [oncelik=" + oncelik + ", value=" + value
				+ ", secili=" + secili + "]";
	}

	public static List<SecilmisOncelik> getSecilmisOncelikler(int sOncelik) {
		List<SecilmisOncelik> ret = new ArrayList<SecilmisOncelik>();
		for (SiparisOnceligi d : SiparisOnceligi.values()) {
			ret.add(new SecilmisOncelik(d.name(), d.ordinal(), sOncelik==d.ordinal()));
		}
		return ret;
	}

}
