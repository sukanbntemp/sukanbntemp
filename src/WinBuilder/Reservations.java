package WinBuilder;

import java.sql.Date;
import java.sql.Time;

public class Reservations {

	private String facilityType;
	private Date date;
	private String session;
	private String time;
	private double price;

	public Reservations(String facilityType, Date date, String session, String time, double price) {
		this.facilityType = facilityType;
		this.date = date;
		this.session = session;
		this.time = time;
		this.price = price;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String string) {
		this.time = string;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String savedValue() {
		return this.facilityType + " | " + this.date + " | " + this.time + " | " + this.session + " | $" + this.price
				+ "0";
	}

}
