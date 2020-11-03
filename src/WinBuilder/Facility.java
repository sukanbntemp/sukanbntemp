package WinBuilder;

import java.text.DecimalFormat;

public class Facility {
	
	private int facilityNum;
	private DecimalFormat price;
	private String facilityType;

	public Facility(int facilityNum, DecimalFormat price, String facilityType) {
		this.facilityNum = facilityNum;
		this.price = price;
		this.facilityType = facilityType;
	}

	public int getFacilityNum() {
		return facilityNum;
	}

	public void setFacilityNum(int facilityNum) {
		this.facilityNum = facilityNum;
	}

	public DecimalFormat getPrice() {
		return price;
	}

	public void setPrice(DecimalFormat price) {
		this.price = price;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

}
