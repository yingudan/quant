package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 策略列表dto
 * 
 * @author shadow
 * @date 2017年4月19日
 */
public class StrategyListDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 策略id
	 */
	private String id;

	/**
	 * 策略名称
	 */
	private String strategyName;

	/**
	 * 回测收益
	 */
	private String profit;
	/**
	 * 阿尔法
	 */
	private String alpha;
	/**
	 * 贝塔
	 */
	private String beta;
	/**
	 * 夏普比率
	 */
	private String shapratio;
	/**
	 * 收益波动率
	 */
	private String returnVolatlity;
	/**
	 * 最大回撤
	 */
	private String maxRetrace;
	/**
	 * 创建时间
	 */
	private Date startTime;
	/**
	 * 上次运作
	 */
	private Date endTime;

	/**
	 * 回测历史
	 */
	private Integer hisOfferNum;
	/**
	 * 实盘模拟
	 */
	private Integer nowOfferNum;
	/**
	 * 策略状态
	 */
	private String status;

	/**
	 * 单次测试记录的唯一标识符
	 */
	private Integer testId;

	/**
	 * 得分
	 */
	private String grade;

	private Integer isAttention;// 是否被关注

	/**
	 * 指标
	 */
	private TestIndexDto indexDto;

	public TestIndexDto getIndexDto() {
		return indexDto;
	}

	public void setIndexDto(TestIndexDto indexDto) {
		this.indexDto = indexDto;
	}

	public Integer getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	public String getShapratio() {
		return shapratio;
	}

	public void setShapratio(String shapratio) {
		this.shapratio = shapratio;
	}

	public String getReturnVolatlity() {
		return returnVolatlity;
	}

	public void setReturnVolatlity(String returnVolatlity) {
		this.returnVolatlity = returnVolatlity;
	}

	public String getMaxRetrace() {
		return maxRetrace;
	}

	public void setMaxRetrace(String maxRetrace) {
		this.maxRetrace = maxRetrace;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getHisOfferNum() {
		return hisOfferNum;
	}

	public void setHisOfferNum(Integer hisOfferNum) {
		this.hisOfferNum = hisOfferNum;
	}

	public Integer getNowOfferNum() {
		return nowOfferNum;
	}

	public void setNowOfferNum(Integer nowOfferNum) {
		this.nowOfferNum = nowOfferNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
