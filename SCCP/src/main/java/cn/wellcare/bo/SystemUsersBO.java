package cn.wellcare.bo;

import cn.wellcare.entity.SystemUsers;

public class SystemUsersBO extends SystemUsers {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3024061110104399479L;
	private String roleId;
	private String roleCode;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
