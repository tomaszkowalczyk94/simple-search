package org.tomaszkowalczyk94.simplesearch.seachengine;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.memory.Searchable;
import org.tomaszkowalczyk94.simplesearch.memory.Word;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchEngineImplTest {

    @Test
    void simpleSearchTest() {
        //given
        SearchEngineImpl searchEngine = new SearchEngineImpl();

        Memory memory = createStubMemory(List.of(
                createStubSearchable("testName1", List.of("test123", "test456")),
                createStubSearchable("testName2", List.of("xxxx", "aaaa"))
        ));

        //when
        List<SearchResult> searchResults = searchEngine.search(List.of("test123", "test456"), memory);

        //then
        assertEquals(1, searchResults.size());

        SearchResult searchResult = searchResults.get(0);
        assertEquals("testName1", searchResult.getName());
        assertEquals(1, searchResult.getPercent(), 0.01);
    }

    private Memory createStubMemory(List<Searchable> searchables) {
        Memory memory = mock(Memory.class);

        Map<String, Searchable> mapOfSearchables = searchables
                .stream()
                .collect(Collectors.toMap(Searchable::getName, searchable -> searchable));

        when(memory.getSearchables()).thenReturn(mapOfSearchables);

        return memory;
    }

    private Searchable createStubSearchable(String name, List<String> strings) {
        Searchable searchable = mock(Searchable.class);

        List<Word> words = strings.stream()
                .map(BasicWordImpl::new)
                .collect(Collectors.toList());

        when(searchable.getName()).thenReturn(name);
        when(searchable.getWords()).thenReturn(words);

        return searchable;
    }

    @AllArgsConstructor
    private static class BasicWordImpl implements Word {
        String string;

        @Override
        public String toString() {
            return string;
        }
    }
}