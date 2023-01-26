package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

// 정리하자면 Spring MVC 에서 요청 파라미터를 받을 때 기본형(단순 타입)은 @RequestParam 으로 받고 나머지는 @ModelAttribute 로 받는다.
// 여기서 이해한 건 내가 HelloData 클래스를 받겠다 해서 쿼리 파라미터로 인스턴스를 전달해주는 건 절대 아니고, 값을 전달해주면 스프링이 알아서 인스턴스로 만들어주는 것
@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 이러면 클래스는 @Controller로 선언되어 있지만 뷰리졸버를 호출하지 않고 ok 문자 자체를 바디에 넣어서 반환해버림
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // 이러면 클래스는 @Controller로 선언되어 있지만 뷰리졸버를 호출하지 않고 ok 문자 자체를 바디에 넣어서 반환해버림
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 이러면 클래스는 @Controller로 선언되어 있지만 뷰리졸버를 호출하지 않고 ok 문자 자체를 바디에 넣어서 반환해버림
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // 요청 파라미터 이름과 변수이름 맞추는건 필수네 이러면 ㅋㅋㅋ
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 이러면 클래스는 @Controller로 선언되어 있지만 뷰리졸버를 호출하지 않고 ok 문자 자체를 바디에 넣어서 반환해버림
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username, // username 못 받으면 오류 but 빈 문자는 들어올 수 있기 때문에 조심해야 함
                                       @RequestParam(required = false) Integer age) { // age 못 받아도 ㄱㅊ * int 는 기본형이기 때문에 null 못받음 Integer는 클래스라 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default") // 결과를 보면 defaultValue가 설정되어 있으면 required는 있든 없든 똑같음ㅋㅋ
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, // 이러면 빈 문자가 들어와도 guest로 해줌
                                      @RequestParam(required = false, defaultValue = "-1") int age) { // 이러면 null이 아니라 -1을 설정하기 때문에 int도 쓸 수 있음
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) { // 스프링 MVC의 힘
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); // 롬복 @Data 덕에 toString도 쓸수있음

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { // 스프링 MVC의 힘
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); // 롬복 @Data 덕에 toString도 쓸수있음

        return "ok";
    }
}
