package reactor;

import reactor.core.publisher.Flux;

public class ReactorTest {
    public static void main(String[] args) {
        //Flux.range(1,10).buffer().subscribe(s-> System.out.println(s.size()));
        Flux.range(1,10).window(5).buffer().subscribe(s-> System.out.println(s.size()));
    }
}
