package com.ujuit.quant.firmoffer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.model.TWechatAttention;
import com.ujuit.quant.firmoffer.model.TWechatAttentionQh;
import com.ujuit.quant.firmoffer.service.TWechatAttentionService;
import com.ujuit.quant.utils.CommonUtils;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
public class TWechatAttentionServiceImpl implements TWechatAttentionService {

	@Resource(name = "dao")
	Dao dao;
	@Resource(name = "daoEEx")
	Dao daoEEx;
	
	@Override
	public Integer insert(TWechatAttention attention) {
		TWechatAttention twechatattention = dao.add(new DataItem(TWechatAttention.class, "insert"), attention);
		return twechatattention == null ? null : twechatattention.getId();
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(new DataItem(TWechatAttention.class, "deleteByPrimaryKey"), id);
	}

	@Override
	public boolean update(TWechatAttention attention) {
		return dao.update(new DataItem(TWechatAttention.class, "updateByPrimaryKeySelective"), attention);
	}

	@Override
	public boolean updateIspush(String type, String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("isPush", type);
		map.put("strategyId", id);
		return dao.update(new DataItem(TWechatAttention.class, "updateIspush"), map);
	}

	@Override
	public boolean addRushWechatAttention(Integer strategyId) {
		TStrategy strategy = dao.find(new DataItem(TStrategy.class, "findById"), strategyId);
		if (strategy != null) {
			List<String> openId = dao.getDataList(new DataItem(TWechatAttention.class, "queryOpenId"),
					strategy.getUserId());
			TWechatAttention att = dao.find(new DataItem(TWechatAttention.class, "queryAttInfo"), strategyId);
			List<TWechatAttention> attentions = new ArrayList<>();
			if (att == null) {
				for (String op : openId) {
					TWechatAttention attention = new TWechatAttention();
					attention.setIsPush(0);
					attention.setStrategyId(strategyId);
					attention.setUserId(strategy.getUserId());
					attention.setOpenId(op);
					attentions.add(attention);
				}
				batchInsert(attentions, 200,"1");
				// insert(attention);
			}
		}
		return true;
	}

	@Override
	public boolean deleteByOpenId(String openId) {
		return dao.delete(new DataItem(TWechatAttention.class, "deleteByOpenId"), openId);
	}

	@Override
	public void batchInsert(List<TWechatAttention> list, int batchSize, String strategyType) {
		if ("2".equals(strategyType)) {// 期货策略
			CommonUtils.batchInsert(daoEEx, new DataItem(TWechatAttentionQh.class, "batchInsert"), list, batchSize);
		} else {
			CommonUtils.batchInsert(dao, new DataItem(TWechatAttention.class, "batchInsert"), list, batchSize);
		}
	}

}
