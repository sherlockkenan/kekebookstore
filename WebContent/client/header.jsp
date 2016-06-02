<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

	<link rel="stylesheet" href="http://jeasyui.com/css/main.css" type="text/css">
	<link rel="stylesheet" href="http://jeasyui.com//css/kube.css" type="text/css">
</head>


<div id="header" class="group wrap header">
	<div class="content">
		<div class="navigation-toggle" data-tools="navigation-toggle" data-target="#navbar-1" style="display: none;">
			<span>EasyUI</span>
		</div>
		<div id="elogo" class="navbar navbar-left">
			<ul>
				<li>
					<a href="${pageContext.request.contextPath }/client/index.jsp" target="body">Keke Bookstore</a>
				</li>
			</ul>
		</div>
		<div id="navbar-1" class="navbar navbar-right">
			<ul>
				<li><a href="${pageContext.request.contextPath }/client/index.jsp" target="body">Home</a></li>
				<li><a href="${pageContext.request.contextPath }/client/cart.jsp" target="body">Cart</a></li>
				<li><a href="${pageContext.request.contextPath }/client/order" target="body">Orders</a></li>
				<li><c:if test="${user!=null }">
	    	                  <li><a>Welcome:${user.username } </a></li>
	    	                  <li><a href="${pageContext.request.contextPath }/logout">Sign out</a></li>
	                </c:if>
	                <c:if test="${user== null}">
	                <li><a href="${pageContext.request.contextPath }/client/login.jsp">Sign in</a></li>
	                </c:if>
				</li>

			</ul>
		</div>
		<div style="clear:both"></div>
	</div>

</div>
