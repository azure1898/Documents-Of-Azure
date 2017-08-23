/**
 * weui.js
 */
 String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	if(arguments[0] instanceof Array){
		arguments = arguments[0];
	}
	for (var s = this, i = 0; i < arguments.length; i++)
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
	return s;
}
var weui = {
	alert : function(msg, title, callback) {
		title = title ? title : "提示信息";
		var alertHtml = '<div class="weui_dialog_alert" style="display: none;">';
		alertHtml += '<div class="weui_mask"></div>';
		alertHtml += '<div class="weui_dialog">';
		alertHtml += '    <div class="weui_dialog_hd"><strong class="weui_dialog_title">'
				+ title + '</strong></div>';
		alertHtml += '    <div class="weui_dialog_bd"></div>';
		alertHtml += '    <div class="weui_dialog_ft">';
		alertHtml += '      <a href="javascript:;" class="weui_btn_dialog primary">确定</a>';
		alertHtml += '  </div>';
		alertHtml += ' </div>';
		alertHtml += '</div>';
		if ($(".weui_dialog_alert").length > 0) {
			$(".weui_dialog_alert .weui_dialog_bd").empty();
		} else {
			$("body").append($(alertHtml));
		}
		var weui_alert = $(".weui_dialog_alert");
		weui_alert.show();
		weui_alert.find(".weui_dialog_bd").html(msg);
		weui_alert.find('.weui_btn_dialog').off("click").on('click',
				function() {
					weui_alert.hide();
					if (callback) {
						callback();
					}
				});
	},
	confirm : function(msg, title, callback) {
		var confirmHtml = '<div class="weui_dialog_confirm">'
				+ '<div class="weui_mask"></div>'
				+ '<div class="weui_dialog">'
				+ '<div class="weui_dialog_hd"><strong class="weui_dialog_title"></strong></div>'
				+ '<div class="weui_dialog_bd"></div>'
				+ '<div class="weui_dialog_ft">'
				+ '<a href="javascript:;" class="weui_btn_dialog default">取消</a>'
				+ '<a href="javascript:;" class="weui_btn_dialog primary">确定</a>'
				+ '</div>' + '</div>' + '</div>';
		if ($(".weui_dialog_confirm").length > 0) {
			$(".weui_dialog_confirm .weui_dialog_bd").empty();
		} else {
			$("body").append($(confirmHtml));
		}
		var weui_confirm = $(".weui_dialog_confirm");
		weui_confirm.show();
		weui_confirm.find(".weui_dialog_title").text(
				title = (title ? title : "确认提示"));
		weui_confirm.find(".weui_dialog_bd").html(msg);
		weui_confirm.find('.primary').off("click").on('click', function() {
			weui_confirm.hide();
			if (callback) {
				callback(true);
			}
		});
		weui_confirm.find('.default').off("click").on('click', function() {
			weui_confirm.hide();
			if (callback) {
				callback(false);
			}
		});
	},
	destroyFlag : false,
	Loading : {
		show : function(msg) {
			var loadingHtml = '<div  class="weui_loading_toast" style="display: none;">'
					+ '<div class="weui_mask_transparent"></div>'
					+ '<div class="weui_toast">'
					+ '<div class="weui_loading">'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_0"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_1"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_2"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_3"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_4"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_5"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_6"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_7"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_8"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_9"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_10"></div>'
					+ ' <div class="weui_loading_leaf weui_loading_leaf_11"></div>'
					+ '</div>'
					+ '<p class="weui_toast_content"></p>'
					+ '</div>' + '</div>';
			var text = (msg ? msg : "处理中..");
			if ($(".weui_loading_toast").length > 0) {
				$(".weui_loading_toast .weui_toast_content").empty();
			} else {
				$("body").append($(loadingHtml));
			}
			var weui_loading = $(".weui_loading_toast");
			weui_loading.find(".weui_toast_content").text(text).end().show();
			var width = weui_loading.find(".weui_toast").width();
			var w = $(document).width();
			weui_loading.find(".weui_toast").css("left", (w - width) / 2);
		},
		hide : function() {
			if (!weui.destroyFlag)
				$(".weui_loading_toast").hide();
		},
		msg : function(msg, time, cls, icon) {
			var successHtml = '<div class="' + cls
					+ '" style="display: none;">'
					+ '<div class="weui_mask_transparent"></div>'
					+ '<div class="weui_toast">' + '<i class="' + icon
					+ '"></i>' + '<p class="weui_toast_content"></p>'
					+ '</div>' + '</div>';
			if ($("." + cls).length < 1) {
				$("body").append($(successHtml));
				$(".weui_mask_transparent").off("click").on("click",
						function() {
							$("." + cls).hide();
						})
			}
			var t = 3000;
			if (time) {
				t = time;
			}
			var weui_success = $("." + cls).find(".weui_toast_content").text(
					msg).end();
			weui_success.show();
			var width = weui_success.find(".weui_toast").width();
			var w = $(document).width();
			weui_success.find(".weui_toast").css("left", (w - width) / 2 - 5);
			setTimeout(function() {
				weui_success.hide();
			}, t);
		},
		success : function(time) {
			this.msg("操作成功", time, "weui_success_toast", "weui_icon_toast");
		},
		info : function(msg, time) {
			this.msg(msg, time, "weui_info_toast", "weui_icon_info");
		},
		error : function(msg, time) {
			this.msg(msg, time, "weui_error_toast", "weui_icon_warn");
		}
	},
	actionSheet:{
		/**
		 * 生成弹出选项
		 * @param cells 选项列表集合 如:[{name:"选项1",value:"1"}];
		 * @param change 选中事件回调函数,通过返回true可以改变默认选中隐藏操作
		 * @param sington 启用单列模式
		 * @returns actionSheet本身
		 */
		create:function(cells,change,sington){
			var item = '<div class="weui_actionsheet_cell" data-value="{1}">{0}</div>';
			var sheet_div = '<div id="actionSheet_wrap_{0}">'+
								'<div class="weui_mask_transition" id="mask"></div>'+
								'<div class="weui_actionsheet" id="weui_actionsheet">'+
									'<div class="weui_actionsheet_menu">'+
									'</div>'+
									'<div class="weui_actionsheet_action">'+
										'<div class="weui_actionsheet_cell" id="actionsheet_cancel">取消</div>'+
									'</div>'+
								'</div>'+
							'</div>';
			var index = $("div[id^='actionSheet_wrap_']").length;
			var iscreate = true;
			if(sington){
				iscreate=(index==0);
				index = 0;
			}
			var _self=this;
			var sheet=null;
			if(iscreate){
				$("body").append(sheet_div.format(index));
				sheet = $("#actionSheet_wrap_"+index);
				sheet.find("#actionsheet_cancel").on("click",function(){
					_self.hide();
				});
			}
			sheet = $("#actionSheet_wrap_"+index);
			sheet.find(".weui_actionsheet_menu").empty();
			var array=[];
			$.each(cells,function(){
				if(!this.name || !this.value){
					throw new Error("cells array style [{name:'name',value:'value'}]! ");
				}
				array.push(item.format(this.name,this.value));
			});
			sheet.find(".weui_actionsheet_menu")
			.append(array.join("")).find(".weui_actionsheet_cell").on("click",function(){
				var cell = $(this);
				var is_hide = change(cell.data("value"),cell.text(),cell);
				if(!is_hide){
					_self.hide();
				}
			});
			this._index = index;
			return _self;
		},
		_index:0,
		show:function(){
			var _self=this;
			$("#actionSheet_wrap_"+this._index).find("#mask")
			.addClass("weui_fade_toggle").show().one("click",function(){
				_self.hide();
			})
			.end()
			.find("#weui_actionsheet")
			.addClass("weui_actionsheet_toggle");
			return this;
		},
		hide:function(){
			$("#actionSheet_wrap_"+this._index).find("#mask")
			.removeClass("weui_fade_toggle").hide()
			.end()
			.find("#weui_actionsheet")
			.removeClass("weui_actionsheet_toggle");
			return this;
		},
		destroy:function(){
			$("#actionSheet_wrap_"+this._index).remove();
		}
	},
	destroy : function() {
		$(
				".weui_dialog_alert,.weui_dialog_confirm,.weui_loading_toast,.weui_success_toast,.weui_info_toast,.weui_error_toast,.weui_actionsheet_cell")
				.remove();
		weui.destroyFlag = true;
	}
};