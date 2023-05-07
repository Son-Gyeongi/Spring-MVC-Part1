package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// HttpServletResponse - 기본 사용법
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 제일 먼저 응답 코드를 어떻게 세팅할까
        // 이렇게 하면 HTTP 응답코드를 넣을 수 있다. 상수(HttpServletResponse.SC_OK)로 정리되어있다.
        // 200 숫자 쓰는 거보다 상수를 쓰는 게 더 낫다.
        // [status-line] HTTP 스펙에서 응답의 첫 번째
        response.setStatus(HttpServletResponse.SC_OK); // 200
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400

        // [response-headers] - 제일 기본적인 기능
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 아래와 같이 적으면 캐시를 완전 무효화 하겠다는 뜻
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // 과거 버전까지 캐시를 무효화(없애다)하려면 더 추가
        response.setHeader("Pragma", "no-cache");
        // 내가 원하는 임의의 헤더를 만들 수 있다. HTTP 응답 헤더에 같이 실려 나간다.
        response.setHeader("my-header", "hello");

        // [Header 편의 메서드]
        content(response); // Content 편의 메서드
        cookie(response); // 쿠키 편의 메서드
        redirect(response); // redirect 편의 메서드

        // [message body]
        PrintWriter writer = response.getWriter();
        // charset=utf-8로 인코딩하면 한글 넣어도 안 꺠진다.
//        writer.println("안녕하세요.");
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2
        // response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 이전에는 위와 같은 방식으로 직접 세팅했다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;
        // 쿠키도 setHeader해서 넣을 수 있다.
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600")
        // Cookie 객체를 만든다.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302
        // Location: /basic/hello-form.html // 여기로 보낼거다.

        // response.setStatus(HttpServletResponse.SC_FOUND); // 302
        // response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
