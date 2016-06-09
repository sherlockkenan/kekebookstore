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
 
.button {  
    display: inline-block;  
    zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */  
    *display: inline;  
    vertical-align: baseline;  
    margin: 0 2px;  
    outline: none;  
    cursor: pointer;  
    text-align: center;  
    text-decoration: none;  
    font: 14px/100% Arial, Helvetica, sans-serif;  
    padding: .5em 2em .55em;  
    text-shadow: 0 1px 1px rgba(0,0,0,.3);  
    -webkit-border-radius: .5em;   
    -moz-border-radius: .5em;  
    border-radius: .5em;  
    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);  
    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);  
    box-shadow: 0 1px 2px rgba(0,0,0,.2);  
}  
.button:hover {  
    text-decoration: none;  
}  
.button:active {  
    position: relative;  
    top: 1px;  
}  
  
.bigrounded {  
    -webkit-border-radius: 2em;  
    -moz-border-radius: 2em;  
    border-radius: 2em;  
}  
.medium {  
    font-size: 12px;  
    padding: .4em 1.5em .42em;  
}  
.small {  
    font-size: 11px;  
    padding: .2em 1em .275em;  
}
/* gray */  
.gray {  
    color: #e9e9e9;  
    border: solid 1px #555;  
    background: #6e6e6e;  
    background: -webkit-gradient(linear, left top, left bottom, from(#888), to(#575757));  
    background: -moz-linear-gradient(top,  #888,  #575757);  
    filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#888888', endColorstr='#575757');  
}  
.gray:hover {  
    background: #616161;  
    background: -webkit-gradient(linear, left top, left bottom, from(#757575), to(#4b4b4b));  
    background: -moz-linear-gradient(top,  #757575,  #4b4b4b);  
    filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#757575', endColorstr='#4b4b4b');  
}  
.gray:active {  
    color: #afafaf;  
    background: -webkit-gradient(linear, left top, left bottom, from(#575757), to(#888));  
    background: -moz-linear-gradient(top,  #575757,  #888);  
    filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#575757', endColorstr='#888888');  
}  
</style>

<body >
	<c:if test="${user == null }">
  		login first
  </c:if>
	<c:if test="${user != null }">
	<div id="content" style="position:relative;left: 600px">
		<IMG height=99 width=136
			src="${pageContext.request.contextPath }/client/profile_getimage">
		<br></br>
	
		<ul style="position:relative;left: -120px;" >
			<li style="margin-bottom: 50px">name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${user.username }</li>

			<li style="margin-bottom: 50px">password:&nbsp;&nbsp;${user.password }</li>

			<li style="margin-bottom: 50px">phone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${user.phone }</li>

			<li style="margin-bottom: 50px">email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${user.email }</li>

			<li style="margin-bottom: 50px">address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${user.address }</li>

		</ul>
		
		<a  href="${pageContext.request.contextPath }/client/profileedit.jsp" class="button gray">Edit</a>
	</div>
	</c:if>
</body>
</html>
