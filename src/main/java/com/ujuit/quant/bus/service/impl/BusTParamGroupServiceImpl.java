package com.ujuit.quant.bus.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujuit.quant.bus.constant.TestStatus;
import com.ujuit.quant.bus.model.TParamGroup;
import com.ujuit.quant.bus.service.BusTParamGroupService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

/**
 * @Description
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
@Service
public class BusTParamGroupServiceImpl implements BusTParamGroupService {

	@Resource
	Dao dao;

	@Override
	public int insert(int test_record_id) throws Exception {
		TParamGroup g = new TParamGroup();

		g.setBacktestId(test_record_id);
		g.setTestStatus(TestStatus.wait.getValue());

		if (!dao.insert(new DataItem(TParamGroup.class, "insert"), g) || g.getId() <= 0) {
			throw new Exception("添加测试参数组失败");
		}

		return g.getId();
	}

}
