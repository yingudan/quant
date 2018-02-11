/*
*@Title:  LoginController.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月18日 下午10:01:04   
 * @version 
*/
package com.ujuit.quant.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.ujuit.quant.constants.ResultStatus;
import com.ujuit.quant.user.constants.ValidateType;
import com.ujuit.quant.user.model.TUser;
import com.ujuit.quant.user.model.TUserModel;
import com.ujuit.quant.user.service.TUserService;
import com.ujuit.quant.utils.MD5;
import com.ujuit.quant.utils.MemCacheCommonUtil;
import com.ujuit.quant.utils.MemCacheSmsCodeUtil;
import com.ujuit.quant.utils.MemberUtil;
import com.ujuit.quant.utils.RandomValidateImageCommon;
import com.ujuit.quant.utils.SendSMSUtils;
import com.ujuit.quant.utils.StringUtils;
import com.ujuit.quant.utils.VerifyCodeUtils;
import com.ujuit.sysmanager.core.constant.SystemStatus;
import com.ujuit.sysmanager.core.exeptioin.MessageException;
import com.ujuit.sysmanager.core.model.ResultData;
import com.ujuit.sysmanager.util.Res;

/*
 *  登录注册    发验证码   
 * @author: cly     
 * @date:   2017年4月18日 下午10:01:04   
 * @version 
*/
@RequestMapping("/member/")
@Controller
public class LoginController {

	@Resource
	TUserService userService;

	/**
	 * @author shadow 获取AttrInfo
	 * @param userId
	 * @return
	 */
	@RequestMapping("queryAttrInfo.m")
	@ResponseBody
	public ResultData queryAttrInfo() {
		Map<String, Object> map = new HashMap<>();
		map.put("web_quant", Res.getProperties("webquantkey"));
		map.put("web_dataoffer", Res.getProperties("webdataofferkey"));
		map.put("web_quantfuture", Res.getProperties("webquantfuturekey"));
		return new ResultData(SystemStatus.SUCCESS, map);
	}

	/**
	 * @author shadow 个人中心 获取个人信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("findUserInfo.m")
	@ResponseBody
	public ResultData findUserInfo(Integer userId) {
		return new ResultData(SystemStatus.SUCCESS, userService.findUserInfo(userId));
	}

	@RequestMapping("saveEdit.m")
	@ResponseBody
	public ResultData saveEdit(Integer userId, String introduce, String userName) {
		// Integer userId=null;
		if (userId == null) {
			return new ResultData(ResultStatus.ERROR, null, "修改id不能为空");
		}
		TUser tUser = new TUser();
		tUser.setIntroduce(introduce);
		tUser.setId(userId);
		tUser.setUserName(userName);
		try {
			userId = userService.edit(tUser);
		} catch (MessageException e) {
			return new ResultData(ResultStatus.ERROR, null, e.getMessage());
		}
		return new ResultData(ResultStatus.SUCCESS, userId);
	}

	@RequestMapping("queryUserInfo.m")
	@ResponseBody
	public ResultData queryUserInfo(String userId) {
		if (StringUtils.isEmpty(userId)) {
			return new ResultData(ResultStatus.ERROR, null, "用户id不能为空");
		}
		return new ResultData(ResultStatus.SUCCESS, userService.queryUserInfo(userId));
	}

	/**
	 * 
	 * 检查是否注册
	 * 
	 * @author cly
	 * @param phoneNum
	 *            手机号
	 * @return
	 * @date 2017年4月19日 上午10:46:25
	 * @return: ResultData
	 */
	@RequestMapping("checkRegister.m")
	@ResponseBody
	public ResultData checkRegister(@RequestParam(required = true) String phoneNum) {
		TUserModel model = userService.findByPhoneNum(phoneNum);
		return new ResultData(ResultStatus.SUCCESS, model != null);
	}

	/**
	 * 
	 * @param phoneNum
	 * @param upass
	 * @param type
	 * @return
	 * @throws MessageException
	 */
	@RequestMapping("updatePassword.m")
	@ResponseBody
	public ResultData updatePassword(@RequestParam Integer userId, @RequestParam String bfPass,
			@RequestParam String nowPass) throws MessageException {
		if (userId == null) {
			return new ResultData(ResultStatus.ERROR, null, "用户id不能为空");
		}
		TUserModel userModel = userService.findById(userId);
		if (userModel == null) {
			return new ResultData(ResultStatus.ERROR, null, "用户不存在");
		}
		if (!userModel.getPassword().equals(MD5.MD5Encode(bfPass))) {
			return new ResultData(ResultStatus.ERROR, null, "用户原密码不对");
		}
		userModel.setPassword(MD5.MD5Encode(nowPass));
		userService.update(userModel);
		return new ResultData(ResultStatus.SUCCESS, true);
	}

	/**
	 * 
	 * 注册或者修改密码 共用
	 * 
	 * @author cly
	 * @param phoneNum
	 * @param upass
	 * @param type
	 *            1：注册 2:修改密码
	 * @param code
	 * @return
	 * @date 2017年4月19日 上午10:59:40
	 * @return: ResultData
	 * @throws MessageException
	 */
	@RequestMapping("register.m")
	@ResponseBody
	public ResultData register(@RequestParam String phoneNum, String nickName, @RequestParam String upass,
			@RequestParam Integer type) throws MessageException {

		if (StringUtils.isEmpty(phoneNum)) {
			return new ResultData(ResultStatus.ERROR, null, "手机号不能为空");
		}

		// if(StringUtils.isEmpty(nickName)){
		// return new ResultData(ResultStatus.ERROR,null,"用户昵称不能为空");
		// }

		if (!isMobile(phoneNum)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入正确的手机号码");
		}

		upass = MD5.MD5Encode(upass);

		TUserModel model = userService.findByPhoneNum(phoneNum);

		if (type == 1) {
			// 注册
			if (model != null) {
				// 已注册
				return new ResultData(ResultStatus.ERROR, "", "该手机号码已注册");
			}
			model = new TUserModel();
			String secretKey = randomSecretKey();
			model.setNickName(nickName);
			model.setUserName(phoneNum);
			model.setPassword(upass);
			model.setSecretKey(secretKey);
			Integer id = userService.insert(model);
			if (id != null) {
				model.setId(id);
				return new ResultData(ResultStatus.SUCCESS, model);
			} else {
				return new ResultData(ResultStatus.ERROR, "", "注册失败");
			}

		} else if (type == 2) {
			// 修改密码
			if (model == null) {
				return new ResultData(ResultStatus.ERROR, "", "账号不存在");
			} else {
				TUserModel modelToSave = new TUserModel();
				modelToSave.setId(model.getId());
				modelToSave.setPassword(upass);
				boolean result = userService.update(modelToSave);
				if (result) {
					return new ResultData(ResultStatus.SUCCESS, "修改成功");
				}
				return new ResultData(ResultStatus.ERROR, "", "修改密码失败");
			}
		} else {
			throw new MessageException("type 不在定义的范围内");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @author cly
	 * @param request
	 * @return
	 * @date 2017年4月19日 下午5:57:21
	 * @return: ResultData
	 */
	@RequestMapping("logout.m")
	@ResponseBody
	public ResultData logout(HttpServletResponse response, HttpServletRequest request) {
		MemberUtil.getLoginUserFromSession(request.getSession());
		MemberUtil.removeLoginUser(request.getSession());

		SSOHelper.clearLogin(request, response);

		return new ResultData(ResultStatus.SUCCESS, null, "退出账号成功");
	}

	/**
	 * 登录
	 * 
	 * @author cly
	 * @param request
	 * @param uname
	 * @param upass
	 * @param imageCode
	 * @return
	 * @date 2017年4月19日 下午3:36:58
	 * @return: ResultData
	 */
	@RequestMapping("login.m")
	@ResponseBody
	public ResultData login(HttpServletRequest request, HttpServletResponse response, @RequestParam String uname,
			@RequestParam String upass, @RequestParam(required = false) String imageCode) {
		if (StringUtils.isEmpty(uname)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入登录账号");
		}

		if (!isMobile(uname)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入正确的手机号码");
		}

		if (StringUtils.isEmpty(upass)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入密码");
		}
		// /**
		// * 这个只是在缓存里面的登录失败次数
		// */
		// int cacheLoginFailTime = 0;
		// try{
		// cacheLoginFailTime =
		// Integer.parseInt((String)request.getSession().getAttribute("loginFailTime"));
		// }catch(Exception e){
		// cacheLoginFailTime = 0;
		// }

		HashMap<String, Integer> map = new HashMap<>();

		upass = MD5.MD5Encode(upass);

		Integer loginFailTime = (Integer) MemCacheCommonUtil.get(getMemCachedLoginFailKey(uname));
		if (loginFailTime == null) {
			loginFailTime = 0;
		}
		// 如果登录失败次数过多
		if (loginFailTime >= 3 && StringUtils.isEmpty(imageCode)) {
			map.put("errorcount", loginFailTime);
			return new ResultData(ResultStatus.ERROR, map, "登录次数过多，请输入图形验证码");
		}

		if (!StringUtils.isEmpty(imageCode)
				&& !RandomValidateImageCommon.validateResponse(request, imageCode, ValidateType.Login)) {
			// 如果验证码不为空 并且 验证码错误
			return new ResultData(ResultStatus.ERROR, "", "验证码不正确");
		}

		TUserModel model = userService.findByPhoneNum(uname);
		if (model == null) {
			return new ResultData(ResultStatus.ERROR, "", "账号或密码错误，请重新输入");
		}

		if (!StringUtils.equals(upass, model.getPassword(), true)) {
			// 登录失败 次数+1

			loginFailTime += 1;
			MemCacheCommonUtil.put(getMemCachedLoginFailKey(uname), loginFailTime, 30 * MemCacheCommonUtil.Minute);
			map.put("errorcount", loginFailTime);
			return new ResultData(ResultStatus.ERROR, map, "账号或密码错误，请重新输入");
		} else {
			MemCacheCommonUtil.put(getMemCachedLoginFailKey(uname), 0, 30 * MemCacheCommonUtil.Minute);
			MemberUtil.saveMember2Session(request.getSession(), model);

			// SSO
			SSOToken st = new SSOToken(request);
			st.setId(model.getId().longValue());
			st.setUid(model.getUserName());
			st.setType(1);

			SSOHelper.setSSOCookie(request, response, st, true);
			// END SSO
			return new ResultData(ResultStatus.SUCCESS, model);
		}
	}

	//
	/**
	 * 
	 * 获取图形验证码
	 * 
	 * @author cly
	 * @param request
	 * @param response
	 * @param type
	 *            类型 1：登录 2：注册 3：修改密码
	 * @date 2017年4月19日 下午4:59:40
	 * @return: void
	 */
	@RequestMapping("getImageValidate.m")
	@ResponseBody
	public void getImageValidateCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer type) {
		ValidateType validateType = ValidateType.select(type);
		RandomValidateImageCommon.setRandomNumber(request, response, validateType);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @author cly
	 * @param phoneNum
	 *            手机号
	 * @param imageCode
	 *            图片验证码
	 * @param type
	 *            类型 2.注册 3.修改密码
	 * @return
	 * @date 2017年4月19日 下午1:57:56
	 * @return: ResultData
	 */
	@RequestMapping("sendSmsCode.m")
	@ResponseBody
	public ResultData sendSmsCode(HttpServletRequest request, @RequestParam String phoneNum,
			@RequestParam String imageCode, @RequestParam Integer type) {

		if (StringUtils.isEmpty(phoneNum)) {
			return new ResultData(ResultStatus.ERROR, null, "手机号不能为空");
		}

		if (!isMobile(phoneNum)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入正确的手机号码");
		}

		ValidateType validateType = ValidateType.select(type);

		TUserModel model = userService.findByPhoneNum(phoneNum);

		if (validateType == ValidateType.Register && model != null) {
			return new ResultData(ResultStatus.ERROR, "", "该手机号码已注册");
		}

		if (validateType == ValidateType.ChangePwd && model == null) {
			return new ResultData(ResultStatus.ERROR, "", "该手机号码未注册");
		}

		if (!RandomValidateImageCommon.validateResponse(request, imageCode, validateType)) {
			// 验证码失败
			return new ResultData(ResultStatus.ERROR, "", "图片验证码不正确");
		}

		// 短信验证码
		String code = VerifyCodeUtils.generateVerifyCode(4, VerifyCodeUtils.SIMPLE_CODES);
		System.out.println("短信验证码 " + code);

		String sms = null;
		if (validateType == ValidateType.Register) {
			sms = String.format("尊敬的用户，欢迎您注册有据量化策略开发系统；您的验证码%s，该验证码5分钟内有效，请尽快完成注册；若非本人操作，请忽略此条信息。【有据信息】", code);
		} else {
			sms = String.format("尊敬的用户，您正在找回密码；您的验证码%s，该验证码5分钟内有效，请尽快完成操作；若非本人操作，请忽略此条信息。【有据信息】", code);
		}

		try {
			int sendResult = SendSMSUtils.sendSMS(phoneNum, sms, "");
			// if(sendResult < 0){
			// return new
			// ResultData(ResultStatus.ERROR,"","验证码发送短信失败，"+SendSMSUtils.showMessge(sendResult));
			// }
		} catch (Exception e) {
			return new ResultData(ResultStatus.ERROR, "", "验证码发送短信失败,请重试");
		}

		MemCacheSmsCodeUtil.saveSmsCode(phoneNum, validateType, code, 5 * 60);

		return new ResultData(ResultStatus.SUCCESS, "", "验证码发送成功,请注意查收");
	}

	/**
	 * 验证 短信验证码是否正确
	 * 
	 * @author cly
	 * @param phoneNum
	 *            手机号
	 * @param code
	 *            验证码
	 * @param type
	 *            类型 2：注册 3：修改密码
	 * @return
	 * @date 2017年4月19日 下午3:23:26
	 * @return: ResultData
	 */
	@RequestMapping("checkSmsCode.m")
	@ResponseBody
	public ResultData checkSmsCode(@RequestParam String phoneNum, @RequestParam String code, Integer type) {

		if (StringUtils.isEmpty(phoneNum)) {
			return new ResultData(ResultStatus.ERROR, null, "手机号不能为空");
		}

		if (!isMobile(phoneNum)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入正确的手机号码");
		}

		if (StringUtils.isEmpty(code)) {
			return new ResultData(ResultStatus.ERROR, null, "请输入验证码");
		}

		ValidateType validateType = ValidateType.select(type);

		TUserModel model = userService.findByPhoneNum(phoneNum);

		if (validateType == ValidateType.Register && model != null) {
			return new ResultData(ResultStatus.ERROR, "", "该手机号码已注册");
		}

		if (validateType == ValidateType.ChangePwd && model == null) {
			return new ResultData(ResultStatus.ERROR, "", "该手机号码未注册");
		}

		String trueCode = MemCacheSmsCodeUtil.getSmsCode(phoneNum, validateType);
		if (trueCode == null) {
			return new ResultData(ResultStatus.ERROR, null, "短信验证码已失效,请重发验证码");
		}

		if (!StringUtils.equals(code, trueCode)) {
			return new ResultData(ResultStatus.ERROR, null, "短信验证码错误");
		}
		// 验证成功后 移除掉验证码缓存
		MemCacheSmsCodeUtil.removeSmsCode(phoneNum, validateType);
		return new ResultData(ResultStatus.SUCCESS, true);
	}

	/**
	 * 获取秘钥
	 * 
	 * @author cly
	 * @param request
	 * @return
	 * @date 2017年4月20日 上午10:07:11
	 * @return: ResultData
	 */
	@RequestMapping("getSecretKey.m")
	@ResponseBody
	public ResultData getSecretKey(HttpServletRequest request) {
		TUserModel tUserModel = MemberUtil.getLoginUserFromSession(request.getSession());
		if (tUserModel != null) {
			return new ResultData(ResultStatus.SUCCESS, tUserModel.getSecretKey());
		} else {
			return new ResultData(ResultStatus.HAVElOGIN, "", "请登录");
		}

	}

	/**
	 * 生成秘钥 uuid
	 * 
	 * @author cly
	 * @return
	 * @date 2017年4月19日 上午11:12:20
	 * @return: String
	 */
	private String randomSecretKey() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 是否是手机号
	 * 
	 * @author cly
	 * @param str
	 * @return
	 * @date 2017年4月19日 下午3:25:49
	 * @return: boolean
	 */
	private boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 获取 存储 登录失败次数的 memcached key
	 *
	 * @author cly
	 * @param phoneNum
	 * @return
	 * @date 2017年4月19日 下午5:58:02
	 * @return: String
	 */
	private String getMemCachedLoginFailKey(String phoneNum) {
		return MemCacheCommonUtil.MEM_LOGIN_FAIL + "_" + phoneNum;
	}

}
