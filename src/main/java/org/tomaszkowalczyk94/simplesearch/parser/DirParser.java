package org.tomaszkowalczyk94.simplesearch.parser;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.simplesearch.memory.Memory;

import java.io.File;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class DirParser implements Parser {

    File directory;

    @Override
    public Memory parse() {
        if(!directory.isDirectory()) {
            throw new ParserException("input file is not directory");
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

}
