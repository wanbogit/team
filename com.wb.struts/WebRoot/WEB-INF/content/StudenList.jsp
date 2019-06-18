<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form>
		<div>
			<table id="tab">
				<thead>
					<tr>
						<th>编号</th>
						<th>名称</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</form>
	<!--Global Javascript -->
	<script src="js/jquery.min.js"></script>


	<!--Core Javascript -->

	<script>
		$(document).ready(tableload());
		function tableload() {
			$.ajax({
						url : "select",
						type : "post",
						dataType : "json",
						success : function(json) {
							$("#tab tbody").html("");
							var trhtml = "";
							for (var i = 0; i < json.length; i++) {
								trhtml += "<tr><td>" + json[i].id
										+ "</td><td>" + json[i].name
										+ "</td><td>" + json[i].remark
										+ "</td></tr>";
							}
							$("#tab tbody").append(trhtml);
						},
						error : function() {
							alert("error");
						}
					});
		}
	</script>
</body>
</html>
