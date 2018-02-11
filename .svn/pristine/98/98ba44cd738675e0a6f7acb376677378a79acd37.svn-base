package com.ujuit.quant.firmoffer.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;
import java.util.List;
import com.ujuit.quant.firmoffer.model.CustomIndicators;
import com.ujuit.quant.firmoffer.service.CustomIndicatorsService;

@Service
public class CustomIndicatorsServiceImpl implements CustomIndicatorsService {

	@Resource
	private Dao dao;

	@Override
	public Integer insert(CustomIndicators CustomIndicators) {
		dao.insert(new DataItem(CustomIndicators.class,"insert"),CustomIndicators);
return CustomIndicators.getId();
	}

	@Override
	public void update(CustomIndicators CustomIndicators) {
		dao.update(new DataItem(CustomIndicators.class,"update"),CustomIndicators);
	}

	@Override
	public void insertBatch(List<CustomIndicators> list) {
		dao.find(new DataItem(CustomIndicators.class,"insertBatch"),list);
	}

	@Override
	public CustomIndicators findById(Integer id){
		return dao.find(new DataItem(CustomIndicators.class,"findById"),id);
	}

	@Override
	public void delete (Integer id){
		dao.delete (new DataItem(CustomIndicators.class,"delete"),id);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.CustomIndicatorsService#findByGroupId(java.lang.Integer)
	 */
	@Override
	public List<CustomIndicators> findByGroupId(Integer groupId) {
		// TODO Auto-generated method stub
		return dao.getData(new DataItem(CustomIndicators.class,"findByGroupId"),groupId);
	}}