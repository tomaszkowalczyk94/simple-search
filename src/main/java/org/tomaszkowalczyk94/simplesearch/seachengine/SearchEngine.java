package org.tomaszkowalczyk94.simplesearch.seachengine;

import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.memory.Word;

import java.util.List;

public interface SearchEngine {
    void search(List<Word> words, Memory memory);



}
