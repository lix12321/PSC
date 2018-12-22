package cn.wellcare.service.acc;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.acc.bo.RechargeParamDto;
import cn.wellcare.acc.entity.PscPiAcc;
import cn.wellcare.acc.entity.PscPiAccDetail;
import cn.wellcare.api.trade.IPscAccPaymentService;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.EnumerateParameter;
import cn.wellcare.model.acc.PscPiAccDetailModel;
import cn.wellcare.model.acc.PscPiAccModel;
import cn.wellcare.pojo.common.AccPaymentResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 账户支付
 */

@Service("pscAccPaymentService")
public class AccountPayService implements IPscAccPaymentService {

	@Resource
	private PscPiAccModel piAccModel;
	@Resource
	private PscPiAccDetailModel pscPiAccDetailModel;

	@Override
	public RpcResult<AccPaymentResult> accPay(Map<String, Object> param) {
		RpcResult<AccPaymentResult> sr = new RpcResult<>();
		try {
			String pkPi = String.valueOf(param.get(BaseParam.USER_ID));// 获取用户主索引
			BigDecimal amount = new BigDecimal(String.valueOf(param.get(BaseParam.PAY_AMOUNT)));// 获取账户支付金额
			Integer pkOrg = Integer.valueOf(String.valueOf(param.get(BaseParam.ORG_ID)));
			PscPiAcc pscPiAcc = piAccModel.getPscPiAccBypkPi(pkPi);// 获取用户账户信息
			if ((pscPiAcc.getAmtAcc().add(pscPiAcc.getCreditAcc())).compareTo(amount) < 0) {
				throw new BusinessException("账户余额不足");
			}
			pscPiAcc.setAmtAcc(pscPiAcc.getAmtAcc().subtract(amount));// 余额扣除支付金额
			piAccModel.updatePscPiAcc(pscPiAcc);// 更新账户信息

			PscPiAccDetail pscPiAccDetail = new PscPiAccDetail();
			// pscPiAccDetail.setPkEmpOpera(); //操作人
			pscPiAccDetail.setPkOrg(pkOrg);
			pscPiAccDetail.setPkPiacc(pscPiAcc.getPkPiacc());
			pscPiAccDetail.setAmtBalance(pscPiAcc.getAmtAcc());
			pscPiAccDetail.setAmount(amount);
			pscPiAccDetail.setEuOptype(EnumerateParameter.TWO);
			pscPiAccDetail.setEuDirect(Integer.valueOf(EnumerateParameter.NEGA));
			pscPiAccDetailModel.savePscPiAccDetail(pscPiAccDetail);// 新增账户流水
			sr.setData(new AccPaymentResult(amount.toString(), String.valueOf(pscPiAcc.getAmtAcc())));
		} catch (Exception e) {
			sr.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode())) {
					sr.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				} else {
					sr.setMsgInfo(e.getMessage());
				}
				sr.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				if (e instanceof UnauthorizedException) {
					sr.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else {
					e.printStackTrace();
					sr.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
			throw e;
		}
		return sr;

	}

	@Override
	public RpcResult<AccPaymentResult> accRecharge(Map<String, Object> param) {
		RpcResult<AccPaymentResult> sr = new RpcResult<>();
		try {
			RechargeParamDto rpd = new RechargeParamDto();
			rpd.setAccId(Integer.valueOf(param.get(BaseParam.USER_ID).toString()));
			rpd.setAccName((String) param.get(BaseParam.USER_NAME));
			rpd.setOrgId(Integer.valueOf((String) param.get(BaseParam.ORG_ID)));
			rpd.setOrderId(Integer.valueOf(param.get(BaseParam.ORDER_ID).toString()));
			rpd.setMoney(new BigDecimal((String) param.get(BaseParam.PAY_AMOUNT)));
			rpd.setRechargeType(PaymentType.getPaymentCodeByNameOrCode((String) param.get(BaseParam.RECHARGE_TYPE)));
			rpd.setHandleName((String) param.get(BaseParam.HANDLE_NAME));
			
			PscPiAcc acc = piAccModel.accRecharge(rpd);

			sr.setData(new AccPaymentResult(((String) param.get(BaseParam.PAY_AMOUNT)), acc.getAmtAcc().toString()));
		} catch (Exception e) {
			sr.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode())) {
					sr.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				} else {
					sr.setMsgInfo(e.getMessage());
				}
				sr.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				if (e instanceof UnauthorizedException) {
					sr.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else {
					e.printStackTrace();
					sr.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
			throw e;
		}
		return sr;
	}

}
