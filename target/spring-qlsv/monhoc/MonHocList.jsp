<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Danh Sach Mon Hoc</title>
		 <meta charset="utf-8">
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body>
		<h2>Danh Sach Mon Hoc</h2>
		<div class="table-responsive">
		<table class="table">
			<thead>
			<tr class="danger">
				<td>#</td>
				<td>Ma Mon</td> 
				<td>Ten Mon</td>
				<td>So TC</td>
				<td>Giang Vien</td>
				
			</tr>
			</thead>

			<tbody>
				<c:forEach items="${danhSachMonHoc}" var="monhoc">
					<tr class="success">
						<td><a href="edit/${monhoc.mamon}">Edit</a></td>
						<td>${monhoc.mamon}</td>
						<td><a href="view/${monhoc.mamon}">${monhoc.tenmon}</a></td>
						<td>${monhoc.sotc}</td>
						<td>${monhoc.giangvienchinh}</td>
						<td><a href="delete/${monhoc.mamon}">DELETE </a></td>
					</tr>
				</c:forEach>
				<tr	 class="active">
					<td>
					<form class="form-inline" role="form" method="GET" action="list">
						<div class="form-group">
							<td colspan="0"><input type="text" name="q" size="10"></td>
							<td><input type="submit" value="   Submit   "></td>
						</div>
					</form>
					</td>
					<form method="POST" action="add">
						<td><a href="form"><input type="button" value="    Add    "></a></td>
					</form>
				</tr>
			</tbody>
		</table>
		</div>
	</body>
</html>