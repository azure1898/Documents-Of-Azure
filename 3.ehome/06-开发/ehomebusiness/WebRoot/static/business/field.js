$(document).ready(function() {

    jQuery.validator.addMethod("fieldname", function (value, element) {
        var patrn = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,10}$/;
        return this.optional(element) || (patrn.test(value));
    }, "请输入中文，英文或数字");
});