package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Header정보 어떻게 출력하는 지 알아보자
 * http://localhost:8080/request-header?username=hello
 */
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    // HttpServlet에서 접근제어자가 protected인 service를 오버라이딩 해야한다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청 메시지의 start line 정보 불러오기
        printStartLine(request);

        // Header 모든 정보 출력
        printHeaders(request);

        // header들을 편리하게 조회하는 기능
        printHeaderUtils(request);
        
        // 기타 부가적인 정보들 지원 - 기타 정보는 HTTP 메시지의 정보는 아니다.
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");

        System.out.println("request.getMethod() = " + request.getMethod()); // HTTP 메서드, GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); // http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); // https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        // header 정보를 가져오는 2가지 방법
        // request.getHeaderNames() - HTTP 요청 메시지에 있는 모든 header 정보를 다 꺼내서 다 출력 가능
/*
1. 옛날 방식 - Enumeration
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) { // 다음 요소가 있으면
            String headerName = headerNames.nextElement(); // 다음 요소를 꺼낸다.
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
 */

//2. 요즘 스타일
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));

        // Header 정보 조회할 때 헤더 부분 하나만 조회하고 싶다면
        request.getHeader("host"); // host만 꺼낼 수 있다.

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]"); // 웹 브라우저에서 보낼 때 Host 정보
        System.out.println("request.getServerName() = " + request.getServerName()); // Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); // Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]"); // 웹 브라우저에서 보낼 때 Accept-Language 정보
        request.getLocales().asIterator() // 웹 브라우저에서 요청하는 언어들 순서
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale()); // 우선순위가 높은 언어
        System.out.println();

        System.out.println("[cookie 편의 조회]"); // cookie도 Http 헤더에 담긴다.
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": "+cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        // Get은 Content을 거의 안 보낸다.
        // HTTP 메시지 바디에 뭐가 담겨있어야지 ContentType이 의미있다.
        System.out.println("request.getContentType() = " + request.getContentType()); // Get 방식이라서 null이 나온다.
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) {
        /**
         * 기타 정보는 HTTP 메시지의 정보는 아니다.
         * 외부에서 네트워크 커넥션이 맺어진 그 정보들이다.
         */
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]"); // 요청이 온 거에 대한 정보
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]"); // 나의 서버에 대한 정보
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
//    지금까지 HttpServletRequest를 통해서 HTTp메시지의 start-line, header 정보 조회 방법을 이해했다.
}
