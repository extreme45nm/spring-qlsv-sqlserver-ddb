<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>svmh List</title>
		 <meta charset="utf-8">
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body>
		<h2>List Of Students</h2>
		<div class="table-responsive">
		<table class="table">
			<thead>
			<tr class="danger">
				<td>#</td>
				<td>ID</td> 
				<td>NAME</td>
				<td>AGE</td>
				<td>ADDRESS</td>
				<td>POINT</td>
			</tr>
			</thead>

			<tbody>
				<c:forEach items="${students}" var="svmh">
					<tr class="success">
						<td><a href="edit/${svmh.id}">Edit</a></td>
						<td>${svmh.id}</td>
						<td><a href="view/${svmh.id}">${svmh.name}</a></td>
						<td>${svmh.age}</td>
						<td>${svmh.address}</td>
						<td>${svmh.point}</td>
						<td><a href="delete/${svmh.id}">DELETE </a></td>
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
						<td><input type="submit" value="    Add    "></td>
					</form>
				</tr>
			</tbody>
		</table>
		</div>
	</body>
</html>