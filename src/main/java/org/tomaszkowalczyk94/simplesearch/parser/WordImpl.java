package org.tomaszkowalczyk94.simplesearch.parser;

import org.tomaszkowalczyk94.simplesearch.memory.Word;

class WordImpl implements Word {
    private final String word;

    WordImpl(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}
