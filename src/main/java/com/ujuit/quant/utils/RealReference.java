package com.ujuit.quant.utils;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.ujuit.strategysdkserver.service.StrategyTestService;
import com.ujuit.sysmanager.util.SpringContextUtils;

/**
 * @author shandowF
 * @date 2017年12月11日
 */
public class RealReference {

	public static final String REMOTEIP = "172.18.246.94";

	public static final String REMOTEPORT = "20883";

	public static StrategyTestService realReference(String remoteIp, String remotePort) {
		if (StringUtils.isEmpty(remoteIp) || StringUtils.isEmpty(remotePort)) {
			remoteIp = REMOTEIP;
			remotePort = REMOTEPORT;
		}
		String url = "dubbo://" + remoteIp + ":" + remotePort
				+ "/com.ujuit.strategysdkserver.service.StrategyTestService";// 更改不同的Dubbo服务暴露的ip地址&端口
		ReferenceBean<StrategyTestService> referenceBean = new ReferenceBean<StrategyTestService>();
		referenceBean.setApplicationContext(SpringContextUtils.getApplicationContext());
		referenceBean.setInterface(com.ujuit.strategysdkserver.service.StrategyTestService.class);
		referenceBean.setUrl(url);
		StrategyTestService strategytestservice = null;
		try {
			referenceBean.afterPropertiesSet();
			strategytestservice = referenceBean.get();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strategytestservice;
	}
}
