
function fillPro(){
	$.ajax({
		type: "POST",
		url: ctx+"/sys/area/loadPro",
		data: null,
		dataType: "JSON",
		success: function(data){
			var hidProId=$("#hidProId").val();
			$("#addrpro").empty();
			var option = "<option value=''>全部省份</option>";
			$.each(data,function(indx,item){
				option += "<option value='"+item.id+"'>"+item.name+"</option>";
			})
			$("#addrpro").append(option);
			$("#addrpro").val(hidProId).trigger("change");//修改初始时，带值选中
		}
	})
}
	
function changeCity(){
	$.ajax({
		type: "POST",
		url: ctx+"/sys/area/changeArea",
		data: {parentId: $("#addrpro").val()},
		dataType: "JSON",
		success: function(data){
			var hidCityId=$("#hidCityId").val();
			$("#addrcity").empty();
			var option = "<option value=''>全部城市</option>";
			$.each(data,function(indx,item){
				option += "<option value='"+item.id+"'>"+item.name+"</option>";
			})
			$("#addrcity").append(option);
			$("#addrcity").val(hidCityId).trigger("change");//修改初始时，带值选中
		}
	})
}
function changeArea(){
	$.ajax({
		type: "POST",
		url: ctx+"/sys/area/changeArea",
		data: {parentId: $("#addrcity").val()},
		dataType: "JSON",
		success: function(data){
			var hidAreaId=$("#hidAreaId").val();
			$("#addrarea").empty();
			var option = "<option value=''>全部区域</option>";
			$.each(data,function(indx,item){
				option += "<option value='"+item.id+"'>"+item.name+"</option>";
			})
			$("#addrarea").append(option);
			$("#addrarea").val(hidAreaId).trigger("change");//修改初始时，带值选中
		}
	})
}
//以select的形式展示楼盘列表
function changeVillage(){
	$.ajax({
		type: "POST",
		url: ctx+"/village/villageInfo/findList",
		data: {
			provinceId: $("#addrpro").val(),
			cityId: $("#addrcity").val()
		},
		dataType: "JSON",
		success: function(data){
			var hidVillageId=$("#hidVillageId").val();
			$("#addrVillage").empty();
			var option = "<option value=''>全部楼盘</option>";
			$.each(data,function(indx,item){
				option += "<option value='"+item.id+"'>"+item.villageName+"</option>";
			})
			$("#addrVillage").append(option);
			$("#addrVillage").val(hidVillageId).trigger("change");//修改初始时，带值选中
		}
	})
}
//以checkbox的形式展示楼盘列表
function getVillageList(){
	$.ajax({
		type: "POST",
		url: ctx+"/village/villageInfo/findList",
		data: {
			provinceId: $("#addrpro").val(),
			cityId: $("#addrcity").val()
		},
		dataType: "JSON",
		success: function(data){
			$("#villageIdList").html('');
			var domRow='';
			var arr =$("#villageIds").val();
			$.each(data,function(indx,item){
				if(arr.indexOf(item.id)<0){
					domRow+='<input  name="villageIdList"  type="checkbox" value="'+item.id+'" >'+item.villageName+'</input>';
				}else{
					domRow+='<input  name="villageIdList" checked="ture" type="checkbox" value="'+item.id+'" >'+item.villageName+'</input>';
				}
			})
			domRow+='<span class="help-inline"><font color="red">*</font> </span>';
			$("#villageIdList").append($(domRow));
		}
	})
}
