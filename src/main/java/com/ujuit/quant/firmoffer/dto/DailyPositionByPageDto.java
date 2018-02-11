package com.ujuit.quant.firmoffer.dto;

import java.util.List;

public class DailyPositionByPageDto {
	private Integer totalPage;// 分页数量;

	private List<DailyPositionDto> list;// 每日持仓

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<DailyPositionDto> getList() {
		return list;
	}

	public void setList(List<DailyPositionDto> list) {
		this.list = list;
	}

}
