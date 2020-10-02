package org.tomaszkowalczyk94.simplesearch.seachengine;

import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordComparatorTest {

    @Value
    private static class InputOfTest {
        String s1;
        String s2;
        LvlOfSimilarity.Level expectedLevelOfSimilarity;
    }

    @Test
    void countLevelOfSimilarity() {

        //given
        List<InputOfTest> inputs = List.of(
                new InputOfTest("test123", "test123", LvlOfSimilarity.Level.THE_SAME),
                new InputOfTest("tEst123", "Test123", LvlOfSimilarity.Level.THE_SAME),
                new InputOfTest("test", "testy", LvlOfSimilarity.Level.MINOR_DIFFERENCE),
                new InputOfTest("tes", "testy", LvlOfSimilarity.Level.DIFFERENT),
                new InputOfTest("te", "testy", LvlOfSimilarity.Level.DIFFERENT),
                new InputOfTest("eweliny", "ewelino", LvlOfSimilarity.Level.MINOR_DIFFERENCE),
                new InputOfTest("eweliny", "ewelinka", LvlOfSimilarity.Level.MINOR_DIFFERENCE),
                new InputOfTest("a", "ala", LvlOfSimilarity.Level.DIFFERENT),
                new InputOfTest("Ewelina", "Jolanta", LvlOfSimilarity.Level.DIFFERENT),
                new InputOfTest("Ela", "Ola", LvlOfSimilarity.Level.DIFFERENT)
        );

        WordComparator wordComparator = new WordComparator();

        inputs.forEach(input -> {
            //when
            LvlOfSimilarity lvlOfSimilarity = wordComparator.countLevelOfSimilarity(input.getS1(), input.getS2());

            //then
            assertEquals(input.getExpectedLevelOfSimilarity(), lvlOfSimilarity.getLevel(), ""+input.getS1()+" "+input.getS2());
        });

    }
}