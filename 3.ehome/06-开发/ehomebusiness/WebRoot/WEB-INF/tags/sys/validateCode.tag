<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="验证码输入框名称"%>
<%@ attribute name="inputCssStyle" type="java.lang.String" required="false" description="验证框样式"%>
<%@ attribute name="imageCssStyle" type="java.lang.String" required="false" description="验证码图片样式"%>
<%@ attribute name="buttonCssStyle" type="java.lang.String" required="false" description="看不清按钮样式"%>
<input type="text" id="${name}" name="${name}" placeholder="验证码" maxlength="5" class="txt required" style="width:75px; height:26px; background:url(${ctxStatic}/images/code.png) 15px center no-repeat; padding-left:36px; color:#8c9093; float:left;border-radius:5px; display:inline-block;${inputCssStyle}"/>
<img src="${pageContext.request.contextPath}/servlet/validateCodeServlet" onclick="$('.${name}Refresh').click();" class="mid ${name}" style="width:60px; height:32px;padding:5px;padding-bottom:10px;${imageCssStyle}"/>
<a href="javascript:" onclick="$('.${name}').attr('src','${pageContext.request.contextPath}/servlet/validateCodeServlet?'+new Date().getTime());" class="mid ${name}Refresh" style="${buttonCssStyle}">看不清</a>