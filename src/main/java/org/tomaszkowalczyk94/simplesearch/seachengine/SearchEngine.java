package org.tomaszkowalczyk94.simplesearch.seachengine;

import org.tomaszkowalczyk94.simplesearch.memory.Memory;

import java.util.List;

public interface SearchEngine {
    List<SearchResult> search(List<String> findBy, Memory memory);



}
