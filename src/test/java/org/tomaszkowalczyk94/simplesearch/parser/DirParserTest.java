package org.tomaszkowalczyk94.simplesearch.parser;

import org.junit.jupiter.api.Test;
import org.tomaszkowalczyk94.simplesearch.TestsUtil;
import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.memory.Searchable;
import org.tomaszkowalczyk94.simplesearch.memory.Word;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DirParserTest {

    private List<String> filenamesInTestDir = List.of(
            "test1.txt",
            "Intel(R) QSFP+ Configuration Utility Release Notes.txt",
            "child dir/8mb file.txt",
            "child dir/sample-text-file.txt",
            "child dir/Chinese(Simplified).txt"
    );

    @Test
    void shouldParse() {
        //given
        File dir = TestsUtil.getTestFile("test_dir_with_txt_files");
        Parser parser = DirParserUtil.create(dir);

        //when
        Memory memory = parser.parse();

        //then
        Map<String, Searchable> output = memory.getSearchables();

        assertEquals(filenamesInTestDir.size(), output.size());
        filenamesInTestDir.forEach(name -> {
            assertTrue(output.containsKey(name));

            Searchable searchable = output.get(name);
            assertEquals(name, searchable.getName());
        });

        Searchable test1Searchable = output.get("test1.txt");

        List<String> listOfWords = test1Searchable.getWords()
                .stream()
                .map(Word::toString)
                .collect(Collectors.toList());

        assertEquals(List.of(
                "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit"
        ), listOfWords);


    }

    @Test
    void shouldThrowExceptionForNotDirectoryInput() {
        //given
        File testFile = TestsUtil.getTestFile("test_empty_file.txt");
        Parser parser = DirParserUtil.create(testFile);

        //when
        try {
            parser.parse();
            fail();
        } catch (Exception e) {
            //then
            assertTrue(e instanceof ParserException);
            assertEquals("input file is not directory", e.getMessage());
        }
    }


}