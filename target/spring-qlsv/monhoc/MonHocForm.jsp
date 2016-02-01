<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Them mon hoc</title>
		<meta charset="utf-8">
	</head>
	<body>
		<h2>Them mon hoc moi</h2>
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
				<tr><td><form:input path="mamon" type="hidden"/></td></tr>
				<tr>
					<td><form:label path="tenmon">Ten Mon</form:label></td>
					<td><form:input path="tenmon"/></td>
					
				</tr>
				<tr>
					<td><form:label path="sotc">So tin chi</form:label></td>
					<td><form:input path="sotc" type="number"/></td>
					
				</tr>
				<tr>
					<td><form:label path="giangvienchinh">Giang Vien</form:label></td>
					<td><form:input path="giangvienchinh"/></td>
				</tr>
								
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
				<tr><td colspan="4"><a href="list"><input type="button" value = "List"></a></td></tr>
			</table>	
		</form:form>
	</body>
	
</html>