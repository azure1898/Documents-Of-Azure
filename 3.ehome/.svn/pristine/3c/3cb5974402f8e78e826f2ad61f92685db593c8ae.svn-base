$(document).ready(function() {
			$("#selectElemId").val("");
});

function elemEdit(){
	if (!$("#selectElemId").val()) {
		alertx("请选择要编辑的行");
		return false;
	} else {
		var elemId = $("#selectElemId").val();
		$("#btuElemEdit").attr("href",$("#btuElemEdit").attr("href") + elemId);
		return true;
	}
}

function elemDelete(moduleName){
	if (!$("#selectElemId").val()) {
		alertx("请选择要删除的行");
		return false;
	} else {
		var elemId = $("#selectElemId").val();
		var tempHref = $("#btuElemDelete").attr("href") + elemId;
		if (confirmx("确实删除此"+ moduleName +"？",tempHref)) {
			return true;
		} else {
			return false;
		}
	}
}

function elemAuthorization(){
	if (!$("#selectElemId").val()) {
		alertx("请选择要授权的行");
		return false;
	} else {
		var elemId = $("#selectElemId").val();
		$("#btuElemAuthorization").attr("href",$("#btuElemAuthorization").attr("href") + elemId);
		return true;
	}
}

/**
 * 冻结或解冻被选中的行（冻结、解冻按钮调用）
 * @param fieldName 后台更新的字段名
 * @param moduleName 操作名（用于输出提示）
 * @param option 操作标识（冻结：frozen；解冻：unFrozen；）
 * @param alertMsgFlg 是否输出提示信息（是：true；否：false；）
 * @return 是否执行冻结或解冻操作
 */
function elemFrozen(fieldName, moduleName, option, alertMsgFlg){
   var frozen = "0";
   var unFrozen = "1";
   var frozenOption = "frozen";
   var unFrozenOption = "unFrozen";
   // 当前记录冻结与否标识
   var oldFlg = $(selectedElem).children().find("#oldFrozen").val();
   // coreCode
   if (option == frozenOption) {
	   if (oldFlg == frozen) {
		   alertx("当前信息已经为冻结状态");
		   return false;
	   } else {
		   // 冻结操作
		   if (!$("#selectElemId").val()) {
				alertx("请选择要冻结的行");
				return false;
			} else {
				var elemId = $("#selectElemId").val();
				var tempHref = $("#btuElemFozen").attr("href") + elemId + "&" + fieldName + "=" + frozen;
				if (alertMsgFlg) {
					if (confirmx("确认要冻结该"+ moduleName +"？",tempHref)) {
						return true;
					} else {
						return false;
					}
				} else {
					alert("执行冻结操作……");
					return false;
				}
			}
	   }
   } else {
	   if (oldFlg == unFrozen) {
		   alertx("当前信息已经为正常状态");
		   return false;
	   } else {
		   // 解冻操作
		    if (!$("#selectElemId").val()) {
				alertx("请选择要解冻的行");
				return false;
			} else {
				var elemId = $("#selectElemId").val();
				var tempHref = $("#btuElemFozen").attr("href") + elemId + "&" + fieldName + "=" + unFrozen;
				if (alertMsgFlg) {
					if (confirmx("确认要解冻该"+ moduleName +"？",tempHref)) {
						return true;
					} else {
						return false;
					}
				} else {
					alert("执行冻结操作……");
					return false;
				}
			}
	   }
   }
}

// 已经被选中的行
var selectedElem = null;
function selectElem(elem){
	if(selectedElem){
		// 撤销选中
		if(selectedElem === elem){
			$("#selectElemId").val("");
			$(elem).attr("class","listRowUnSelected");
			selectedElem = null;
			return;
		}
	}
	
	$("#selectElemId").val($(elem).children().find("#elemId").val());
	$.each($(elem).parent().children(),function(index,value,array){
		$(value).attr("class","listRowUnSelected");
	});
	$(elem).attr("class","listRowSelected");
	selectedElem = elem;
}