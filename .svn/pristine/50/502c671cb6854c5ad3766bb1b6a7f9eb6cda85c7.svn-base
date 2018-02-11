package com.ujuit.quant.firmoffer.service;

import java.util.List;

import com.ujuit.quant.firmoffer.model.TWechatAttention;

public interface TWechatAttentionService {

	Integer insert(TWechatAttention attention);

	boolean delete(int id);

	public boolean update(TWechatAttention attention);

	public boolean updateIspush(String type, String id);

	public boolean addRushWechatAttention(Integer strategyId);

	public boolean deleteByOpenId(String openId);

	/**
	 * 批量插入
	 * 
	 * @param list
	 * @param batchSize
	 */
	public void batchInsert(List<TWechatAttention> list, int batchSize,String strategyType);
}
