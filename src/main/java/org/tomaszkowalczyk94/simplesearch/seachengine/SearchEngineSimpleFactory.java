package org.tomaszkowalczyk94.simplesearch.seachengine;

public class SearchEngineSimpleFactory {

    public SearchEngine create() {
        return new SearchEngineImpl(new WordComparator());
    }
}
