package com.markit.kyc.citrus.action;

public class User {

	private String USERID;
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getFIRSTNAME() {
		return FIRSTNAME;
	}
	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}
	public String getLASTNAME() {
		return LASTNAME;
	}
	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}
	public String getLEADCONTACT() {
		return LEADCONTACT;
	}
	public void setLEADCONTACT(String lEADCONTACT) {
		LEADCONTACT = lEADCONTACT;
	}
	public String getCOMPID() {
		return COMPID;
	}
	public void setCOMPID(String cOMPID) {
		COMPID = cOMPID;
	}
	public String getEMAILID() {
		return EMAILID;
	}
	public void setEMAILID(String eMAILID) {
		EMAILID = eMAILID;
	}
	private String FIRSTNAME;
	private String LASTNAME;
	private String LEADCONTACT;
	private String COMPID;
	private String EMAILID;
}
