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
        LevelOfSimilarity expectedLevelOfSimilarity;
    }

    @Test
    void countLevelOfSimilarity() {

        //given
        List<InputOfTest> inputs = List.of(
                new InputOfTest("test123", "test123", LevelOfSimilarity.EXACTLY_THE_SAME),
                new InputOfTest("tEst123", "Test123", LevelOfSimilarity.EXACTLY_THE_SAME),
                new InputOfTest("test", "testy", LevelOfSimilarity.MINOR_DIFFERENCE),
                new InputOfTest("tes", "testy", LevelOfSimilarity.DIFFERENT),
                new InputOfTest("te", "testy", LevelOfSimilarity.DIFFERENT),
                new InputOfTest("eweliny", "ewelino", LevelOfSimilarity.MINOR_DIFFERENCE),
                new InputOfTest("eweliny", "ewelinka", LevelOfSimilarity.MINOR_DIFFERENCE),
                new InputOfTest("a", "ala", LevelOfSimilarity.DIFFERENT),
                new InputOfTest("Ewelina", "Jolanta", LevelOfSimilarity.DIFFERENT),
                new InputOfTest("Ela", "Ola", LevelOfSimilarity.DIFFERENT)
        );

        WordComparator wordComparator = new WordComparator();

        inputs.forEach(input -> {
            //when
            LevelOfSimilarity levelOfSimilarity = wordComparator.countLevelOfSimilarity(input.getS1(), input.getS2());

            //then
            assertEquals(input.getExpectedLevelOfSimilarity(), levelOfSimilarity, ""+input.getS1()+" "+input.getS2());
        });

    }
}