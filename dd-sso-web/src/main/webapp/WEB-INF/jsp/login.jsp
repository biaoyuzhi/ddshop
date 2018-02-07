<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>登录天天</title>
<link rel="stylesheet" type="text/css" href="css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="css/headerfooterindex.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />

<!-- sweetalert -->
<link rel="stylesheet" type="text/css" href="js/sweetalert/sweetalert.css">
<style>
	.login_main li label.error{
		color: red;
		text-align: left;
    	width: 200px;
	}
</style>
</head>
<body>
	<!-- header -->
	<div class="header">
		<a href="http://www.ttshop.com"><img src="images/logo.png" border="0"><span>欢迎登录</span></a>
	</div>
	<!-- login_main -->
	<div class="login_main clear">
		<div class="pic">
			<a href="http://www.ttshop.com/html/activity/1472720729.html" target="_blank"><img src="images/06f42c372620f92b40da77a8b23cdf7f.png"></a>
		</div>
		<!-- login -->
		<div class="login">
			<div class="login_header">
				<span>您还未登录</span> <a href="register">免费注册</a>
			</div>

			<div class="login_box clear">
				<ul class="loginnav">
					<li class="curr" mark="sfbest"><em></em>优选账号</li>
				</ul>

				<div class="logincon">
					<ul>
						<form id="formlogin" method="post">
							<li>
								<span class="title">手机/用户名<font style="padding-left: 20px" class="error_line" color="#ff0000"></font></span>
								<span class="border" style="position: relative"> 
									<input class="new-input1 new-color" tabindex="1" id="username" name="username" size="25" htmlescape="true" title="请输入用户名"> 
									<em class="icon1" id="usernamelogo"></em> 
								</span>
							</li>

							<li class="m-t12"><span class="title">登录密码</span> 
								<span class="border" style="position: relative"> 
									<input id="password" name="password" tabindex="2" type="password"> 
									<em class="icon2" id="passwordlogo"></em> 
								</span> 
							</li>
							<div class="clear"></div>
							<li>
								<div style="width: 65px; margin-left: 260px;">
									<a href="https://passport.ttshop.com/reg/findpass/?returnUrl=http://www.ttshop.com" class="forget-passWord">忘记密码?</a>
								</div>
							</li>
							<li class="m-t5" id="btn_sub">
								<button id="login_sub" class="login_btn" style="border:0;width:100%">登录</button>
							</li>
						</form>
					</ul>
				</div>


				<ul class="blink">
					<li class="p-f10">
						<h2 class="h2">合作网站账户登录：</h2>
						<div>
							<a
								href="http://api2.ttshop.com/unionlogin/qq/oauth/qq_login.php?returnUrl=http://www.ttshop.com/"
								class="link">QQ</a>&nbsp;|&nbsp; <a
								href="https://api.weibo.com/oauth2/authorize?client_id=1800908332&amp;redirect_uri=https%3A%2F%2Fpassport.ttshop.com%2Fcallback%2Fsina&amp;response_type=code&amp;state=&amp;display=?returnUrl=http://www.ttshop.com/"
								class="link">新浪微博</a> &nbsp;|&nbsp; <a
								href="https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801198099&amp;redirect_uri=https%3A%2F%2Fpassport.ttshop.com%2Fcallback%2Fqq&amp;response_type=code&amp;type=?returnUrl=http://www.ttshop.com/"
								class="link">腾讯微博</a> &nbsp;|&nbsp; <a
								href="http://api2.ttshop.com/unionlogin/alipay.php?returnUrl=http://www.ttshop.com/"
								class="link">支付宝</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!-- /login -->
	</div>
	<!-- /login_main -->
	<div class="footer">
		<span> <a href="http://www.ttshop.com/www/379/5109.html"
			rel="nofollow" class="footerlink1">关于我们</a> | <a
			href="http://www.ttshop.com/www/380/5116.html" rel="nofollow"
			class="footerlink1">联系我们</a> | <a
			href="http://www.ttshop.com/www/381/5117.html" rel="nofollow"
			class="footerlink1">招聘人才</a> | <a
			href="http://www.ttshop.com/www/330/2705.html" rel="nofollow"
			class="footerlink1">友情链接</a> | <a
			href="http://supplier.ttshop.com/supplierApply" rel="nofollow"
			class="footerlink1">供应商申请</a>
		</span>
		<p>
			北京天天电子商务有限公司<br> 北京市公安局顺义分局备案11011302000890号|<a
				href="http://www.miibeian.gov.cn" target="_blank" rel="nofollow"
				class="footerlink1">京ICP备12011349号</a>|<a
				href="http://www.ttshop.com/www/174/461.html" target="_blank"
				rel="nofollow" class="footerlink1">企业营业执照</a><br> Copyright©
					天天商城 ttshop.com 版权所有<br>
		</p>
	</div>
	<!-- /footer -->
	
	
		
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- jquery validate -->
	<script type="text/javascript" src="js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript" src="js/jquery-validation/jquery.form.js"></script>
	
	<!-- sweetalert -->
	<script type="text/javascript" src="js/sweetalert/sweetalert.min.js"></script>

	<script>
	
	//改变默认的错误信息输出位置
	$.validator.setDefaults({
		errorPlacement:function(error,element) {  
			error.appendTo(element.parent().parent());
	   }
	})
	
	// 在键盘按下并释放及提交后验证提交表单
	$("#formlogin").validate({
	    rules: {
	    	username: 'required',
	    	password: 'required'
	    } ,
	    messages: {
	    	username: '请输入用户名',
	    	password: '请输入密码'
	    },
	    submitHandler:function(form){
	    	$(form).ajaxSubmit({
	    		type:'post',
	    		url: 'user/login',
		 		success: function(data){
		 			var redirectUrl = "${redirect}";
		 			if(data.success){
		 				swal({
		 					
		 					  title: '提示',
		 					  text: data.message,
		 					  confirmButtonText: "确定"
		 					},
		 					function(){
		 						if (redirectUrl == "") {
									location.href = "http://localhost:8081/shop";
								} else {
									
									location.href = redirectUrl;
								}
		 					});
		 			}else{
		 				swal(data.message);
		 			}
		 		}
	    	});  
        }    
	});
	
	</script>
</html>