package cn.wellcare.core.constant;

/**
 * 系统URL映射
 * 
 * @author zhaihl
 *
 */
public final class Constants {

	/**
	 * 微信本地扫码支付
	 */
	public static final String WXPAY_TOPAY = "topay";
	public static final String WXPAY_JSPAY = "weChatJSpay";
	public static final String WXPAY_JSAPI_RESULT = "payresult";
	public static final String INDEX_PAGE = "index";
	public static final String ALIPAY_H5_PAY = "h5api";
	public static final String UNIFY_PAY_CONTEXT = "unifyPay";
	public static final String WXPAY_DOPAY = "dopay";
	public static final String WXPAY_NATIVE_NOTIFY = "wechatNotify";
	public static final String QUERY_ORDERID = "queryOrderid";
	public static final String ALIPAY_NATIVE_NOTIFY = "alipayNotify";
	public static final String DO_ACCPAY = "doAccpay";
	public static final String REFUND_PAY = "refundPay";
	public static final String ACCOUNT_REFUNDPAY = "accountRefundPay";
	public static final String ACCOUNT_BALANCE_REFUND = "accountBalanceRefund";
	/**
	 * json格式
	 */
	public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

	/**
	 * 响应消息
	 */
	public static final String RESONSE_SUCCESS_CODE = "200";
	public static final String RESONSE_SUCCESS_MSG = "请求成功";

	public static final String ALIPAY_ORDER_SUBJECT = "西安佑君支付平台订单";

	public static final String RESOURCE_NOT_FOUND = "404";
	public static final String SERVER_ERROR = "500";

	/**
	 * 后台默认每页数据大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final String SERVICE_RESULT_CODE_SYSERROR = "syserror";

	/**
	 * 支付签名编码
	 */
	public static final String SIGN_CHARSET = "utf-8";

	/**
	 * 服务器允许的最大时间误差（秒）
	 */
	public static final long SERVER_ACCEPT_MAX_SECOND = 15l;
	/**
	 * 前端返回结果标志：成功
	 */
	public static final int SERVICE_RESULT_SUCCESS = 200;
	/**
	 * 前端返回结果标志：业务异常
	 */
	public static final int SERVICE_RESULT_BUS_ERROR = -1;
	/**
	 * 前端返回结果标志：其它异常
	 */
	public static final int SERVICE_RESULT_OTHER = -2;

	private Constants() {
	}
}