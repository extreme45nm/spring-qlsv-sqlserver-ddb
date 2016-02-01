<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Danh Sach Sinh Vien</title>
		 <meta charset="utf-8">
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body>
		<h2>Danh Sach Sinh Vien</h2>
		<div class="table-responsive">
		<table class="table">
			<thead>
			<tr class="danger">
				<td>#</td>
				<td>Masv</td> 
				<td>ChuyenKhoa</td>
				<td>HoDem</td>
				<td>Ten</td>
				<td>Tuoi</td>
				<td>DiaChi</td>
				<td>QueQuan</td>
				<td>ChucVu</td>
				<td><a href="form"><input type="button" value="    Add    "></a></td>
			</tr>
			</thead>

			<tbody>
				<c:forEach items="${sinhviens}" var="sv">
					<tr class="success">
						<td><a href="edit/${sv.masv}">Edit</a></td>
						<td>${sv.masv}</td>
						<td>${sv.chuyenkhoa}</td>
						<td>${sv.hodem}</td>
						<td><a href="view/${sv.masv}">${sv.ten}</a></td>
						<td>${sv.tuoi}</td>
						<td>${sv.diachi}</td>
						<td>${sv.quequan}</td>
						<td>${sv.chucvu}</td>
						<td><a href="delete/${svmh.id}">DELETE </a></td>
					</tr>
				</c:forEach>
				<tr	 class="active">
					<td></td>
					<td></td>
					<td></td>
					<td>
					<form class="form-inline" role="form" method="GET" action="list">
						<div class="form-group">
							<td colspan="1"><input type="text" name="q" size="10"></td>
							<td><input type="submit" value="   Search   "></td>
						</div>
					</form>
					</td>
					
				</tr>
			</tbody>
		</table>
		</div>
	</body>
</html>