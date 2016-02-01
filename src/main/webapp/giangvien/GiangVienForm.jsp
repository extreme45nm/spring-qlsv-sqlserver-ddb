<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add GiangVien</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Nhap Thong Tin Giang Vien</h2>
		
		<c:choose>
			<c:when test="${magv==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				<tr><td><form:input path="magv" type="hidden"/></td></tr>
				<tr>
					<td><form:label path="makhoa">Ma Khoa</form:label></td>
					<td><form:input path="makhoa"/></td>
					<td><form:errors path="makhoa"></form:errors>
				</tr>
				<tr>
					<td><form:label path="tengv">Ten GV</form:label></td>
					<td><form:input path="tengv"/></td>
					<td><form:errors path="tengv"></form:errors>
				</tr>
				<tr>
					<td><form:label path="tdhv">TDHV</form:label></td>
					<td><form:input path="tdhv"/></td>
					<td><form:errors path="tdhv"></form:errors>
				</tr>
				
				<tr>
					<td><form:label path="ns">Sinh Nhat</form:label></td>
					<td><form:input path="ns" type="date"/></td>
					<td><form:errors path="ns"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="capham">Cap Ham</form:label></td>
					<td><form:input path="capham"/></td>
				</tr>
								
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
				<tr><td colspan="4"><a href="list"><input type="button" value = "View List" ></a></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>