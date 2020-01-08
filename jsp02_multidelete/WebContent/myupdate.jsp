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
<%@ include file="./form/header.jsp" %>


	<%
		int seq = Integer.parseInt(request.getParameter("seq"));

		MDDao dao = new MDDao();

		MDDto dto = dao.selectOne(seq);
	%>



	<h1>수정하기</h1>

	<form action="myupdateres.jsp" method="post">
		<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
		<table border="1">



			<tr>

				<th>이름</th>

				<td><%=dto.getWriter()%></td>

			</tr>



			<tr>

				<th>제목</th>

				<td><input type="text" name="title"
					value="<%=dto.getTitle()%>"></td>

			</tr>

			<tr>

				<th>내용</th>

				<td><textarea rows="10" cols="60" name="content"><%=dto.getContent()%></textarea>

				</td>

			</tr>



			<tr>

				<td colspan="2" align="right"><input type="submit" value='수정'>

					<input type="button" value='취소' onclick="location.href='mydetail.jsp?seq=<%=dto.getSeq()%>'"> <input
					type="button" value='목록' onclick="location.href='mylist.jsp'">

				</td>

			</tr>



		</table>

	</form>


<%@ include file="./form/footer.jsp" %>
</body>

</html>