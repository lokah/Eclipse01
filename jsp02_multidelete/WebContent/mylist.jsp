<%@page import="com.md.dao.MDDao"%>
<%@page import="com.md.dto.MDDto"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF=8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>

function wirteForm(){
	location.href="boardwriteform.jsp";
	
	
}
</script>

<style type="text/css">
#list {
border: 2px solid orange;
color : gray;
}
#alist{
text-decoration: none;
}

</style>


</head>
<body>

	<%
		MDDao dao = new MDDao();
		List<MDDto> list = dao.selectList();
	%>
<%@ include file="./form/header.jsp" %>



	<h1>List</h1>

<form action="./muldel.jsp" method="post">

<table border="1">

<col width="30"/>
<col width="50"/>
<col width="100"/>
<col width="300"/>
<col width="100"/>

<tr>
<th>
<input type="checkbox" name="all" onclick="allChk(this.checked);">
</th>
<th>번호</th>
<th>작성자</th>
<th>제목</th>
<th>작성일</th>
</tr>

<%

if(list.size()==0){
	
	

%>

<tr>
<td colspan="5">........작성된 글이 존재하지 않습니다..........</td>
</tr>
<%
}else{
	
	for(MDDto dto :list){
		
	

%>

<tr>
<td>
<input type="checkbox" name="chk" value="<%=dto.getSeq()%>"></td>
<td><%=dto.getSeq() %></td>
<td><%=dto.getWriter() %></td>
<td><a href="mydetail.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
<td><%=dto.getRegdate() %></td>
</tr>
<%
	}
}
%>

<tr>

<td colspan="5">
<input type="submit" value="선택삭제">
<input type="button" value="글쓰기" onclick="wirteForm();">
</td>
</tr>
</table>
</form>
<%@ include file="./form/footer.jsp" %>



</body>
</html>