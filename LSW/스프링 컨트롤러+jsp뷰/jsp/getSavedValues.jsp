<%@page import="or.kosta.mvc.controller.StateNumber"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String getV = StateNumber.getStateNumber().view();
%>
<%=getV%>