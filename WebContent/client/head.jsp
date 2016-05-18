<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Case</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <script src="../jquery-easyui-1.4.5/jquery.min.js"></script>
  <script src="../jquery-easyui-1.4.5/bootstrap.min.js"></script>
</head>
<style>
   .navbar-brand {
      font-size: 20px;
    
   
  }
  .navbar {
  position: relative;
  min-height: 50px;
  margin-bottom: 20px;
  border: 1px solid transparent;
}
</style>
<body>

<nav class="navbar navbar-inverse" >
  <div class="container-fluid" >
    <div class="navbar-header">
       <a class="navbar-brand" href="${pageContext.request.contextPath }/client/index.jsp" target="body">Keke Bookstore</a>		
    </div>
    <ul class="nav navbar-nav">
      <li></li>
      <li><a href="${pageContext.request.contextPath }/client/index.jsp" target="body">Home</a></li>
	  
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <li><a href="${pageContext.request.contextPath }/client/cart.jsp" target="body">Cart</a></li>
	 <li><a href="${pageContext.request.contextPath }/client/order" target="body">Orders</a></li>
    <c:if test="${user!=null }">
       <li><a>Welcome:${user.username } </a></li>
       <li><a href="${pageContext.request.contextPath }/client/logout">Sign out</a></li>
	</c:if>
	 <c:if test="${user== null}">
	 <li><a href="${pageContext.request.contextPath }/client/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	 </c:if>
				
    </ul>
  </div>
</nav>


</body>
</html>
