<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Khoa-Bo Mon</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Nhap Thong Tin Khoa-Bo Mon</h2>
		
		<c:choose>
			<c:when test="${makhoa==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				
				<tr>
					<td><form:label path="makhoa">Ma Khoa</form:label></td>
					<td><form:input path="makhoa"/></td>
					<td><form:errors path="makhoa"></form:errors>
				</tr>
				<tr>
					<td><form:label path="tenkhoa">Ten Khoa</form:label></td>
					<td><form:input path="tenkhoa"/></td>
					<td><form:errors path="tenkhoa"></form:errors>
				</tr>
				<tr>
					<td><form:label path="malanhdaokhoa">Ma Lanh Dao Khoa</form:label></td>
					<td><form:input path="malanhdaokhoa"/></td>
					<td><form:errors path="malanhdaokhoa"></form:errors>
				</tr>
				
		
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
				<tr><td colspan="4"><a href="list"><input type="button" value = "View List" ></a></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>