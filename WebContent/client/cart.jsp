<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
<body style="text-align:center;">
    <div id="head">
            <%@include file="/client/head.jsp" %>       
    </div>

  	
  	
  	<h2>CartList</h2>
    <table width="40%" border="1" align="center" style="text-align: center;">
    	<tr>
    		<td>Name</td>
    		<td>Author</td>
    		<td>Price</td>
    		<td>Quantity</td>
    		<td>Subtotal</td>
    		<td>Manager</td>
    	</tr>
    	<c:forEach var="me" items="${cart.map }">
    		<tr>
    			<td>${me.value.book.name }</td>
	    		<td>${me.value.book.author }</td>
	    		<td>${me.value.book.price }</td>
	    		<td>${me.value.quantity }</td>
	    		<td>${me.value.price }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/client/addcart?method=delete&bookid=${me.value.book.id}">Delete</a>
	    		</td>
    		</tr>
    	</c:forEach>
    	
    	<tr>
    		<td colspan="1">Total</td>
    		<td colspan="5">${cart.price }</td>
    	</tr>
	</table>
	<a href="${pageContext.request.contextPath }/client/createorder">Buy</a>

   </body>

</body>
</html>