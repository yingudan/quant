/*
*@Title:  TUserServiceImpl.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月18日 下午10:55:26   
 * @version 
*/
package com.ujuit.quant.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujuit.quant.firmoffer.dto.DataOfferUserInfoDto;
import com.ujuit.quant.firmoffer.dto.ProductDto;
import com.ujuit.quant.firmoffer.dto.UserInfoDto;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.user.model.TUser;
import com.ujuit.quant.user.model.TUserModel;
import com.ujuit.quant.user.service.TUserService;
import com.ujuit.sysmanager.core.exeptioin.MessageException;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

/*
 * TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月18日 下午10:55:26   
 * @version 
*/
@Service
public class TUserServiceImpl implements TUserService{
	
	@Resource(name = "dao")
	Dao dao;
	
	@Resource(name = "daoEx")
	Dao daoEx;

	
	
	/* (non-Javadoc)
	 * @see com.ujuit.quant.TUserService#insert(com.ujuit.quant.TUserModel)
	 */
	@Override
	public Integer insert(TUserModel model) {
		TUserModel tUserModel = dao.add(new DataItem(TUserModel.class, "insert"), model);
		return tUserModel == null?null:tUserModel.getId();
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.TUserService#update(com.ujuit.quant.TUserModel)
	 */
	@Override
	public boolean update(TUserModel model) {
		return dao.update(new DataItem(TUserModel.class, "update") , model);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.TUserService#findById(java.lang.Integer)
	 */
	@Override
	public TUserModel findById(Integer id) {
		return dao.find(new DataItem(TUserModel.class, "selectByPrimaryKey"), id);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.TUserService#findByPhoneNum(java.lang.String)
	 */
	@Override
	public TUserModel findByPhoneNum(String phoneNum) {
		return dao.find(new DataItem(TUserModel.class, "selectByPhoneNum"), phoneNum);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.TUserService#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changePassword(String phoneNum, String password) {
		return false;
	}

	@Override
	public UserInfoDto queryUserInfo(String userId) {
		UserInfoDto dto=dao.find(new DataItem(TUserModel.class, "queryUserInfo"), Integer.valueOf(userId));
		//测试类型 0.历史回测 1.实时测试
		Map<String,Object> map = new HashMap<>();
		map.put("type", 0);
		map.put("userId", userId);
		dto.setHisNum(dao.find(new DataItem(TTestRecord.class, "queryOfferNumByUserId"), map).toString());
		map.put("type", 1);
		dto.setNowNum(dao.find(new DataItem(TTestRecord.class, "queryOfferNumByUserId"), map).toString());
		return dto;
	}

	@Override
	public DataOfferUserInfoDto findUserInfo(Integer userId) {
		DataOfferUserInfoDto userInfoDto = dao.find(new DataItem(TUserModel.class, "queryDataOfferUser"), userId);
		if (userInfoDto != null) {
			List<ProductDto> producList = daoEx.getDataList(new DataItem(ProductDto.class, "qeuryProducList"), userId);
			if (producList != null) {
				userInfoDto.setProducList(producList);
			}
		} else {
			userInfoDto = new DataOfferUserInfoDto();
		}
		return userInfoDto;
	}

	@Override
	public Integer edit(TUser user) throws MessageException {
//		user.setAccessKey(UUID.randomUUID().toString());
//		user.setType("2");// 数据平台用户
		user.setUserName(user.getUserName());
		user.setIntroduce(user.getIntroduce());
		boolean res =dao.update(new DataItem(TUserModel.class, "updateTUser"), user);
		if(res==false){
			throw new MessageException("保存用户信息失败");
		}
		return dao.find(new DataItem(TUserModel.class, "queryUserId"), user.getCallPhone());
	}

}
