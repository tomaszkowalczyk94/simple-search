package org.tomaszkowalczyk94.simplesearch.parser;

import org.tomaszkowalczyk94.simplesearch.seachengine.Word;

class WordImpl implements Word {
    private final String word;

    public WordImpl(String word) {
        this.word = word;
    }
}
