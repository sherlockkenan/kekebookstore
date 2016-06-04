<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>Manage Platform</title>
  </head>
  
  <frameset rows="50px,*" frameborder=no >
  	<frame src="${pageContext.request.contextPath }/admin/head.jsp" name="head">
  	<frameset cols="15%,*" frameborder=no>
  		<frame src="${pageContext.request.contextPath }/admin/leftnav.jsp" name="left">
  		<frame src="${pageContext.request.contextPath }/admin/right.jsp" name="right">
  	</frameset>
  </frameset>
</html>
