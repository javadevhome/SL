package org.slsale.pojo;

/**
 * GoodsPackAffiliated
 * @author bdqn_hl
 * @date 2014-2-21
 */
public class GoodsPackAffiliated extends Base{
	private Integer id;
	private Integer goodsPackId;
	private Integer goodsInfoId;
	private Integer goodsNum;
	private String goodsName;
	private Double realPrice;
	private String unit;
	
	
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
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
	public Integer getGoodsPackId() {
		return goodsPackId;
	}
	public void setGoodsPackId(Integer goodsPackId) {
		this.goodsPackId = goodsPackId;
	}
	public Integer getGoodsInfoId() {
		return goodsInfoId;
	}
	public void setGoodsInfoId(Integer goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
}
