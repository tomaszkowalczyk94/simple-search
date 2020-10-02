package org.tomaszkowalczyk94.simplesearch;

import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.parser.Parser;
import org.tomaszkowalczyk94.simplesearch.parser.ParserSimpleFactory;
import org.tomaszkowalczyk94.simplesearch.seachengine.SearchEngine;
import org.tomaszkowalczyk94.simplesearch.seachengine.SearchEngineSimpleFactory;
import org.tomaszkowalczyk94.simplesearch.seachengine.SearchResult;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class CommandLine {

    void run(String[] args) {
        File folderToSearchIn = getFolderToSearchIn(args[0]);

        Parser parser = new ParserSimpleFactory().createDirParser(folderToSearchIn);
        Memory memory = parser.parse();
        SearchEngine searchEngine = new SearchEngineSimpleFactory().create();

        while (true) {
            askAndSearch(searchEngine, memory);
        }
    }

    private void askAndSearch(SearchEngine searchEngine,  Memory memory) {

        System.out.println("write a sentence: ");
        Scanner scanner = new Scanner(System. in);
        String text = scanner.nextLine();

        List<String> findBy = splitSearchTextToList(text);

        List<SearchResult> results = searchEngine.search(findBy, memory);

        results.forEach(searchResult -> System.out.println(String.format("file: %s. percent: %.2f", searchResult.getName(),+searchResult.getPercent()*100)));

    }

    private List<String> splitSearchTextToList(String text) {
        return Arrays.stream(text.split("\\s")).collect(Collectors.toList());
    }

    private File getFolderToSearchIn(String parameter) {
        final String userDir = System.getProperty("user.dir");
        return new File(userDir, parameter);
    }
}
