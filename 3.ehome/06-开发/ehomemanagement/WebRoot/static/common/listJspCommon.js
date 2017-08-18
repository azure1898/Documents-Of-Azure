$(document).ready(function() {
    $("#selectElemId").val("");
});

/**
 * 编辑
 * 
 * @returns {Boolean}
 */
function elemEdit(moduleName) {
    console.log(moduleName)
    if (!$("#selectElemId").val()) {
        if(moduleName==undefined||moduleName==''){
            alertx("请选择要编辑的行");
        }else{
            alertx("请选择要编辑的"+moduleName);
        }
        return false;
    } else {
        var elemId = $("#selectElemId").val();
        $("#btuElemEdit").attr("href", $("#btuElemEdit").attr("href") + elemId);
        return true;
    }
}

/**
 * 删除
 * 
 * @returns {Boolean}
 */
function elemDelete(moduleName) {
    if (!$("#selectElemId").val()) {
        if(moduleName == undefined || moduleName==''){
            alertx("请选择要删除的行");
        }else{
            alertx("请选择要删除的"+moduleName);
        }
        return false;
    } else {
        var elemId = $("#selectElemId").val();
        var tempHref = $("#btuElemDelete").attr("href") + elemId;
        var msgConfirm = "确定删除这条数据？";
        if (moduleName != undefined && moduleName != '') {
            msgConfirm = "确定删除此" + moduleName + "？";
        }
        if (confirmx(msgConfirm , tempHref)) {
            return true;
        } else {
            return false;
        }
    }
}

/**
 * 授权
 * 
 * @returns {Boolean}
 */
function elemAuthorization() {
    if (!$("#selectElemId").val()) {
        alertx("请选择要授权的角色");
        return false;
    } else {
        var elemId = $("#selectElemId").val();
        $("#btuElemAuthorization").attr("href", $("#btuElemAuthorization").attr("href") + elemId);
        return true;
    }
}

/**
 * 关联产品线
 * 
 * @returns {Boolean}
 */
function elemEditProductLine() {
    if (!$("#selectElemId").val()) {
        alertx("请选择要关联产品线的行");
        return false;
    } else {
        var elemId = $("#selectElemId").val();
        $("#btuElemProductLine").attr("href", $("#btuElemProductLine").attr("href") + elemId);
        return true;
    }
}

/**
 * 冻结或解冻被选中的行（冻结、解冻按钮调用）
 * 
 * @param fieldName
 *            后台更新的字段名
 * @param moduleName
 *            操作名（用于输出提示）
 * @param option
 *            操作标识（冻结：frozen；解冻：unFrozen；）
 * @param alertMsgFlg
 *            是否输出提示信息（是：true；否：false；）
 * @return 是否执行冻结或解冻操作
 */
function elemFrozen(fieldName, moduleName, option, alertMsgFlg) {
    var frozen = "1";
    var unFrozen = "0";
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
                if(moduleName==''){
                    alertx("请选择要冻结的行");
                }else{
                    alertx("请选择要冻结的"+moduleName);
                }
                return false;
            } else {
                var elemId = $("#selectElemId").val();
                var tempHref = $("#btuElemFozen").attr("href") + elemId + "&" + fieldName + "=" + frozen;
                var msgFozenConfirm = "确认要冻结此行吗？";
                if (moduleName != undefined && moduleName != '') {
                    msgFozenConfirm = "确认冻结此" + moduleName + "吗？";
                }
                if (alertMsgFlg) {
                    if (confirmx(msgFozenConfirm, tempHref)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
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
                if(moduleName==''){
                    alertx("请选择要取消冻结的行");
                }else{
                    alertx("请选择要取消冻结的"+moduleName);
                }
                return false;
            } else {
                var elemId = $("#selectElemId").val();
                var tempHref = $("#btuElemFozen").attr("href") + elemId + "&" + fieldName + "=" + unFrozen;
                var msgUnFozenConfirm = "确认要取消冻结此行吗？";
                if (moduleName != undefined && moduleName != '') {
                    msgUnFozenConfirm = "确认取消冻结此" + moduleName + "吗？";
                }
                if (alertMsgFlg) {
                    if (confirmx(msgUnFozenConfirm, tempHref)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }
    }
}

// 已经被选中的行
var selectedElem = null;
function selectElem(elem) {
    if (selectedElem) {
        // 撤销选中
        if (selectedElem === elem) {
            $("#selectElemId").val("");
            $(elem).attr("class", "listRowUnSelected");
            selectedElem = null;
            return;
        }
    }

    $("#selectElemId").val($(elem).children().find("#elemId").val());
    $.each($(elem).parent().children(), function(index, value, array) {
        $(value).attr("class", "listRowUnSelected");
    });
    $(elem).attr("class", "listRowSelected");
    selectedElem = elem;
}