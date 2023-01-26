package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//값이 두개 이상일 땐 value = "" , "" 이 형식으로 해줘야 함
// MyLogger 클래스에 proxy 설정을 해두었기 때문에 의존주입할 때 진짜 MyLogger가 아닌
// 컨테이너가 CGLIB을 사용해서 MyLogger를 조작한 인스턴스가 만들어져서 걔가 Provider 역할을 한다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    // 이 URL은 빈이 생성되는 시점에는 알 수 없으므로 외부에서 setter로 입력받는다
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+ uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        // 전 세계적으로 절대 겹치지 않는 유니크한 아이디가 하나 생성됨
        uuid = UUID.randomUUID().toString();
        System.out.println("["+ uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+ uuid + "] request scope bean close:" + this);
    }

}
