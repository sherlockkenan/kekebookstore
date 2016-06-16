<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Profile</title>
</head>
<div id="header">
	<%@include file="/client/head.jsp"%>
</div>
<style type="text/css">

element.style {
}
.three-fourths {
    width: 75%;
}
.column {

    padding-right: 50px;
    padding-left: 200px;
}
* {
    box-sizing: border-box;
}
user agent stylesheet
div {
    display: block;
}
Inherited from body.logged-in.env-production.windows.page-account
body {
    min-width: 1020px;
    word-wrap: break-word;
}
body {
    font: 13px/1.4 Helvetica, arial, nimbussansl, liberationsans, freesans, clean, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    color: #333;
    background-color: #fff;
}
Inherited from html.is-copy-enabled.is-u2f-enabled
html {
    font-family: sans-serif;
    -ms-text-size-adjust: 100%;
    -webkit-text-size-adjust: 100%;
    
}
input.file{
    z-index:99;
	border:1px solid #ccc;
	padding:2px;
	width:150px;
	vertical-align:middle;
	color:#999;
	display: none;
}

label {
    font-size: 13px;
    font-weight: bold;
}


</style>

<body>
	<c:if test="${user == null }">
  		login first
  </c:if>
  <c:if test="${user != null }">
	<div class="column three-fourths">
		<!-- Public Profile -->
		<div class="boxed-group">
			<h3 style="text-align:center">User profile</h3>
			<br></br>
			<div class="boxed-group-inner clearfix">
				<!-- </textarea> -->
				<!-- '"` -->
				<form accept-charset="UTF-8" action="${pageContext.request.contextPath }/client/profile_profileedit"
					 method="post" enctype="multipart/form-data">
				

					<div class="column two-thirds">
						<dl class="form-group edit-profile-avatar">
							<dt>
								<label for="upload-profile-picture">Profile picture</label>
							</dt>
							<dd class="avatar-upload-container clearfix">
								<img alt="@sherlockkenan" class="avatar left" height="100"
									src="${pageContext.request.contextPath }/client/profile_getimage"
									width="100">
								<div class="avatar-upload">
									<a href="#" class="btn button-change-avatar"> 
									<label for="upload-profile-picture"> Upload new picture
										 <input id="upload-profile-picture" class="file" name="upload" type="file">
									</label>
									</a>								
									
								</div>
								<!-- /.avatar-upload -->
							</dd>
						</dl>

						<dl class="form-group">
							<dt>
								<label for="user_profile_name">Name</label>
							</dt>
							<dd>
								<input class="form-control" id="user_profile_name"
									name="user_name" size="30" type="text" value="${user.username }">
							</dd>
						</dl>
						
						<dl class="form-group">
							<dt>
								<label for="user_profile_name">Password</label>
							</dt>
							<dd>
								<input class="form-control" id="user_profile_name"
									name="password" size="30" type="text" value="${user.password }">
							</dd>
						</dl>
						
						<dl class="form-group">
							<dt>
								<label for="user_profile_name">Phone</label>
							</dt>
							<dd>
								<input class="form-control" id="user_profile_name"
									name="phone" size="30" type="text" value="${user.phone }">
							</dd>
						</dl>
						
						<dl class="form-group">
							<dt>
								<label for="user_profile_name">Email</label>
							</dt>
							<dd>
								<input class="form-control" id="user_profile_name"
									name="email" size="30" type="text" value="${user.email }">
							</dd>
						</dl>
						
						<dl class="form-group">
							<dt>
								<label for="user_profile_name">Address</label>
							</dt>
							<dd>
								<input class="form-control" id="user_profile_name"
									name="address" size="30" type="text" value="${user.address }">
							</dd>
						</dl>
						
						<p>
							<button type="submit" class="btn btn-primary">Update profile</button>
						</p>
					</div>
				</form>
				
			</div>
		</div>



	</div>

	
	</c:if>
</body>
</html>
