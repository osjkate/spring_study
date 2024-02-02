package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 구성(설정)
// FactoryMethod를 통해 제공해주는 방법 : 외부에서 메서드를 호출해서 빈을 생성
@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    // 두 개 생성? no 다 같은 객체 반환해줌

    // 예상
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 출력해보면
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // @Configuration 때문!


    // 생성자 주입을 통해 DI 구현(관심사의 분리)
    // 객체의 생성과 연결을 담당

    // 역할들이 보이게 리팩토링
    @Bean   // SpringContainer에 등록하기
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
//        return new DBMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    // 구성영역만 변경하면 됨 (사용 영역은 고칠 필요 없음)
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
