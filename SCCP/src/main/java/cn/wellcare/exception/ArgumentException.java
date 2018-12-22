package cn.wellcare.exception;

/**
 * 参数错误，方法调用的入参不符合要求
 * 
 * @author zhaihl
 *
 */
public class ArgumentException extends BusinessException {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5961619353798906031L;

	public ArgumentException(String message) {
		super(message);
	}

}
