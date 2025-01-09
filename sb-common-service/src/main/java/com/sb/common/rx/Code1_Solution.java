package com.sb.common.rx;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Code1_Solution {

    private static List<String> SUPER_HEROES = Arrays.asList(
            "Superman",
            "Batman",
            "Aquaman",
            "Asterix",
            "Captain America"
    );

    public static void main(String... args) {
        Observable.fromIterable(SUPER_HEROES)
//                .doOnNext(s -> System.out.println("Next >> " + s))
                .map(s->s+" 1")
                .doOnNext(s-> System.out.println(s))
                .doOnComplete(() -> System.out.println("Completion"))
                .subscribe();
    }
}
