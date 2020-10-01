package org.tomaszkowalczyk94.simplesearch.parser;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.simplesearch.memory.Searchable;
import org.tomaszkowalczyk94.simplesearch.memory.Word;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class SearchableImpl implements Searchable {

    String name;
    List<Word> words;
}
