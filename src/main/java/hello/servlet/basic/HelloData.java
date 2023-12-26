package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter // 롬복 사용!
public class HelloData {
    private String username;
    private int age;
}
