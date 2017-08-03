<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
       body {
			font-family:Microsoft Yahei,Arial, Helvetica, sans-serif; 
			color: #666666;
			font-size:12px;
			background:url(${ctxStatic}/images/bg.png) center top no-repeat;
			font-family:"微软雅黑";
		}
		.clear {
			clear: both;
		}
		body, div, ul, ol, li, form, p, input, fieldset, h1, h2, h3, h4, h5, h6, dl, dt, dd {
			margin: 0;
			padding: 0;
			list-style: none;
		}
		
		.login_top{ width:310px; height:120px; text-align:center; line-height:130px; margin:auto;}
		.login_top a{ font-size:40px; color:#fff;}
		.login_m{ width:310px; height:260px; margin:auto; background:url(${ctxStatic}/images/bg2.png) left center no-repeat; padding-top:30px;}
		.login_m_but{ width:228px; height:37px; margin:auto;background:url(${ctxStatic}/images/001.png) left center no-repeat;}
		.login_m_but_icon{width:192px; height:37px; background:url(${ctxStatic}/images/adm.png) 15px center no-repeat; padding-left:36px; border:none; text-align:left; color:#8c9093;}
		.login_m_but_icon_2{width:192px; height:37px; background:url(${ctxStatic}/images/passworld.png) 15px center no-repeat; padding-left:36px; border:none; text-align:left; color:#8c9093;}
		.login_m_but2{ width:230px; height:37px; margin:auto;}
		.login_m_but_icon2{width:111px; height:37px; background:url(${ctxStatic}/images/code.png) 15px center no-repeat; padding-left:36px; border:none; text-align:left; color:#8c9093; float:left; display:inline-block;}
		.login_m_but_icon2_r{ float:left; width:67px;}
		.mtop20{ margin-top:20px;}
		.mtop110{ margin-top:140px;}
		.mtop10{ margin-top:10px;}
		.login_but1{ width:232px; height:41px; background:url(${ctxStatic}/images/but_normal.png) left center no-repeat; margin:auto; margin-top:20px;}
		.login_but1:hover{ width:232px; height:41px; background:url(${ctxStatic}/images/but_push.png) left center no-repeat; margin:auto; margin-top:20px;}
		.wjmm{ width:54px; height:30px; margin:auto; line-height:30px; background:url(${ctxStatic}/images/about.png) left center no-repeat; padding-left:20px;}
		.wjmm a{ color:#60c1f7; text-decoration:none;}
		.foot{ text-align:center; color:#7c7c7c; font-size:14px;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "用户名不能为空"},password: {required: "者密码不能为空"},
					validateCode: {remote: "验证码填写有误", required: "请输入验证码"}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
		    <c:if test="${message !=null && message !=''}">
				<label id="loginError" class="error">${message}</label>
			</c:if>
		</div>
	</div>
	
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
	    <div class="login_top">
		<a>商家管理系统</a>
		</div>
		<div class="login_m">
			<div class="login_m_but">
			  <input name="username" id="username" type="text" maxlength="30" class="login_m_but_icon" style="width:185px; height:28px; background:url(${ctxStatic}/images/adm.png) 15px center no-repeat; padding-left:36px; border:none; text-align:left; color:#8c9093;" placeholder="请输入用户名" autocomplete="off"/>
			</div>
			<div class="mtop20"></div>
			<div class="login_m_but">
				<input type="password" id="password" name="password" type="text"  maxlength="30" style="width:185px; height:28px; background:url(${ctxStatic}/images/passworld.png) 15px center no-repeat; padding-left:36px; border:none; text-align:left; color:#8c9093;" placeholder="请输入密码" autocomplete="off"/>
			</div>
			<div class="mtop20"></div>		
			<div class="login_m_but2">
				<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
			</div>
		<!--<div class="login_m_but2">
		<input name="" type="text"  class="login_m_but_icon2" value="请输验证码"/>
		<div class="login_m_but_icon2_r"><img src="${ctxStatic}/images/code.jpg" width="67" height="34" /></div>
		<div class="clear"></div>
		</div>-->
			<div class="login_but1">
				<input type="submit" name="" value=""  style="width: 232px;height: 41px;border:0px;background:none;"/>
			</div>
			<div class="mtop10"></div>
		<!-- <div class="wjmm"><a href="#">忘记密码</a></div> -->
		</div>
		<div class="mtop110"></div>
		<div class="foot">copyright©2007-2017普及网络科技有限公司</div>
			
	</form>

</body>
</html>