package com.javalab.sendreirect;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//중요 한글 메시지 전달시 URL 인코딩용 API(클래스)
import java.net.URLEncoder;
import java.net.URLDecoder;

/**
 * [로그인 서블릿]
 * 로그인 페이지를 띄워주는 기능 / 로그인을 진행하는 두 가지 기능
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   // 로그인 폼 화면을 띄워주는 메소드
   protected void doGet(HttpServletRequest request, 
                  HttpServletResponse response) throws ServletException, IOException {
      System.out.println("doGet");
 
      request.setCharacterEncoding("utf-8");
      
      String errorMsg = "";
      
      // doPost메소드에서 쿼리스트링에 달아보낸 errorMsg 추출
      if(request.getParameter("errorMsg") != null) {

         /*
          * [디코딩]
          * 오류 메시지를 보내는 쪽에서 utf-8 방식으로 2byte씩 인코딩 했으므로
          * 받는 쪽에서도 utf-8 방식으로 2byte씩 디코딩해야 완벽한 메시지 복원됨.
          */
         errorMsg = request.getParameter("errorMsg");
         errorMsg = URLDecoder.decode(errorMsg, "UTF-8");
      }
      // 오류 메시지 request 객체에 저장
      request.setAttribute("errorMsg", errorMsg);
      // 로그인 페이지(화면) 이동
      RequestDispatcher dispatcher = request.getRequestDispatcher("/loginForm.jsp");
      dispatcher.forward(request, response);
   }

   /**
    * 로그인 처리를 하는 메소드
    */
   protected void doPost(HttpServletRequest request, 
                  HttpServletResponse response) throws ServletException, IOException {
        
      System.out.println("doPost");
      
      // post 방식 파라미터 전달받을 때 인코딩 방식 지정
      request.setCharacterEncoding("utf-8");
      
      // 사용자 브라우저에 내려보내는 컨텐트 타입 설정
      response.setContentType("text/html; charset=utf-8");
      // 사용자 브라우저에 응답할 때 한글이 깨지지 않고 전달되도록 설정 
      response.setCharacterEncoding("utf-8");
      
      String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        
        // 아이디와 비밀번호가 같으면 인증 OK, 아니면 인증 Fail 가정
        boolean isAuthenticated = id.equals(pwd) ? true : false ;
        
        if (isAuthenticated) {
            // 계정 인증에 성공시 네이버 홈페이지로 이동
            response.sendRedirect("https://www.naver.com");
        } else {
            // 인증 실패시 다시 로그인 페이지로 이동
           String errorMsg = "아이디와 비밀번호를 확인하세요.";
           /*
            * [한글 메시지의 경우 인코딩]
            * - 한글 메시지를 Url에 쿼리스트링 형태로 달고 가기 위해 인코딩 해야함.
            * - Url에 달아서 보낼 메시지 인코딩(UTF-8)해서 2byte씩 처리
            * - 웹페이지의 본문(메시지 바디)에서는 UTF-8을 허용하지만, 
            *   웹브라우저의 주소창에서는 오로지 ASCII 코드만 허용해서 문제가됨.
            * - ASCII가 아닌 문자를 포함한 모든 특수 문자는 퍼센트 기호와 
            *   두 자리 16진수 숫자로 대체됨.
            * - 인코딩방식 : 입력된 문자를 ASCII 테이블에서 매칭되는 hex 값에 
          *   %를 앞에 붙이면됨.(퍼센트 인코딩)  
            * - URLEncoder는 import java.net.URLEncoder;
            * - 인코딩 안하면 : java.lang.IllegalArgumentException 예외
            */
           String encodedText = URLEncoder.encode(errorMsg, "UTF-8");
           System.out.println("보내는 쪽에서 encodedText : " +  encodedText);
           /*
            * sendRedirect는 get방식 요청으로 주소창에 파라미터를 달고감.
            *  - 현재 서블릿 클래스의 doGet()메소드로 요청이 감.
            */
           String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login?errorMsg=" + encodedText);
        }
    }
}