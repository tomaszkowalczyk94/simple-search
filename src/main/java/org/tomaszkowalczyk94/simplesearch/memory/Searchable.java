package org.tomaszkowalczyk94.simplesearch.memory;

import org.tomaszkowalczyk94.simplesearch.seachengine.Word;

import java.util.List;

public interface Searchable {
    String getName();
    List<Word> getListOfWords();
}
