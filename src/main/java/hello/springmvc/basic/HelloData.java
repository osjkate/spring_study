package hello.springmvc.basic;

import lombok.Data;

@Data
// getter, setter, toString, EqualsAndHashCode,
// RequiredArgsConstructor를 자동으로 제공해준다.
public class HelloData {
    private String username;
    private int age;
}
