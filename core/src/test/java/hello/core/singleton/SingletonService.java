package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // 자바가 생성되면 인스턴스 생성해서 넣어놓음

    public static SingletonService getInstance() {
        return instance;
    }
    // get 메소드를 통해서만 참조할 수 있다.

    private SingletonService() {
        // private 생성자하면 새로운 인스턴스를 만들 수 없다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
