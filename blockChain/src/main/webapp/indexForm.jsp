<p class="article-meta">Login form</p>
<p class="lead">Use the form below to login and see cars in
	blockChain ledger.</p>
<form method="post" action="BlockChainServlet">
	<h2>Name:</h2>
	<input type="text" id="user" name="userName" />
	<h2>Password:</h2>
	<input type="password" id="passwd" name="password" />
	<h2>Query:</h2>
	<input type="text" id="queryType" name="query" />
	<h2>Arguments:</h2>
	<input type="text" id="arg" name="args" /> <input type="submit"
		id="login" value="Login" />

</form>


<div id="content"><div th:fragment="response"><div class="responce">
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


<form method="post" action="#" th:action="@{/login}"
							th:object="${LoginCollect}">
							<h2>Name:</h2>
							<input type="text" id="user" th:field="*{userName}" />
							<h2>Password:</h2>
							<input type="password" id="passwd" th:field="*{password}" />
							<h2>Query:</h2>
							<input type="text" id="queryType" th:field="*{query}" />
							<h2>Arguments:</h2>
							<input type="text" id="arg" th:field="*{args}" /> </br>
							</br>
							</br>
							<input type="submit" id="login" value="Login" />

						</form>
