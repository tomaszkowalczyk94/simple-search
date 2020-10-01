package org.tomaszkowalczyk94.simplesearch.memory;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class MemoryImpl implements Memory {

    Map<String, Searchable> searchableByName;

    public MemoryImpl() {
        this.searchableByName = new HashMap<>();
    }

    @Override
    public Map<String, Searchable> getSearchables() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
