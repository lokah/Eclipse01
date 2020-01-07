<%@page import="com.md.dao.MDDao"%>
<%@page import="com.md.dto.MDDto"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

<%

	request.setCharacterEncoding("UTF-8");

%>

<%

	response.setContentType("text/html; charset=UTF-8");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%

		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		MDDto dto = new MDDto();

		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);

		MDDao dao = new MDDao();

		int res = dao.update(dto);

		if (res > 0) {

	%>

	<script type="text/javascript">

		alert("수정 성공");

 

		location.href = "mylist.jsp";

	</script>


	<%

		} else {

	%>

 

	<script type="text/javascript">

		alert("수정 실패");

 

		location.href = "myupdate.jsp";

	</script>

 

	<%

		}

	%>

 

</body>

</html>