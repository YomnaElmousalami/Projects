package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author cs-ddjorr0 & wbrow004 & bbake017
 * 
*/

public class TestReportManager {
    private Website website;
    private ReportManager manager;
    private Website other_website;

    @BeforeEach
    public void setUp() {
        website = new Website();
        manager = new ReportManager(website);

        Path path = Paths.get("/path/to/local/directory");
        Set<URL> urls = new HashSet<URL>();
        Set<HTMLDocument> docs = new HashSet<>();

        other_website = new Website(path, urls, docs);
    }

    @Test
    public void testNonDefaultConstructor() {
        assertThat(manager.getSourceData(), is(notNullValue()));
        assertThat(manager.getSourceData(), is(equalTo(website)));
        assertTrue(manager.getBaseFileName().contains("-summary"));
    }

    @Test
    public void testSetSourceData() {
        manager.setSourceData(other_website);
        assertThat(manager.getSourceData(), is(notNullValue()));
        assertThat(manager.getSourceData(), is(equalTo(other_website)));
    }

    @Test
    public void testSetBaseFileName() {
        String new_baseFileName = "Random Name";
        manager.setBaseFileName(new_baseFileName);
        assertThat(manager.getBaseFileName(), is(notNullValue()));
        assertThat(manager.getBaseFileName(), equalTo(new_baseFileName));
    }

    @Test
    public void testDetermineBaseFileName() {
        manager.determineBaseFileName();
        assertNotNull(manager.getBaseFileName());
        assertTrue(manager.getBaseFileName().contains("-summary"));
    }

    @Test
    public void testWriteReportNames() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

        manager.writeReportNames(bufferedWriter);
        String writtenNames = stringWriter.toString();

        assertThat(writtenNames, containsString(".txt"));
        assertThat(writtenNames, containsString(".json"));
        assertThat(writtenNames, containsString(".xlsx"));
    }

    @Test
    public void testWriteAll() {
        // This test may require mocking or further setup depending on the implementation
        // of the TextReportWriter, JSONReportWriter, and ExcelReportWriter classes.
        assertDoesNotThrow(() -> manager.writeAll());
    }

}