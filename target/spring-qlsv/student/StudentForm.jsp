<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add New svmh</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Please input some svmh information</h2>
		
		<c:choose>
			<c:when test="${id==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				<tr><td><form:input path="id" type="hidden"/></td></tr>
				<tr>
					<td><form:label path="name">Name</form:label></td>
					<td><form:input path="name"/></td>
					<td><form:errors path="name"></form:errors>
				</tr>
				<tr>
					<td><form:label path="age">Age</form:label></td>
					<td><form:input path="age" type="number"/></td>
					<td><form:errors path="age"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="address">Address</form:label></td>
					<td><form:input path="address"/></td>
				</tr>
				<tr>
					<td><form:label path="point">Point</form:label></td>
					<td><form:input path="point" type="number" step="any"/></td>
					<td><form:errors path="point"></form:errors>
				</tr>
				
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>