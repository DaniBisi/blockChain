<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.LinkedList"%>
<%@ page import="magenta.blockChain.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Demo App - query blockChain</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />

</head>
<body>
	<div id="container">
		<div id="header">
			<h1>
				<a href="/">Demo App - query blockChain</a>
			</h1>
			<div class="clear"></div>
		</div>
		<div id="nav">
			<ul>
				<li class="start"><a href="index.jsp">Home</a></li>
				<li><a href="index.jsp?module=Example">Examples</a></li>
				<li><a href="index.jsp?module=Example">Commands</a></li>
			</ul>
		</div>
		<div id="body">
			<div id="content">
				<div class="responce">
				<b><%= request.getParameter("module") %></b>!
				
					<%
						String module = "indexForm.jsp";
						 if(request.getAttribute("module")!=null){
							module = (String)request.getAttribute("module");
						} 
						 else if(request.getParameter("module")!=null){
							 module = (String)request.getParameter("module");
							 module = module +".jsp";
						 }
					%>
					<jsp:include page='<%=module %>' flush="true"/>
				</div>

			</div>


			<div id="footer">
				
			</div>
		</div>
</body>
</html>