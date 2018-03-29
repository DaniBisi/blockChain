<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.LinkedList"%>
<%@ page import="magenta.blockChain.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
				<h2>Transaction number:  <%=request.getAttribute("txId")%></h2>
				<p>User: <%=request.getAttribute("user")%></p>

				<div class="responce">
					<%
						LinkedList<Car> cars = (LinkedList<Car>) request.getAttribute("records");
						String htmlDiv = "";
						for (Car car : cars) {
							htmlDiv = htmlDiv + "<ul>" + "<li>" + car.getKey() + "</li>";
							htmlDiv = htmlDiv + "<li>" + car.getColor() + "</li>";
							htmlDiv = htmlDiv + "<li>" + car.getMake() + "</li>";
							htmlDiv = htmlDiv + "<li>" + car.getModel() + "</li>";
							htmlDiv = htmlDiv + "<li>" + car.getOwner() + "</li>";
							htmlDiv = htmlDiv + "</ul>";
						};
						out.println(htmlDiv);
					%>
				</div>

			</div>


		