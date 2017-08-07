<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>发言管理</title>
    <meta name="decorator" content="default" />
    <link rel="stylesheet" href="${ctxStatic}/common/multiplefileUpload.css" type="text/css" />
    <script type="text/javascript">
        $(document).ready(function() {
        });

        function showtab(s, h1, h2) {
            document.getElementById("menu" + s).style.background = "#eee"
            document.getElementById("con" + s).style.display = "block"
            document.getElementById("menu" + h1).style.background = "#ccc"
            document.getElementById("con" + h1).style.display = "none"
            document.getElementById("menu" + h2).style.background = "#ccc"
            document.getElementById("con" + h2).style.display = "none"
        }
        onload = function() {
            showtab(1, 2, 3)
        }

        function changeCommentDelFlag(id) {
            $.ajax({
                type: 'POST',
                url: '${ctx}/comment/socialComment/changeDelFlag',
                data: {
                    id: id
                },
                dataType: 'json',
                success: function(data) {
                    if (data == "1") {
                        $.jBox.tip('删除评论成功！');
                        //延迟一秒再跳转
                        setTimeout(function() {
                            location.reload();
                        }, 1000);
                    } else {
                        $.jBox.tip('删除评论失败！');
                    }
                }
            });
        }

        function changeForwardDelFlag(id) {
            $.ajax({
                type: 'POST',
                url: '${ctx}/comment/socialForward/changeDelFlag',
                data: {
                    id: id
                },
                dataType: 'json',
                success: function(data) {
                    if (data == "1") {
                        $.jBox.tip('删除评论成功！');
                        //延迟一秒再跳转
                        setTimeout(function() {
                            location.reload();
                        }, 1000);
                    } else {
                        $.jBox.tip('删除评论失败！');
                    }
                }
            });
        }

        function sendfid(pid, username) {
            $("#fid").val(pid);
            $("#content").attr("placeholder", "@" + username);
        }

        function saveComment() {
            var speakid = $("#speakid").val();
            var subjectid = $("#subjectid").val();
            var content = $("#content").val();
            var fid = $("#fid").val();
            $.ajax({
                type: 'POST',
                url: '${ctx}/comment/socialComment/save',
                data: {
                    speakid: speakid,
                    subjectid: subjectid,
                    content: content,
                    fid: fid
                },
                dataType: 'json',
                success: function(data) {
                    if (data == "1") {
                        $.jBox.tip('评论成功！');
                        //延迟一秒再跳转
                        setTimeout(function() {
                            location.reload();
                        }, 1000);
                    } else {
                        $.jBox.tip('评论失败！');
                    }
                }
            });
        }
    </script>
    <style>
        #menu {
    width: 1024px;
    height: 40px;
    padding: 0;
    margin: 0;
}

#menu  li {
    width: 33.33%;
    height: 40px;
    LIST-STYLE-TYPE: none;
    float: left;
    background: #ccc;
    border-bottom: 1px
}

#menu  li a {
    display: block;
    width: 100%;
    text-align: center;
    font-size: 20px;
    line-height: 40px;
    TEXT-DECORATION: none;
    color: #000
}

#menu  li a:hover {
    color: #f00
}

.con {
    width: 100%;
    height: 80px;
    position: relative;
    padding: 5px;
}

#tab {
    width: 100%;
    padding: 0;
    margin: 0
}
    </style>
</head>

<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/speak/socialSpeak/">发言详情</a>
        </li>
    </ul>
    <br/>
    <form:form id="inputForm" modelAttribute="socialSpeak" enctype="multipart/form-data" action="${ctx}/speak/socialSpeak/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <input id="speakid" type="hidden" value="${socialSpeak.id}" />
        <input id="subjectid" type="hidden" value="${socialSpeak.subjectid}" />
        <sys:message content="${message}" />
        <div class="control-group">
            <table>
                <tr>
                    <td>
                        <label>发布时间：
                            <fmt:formatDate value="${socialSpeak.createtime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</label>
                        <label>发布人：${socialSpeak.username}&nbsp;&nbsp;</label>
                        <label>查阅：${socialSpeak.readnum }次</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${socialSpeak.istop =='1'}">    <font color="red">【置顶】</font>
                        </c:if>
                        <c:if test="${socialSpeak.subjectname != '' && socialSpeak.subjectname != null}">   <font color="blue">#${socialSpeak.subjectname}#</font>
                        </c:if>
                        <c:if test="${socialSpeak.tag != '' && socialSpeak.tag != null}">   <font color="blue">#${socialSpeak.tag}#</font>
                        </c:if>${socialSpeak.content}</td>
                </tr>
                <tr>
                    <td>
                        <c:forEach items="${socialSpeak.imageUrls}" var="imgUrl">
                            <img src="${imgUrl}" />
                        </c:forEach>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="content" placeholder="填写评论内容" />
                        <input type="button" value="发送" onclick="saveComment();" />
                        <input type="hidden" id="fid" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id=tab>
                            <div>
                                <ul id="menu">
                                    <li id="menu1"><a href=### onclick=showtab(1,2,3)>评论（${commentSize }）</a>
                                    </li>
                                    <li id="menu2"><a href=### onclick=showtab(2,1,3)>转发（${fowardSize }）</a>
                                    </li>
                                    <li id="menu3"><a href=### onclick=showtab(3,2,1)>点赞（${praiseSize }）</a>
                                    </li>
                                </ul>
                            </div>
                            <div class=con id="con1">
                                <table id="contentTable" class="table table-bordered table-condensed">
                                    <thead>
                                        <tr>
                                            <th>评论用户</th>
                                            <th>评论内容</th>
                                            <th>评论时间</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${socialComment}" var="socialComment" varStatus="status">
                                            <tr>
                                                <td>${socialComment.username}</td>
                                                <td style="text-align:left">
                                                    <c:if test="${socialComment.fid != '' && socialComment.fid != null}">回复：<font color="blue">@${socialComment.replyname}</font>
                                                    </c:if>${socialComment.content}</td>
                                                <td>
                                                    <fmt:formatDate value="${socialComment.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                </td>
                                                <td>    <a href="JavaScript:void(0)" onclick="changeCommentDelFlag('${socialComment.id}');">删除</a>
                                                    <a href="JavaScript:void(0)" onclick="sendfid('${socialComment.id}','${socialComment.username}' );">回复</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class=con id="con2">
                                <table id="contentTable" class="table table-bordered table-condensed">
                                    <thead>
                                        <tr>
                                            <th>转发用户</th>
                                            <th>转发内容</th>
                                            <th>转发时间</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${socialForward}" var="socialForward" varStatus="status">
                                            <tr>
                                                <td>${socialForward.username}</td>
                                                <td style="text-align:left">${socialForward.reason}</td>
                                                <td>
                                                    <fmt:formatDate value="${socialForward.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                </td>
                                                <td>    <a href="#" onclick="changeForwardDelFlag(${socialForward.id});">删除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class=con id="con3">
                                <table id="contentTable" class="table table-bordered table-condensed">
                                    <thead>
                                        <tr>
                                            <th>点赞用户</th>
                                            <th>点赞时间</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${socialPraise}" var="socialPraise" varStatus="status">
                                            <tr>
                                                <td>${socialPraise.username}</td>
                                                <td>
                                                    <fmt:formatDate value="${socialPraise.praiseTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
        </div>
    </form:form>
    <script src="${ctxStatic}/common/multiplefileUpload.js"></script>
</body>
</html>