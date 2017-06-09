<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "paran.join.*" %>
<%@ page import = "java.sql.Timestamp" %>

<link rel="stylesheet" href="./css/style.css"/>
<script src="./js/jquery-3.1.0.min.js"></script>


<jsp:useBean id="member" class="paran.join.StudentDataBean">
	<!-- StudentDataBean member = new StudentDataBean(); -->
    <jsp:setProperty name="member" property="id" />
    <jsp:setProperty name="member" property="name" />
    <jsp:setProperty name="member" property="pwd" />
    <jsp:setProperty name="member" property="birth_ymd" />
    <jsp:setProperty name="member" property="sexual_tp" />
    <jsp:setProperty name="member" property="addr" />
    <jsp:setProperty name="member" property="cell_phone" />
          
    <!-- 만약 폼 필드의 이름과 자바 빈 프로퍼티의 이름이 모두 일치한다면 다음처럼 한줄로 처리할 수 있다: -->
    <%-- <jsp:setProperty name="member" property="*" /> --%>
</jsp:useBean>


<%  
  JoinDBBean manager = new JoinDBBean();
  manager.insertMember(member);
%>