package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springBoot에서 서블릿을 쓸 수 있게 지원
 * @ServletComponentScan 애노테이션 지원
 * 스프링이 자동으로 내 패키지(hello.servlet) 포함해서 하위 패키지를 다 뒤져서 서블릿을 다 찾아서
 * 자동으로 서블릿에 등록해서 실행할 수 있도록 도와준다.
 */
@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	// 스프링 부트가 설정 정보를 가져와서 자동으로 해준다.
	// 그래서 우리가 직접 안해도 된다.
//	@Bean
//	ViewResolver internalResourceViewResolver() {
//		return new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
//	}

}
