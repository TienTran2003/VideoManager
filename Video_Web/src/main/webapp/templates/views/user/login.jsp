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
	<div class="container-fluid tm-mt-60">
		<div class="row tm-mb-50">
			<div class="col-lg-12 col-12 mb-5">
			 <h2 class="login tm-text-primary mb-5">Login</h2>
				<form id="login-form" action="login" method="post"
					class="tm-contact-form mx-auto">
					<div class="form-group">
						<input type="text" name="username" class="form-control rounded-0"
							placeholder="Username" required />
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-control rounded-0"
							placeholder="Password" required />
					</div>
					<div class="form-group tm-text-right">
						<button type="submit" class="btn btn-primary">Send</button>
					</div>
				</form>
			</div>
		
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->

	<%@ include file="/common/footer.jsp"%>
</body>
</html>