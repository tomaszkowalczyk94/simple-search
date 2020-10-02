package org.tomaszkowalczyk94.simplesearch.parser;

import java.io.File;

public class ParserSimpleFactory {

    public Parser createDirParser(File dir) {
        return new DirParser(dir);
    }
}
