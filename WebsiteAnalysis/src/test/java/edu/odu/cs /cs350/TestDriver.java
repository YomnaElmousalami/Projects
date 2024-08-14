package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;

public class TestDriver {
    @Test
    public void testGetWebPath() throws IOException {
        String[] args = { "https://google.com", "http://example.com" };
        var getWebPath = Driver.getWebPath(args).toString();
        assertEquals(getWebPath, args[0]);

        Exception exception = assertThrows(Exception.class, () -> {
            Driver.getBaseURLs(args);
        });

        assertTrue(exception instanceof java.io.IOException);
    }

    @Test
    void testGetWebPathInvalid() {
        String[] args = { "" };
        assertThrows(java.nio.file.InvalidPathException.class, () -> {
            Driver.getWebPath(args);
        });
    }

    @Test
    void testGetBaseURLs() throws Exception {
        String[] args1 = { "path/to/local/website", "http://example.com" };
        Set<URL> result1 = Driver.getBaseURLs(args1);

        Set<URL> expected1 = new HashSet<>();
        expected1.add(new URL("http://example.com"));
        assertEquals(expected1, result1);

        String[] args2 = { "path/to/local/website", "http://example.com", "http://example.com/page1",
                "http://example.com/page2" };
        Set<URL> result2 = Driver.getBaseURLs(args2);

        Set<URL> expected2 = new HashSet<>();
        expected2.add(new URL("http://example.com"));
        expected2.add(new URL("http://example.com/page1"));
        expected2.add(new URL("http://example.com/page2"));
        assertEquals(expected2, result2);

        String[] args3 = { "path/to/local/website", "http://example.com", "invalid-url" };
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Driver.getBaseURLs(args3);
        });
        assertTrue(exception.getCause() instanceof java.net.MalformedURLException);
    }

    @Test
    public void testPrintInsuffucientArgsMessage() throws Exception {
        String[] args = { "wow" };
        boolean thrown = false;

        try {
            Driver.main(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}