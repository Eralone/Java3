package Lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class iteratMSTest_Lesson6 {

    @ParameterizedTest
    @DisplayName("Question 2")
    @MethodSource("data")
    void iteratMS(int[] ret, int[] ms) {
        for (int i = 0; i < ret.length; i++) {
            Assertions.assertEquals(ret[i], Main.iteratMS(ms)[i]);
        }

    }

    static Stream<Arguments> data(){
        return Stream.of(Arguments.arguments(new int[]{6, 7, 9, 7}, new int[]{2, 4, 6, 7, 9, 7}),
                Arguments.arguments(new int[]{6}, new int[]{1,2,6,5,2,3,4,6}),
                Arguments.arguments(new int[]{5,9}, new int[]{8,1,7,2,3,4,5,9}),
                Arguments.arguments(new int[]{3,2,5,6}, new int[]{5,2,6,7,8,9,1,4,3,2,5,6}),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 6, 7, 9}));
    }

    @ParameterizedTest
    @DisplayName("Question 3")
    @MethodSource("Q3")
    void bool(boolean b, int[] ms) {
//        Assertions.assertTimeout(Duration.ofSeconds(1), ()-> { //Если грузит боьше 1 секунды - то выдает ошибку (последующие тесты параметризированного типа продолжаются)
//            sleep(999);
            Assertions.assertEquals(b, Bool.bool(ms));
//        });
//        Assumptions.assumeTrue(b);// Если указать даже верное решение (с булевым выражением равным true, то все-равно выведет ошибку и не проведет весь параметризированный тест
    }

    static Stream<Arguments> Q3(){
        return Stream.of(Arguments.arguments(false, new int[]{6, 7, 9, 7, 4}),
                Arguments.arguments(false, new int[]{1,2,6,5,2,3,4,6}),
                Arguments.arguments(false, new int[]{8,1,7,2,3,4,5,9}),
                Arguments.arguments(false, new int[]{5,2,6,7,8,9,3,2,5,6,3,5,6,8,0,3,5,6,4}),
                Arguments.arguments(true, new int[]{2, 6, 7, 9,5,2,6,7,8,9,3,2,5,6,3,5,6,8,0,3,5,6,7,9,2,3,5,6,6,3,2,5,6,7,8,9,0,2,3,6,5,0}));
    }

}