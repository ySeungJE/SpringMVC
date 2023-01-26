package hello.springmvc.basic.request;


import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

// http의 메세지 바디에 담긴 데이터 읽기
@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException { // 스프링이 <> 안에 있는 자료형의 데이터를 message body 에서 알아서 읽어들임

        String body = httpEntity.getBody();

        log.info("messageBody={}", body);

        return new HttpEntity<>("ok"); // 이렇게 하면 @Controller 클래스이지만 view 조회 안하고 바로 메세지 바디에 직접 반환함
    }


    // 이 코드가 바로 위와 동일하게 동작함. 훨씬 간단하고 직관적이고 사용하기 쉬움
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException { // 스프링이 <> 안에 있는 자료형의 데이터를 message body 에서 알아서 읽어들임

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
