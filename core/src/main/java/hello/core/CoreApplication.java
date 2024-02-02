package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 여기 안에 componentScan 들어 있음 -> 스프링 부트 사용하면 따로 configuration 클래스를 만들지 않아도 됨
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
