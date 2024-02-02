package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;


class StatefulServiceTest {

    @Test
    void statefulServiceTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10000원을 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B 사용자가 20000원을 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액을 조회한다.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 20000원이 나옴

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        // 싱글톤이기 때문에 같은 빈 객체를 반환 함. --> 내부의 변수가 client에 의존하기 때문에 값이 바뀜
        // 특정 클라이언트가 공유되는 변수의 값을 바꿔버림
        // 이러한 오류는 잡기도 어렵기 때문에 꼭 주의해서 설계해야 함.
    }

    @Test
    void statefulServiceTest2() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService2 statefulService1 = ac.getBean(StatefulService2.class);
        StatefulService2 statefulService2 = ac.getBean(StatefulService2.class);

        // ThreadA : A 사용자가 10000원을 주문
        int price1 = statefulService1.order("userA", 10000);
        // ThreadB : B 사용자가 20000원을 주문
        int price2 = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액을 조회한다.
        System.out.println("price1 = " + price1); // 20000원이 나옴
        System.out.println("price2 = " + price2);

        assertThat(price1).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatefulService2 statefulService2() {
            return new StatefulService2();
        }
    }

}