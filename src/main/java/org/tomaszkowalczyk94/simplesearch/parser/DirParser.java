package org.tomaszkowalczyk94.simplesearch.parser;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.memory.Searchable;
import org.tomaszkowalczyk94.simplesearch.memory.Word;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class DirParser implements Parser {

    File directory;

    @Override
    public Memory parse() {

        if (!directory.exists()) {
            throw new ParserException("catalog dont exists");
        }

        if (!directory.isDirectory()) {
            throw new ParserException("input file is not directory");
        }

        Collection<File> files = FileUtils.listFiles(directory, null, true);

        Map<String, Searchable> mapOfSearchables = files
                .stream()
                .map(this::mapToSearchable)
                .collect(Collectors.toMap(Searchable::getName, searchable -> searchable));

        return new MemoryImpl(mapOfSearchables);
    }

    @SneakyThrows
    private Searchable mapToSearchable(File file) {
        List<Word> words = new LinkedList<>();

        try (LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8")) {
            while (lineIterator.hasNext()) {
                Collection<Word> parsedWords = parseLine(lineIterator.nextLine());
                words.addAll(parsedWords);
            }
        }

        return new SearchableImpl(createName(file), words);
    }

    private Collection<Word> parseLine(String line) {
        return Arrays.stream(splitByWhiteChars(line))
                .map(this::removeUnnecessaryChars)
                .map(String::intern)
                .map(WordImpl::new)
                .collect(Collectors.toList());
    }

    private String[] splitByWhiteChars(String line) {
        return line.split("\\s");
    }

    private String removeUnnecessaryChars(String val) {
        return val
                .replace(".", StringUtils.EMPTY)
                .replace(",", StringUtils.EMPTY);
    }

    private String createName(File file) {
        return FilenameUtils.separatorsToUnix(directory.toPath().relativize(file.toPath())
                .toString());
    }

}
