package cn.wellcare.service;

import cn.wellcare.constant.BaseParam;
import cn.wellcare.dao.SysOrganizationDao;
import cn.wellcare.entity.SysOrganization;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.utils.CommonUtils;
import cn.wellcare.utils.Md5;
import cn.wellcare.utils.PagerInfo;
import cn.wellcare.utils.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "sysOrganizationService")
public class SysOrganizationService {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private SysOrganizationDao sysOrganizationDao;

	/**
	 * 根据id取得机构
	 * 
	 * @param sysOrganizationId
	 * @return
	 */
	public ServiceResponse<SysOrganization> getSysOrganizationById(Integer sysOrganizationId) {
		ServiceResponse<SysOrganization> serviceResult = new ServiceResponse<SysOrganization>();
		try {
			serviceResult.setData(sysOrganizationDao.get(sysOrganizationId));
		} catch (Exception e) {
			log.error("[ISysOrganizationService][getSysOrganizationById]根据id[" + sysOrganizationId + "]取得机构时出现未知异常：",
					e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	/**
	 * 保存机构
	 * 
	 * @param sysOrganization
	 * @return
	 */
	public ServiceResponse<Integer> saveSysOrganization(SysOrganization sysOrganization) {
		ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
		try {
			// 机构密码默认为123456
			sysOrganization.setAuthSecret(Md5.getMd5String(BaseParam.ORG_PASSWORD));
			// 认证密钥随机生成16位加密的随机数
			sysOrganization.setAuthSecret(Md5.getMd5String(RandomUtil.randomNumber(16)));
			serviceResult.setData(sysOrganizationDao.save(sysOrganization));
		} catch (Exception e) {
			log.error("[ISysOrganizationService][saveSysOrganization]保存机构时出现未知异常：", e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	/**
	 * 更新机构
	 * 
	 * @param sysOrganization
	 * @return
	 * @see SysOrganizationService#updateSysOrganization(SysOrganization)
	 */
	public ServiceResponse<Integer> updateSysOrganization(SysOrganization sysOrganization) {
		ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
		try {
			// 如果密码被修改，
			if (sysOrganization != null && !StringUtils.isEmpty(sysOrganization.getAuthPwd())) {
				sysOrganization.setAuthPwd(Md5.getMd5String(sysOrganization.getAuthPwd()));
			}
			serviceResult.setData(sysOrganizationDao.update(sysOrganization));
		} catch (Exception e) {
			log.error("[ISysOrganizationService][updateSysOrganization]更新机构时出现未知异常：", e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public ServiceResponse<List<SysOrganization>> page(Map<String, Object> queryMap, PagerInfo pager) {
		ServiceResponse<List<SysOrganization>> serviceResult = new ServiceResponse<List<SysOrganization>>();
		try {
			Map<String, Object> param = new HashMap<String, Object>(queryMap);
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(sysOrganizationDao.getCount(param));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			param.put("start", start);
			param.put("size", size);
			serviceResult.setData(sysOrganizationDao.getList(param));
		} catch (Exception e) {
			log.error("[SysOrganizationService][page] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}

		}
		return serviceResult;
	}

	public ServiceResponse<Boolean> del(Integer id) {
		ServiceResponse<Boolean> serviceResult = new ServiceResponse<Boolean>();
		try {
			serviceResult.setData(sysOrganizationDao.del(id) > 0);
		} catch (Exception e) {
			log.error("[SysOrganizationService][del] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public ServiceResponse<List<SysOrganization>> getByAuthName(String username) {
		ServiceResponse<List<SysOrganization>> serviceResult = new ServiceResponse<>();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("authName", username);
			serviceResult.setData(sysOrganizationDao.getList(param));
		} catch (Exception e) {
			log.error("[SysOrganizationService][getByAuthName] exception:" + e.getMessage());
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	private void dbConstrains(SysOrganization sysOrganization) {
		sysOrganization.setAuthName(CommonUtils.dbSafeString(sysOrganization.getAuthName(), true, 32));
		sysOrganization.setAuthPwd(CommonUtils.dbSafeString(sysOrganization.getAuthPwd(), true, 32));
		sysOrganization.setAuthSecret(CommonUtils.dbSafeString(sysOrganization.getAuthSecret(), true, 255));
		sysOrganization.setOrgCode(CommonUtils.dbSafeString(sysOrganization.getOrgCode(), true, 32));
		sysOrganization.setOrgIndex(CommonUtils.dbSafeString(sysOrganization.getOrgIndex(), true, 255));
		sysOrganization.setOrgName(CommonUtils.dbSafeString(sysOrganization.getOrgName(), true, 32));
		sysOrganization.setPyCode(CommonUtils.dbSafeString(sysOrganization.getPyCode(), true, 32));
		sysOrganization.setShortName(CommonUtils.dbSafeString(sysOrganization.getShortName(), true, 32));
	}
}