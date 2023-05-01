<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
   <form    name="frmLogin" 
         method="post" 
         action="<c:url value='/login' />">
      
      <h1 style="text-align: center">로그인</h1>
      <table align="center" border="0">
         <tr>
            <td><p align="right">아이디</td>
            <td><input type="text" name="id"></td>
         </tr>
         <tr>
            <td><p align="right">비밀번호</td>
            <td><input type="password" name="pwd"></td>
         </tr>
         <tr>
            <td><p>&nbsp;</p></td>
            <td>
               <input type="submit" value="로그인">
               <input   type="reset" value="다시입력">
            </td>
         </tr>
         <c:if test="${not empty errorMsg}">
            <tr style="text-align: center;">
               <td colspan="2">
                  <p style="color: red;">${errorMsg}</p>
               </td>
            </tr>         
         </c:if>
      </table>
   </form>
</body>
</html>