package org.tomaszkowalczyk94.simplesearch.parser;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.memory.Searchable;

import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class MemoryImpl implements Memory {

    Map<String, Searchable> searchableByName;

    @Override
    public Map<String, Searchable> getSearchables() {
        return searchableByName;
    }
}
