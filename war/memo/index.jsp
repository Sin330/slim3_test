<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.slim3.org/functions" prefix="f"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Memo List</title>
</head>
<body>
	<h1>Memo</h1>
	<div class="container-fluid">
		<div class="main">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Title</th>
							<th>Memo</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${memoList}">
							<tr>
								<td><a href="edit?id=${f:h(e.key.id)}">${f:h(e.title)}</a></td>
								<td>${f:h(e.memo)}</td>
								<td>${f:h(e.updateDateString)}</td>
								<td><a href="delete?id=${f:h(e.key.id)}" class="btn btn-st btn-default">削除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<a href="edit" class="btn btn-lg btn-default">追加</a>
		</div>
	</div>
</body>
</html>