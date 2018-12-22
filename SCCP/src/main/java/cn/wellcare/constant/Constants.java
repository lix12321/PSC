package cn.wellcare.constant;

/**
 * 系统常量
 * 
 * @author zhaihl
 *
 */
public final class Constants {
	/**
	 * 响应消息
	 */
	public static final String RESONSE_SUCCESS_CODE = "200";
	public static final String RESONSE_SUCCESS_MSG = "请求成功";

	/**
	 * 后台默认每页数据大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final String SERVICE_RESULT_CODE_SYSERROR = "syserror";

	/**
	 * 默认编码
	 */
	public static final String DEFAULT_CHARSET = "utf-8";

	/** 系统资源管理-系统管理根结点，与数据库初始化数据（system_resources）相关，如果修改数据库请修改此值 */
	public static final int SYSTEM_RESOURCE_ROOT = 0;

	/**
	 * 服务器允许的最大时间误差（秒）
	 */
	public static final int SERVER_ACCEPT_MAX_SECOND = 15;

	/** 验证码key */
	public static final String VERIFY_NUMBER_NAME = "verify_number";

	/**
	 * 系统管理员编码
	 */
	public static final String SYSTEM_ADMIN_CODE = "ADMIN";
	public static final String SERVER_KEY = "e75c9893cf466e8c904c005337a5ed2f";
	public static final String API_AUTH_URI = "/api/auth";
	public static final String API_USER = "/api/user";
	public static final String API_CCP = "/api/ccp";
	public static final String REQUEST_TYPE = "REQUEST_TYPE";
	public static final String DATA_TYPE = "DATA_TYPE";

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