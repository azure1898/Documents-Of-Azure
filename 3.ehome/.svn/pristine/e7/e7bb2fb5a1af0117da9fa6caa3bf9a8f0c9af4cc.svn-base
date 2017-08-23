<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家资料</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
<meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
<title>百度地图API自定义地图</title>
<!--引用百度地图API-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E2e4456f16476fe33def2b01ab80079a"></script>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>

</head>

<body style="border: #ccc solid 1px; padding: 10px; height: 100%;">
     <sys:message content="${message}" />
    <div style="float: left; width: 50%; word-break: break-all; padding-right: 20px">
        <div class="control-group">
            <label class="control-label" style="font-weight: bold;">商家资料</label> <label class="control-label" style="float: right;"> <a href="${ctx}/setup/businessInfo/form">修改资料></a>
            </label>
        </div>
        <div class="control-group" style="margin-top: 15px">
            <div class="controls">
                <label class="control-label">商家名称: </label> <span id="name"></span>

            </div>
        </div>
        <div class="control-group" style="margin-top: 15px">
            <div class="controls">
                <label class="control-label">商家电话： </label> <span id="phone"></span>
            </div>
        </div>
        <div class="control-group" style="margin-top: 15px">
            <div class="controls">
                <label class="control-label">商家地址： </label><span id="address"></span>
            </div>
        </div>
        <div class="control-group" style=" margin-top: 15px">
            <div class="controls">
                <label class="control-label">商家介绍： </label> 
            </div>
        </div>
        <div class="control-group" style="  height:65%;overflow: auto;">
            <div class="controls">
                 <span id="desc"></span>
            </div>
        </div>
    </div>
        <!--百度地图容器-->
        <div style="width: 500px; height: 350px; border: #ccc solid 1px; font-size: 12px" id="map"></div>

</body>
<script type="text/javascript">
    $("#state").click(function() {
        console.log($(this).attr("checked"))
        if ($(this).attr("checked") == "checked") {
            updateState(0);
        } else {
            updateState(1);
        }
    })

    function updateState(state) {
        $.ajax({
            type : 'POST',
            url : '${ctx}/setup/businessInfo/updateState',
            data : {
                state : state
            },
            success : function(data) {
                if (data == '') {
                    loading('正在提交，请稍等...');
                    form.submit();
                } else {
                   alert(data)
                }
            }
        })
    }
</script>
<script type="text/javascript">
    //创建和初始化地图函数：
    function initMap(longitude, latitude, name) {
        createMap(longitude, latitude); //创建地图
        setMapEvent(); //设置地图事件
        addMapControl(); //向地图添加控件
        addMapOverlay(name); //向地图添加覆盖物
    }
    function createMap(longitude, latitude, name) {
        map = new BMap.Map("map");
        map.centerAndZoom(new BMap.Point(longitude, latitude), 15);
    }
    function setMapEvent() {
        map.enableScrollWheelZoom();
        map.enableKeyboard();
        map.enableDragging();
        map.enableDoubleClickZoom()
    }
    function addClickHandler(target, window) {
        target.addEventListener("click", function() {
            target.openInfoWindow(window);
        });
    }
    function addMapOverlay(name) {
        var markers = [ {
            content : "",
            title : name,
            imageOffset : {
                width : 0,
                height : 3
            },
            position : {
                lat : 34.769389,
                lng : 113.616584
            }
        } ];
        for (var index = 0; index < markers.length; index++) {
            var point = new BMap.Point(markers[index].position.lng, markers[index].position.lat);
            var marker = new BMap.Marker(point, {
                icon : new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(20, 25), {
                    imageOffset : new BMap.Size(0, 0 - 11 * 25)
                // 设置图片偏移 
                })
            });
            var label = new BMap.Label(markers[index].title, {
                offset : new BMap.Size(25, 5)
            });
            var opts = {
                width : 200,
                title : markers[index].title,
                enableMessage : false
            };
            var infoWindow = new BMap.InfoWindow(markers[index].content, opts);
            marker.setLabel(label);
            addClickHandler(marker, infoWindow);
            map.addOverlay(marker);
        }
        ;
    }
    //向地图添加控件
    function addMapControl() {
        var scaleControl = new BMap.ScaleControl({
            anchor : BMAP_ANCHOR_BOTTOM_LEFT
        });
        scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
        map.addControl(scaleControl);
        var navControl = new BMap.NavigationControl({
            anchor : BMAP_ANCHOR_TOP_LEFT,
            type : BMAP_NAVIGATION_CONTROL_LARGE
        });
        map.addControl(navControl);
        var overviewControl = new BMap.OverviewMapControl({
            anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
            isOpen : true
        });
        map.addControl(overviewControl);
    }
    var map;

    $.ajax({
        type : 'POST',
        url : '${ctx}/setup/businessInfo/getInfo',
        success : function(data) {
            console.log(data.businessName)
            $("#name").html(data.businessName);
            $("#phone").html(data.phoneNum);
            $("#address").html(data.remarks);
            $("#desc").html(data.businessIntroduce);
            initMap(data.longitude, data.latitude, data.businessName)
        }
    })
</script>
</html>