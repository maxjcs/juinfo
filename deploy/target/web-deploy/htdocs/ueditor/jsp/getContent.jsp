<%@ page language="java" pageEncoding="gbk"%>
<%
request.setCharacterEncoding("gbk");
response.setCharacterEncoding("gbk");
String content = request.getParameter("myEditor");
String content1 = request.getParameter("myEditor1");


response.getWriter().print("��1���༭����ֵ");
response.getWriter().print(content);
response.getWriter().print("<br/>��2���༭����ֵ<br/>");
response.getWriter().print("<textarea style='width:500px;height:300px;'>"+content1+"</textarea><br/>");
response.getWriter().print("<input type='button' value='�������' onclick='javascript:location.href = \"../_examples/submitFormDemo.html\"' /></script>");
%>