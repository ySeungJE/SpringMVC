package hello.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
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
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper(); // json 받는데 이거 굳이 만들어야 함? 왤케 불편함 스프링?

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");

    }


    @ResponseBody
    @PostMapping("/request-body-json-v2")    // 여기까진 일반 문자열을 받은 것과 마찬가지라 앞에꺼랑 차이 안남
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")    // 이거는 그냥 메세지 바디에 단순 문자열 받은거랑 똑같은디??
    public String requestBodyJsonV3(@RequestBody HelloData data) throws IOException { // 이거는 클래스 변수에 ObjectMapper 선언 안해줘도 알아서 돌아감

        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException { // 이거는 클래스 변수에 ObjectMapper 선언 안해줘도 알아서 돌아감
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v5")    // 내가 존나 궁금했던거 인스턴스는 넘겨주는가?
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException { // 이거는 클래스 변수에 ObjectMapper 선언 안해줘도 알아서 돌아감
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
}
