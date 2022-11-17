<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>ユーザー管理</title>
</head>
<body>
  <div class="container">
    <h1>ユーザー削除</h1>
    <p><a href="logout">ログアウト</a></p>
    <div class="row">
      <div class="col">
        <form action="" method="post">
          <table class="table table-bordered">
            <tr>
              <th>ID</th>
              <td>
                <c:out value="${room.roomId }" />
              </td>
            </tr>
            <tr>
              <th>ルーム名</th>
              <td>
                <c:out value="${room.roomName }" />
              </td>
            </tr>
            <tr>
              <th>ルームタイプ</th>
              <td>
                <c:out value="${room.roomTypeName }" />
              </td>
            </tr>
          </table>
          <input type="submit" value="削除" class="btn btn-primary">
          <a href="listRoom" class="btn btn-light">キャンセル</a>
        </form>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script>
			$(document).ready(function() {
				$("form").submit(function() {
					return confirm("本当に削除しますか？");
				});
			});
		</script>
</body>
</html>
