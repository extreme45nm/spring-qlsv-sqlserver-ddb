<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Lop Hoc</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Nhap Thong Tin Lop Hoc</h2>
		
		<c:choose>
			<c:when test="${malop==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				
				<tr>
					<td><form:label path="malop">Ma Lop</form:label></td>
					<td><form:input path="malop"/></td>
					<td><form:errors path="malop"></form:errors>
				</tr>
				<tr>
					<td><form:label path="gvcn">GVCN</form:label></td>
					<td><form:input path="gvcn"/></td>
					<td><form:errors path="gvcn"></form:errors>
				</tr>
				<tr>
					<td><form:label path="mack">Ma CK</form:label></td>
					<td><form:input path="mack"/></td>
					<td><form:errors path="mack"></form:errors>
				</tr>
				
				<tr>
					<td><form:label path="maloptruong">Ma Lop Truong</form:label></td>
					<td><form:input path="maloptruong" type="number"/></td>
					<td><form:errors path="maloptruong"></form:errors></td>
				</tr>
				
				
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
				<tr><td colspan="4"><a href="list"><input type="button" value = "View List" ></a></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>