package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Controller 이걸 쓰면 loTest() 가 반환하는 문자열은 viewName 으로 인식됨
@Slf4j
@RestController // 얘는 그런 거 없이 그냥 단순 문자열로 인식하기 때문에 테스트하기에 좋음
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";
        System.out.println("name = " + name); // 과거

        log.trace("trace log=" + name); // 이렇게 하면 함수 호출 안해도 연산해서 보관함, 메모리와 cpu낭비

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
