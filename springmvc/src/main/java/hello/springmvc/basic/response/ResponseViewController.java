package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")  //  이 버전이 가장 적당
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello"; // @Controller 이면서 String을 반환하면 어떻게 된다? viewName을 반환하는게 되어서 알아서 찾아간다
    }

    @RequestMapping("/response/hello") // 권장 사항 아님, 코드에 명시성이 부족함
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
