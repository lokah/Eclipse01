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
String writer = request.getParameter("writer");
String title = request.getParameter("title");
String content = request.getParameter("content");
MDDto dto = new MDDto();
dto.setWriter(writer);
dto.setTitle(title);
dto.setContent(content);

MDDao dao = new MDDao();

int res = dao.insert(dto);//해당 row 의 갯수 res 
if(res>0){
%>

<script type="text/javascript">
alert("글 작성 성공");
location.href ="mylist.jsp";
</script>	
	<%
}else{
	%>
	
	<script type="text/javascript">
	alert("글 작성 실패");
	location.href="boardwriteform.jsp";
	
	</script>
	<%
}
	%>



</body>
</html>