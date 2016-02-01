<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Chuyen Khoa</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Nhap Thong Tin Chuyen Khoa</h2>
		
		<c:choose>
			<c:when test="${mack==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				
				<tr>
					<td><form:label path="mack">Ma Chuyen Khoa</form:label></td>
					<td><form:input path="mack"/></td>
					<td><form:errors path="mack"></form:errors>
				</tr>
				<tr>
					<td><form:label path="tenck">Ten Chuyen Khoa</form:label></td>
					<td><form:input path="tenck"/></td>
					<td><form:errors path="tenck"></form:errors>
				</tr>
				<tr>
					<td><form:label path="lanhdaock">Lanh Dao CK</form:label></td>
					<td><form:input path="lanhdaock"/></td>
					<td><form:errors path="lanhdaock"></form:errors>
				</tr>
				
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
				<tr><td colspan="4"><a href="list"><input type="button" value = "View List" ></a></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>