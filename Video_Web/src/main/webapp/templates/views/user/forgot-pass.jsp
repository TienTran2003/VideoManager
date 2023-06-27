<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<%@ include file="/common/head.jsp"%>
<style type="text/css">
.btn1 {
	background-color: #019edb;
	width: 60px;
	text-align: center;
	border: none;
}
.login{
	text-align: center;
}

</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="container-fluid col-lg-4 col-4  tm-mt-60">
		<div class="row tm-mb-50">
			<div class="mb-5">
			 <h2 class="login tm-text-primary mb-5">Forgot Password</h2>
					<div class="form-group">
						<input type="email" id="email" name="email" class="form-control rounded-0"
							placeholder="Email" required />
					</div>
					<div class="form-group tm-text-right">
						<button id="sendBtn" type="submit" class="btn btn-primary" style="float: right;">Send</button>
					</div>
					<h5 style="color: red" id="messageReturn"></h5>
			</div>
		
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->

	<%@ include file="/common/footer.jsp"%>
</body>
<script>
	$('#sendBtn').click(function(){
		$('#messageReturn').text('');
		var email = $('#email').val();
		var formData = {'email' : email};
		$.ajax({
			url: 'forgotPass',
			type: 'POST',
			data: formData
		}).then(function(data){
			$('#messageReturn').text('Password has been reset, please check your email!!!');
			setTimeout(function(){
				window.location.href = 'http://localhost:8080/PS24803_TranVanTien_ASM/index';
			}, 5*1000);
		}).fail(function(error) {
			$('#messageReturn').text('Your information is not correct, try again!!!');
		});
	});
</script>
</html>