<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add SinhVien</title>
	</head>
	<div class="container-fluid">
	<body>
		<h2>Nhap Thong Tin Sinh Vien</h2>
		
		<c:choose>
			<c:when test="${masv==null}">
				<c:set var="action" value="add"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="../add"></c:set>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" id="command" action="${action}">
			<table>
				<tr><td><form:input path="masv" type="hidden"/></td></tr>
				<tr>
					<td><form:label path="chuyenkhoa">ChuyenKhoa</form:label></td>
					<td><form:input path="chuyenkhoa"/></td>
					<td><form:errors path="chuyenkhoa"></form:errors>
				</tr>
				<tr>
					<td><form:label path="hodem">HoDem</form:label></td>
					<td><form:input path="hodem"/></td>
					<td><form:errors path="hodem"></form:errors>
				</tr>
				<tr>
					<td><form:label path="ten">Ten</form:label></td>
					<td><form:input path="ten"/></td>
					<td><form:errors path="ten"></form:errors>
				</tr>
				
				<tr>
					<td><form:label path="tuoi">Tuoi</form:label></td>
					<td><form:input path="tuoi" type="number"/></td>
					<td><form:errors path="tuoi"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="diachi">DiaChi</form:label></td>
					<td><form:input path="diachi"/></td>
				</tr>
				<tr>
					<td><form:label path="quequan">QueQuan</form:label></td>
					<td><form:input path="quequan"/></td>					
				</tr>
				<tr>
					<td><form:label path="chucvu">ChucVu</form:label></td>
					<td><form:input path="chucvu"/></td>					
				</tr>
				
				<tr><td colspan="4"><input type="submit" value = "Submit"></td></tr>
				<tr><td colspan="4"><a href="list"><input type="button" value = "View List" ></a></td></tr>
			</table>	
		</form:form>
	</body>
	</div>
</html>