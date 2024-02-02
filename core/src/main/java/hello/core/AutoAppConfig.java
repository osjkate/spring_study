package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
        // basePackages = "hello.core.member", 하면 order 가 등록되지 않음, 스캔 시작 패키지를 정해줌
        // 두 개 지정할 수도 있음
//        basePackageClasses = AutoAppConfig.class ==> 클래스를 아예 지정할수도 있다.
        // 지정하지 않으면 default는 이 클래스가 들어있는 패키지부터 시작한다.
        // --> 패키지 위치를 딱히 정해두지 않고 해당 클래스를 최상단에 위치하도록 두는 것이 좋음

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 원래는 다 지우고 하니깐 이거 안해도 됨.
)      // @Component 붙은거 다 @Bean해줌
public class AutoAppConfig {

//    @Bean(name="memberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
