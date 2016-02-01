<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Sinh Vien Mon Hoc</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Nhap Sinh Vien Mon Hoc</h2>
		
		<c:choose>
			<c:when test="${mamon==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				
				<tr>
					<td><form:label path="mamon">Ma Mon</form:label></td>
					<td><form:input path="mamon" type="number"/></td>
					<td><form:errors path="mamon"></form:errors></td>
				</tr>
				
				<tr>
					<td><form:label path="masv">MA SV</form:label></td>
					<td><form:input path="masv"/></td>
					<td><form:errors path="masv"></form:errors>
				</tr>
				
				
				<tr>
					<td><form:label path="diem">Diem:</form:label></td>
					<td><form:input path="diem"/></td>
				</tr>
				
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>