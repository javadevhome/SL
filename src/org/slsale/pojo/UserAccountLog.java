package org.slsale.pojo;

import javax.xml.crypto.Data;

public class UserAccountLog extends Base{
	private int accountLogId;	//主键ID
	private int userId;			//用户ID
	private Data actionTime;	//操作时间
	private String actionDesc;	//动作摘要
	private int actionType;		//动作类型
	private double baseIn;		//基本入账
	private double baseOut;		//基本出账
	private double baseBalance;	//基本余额
	private double repeatIn;	//重消入账
	private double repeatOut;	//重消出账
	private double repeatBalance;//重消余额
	
	public int getAccountLogId() {
		return accountLogId;
	}
	public void setAccountLogId(int accountLogId) {
		this.accountLogId = accountLogId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Data getActionTime() {
		return actionTime;
	}
	public void setActionTime(Data actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public double getBaseIn() {
		return baseIn;
	}
	public void setBaseIn(double baseIn) {
		this.baseIn = baseIn;
	}
	public double getBaseOut() {
		return baseOut;
	}
	public void setBaseOut(double baseOut) {
		this.baseOut = baseOut;
	}
	public double getBaseBalance() {
		return baseBalance;
	}
	public void setBaseBalance(double baseBalance) {
		this.baseBalance = baseBalance;
	}
	public double getRepeatIn() {
		return repeatIn;
	}
	public void setRepeatIn(double repeatIn) {
		this.repeatIn = repeatIn;
	}
	public double getRepeatOut() {
		return repeatOut;
	}
	public void setRepeatOut(double repeatOut) {
		this.repeatOut = repeatOut;
	}
	public double getRepeatBalance() {
		return repeatBalance;
	}
	public void setRepeatBalance(double repeatBalance) {
		this.repeatBalance = repeatBalance;
	}
	public UserAccountLog(int accountLogId, int userId, Data actionTime,
			String actionDesc, int actionType, double baseIn, double baseOut,
			double baseBalance, double repeatIn, double repeatOut,
			double repeatBalance) {
		super();
		this.accountLogId = accountLogId;
		this.userId = userId;
		this.actionTime = actionTime;
		this.actionDesc = actionDesc;
		this.actionType = actionType;
		this.baseIn = baseIn;
		this.baseOut = baseOut;
		this.baseBalance = baseBalance;
		this.repeatIn = repeatIn;
		this.repeatOut = repeatOut;
		this.repeatBalance = repeatBalance;
	}
	public UserAccountLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
