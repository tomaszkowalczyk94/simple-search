package org.tomaszkowalczyk94.simplesearch;

import java.io.File;
import java.net.URL;


public class TestsUtil {

    public static File getTestFile(String dir) {
        ClassLoader classLoader = TestsUtil.class.getClassLoader();
        URL resource = classLoader.getResource(dir);
        return new File(resource.getFile());
    }
}
