package hello.core.lifecycle;

// 자바에서 지원하는 에노테이션
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 인터페이스 사용
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient{
    private String url;

    public NetworkClient() {
        // 생성자 호출 시 url이 빈 상태로 실행됨
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }
    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);

    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 초기화 완료 시 호출됨
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    // 빈이 종료될 때 호출됨
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
    // 초기화 완료 시 호출됨
    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    // 빈이 종료될 때 호출됨
    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
