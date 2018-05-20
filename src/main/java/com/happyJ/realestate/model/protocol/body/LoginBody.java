/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol.body
 *  @fileName : LoginBody.java
 *  @author : yongpal
 *  @since 2016. 7. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 28.        yongpal       create LoginBody.java
 *  </pre>
 ******************************************************************************/
public class LoginBody {
	
	private String userId;
	private int level;
	private String expired;
	private String position;
	private String depart;
	private String team;
	private String userName;
	private String phoneNum;
	private String permit;
	private String rejectReason;
	private String useLimitDate;
	private String passwordLimitDate;
	private String smsReceive;
	private String departModifyingYn;
	private String quittingYn;
	private String chgDepart;
	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the expired
	 */
	public String getExpired() {
		return expired;
	}
	/**
	 * @param expired the expired to set
	 */
	public void setExpired(String expired) {
		this.expired = expired;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the depart
	 */
	public String getDepart() {
		return depart;
	}
	/**
	 * @param depart the depart to set
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}
	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the permit
	 */
	public String getPermit() {
		return permit;
	}
	/**
	 * @param permit the permit to set
	 */
	public void setPermit(String permit) {
		this.permit = permit;
	}
	/**
	 * @return the rejectReason
	 */
	public String getRejectReason() {
		return rejectReason;
	}
	/**
	 * @param rejectReason the rejectReason to set
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	/**
	 * @return the useLimitDate
	 */
	public String getUseLimitDate() {
		return useLimitDate;
	}
	/**
	 * @param useLimitDate the useLimitDate to set
	 */
	public void setUseLimitDate(String useLimitDate) {
		this.useLimitDate = useLimitDate;
	}
	/**
	 * @return the passwordLimitDate
	 */
	public String getPasswordLimitDate() {
		return passwordLimitDate;
	}
	/**
	 * @param passwordLimitDate the passwordLimitDate to set
	 */
	public void setPasswordLimitDate(String passwordLimitDate) {
		this.passwordLimitDate = passwordLimitDate;
	}
	/**
	 * @return the smsReceive
	 */
	public String getSmsReceive() {
		return smsReceive;
	}
	/**
	 * @param smsReceive the smsReceive to set
	 */
	public void setSmsReceive(String smsReceive) {
		this.smsReceive = smsReceive;
	}
	/**
	 * @return the departModifyingYn
	 */
	public String getDepartModifyingYn() {
		return departModifyingYn;
	}
	/**
	 * @param departModifyingYn the departModifyingYn to set
	 */
	public void setDepartModifyingYn(String departModifyingYn) {
		this.departModifyingYn = departModifyingYn;
	}
	/**
	 * @return the quittingYn
	 */
	public String getQuittingYn() {
		return quittingYn;
	}
	/**
	 * @param quittingYn the quittingYn to set
	 */
	public void setQuittingYn(String quittingYn) {
		this.quittingYn = quittingYn;
	}
	/**
	 * @return the chgDepart
	 */
	public String getChgDepart() {
		return chgDepart;
	}
	/**
	 * @param chgDepart the chgDepart to set
	 */
	public void setChgDepart(String chgDepart) {
		this.chgDepart = chgDepart;
	}

}
