package com.ujuit.quant.firmoffer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujuit.quant.firmoffer.model.Dailytarget;
import com.ujuit.quant.firmoffer.service.DailytargetService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
public class DailytargetServiceImpl implements DailytargetService {

	@Resource
	Dao dao;

	@Override
	public Integer insert(Dailytarget dailytarget) {
		dao.insert(new DataItem(Dailytarget.class, "insert"), dailytarget);
		return dailytarget.getId();
	}

	@Override
	public void update(Dailytarget dailytarget) {
		dao.update(new DataItem(Dailytarget.class, "update"), dailytarget);
	}

	@Override
	public void insertBatch(List<Dailytarget> list) {
		dao.find(new DataItem(Dailytarget.class, "insertBatch"), list);
	}

	@Override
	public Dailytarget findById(Integer id) {
		return dao.find(new DataItem(Dailytarget.class, "findById"), id);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(new DataItem(Dailytarget.class, "delete"), id);
	}

	@Override
	public Integer queryProfitNum(Integer subAccountId) {
		Dailytarget dailytarget = dao.find(new DataItem(Dailytarget.class, "findBySubAccountId"), subAccountId);
		if (dailytarget != null) {
			return dailytarget.getProfitNum();
		}
		return 0;
	}

	@Override
	public Integer querylossNum(Integer subAccountId) {
		Dailytarget dailytarget = dao.find(new DataItem(Dailytarget.class, "findBySubAccountId"), subAccountId);
		if (dailytarget != null) {
			return dailytarget.getLossNum();
		}
		return 0;
	}
}