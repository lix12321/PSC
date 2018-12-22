<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/sysorganization"/>

<style>
.dd-group .fluidbox .lab-item {
	margin-top: 3px;
}
</style>

<script language="javascript">
	$(function() {
		$("#add").click(function() {
			if ($('#addForm').form('validate')) {
				$('#addForm').ajaxSubmit(function(){
                    location.href = '${currentBaseUrl}';
                });
			}
		});
		
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

	});

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			编辑机构<span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<form method="post" class="validForm" id="addForm" name="addForm" 
				enctype="multipart/form-data" action="${currentBaseUrl}/doAdd"> 
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
                    <input type="hidden" name="id" value="${obj.id}">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>机构名称: </label>
							<input type="text" id="orgName" name="orgName"
								class="txt w200 easyui-validatebox"
								missingMessage="机构名称必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" value="${obj.orgName}"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>机构简称: </label>
							<input type="text" id="shortName" name="shortName"
								class="txt w200 easyui-validatebox"
								missingMessage="机构简称必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" value="${obj.shortName}"/>
						</p>
					<#--</div>
					<div class="fluidbox">-->
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>机构编码: </label>
							<input type="text" id="orgCode" name="orgCode"
								class="txt w200 easyui-validatebox"
								missingMessage="机构编码必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" value="${obj.orgCode}"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>拼音码: </label>
							<input type="text" id="pyCode" name="pyCode"
								class="txt w200 easyui-validatebox"
								missingMessage="拼音码必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" value="${obj.pyCode}"/>
						</p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>认证用户名: </label>
                            <input type="text" id="authName" name="authName"
                                   class="txt w200 easyui-validatebox"
                                   missingMessage="认证用户名必填，2-20个字符"
                                   data-options="required:true,validType:'length[2,20]'" value="${obj.authName}"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>认证密码: </label>
                            <input type="text" id="authPwd" name="authPwd"
                                   class="txt w200 easyui-validatebox"
                                   missingMessage="认证密钥必填，6-8个字符"
                                   data-options="required:false,validType:'length[6,8]'" placeholder="点此修改密码"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>认证密钥: </label>
                            <input type="text" id="authSecret" name="authSecret" readonly
                                   class="txt w200 easyui-validatebox"
                                   data-options="required:false" value="${obj.authSecret}"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red"></font>机构主页: </label>
                            <input type="text" id="orgIndex" name="orgIndex"
                                   class="txt w200 easyui-validatebox"
                                   data-options="required:false" value="${obj.orgIndex}"/>
                        </p>
					</div>
						
				</dd>
			</dl>
			<p class="p-item p-btn">
				<input type="button" id="add" class="easyui-linkbutton" value="提交" /> <input
					type="button" id="back" class="easyui-linkbutton" value="返回" />
			</p>
			</form>
		</div>
	</div>
</div>

<#include "/commons/_detailfooter.ftl" />
