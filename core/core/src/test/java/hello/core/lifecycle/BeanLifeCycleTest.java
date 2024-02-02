package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        // 클로즈 호출할 때, 빈이 죽음!
        ac.close();

    }
    // 스프링 빈은 객체를 생성한 후에 -> 의존관계를 주입한다.
    // 단, 생성자 주입은 다를 수 있음 객체 생성 시 의존관계 주입한다.
    // 초기화 하는 작업 : 객체 안에 필요한 값이 다 연결되어 있는 상태 -> 일 시작 가능할 떄 즉, 의존관계 주입까지 다 완료된 이후
    // 개발자가 초기화가 되었는지 어케 확인함??
    // 스프링은 의존 관계 주입이 완료되면 콜백을 함(다양한 방식으로)

    // 스프링 빈의 이벤트 라이프 사이클
    // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백(초기화 완료됐다!) -> 사용 -> 소멸 전 콜백 -> 스프링 종료

    // 생성자가 편하지 않나?
    // 객체의 생성과 초기화를 분리하자! -> 객체를 생성하는데 집중해야 함.
    // 객체의 초기화(동작)하는 것은 별도의 초기화 메서드로 분리하는 것이 좋다!
    // 생성자는 필수정보!!를 파라미터로 받고 메모리를 할당해서 객체를 생성하는 책임
    // 초기화는 외부 커넥션을 연결하는 등 무거운 작업을 수행
    // 유지 보수 관점에서 분리하는 것이 좋다!

    // 스프링은 3가지 방법으로 빈 생명주기 콜백을 지원한다.
    /*
    * 1. 인터페이스
    * 2. 빈 등록 초기화, 소멸 메소드
    * 3. 에노테이션 @PostConsturct, @PreDestroy
    * */

    @Configuration
    static class LifeCycleConfig {

//        @Bean(initMethod = "init", destroyMethod = "close") 설정 정보 사용하기
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
