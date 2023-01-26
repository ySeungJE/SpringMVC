package hello.springmvc.basic.requestmapping;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {


    // 여기서 알아야 할 건 같은 URL 이라도 요청 메소드가 다르면 다르게 취급 된다는 것 (RestController 에서만??)
    @GetMapping("")
    public String user() {
        return "get users";
    }

    @PostMapping("")
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userID =" + userId;
    }
}
