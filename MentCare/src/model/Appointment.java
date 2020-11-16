/**
 * Object that holds appointment info
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//this is literally just a custom object, with getters and setters
//just like eclipse can make itself
//this model needs to coincide directly to how the DB is set up.
public class Appointment {

	private StringProperty appNum;
	private StringProperty Pnum;
	private StringProperty Pname;
	private StringProperty DocID;
	private StringProperty apDate;
	private StringProperty apTime;
	private StringProperty passed;
	private StringProperty missed;
	private StringProperty phone;
	
	/**
	 * Appointment Constructor
	 * @param appnum	Unique Appointment ID
	 * @param pnum		Patient Number
	 * @param pname		Patient's Name
	 * @param DocID		Doctor ID
	 * @param apdate	Appointment Date
	 * @param aptime	Appointment Time
	 * @param Passed	
	 * @param Missed
	 * @param phoneNum	Patient's Phone number
	 */
	public Appointment(String appnum, String pnum, String pname, String DocID, String apdate, String aptime, String Passed, String Missed, String phoneNum) {
		this.appNum = new SimpleStringProperty(appnum);
		this.Pnum = new SimpleStringProperty(pnum);
		this.Pname = new SimpleStringProperty(pname);
		this.DocID = new SimpleStringProperty(DocID);
		this.apDate = new SimpleStringProperty(apdate);
		this.apTime = new SimpleStringProperty(aptime);
		this.passed = new SimpleStringProperty(Passed);
		this.missed = new SimpleStringProperty(Missed);
		this.phone = new SimpleStringProperty(phoneNum);
	}

	public StringProperty getAppNum() {
		return appNum;
	}

	public void setAppNum(StringProperty appNum) {
		this.appNum = appNum;
	}

	public StringProperty getPnum() {
		return Pnum;
	}

	public int getPnumInt() {
		return Integer.parseInt(Pnum.get());
	}

	public void setPnum(StringProperty pnum) {
		Pnum = pnum;
	}

	public StringProperty getPname() {
		return Pname;
	}

	public void setPname(StringProperty pname) {
		Pname = pname;
	}

	public StringProperty getDocID() {
		return DocID;
	}

	public void setDocID(StringProperty docID) {
		DocID = docID;
	}

	public StringProperty getApDate() {
		return apDate;
	}

	public String getApDateString() {
		return apDate.get();
	}

	public void setApDate(StringProperty apDate) {
		this.apDate = apDate;
	}

	public StringProperty getApTime() {
		return apTime;
	}

	public String getApTimeString() {
		return apTime.get();
	}

	public void setApTime(StringProperty apTime) {
		this.apTime = apTime;
	}

	public StringProperty getPassed() {
		return passed;
	}

	public void setPassed(StringProperty passed) {
		this.passed = passed;
	}

	public StringProperty getMissed() {
		return missed;
	}

	public void setMissed(StringProperty missed) {
		this.missed = missed;
	}
	public void setPhone(StringProperty phoneNum) {
		this.phone = phoneNum;
	}

	public StringProperty getPhone() {
		return phone;
	}



}