<%@page import="com.my.dao.MyDao"%>
<%@page import="com.my.dto.MyDto"%>
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
int myno = Integer.parseInt(request.getParameter("myno"));
MyDao dao = new MyDao();

MyDto dto = dao.selectOne(myno);

%>

<h1>수정하기</h1>
<form action="myupdateres.jsp" method="post">
<table border="1">

<tr> 
<th>이름</th>
<td><input type = "text" name="myname" value="<%=dto.getMyname()%>"></td>
</tr>

<tr>
<th>제목</th>
<td><input type = "text" name = "mytitle" value="<%=dto.getMytitle()%>"></td>
</tr>
<tr>
<th>내용</th>
<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getMycontent()%></textarea>
</td>
</tr>

<tr>
<td colspan="2" align="right">
<input type="submit" value='수정완료'>
<input type="button" value='삭제' onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno()%>'">
<input type="button" value='목록' onclick="location.href='mylist.jsp'">
</td>
</tr>

</table>
</form>

</body>
</html>