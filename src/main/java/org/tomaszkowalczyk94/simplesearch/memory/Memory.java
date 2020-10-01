package org.tomaszkowalczyk94.simplesearch.memory;

import java.util.Map;

public interface Memory {

    Map<String, Searchable> getSearchables();

}
