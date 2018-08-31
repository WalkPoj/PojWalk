$(function(){
	//最后一个没有边框
	$("nav dl:last,.shouyi dl:last").css("border","0");
	//.bankList li 银行选择	
	$(".bankList li").click(function(){
		$(this).addClass("banSty").siblings("li").removeClass("banSty");
		})	
	//添加银行卡	
	$(".tianjiayinhang").click(function(){
		$(".addYinhang").fadeIn();
		})	
	$(".glyphicon-remove").click(function(){
		$(".addYinhang").fadeOut();
		})	
	
	//站内信息 点击展开收起效果	
	$(".znxx dt em").click(function(){
		$(this).find("b").toggle();
		$(this).find("i").toggle();
		$(this).parents("dt").next("dd").toggle().siblings("dd").hide();
		})	
	//安全设置 手机号码下一步	
	$(".sjyz-one-next").click(function(){
		$(this).parents("form").next(".sjyz-two").show();
		$(this).parents(".sjyz-one").hide();
		$(this).parents("dd").find(".Step li:eq(1)").addClass("stepCur");
		})	
	$(".sjyz-two-next").click(function(){
		$(this).parents(".sjyz-two").hide();
		$(this).parents("form").prev(".sjyz-one").hide();
		$(this).parents("dd").find(".sjyz-ok").show();
		$(this).parents("dd").find(".Step li:eq(2)").addClass("stepCur");
		});
	// 安全设置 点击展开收起效果	
	$(".Safety dt em").click(function(){
		$(this).parents("dt").next("dd").toggle().siblings("dd").hide();
		});
    <!--键盘按下-->
    $("#jbj").keyup(function () {
        var input = $("input[name=psw],select");
        for(var i=0;i<input.length;i++){
            switch (i){
                case 0:
                    if (input.eq(i).val() != "") {
                        $(".title").text("")
                    }
                    break;
                case 1:
                    if (input.eq(i).val() != "") {
                        $(".title").text("")
                    }
                    break;
                case 2:
                    if (input.eq(i).val() != "") {
                        $(".title").text("")
                    }
                    break;
            }
        }
        return true;
    });
    <!--密码修改验证-->
    $("#jbj").submit(function () {
        var input = $("input[name=psw],select");
        for(var i=0;i<input.length;i++){
            switch (i){
                case 0:
                    if (input.eq(i).val() == "") {
                        alert(input.eq(i).val());
                        // $.messager.alert("消息提醒", "验证码错误!", "warning");
                        $(".title").text("请填写用户名!").css("color","red");
                        $("input[name=psw],select").eq(i).focus();
                        return false;
                    }
                    break;
                case 1:
                    if (input.eq(i).val() != "") {
                        $(".psw").text("请填写新密码!").css("color","red");
                        $("input[name=psw],select").eq(i).focus();
                        return false;
                    }
                    break;
                case 2:
                    if (input.eq(i).val() != "") {
                        $(".npsw").text("请再次填写新密码!").css("color","red");
                        $("input[name=psw],select").eq(i).focus();
                        return false;
                    }else if(input.eq(i).val() != input.eq(1).val()){
                        $(".npsw").text("两次输入的密码不一致!").css("color","red");
                        $("input[name=psw],select").eq(i).focus();
                        return false;
					}
                    break;
            }
        }
        return true;
    });
	});
	
	
function next()
{
document.write("<a href='javascript:history.go(-1)'>后退</a>   &nbsp;&nbsp;<a href='javascript:history.go(0)'>刷新</a>&nbsp;&nbsp;<a href='javascript:history.go(1)'>前进</a>   <form>   <input name='ht' type='button' onclick='javascript:history.go(-1)' value='后退' />   <input name='sx' type='button' onclick='javascript:history.go(0)' value='刷新' />   <input name='qj' type='button' onclick='javascript:history.go(1)' value='前进' />   </form> ");
}	