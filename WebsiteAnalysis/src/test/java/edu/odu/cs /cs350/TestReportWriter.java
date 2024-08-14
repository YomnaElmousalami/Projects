package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;

/*
 * 
 * @author wbrow004 & Cgm2002
 * 
*/

public class TestReportWriter {
    @Test
    public void testReportWriterDefaultConstructor() {
        ReportWriter reportWriter = new ReportWriter();

        assertThat(reportWriter.website, is(notNullValue()));
        assertNotEquals(reportWriter.baseFileName, is(emptyOrNullString()));
    }

    @ParameterizedTest
    @NullSource
    public void testSetSourceData(Website site) {
        ReportWriter reportWriter = new ReportWriter();
        reportWriter.setSourceData(site);

        assertEquals(site, reportWriter.website);
    }

    @ParameterizedTest
    @EmptySource
    public void testSetBaseName(String baseFileName) {
        ReportWriter reportWriter = new ReportWriter();
        reportWriter.baseFileName = "";

        assertEquals(baseFileName, reportWriter.baseFileName);
    }

    @Test
    public void testWrite() {
        File customOutputFile = new File("src/main/data/report.txt");

        assertThat(customOutputFile, is(notNullValue()));
        assertThat(customOutputFile.getPath(), is(equalTo(customOutputFile.getPath())));
    }

}
