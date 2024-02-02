package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private long id;
    private String username;
    private long age;

    public Member() {

    }

    public Member(String username, long age) {
        this.username = username;
        this.age = age;
    }

}
