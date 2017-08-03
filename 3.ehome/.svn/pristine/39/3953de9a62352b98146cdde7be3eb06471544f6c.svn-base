/**
 * 校验全体金额（整数位数可自定义，小数固定为1到2位）
 * @param validateValue 要校验的值（若为null直接返回false）
 * @param integerFigure 整数位数（int）
 * @param allowZeroFlag 是否允许为零（boolean）
 * @param allowEmptyFlag 是否允许为空字符串（boolean）
 * @return 是否校验通过（校验通过为true；不通过为false）
 */ 
function validateMoney(validateValue, integerFigure, allowZeroFlag, allowEmptyFlag) {
    // 1到?位正整数 或 1到?+2位正浮点数（1到2位小数）
    var moneyPatrn = "^[1-9]\\d{0,?}\\.\\d{1,2}|0\\.\\d{1,2}|[1-9]\\d{0,?}";
    if (validateValue == null) {
        return false;
    }
    if(integerFigure < 0){
        integerFigure = 0;
    }
    // 替换正则表达式中的整数最大位数
    moneyPatrn = moneyPatrn.replace(/\?/g, (integerFigure - 1));
    // 允许为零
    if (allowZeroFlag) {
        moneyPatrn = moneyPatrn + "|[0-9]";
    }
    moneyPatrn = moneyPatrn + "$";
    
    var regExpMoney = new RegExp(moneyPatrn);
    
    // empty判定
    if (allowEmptyFlag && validateValue == "") {
        return true;
    }
    
    // 正则表达式校验
    return regExpMoney.exec(validateValue) != null && validateValue == regExpMoney.exec(validateValue)[0];
}

/**
 * 校验电话号码（手机、固定电话）
 * @param phoneNumber 电话号码（手机、固定电话）
 * @return 是否校验通过（校验通过返回true；不通过或为空，返回false）
 */
function validatePhoneNumber(phoneNumber) {
    if (phoneNumber == undefined || phoneNumber == null || phoneNumber == "") {
        return false;
    }
    // 手机号码
    // 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
    // 联通号码段:130、131、132、136、185、186、145
    // 电信号码段:133、153、180、189
    var callPhonePatrn = "(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})";
    
    // 固定电话号码（可有分区号）
    // 例：0371-9876545、0371-69876545、010-9876545、010-69876545、9876545、69876545
    var landlinePhonePatrn = "(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)|(\\d{7,8}(-\\d{1,4})?)";
    
    var phonePatrn = "^" + callPhonePatrn + "|" + landlinePhonePatrn + "$";
    var regExpPhone = new RegExp(phonePatrn);
    
    // 正则表达式校验
    return regExpPhone.exec(phoneNumber) != null && phoneNumber == regExpPhone.exec(phoneNumber)[0];
}