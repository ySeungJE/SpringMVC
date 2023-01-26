package hello.springmvc.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")  // 스프링 애노테이션 기반 컨트롤러는 다양한 기반의 파라미터를 받아들일 수 있기 때문에 헤더와 쿠키정보 등도 간단히 확인할 수 있음
    public String headers(HttpServletRequest request, // 서블릿 request
                          HttpServletResponse response, // 서블릿 response
                          HttpMethod httpMethod, // Http 메소드 클래스?
                          Locale locale, // 언어 지원 관련 클래스
                          @RequestHeader MultiValueMap<String, String> headerMap, // 헤더정보 한번에
                          @RequestHeader("host") String host, // 헤더정보 하나씩
                          @CookieValue(value = "myCookie", required = false) String cookie) // 쿠키 정보
    {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
