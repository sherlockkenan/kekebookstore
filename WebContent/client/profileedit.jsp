<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>add photo and </title>
  </head>
  <div id="header">
            <%@include file="/client/head.jsp" %>     
   </div>
  <body  style="text-align:center;">
   <c:if test="${user == null }">
  		login first
  </c:if>
  <c:if test="${user != null }">
  <form action="${pageContext.request.contextPath }/client/profile_imageedit" method="post" enctype="multipart/form-data" align="center">
    <table frame="border" width="50%" align="center" cellspacing="10" >
    	<tr>
    		<td>添加图片</td>
    	</tr>
    	<tr>
    	    <td>
    			<input type="file" name="upload">
    		</td>
    	</tr>
    	<tr></tr>
    	<tr>
    		<td>
    			<input type="submit" value="提交">
    		</td>
    	</tr>
    </table>
  </form>
  <br></br>
  <br></br>
  <form action="${pageContext.request.contextPath }/client/profile_useredit" method="post" enctype="multipart/form-data" align="center">
    <table frame="border" width="50%" align="center">
    	
    	<tr>
    		<td>password</td>
    		<td>
    			<input type="text" name="password">
    		</td>
    	</tr>
    	
    	<tr>
    		<td>phone</td>
    		<td>
    			<input type="text" name="phone">
    		</td>
    	</tr>
    	<tr>
    		<td>email</td>
    		<td>
    			<input type="text" name="email">
    		</td>
    	</tr>
    	<tr>
    		<td>address</td>
    		<td>
    			<input type="text" name="address">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<input type="reset" value="清空">
    		</td>
    		<td>
    			<input type="submit" value="提交">
    		</td>
    	</tr>
    </table>
    </form>
    </c:if>
  </body>
</html>
