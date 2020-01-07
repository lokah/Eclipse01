<%@page import="com.md.dao.MDDao"%>
<%@page import="com.md.dto.MDDto"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	int seq = Integer.parseInt(request.getParameter("seq"));
	MDDao dao = new MDDao();
	MDDto dto = dao.selectOne(seq);


%>


<h1>Detail</h1>
<table border="1">
<tr>
<th>이름</th>
<td><%=dto.getWriter() %></td>
</tr>
<tr>
<th>제목</th>
<td><%=dto.getTitle() %></td>
</tr>
<tr>
<th>내용</th>
<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea>
</td>
</tr>

<tr>
<td colspan="2" align="right">
<input type="button" value='수정' onclick="location.href='myupdate.jsp?seq=<%=dto.getSeq()%>'"> 
<input type="button" value='삭제' onclick="location.href='mydelete.jsp?seq=<%=dto.getSeq()%>'"> <!-- 쿼리 스트링 -->
<input type="button" value='목록' onclick="location.href='mylist.jsp'">
</td>
</tr>
</table>

</body>
</html>