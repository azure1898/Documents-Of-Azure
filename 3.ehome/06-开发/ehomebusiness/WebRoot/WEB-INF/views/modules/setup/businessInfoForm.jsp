<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商家信息管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/singlefileUpload.css" type="text/css" />
<script type="text/javascript">
    $(document).ready(function() {
        $("#shangpin").hide();//0如果选择的商家分类所对应的产品模式为“商品配买”，那么关联显示出此块内容
        $("#fuwu").hide();//1如果选择的商家分类所对应的产品模式为“服务预约”，那么关联显示出此块内容。
        fillPro();//加载全部省市区数据；

        KindEditor.ready(function(K) {
            var editor1 = K.create('textarea[name="businessIntroduce"]', {
                cssPath : '${ctxStatic}/plugins/code/prettify.css',
                uploadJson : "${ctx}/UploadFile.do",
                allowFileManager : false,
                themeType : 'simple',
                allowImageUpload : true,//允许上传图片
                afterChange : function() {
                    var limitNum = 65000;
                    if (this.count() > limitNum) {
                        $(".word_message").show();
                    } else {
                        $(".word_message").hide();
                    }
                },
                afterBlur : function() {
                    this.sync();
                },
                items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|', 'emoticons', 'image', 'link' ]
            });
            prettyPrint();
        });
        // 校验商家标签：标签以逗号隔开，最多5个
        $.validator.addMethod("checkBusinessLabel", function(value, element, params) {
            // 逗号
            var commaSign = ",";
            // 排除未填写商家标签的情况
            if (value == undefined || value == "") {
                return true;
            }
            // 正则表达式：标签以逗号隔开，最多5个
            var labelPatrn = "^([a-zA-Z0-9\u4e00-\u9fa5]+" + commaSign + "){0,4}[a-zA-Z0-9\u4e00-\u9fa5]+$";
            var regExpLabel = new RegExp(labelPatrn);
            return regExpLabel.exec(value) != null && value == regExpLabel.exec(value)[0];
        }, "商家标签应以半角逗号隔开，最多输入5个");
        jQuery.validator.addMethod("isPhone", function(value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
            var tel = /^\d{3,4}-?\d{7,9}$/;
            if (/-/.test(value)) {
                return this.optional(element) || tel.test(value);
            } else {
                return this.optional(element) || (length == 11 && mobile.test(value));
            }
        }, "请正确填写您的联系电话");
        $("#inputForm").validate({
            rules : {
                phoneNum : {
                    required : true,
                    minlength : 11,
                    isPhone : true
                },
                businessLabel : {
                    required : true,
                    checkBusinessLabel : true,
                    maxlength : 128
                },
            },
            messages : {
                phoneNum : {
                    required : "请输入联系电话",
                    isPhone : "请正确填写您的联系电话"
                },
                contactPerson : {
                    required : "请输入联系人"
                },
                businessLabel : {
                    required : "请填写至少一个商家标签"
                },
                businessHours : {
                    required : "请填写营业时间"
                },
            },
            submitHandler : function(form) {
                var villageIdList = '';
                if (KindEditor.instances[0].html().length > 65000) {
                    $(".word_message").show();
                    return;
                }
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer : "#messageBox",
            errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
        var arr = $("#categoryIds").val().replace("[", "").replace("]", "").replace(/\s/g, "");//split(",")
        arr = arr.split(",")
        for (var i = 0; i < arr.length; i++) {
            $("input[name='categoryIdList']").each(function() {
                $(this).attr('disabled', true);//按钮不可用  
                if ($(this).val() == arr[i]) {
                    $(this).attr("checked", "ture");
                }
            })
        }
        /* 省市 服务范围 */
        $("#addrpro").change(function() {
            getVillageList();
        })
        $("#addrcity").change(function() {
            getVillageList();
        })
    });
</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li>
            <span>
                <a href="${ctx}/setup/businessInfo/">商家设置 ></a>商家资料
            </span>
        </li>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="businessInfo" action="${ctx}/setup/businessInfo/save" method="post" enctype="multipart/form-data" class="form-horizontal">
        <form:hidden id="businessinfoId" path="id" />
        <input id="serviceModel" type="hidden" value="${businessInfo.serviceModel}" />
        <input id="villageIds" type="hidden" value="${businessInfo.villageIds}" />
        <input id="categoryIds" type="hidden" value="${businessInfo.categoryIdList}" />
        <form:hidden id="businessinfoId" path="useState" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">商户名称：</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${not empty businessInfo.id}">
                        <input type="text" id="businessName" value="${businessInfo.businessName}" readonly="readonly" name="businessName">
                    </c:when>
                    <c:otherwise>
                        <form:input path="businessName" htmlEscape="false" maxlength="64" class="input-xlarge required" />
                    </c:otherwise>
                </c:choose>

                <span class="help-inline"> (如需修改名称，请联系普及网络相关工作人员) </span>
            </div>
        </div>

        <div class="control-group" id="localImag">
            <label class="control-label">商家图片：</label>
            <div class="controls">
                <div class="img-box full" id="imgArea">
                    <section class=" img-section">
                        <div class="z_photo upimg-div clear" style="width: 150px;">
                            <c:if test="${businessInfo.businessPic != ''}">
                                <section class="up-section fl">
                                    <span class="up-span"></span>
                                    <img src="${ctxStatic}/images/a7.png" class="close-upimg"> <img src="${businessInfo.businessPic}" class="up-img">
                                </section>
                            </c:if>
                            <section class="z_file fl" <c:if test="${businessInfo.businessPic != ''}">style="display: none;"</c:if>>
                                <img src="${ctxStatic}/images/a10.png" class="add-img">
                                <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" />
                            </section>
                        </div>
                    </section>
                </div>
                <aside class="mask works-mask">
                    <div class="mask-content" id="imgAside">
                        <p class="del-p ">您确定要删除图片吗？</p>
                        <p class="check-p">
                            <span class="del-com wsdel-ok">确定</span>
                            <span class="wsdel-no">取消</span>
                        </p>
                    </div>
                </aside>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">联系人：</label>
            <div class="controls">
                <form:input path="contactPerson" htmlEscape="false" maxlength="20" class="input-xlarge required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">联系电话：</label>
            <div class="controls">
                <form:input path="phoneNum" htmlEscape="false" maxlength="64" class="input-xlarge required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">商家分类:</label>
            <div class="controls">
                <%-- <form:checkboxes path="categoryIdList" disabled="true"  items="${allCategory}" itemLabel="categoryName" itemValue="id" htmlEscape="false" class="required" /> --%>
                <c:forEach items="${allCategory}" var="category" varStatus="status">
                    <input ${not empty businessInfo.id?'disabled="disabled" ':''} name="categoryIdList" type="checkbox" pattren="${category.prodType}" value="${category.id}">${category.categoryName}</input>
                </c:forEach>
                <span class="help-inline"> (如需修改商家分类，请联系普及网络相关工作人员) </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">城市：</label>
            <div class="controls">

                <input type="text" class="hide" id="hidProId" value="${businessInfo.addrPro }">
                <input type="text" class="hide" id="hidCityId" value="${businessInfo.addrCity }">
                <input type="text" class="hide" id="hidAreaId" value="${businessInfo.addrArea }">
                <select id="addrpro" disabled="disabled" name="addrPro" style="width: 120px" onchange="changeCity()">
                    <option value="">全部省份</option>
                </select>
                <select id="addrcity" disabled="disabled" name="addrCity" style="width: 120px" onchange="changeArea()">
                    <option value="">全部城市</option>
                </select>
                <select id="addrarea" disabled="disabled" name="addrArea" style="width: 120px">
                    <option value="">全部区域</option>
                </select>
                <input name="addrDetail" type="text" value="${businessInfo.addrDetail}" disabled="disabled" class="input-xlarge required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">经度：</label>
            <div class="controls">
                <input name="longitude" type="text" value="${businessInfo.longitude}" disabled="disabled" class="input-xlarge required" />
                <span class="help-inline"> （纬度小于90，大于-90， 格式例如：39.896942 ） </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">纬度：</label>
            <div class="controls">
                <input name="latitude" type="text" value="${businessInfo.latitude}" disabled="disabled" class="input-xlarge required" />
                <span class="help-inline"> （经度小于180，大于-180，格式例如：116.460565 ） </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">服务范围：</label>
            <div class="controls" id="villageIdList"></div>
            <span class="help-inline"> (如需修改地址、坐标、服务范围，请联系普及网络相关工作人员) </span>
        </div>
        <div class="control-group">
            <label class="control-label">商家标签：</label>
            <div class="controls">
                <form:input path="businessLabel" htmlEscape="false" maxlength="128" class="input-xlarge required" />
                <span class="help-inline">
                    标签以半角逗号隔开，最多5个 <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">营业时间：</label>
            <div class="controls">
                <form:input path="businessHours" htmlEscape="false" maxlength="256" class="input-xlarge required" />
                <span class="help-inline">
                    <font color="red">*</font>
                </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">商家介绍描述：</label>
            <div class="controls">
                <form:textarea id="businessIntroduce" path="businessIntroduce" rows="4" maxlength="200" style="width:1000px;height:400px" class="input-xlarge" />
                <label for="businessIntroduce" class="error word_message" style="display: none;">超出最大长度，请适当缩减内容</label>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="setup:businessInfo:edit">
                <input id="btnSubmit" style="width:60px" class="commonbtn" type="submit" value="保 存" />&nbsp;
                </shiro:hasPermission>
            <shiro:hasPermission name="setup:businessInfo:services">
                <input class="commonbtn" style="width:100px" type="button" onclick="window.location.href='${ctx}/setup/businessInfo/edit'" value="继续营业设置"/>
            </shiro:hasPermission>
            <input id="btnCancel" style="width:60px" class="commonbtn" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
    <script src="${ctxStatic}/common/singlefileUpload.js"></script>
</body>
</html>