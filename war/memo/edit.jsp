<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>memo Edit</title>
</head>
<body>
	<div class="container">
		<h2>Memo Edit</h2>
		<form method="post" action="/memo/upsert" class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">タイトル</label>
				<div class="col-sm-10">
					<input type="text" name="title" value="${f:h(memo.title)}" class="form-control" placeholder="Title">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">メモ</label>
				<div class="col-sm-10">
					<input type="text" name="memo" value="${f:h(memo.memo)}" class="form-control" placeholder="Memo">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" /> <a href="index" class="btn btn-primary">戻る</a>
					<input type="hidden" name="id" value="${f:h(memo.key.id)}">
				</div>
			</div>
		</form>
	</div>
</body>
</html>