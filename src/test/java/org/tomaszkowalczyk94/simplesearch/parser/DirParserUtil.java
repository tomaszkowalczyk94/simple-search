package org.tomaszkowalczyk94.simplesearch.parser;

import java.io.File;

public class DirParserUtil {

    public static Parser create(File file) {

        return new DirParser(file);
    }
}
