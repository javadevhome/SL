package org.slsale.pojo;

import java.util.Date;

/**
 * GoodsInfo
 * @author bdqn_hl
 * @date 2014-2-21
 */
public class GoodsInfo extends Base{
	private Integer id;
	private String goodsSN;
	private String goodsName;
	private String goodsFormat;
	private Double marketPrice;
	private Double realPrice;
	private Integer state;
	private String note; 
	private Integer num;
	private String unit;
	private String createdBy;
	private Date createTime;
	private Date lastUpdateTime;
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsSN() {
		return goodsSN;
	}
	public void setGoodsSN(String goodsSN) {
		this.goodsSN = goodsSN;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsFormat() {
		return goodsFormat;
	}
	public void setGoodsFormat(String goodsFormat) {
		this.goodsFormat = goodsFormat;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	
}
