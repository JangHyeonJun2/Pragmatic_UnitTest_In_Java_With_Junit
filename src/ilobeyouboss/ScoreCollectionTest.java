package ilobeyouboss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * fail 정적 메서드는 org.junit.jupiter.api.Assertions 클래스에 있다.
 */
class ScoreCollectionTest {
    @Test
    void test() {

    }

    @Test
    void answerArithmeticMeanOfTwoNumbers() {
        //given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        //when
        int actualResult = collection.arithmeticMean();

        //then
        assertEquals(actualResult, 6);


    }
}