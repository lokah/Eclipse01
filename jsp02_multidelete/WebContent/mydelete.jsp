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
int res = dao.delete(seq);
if(res>0){
	%>
	
	<script type="text/javascript">
	
	alert("삭제 성공");
	location.href="mylist.jsp";
	</script>
	
	<%
}else{
	%>
	<script type="text/javascript">
	
	alert("삭제 실패");
	location.href="mydetail.jsp?seq=<%=seq%>";
	</script>
<%
}
%>




</body>
</html>