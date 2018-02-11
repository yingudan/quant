/*
*@Title:  TUserService.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月18日 下午10:27:36   
 * @version 
*/
package com.ujuit.quant.user.service;

import com.ujuit.quant.firmoffer.dto.DataOfferUserInfoDto;
import com.ujuit.quant.firmoffer.dto.UserInfoDto;
import com.ujuit.quant.user.model.TUser;
import com.ujuit.quant.user.model.TUserModel;
import com.ujuit.sysmanager.core.exeptioin.MessageException;

/*
 * 用户操作
 * @author: cly     
 * @date:   2017年4月18日 下午10:27:36   
 * @version 
*/
public interface TUserService {
	
	/**
	 * 创建账户
	 * @author  cly
	 * @param model
	 * @return
	 * @date 2017年4月18日 下午10:36:28       
	 * @return: Integer
	 */
	public Integer insert(TUserModel model);
	
	public UserInfoDto  queryUserInfo(String userId);
	
	public DataOfferUserInfoDto findUserInfo(Integer userId);
	/**
	 * 更新账户信息
	 * @author  cly
	 * @param model
	 * @return
	 * @date 2017年4月18日 下午10:36:50       
	 * @return: boolean
	 */
	public boolean update(TUserModel model);
	
	/**
	 * 通过id 查询用户实体
	 * @author  cly
	 * @param id
	 * @return
	 * @date 2017年4月18日 下午10:37:01       
	 * @return: TUserModel
	 */
	public TUserModel findById(Integer id);
	
	/**
	 * 通过手机号查询用户实体
	 * @author  cly
	 * @param phoneNum
	 * @return
	 * @date 2017年4月18日 下午10:37:21       
	 * @return: TUserModel
	 */
	public TUserModel findByPhoneNum(String phoneNum);
	
	/**
	 * 更改手机号
	 * @author  cly
	 * @param phoneNum
	 * @param password
	 * @return
	 * @date 2017年4月18日 下午10:37:41       
	 * @return: boolean
	 */
	public boolean changePassword(String phoneNum,String password);
	
	
	/**
	 * @author shadow 新增修改用户
	 * @param newsTrends
	 * @return
	 * @throws MessageException
	 */
	public Integer edit(TUser user) throws MessageException;
}
