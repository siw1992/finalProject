<%@page import="or.kosta.mvc.controller.StateNumber"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
   request.setCharacterEncoding("euc-kr");
   String savedStatus = StateNumber.getStateNumber().view();
   switch(savedStatus)
   {
   case "1":
	   System.out.println("ON - 부저울림");
	   break;
   case "2":
	   System.out.println("OFF - 부저안울림");
	   break;
   }
%>
<%=savedStatus%>