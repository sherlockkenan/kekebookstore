
<html>
<head>

	<title>Keke Bookstore </title>

	<script src="../jquery-easyui-1.4.5/jquery.min.js"></script>

	<script src="../jquery-easyui-1.4.5/bootstrap.min.js"></script>


	<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>


<div id="header">
            <%@include file="/client/head.jsp" %>     
   </div>

<div class="container">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3>Have an Account?</h3>
	</div>
	<div class="modal-body">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#login" data-toggle="tab">Login</a></li>
			<li><a href="#create" data-toggle="tab">Create Account</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active in" id="login">
				<form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="POST">
					<fieldset>
						<div id="legend">
							<legend class="">Login</legend>
						</div>
						<div class="control-group">
							<!-- Username -->
							<label class="control-label" for="username">Username</label>

							<div class="controls">
								<input type="text" id="username" name="username" placeholder=""
								       class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<!-- Password-->
							<label class="control-label" for="password">Password</label>

							<div class="controls">
								<input type="password" id="password" name="password" placeholder=""
								       class="input-xlarge">
							</div>
						</div>


						<div class="control-group">
							<!-- Button -->
							<div class="controls">
								<button class="btn btn-success">Login</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="tab-pane fade" id="create">
				<form id="tab" action="${pageContext.request.contextPath}/register" method="POST">
					<fieldset>
						<div id="legend">
							<legend class="">Sign up</legend>
						</div>
						<div class="container">
							<div class="control-group">
								<!-- Username -->
								<label class="control-label" for="username">Username</label>

								<div class="controls">
									<input type="text" id="username" name="username" placeholder=""
									       class="input-xlarge">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="mpassword">Password</label>

								<div class="controls">
									<input type="password" id="password" name="password" placeholder=""
									       class="input-xlarge">
								</div>
							</div>
							<div class="control-group">

								<label class="control-label" for="cpassword">Confirm password</label>

								<div class="controls">
									<input type="password" id="cpassword" name="cpassword" placeholder=""
									       class="input-xlarge">
								</div>
							</div>
							<div class="control-group">

								<label class="control-label" for="phone">Phone number</label>

								<div class="controls">
									<input type="phone" id="phone" name="phone" placeholder=""
									       class="input-xlarge">
								</div>
							</div>
							
							<div class="control-group">

								<label class="control-label" for="email">Email</label>

								<div class="controls">
									<input type="email" id="email" name="email" placeholder=""
									       class="input-xlarge">
								</div>
							</div>
							<div class="control-group">

								<label class="control-label" for="address">Address</label>

								<div class="controls">
									<input type="address" id="address" name="address" placeholder=""
									       class="input-xlarge">
								</div>
							</div>

						</div>
						<div>
							<button class="btn btn-primary">Create Account</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>