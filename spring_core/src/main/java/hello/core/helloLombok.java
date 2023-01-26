package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//getter setter 를 어노테이션 만으로 만들 수 있음
@Getter
@Setter
@ToString
public class helloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        helloLombok helloLombok = new helloLombok();
        helloLombok.setName("asdaf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
