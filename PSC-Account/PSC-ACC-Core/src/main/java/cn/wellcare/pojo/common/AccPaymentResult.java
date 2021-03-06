package cn.wellcare.pojo.common;

import java.io.Serializable;

import cn.wellcare.core.constant.Constants;

/**
 * 支付返回结果
 * 
 * @author zhaihl
 * @date 2018年10月15日
 */
public class AccPaymentResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4136276831033390226L;
	// 支付/充值金额
	private String totalFee;
	// 账户余额
	private String amtAcc;
	private boolean success;
	private String msg;

	public AccPaymentResult() {
		this.success = true;
		this.msg = Constants.RESONSE_SUCCESS_MSG;
	}

	public AccPaymentResult(String totalFee, String amtAcc, String msg) {
		this.totalFee = totalFee;
		this.amtAcc = amtAcc;
		this.success = false;
		this.msg = msg;
	}

	public AccPaymentResult(String totalFee, String amtAcc) {
		this.totalFee = totalFee;
		this.amtAcc = amtAcc;
		this.success = true;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getAmtAcc() {
		return amtAcc;
	}

	public void setAmtAcc(String amtAcc) {
		this.amtAcc = amtAcc;
	}

	@Override
	public String toString() {
		return "PaymentResult [totalFee=" + this.totalFee + ", amtAcc=" + this.amtAcc + ", success=" + this.success
				+ ", msg=" + this.msg + "]";
	}

}
