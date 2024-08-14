package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;


/**
 * @author wbrow004
 * 
*/
public class TestExcelReportWriter 
{
    
    @ParameterizedTest
    @NullSource
    public void testSetSourceData(Website site) 
    {
        ExcelReportWriter reportWriter = new ExcelReportWriter();
        reportWriter.setSourceData(site);

        assertEquals(site, reportWriter.website);
    }

    @ParameterizedTest
    @EmptySource
    public void testSetBaseName(String baseFileName) 
    {
        ExcelReportWriter reportWriter = new ExcelReportWriter();
        reportWriter.baseFileName = "";
        assertEquals(baseFileName, reportWriter.baseFileName);
    }

    @Test
    public void testWrite() 
    {
        File customOutputFile = new File("Report.xlsx");

        assertThat(customOutputFile, is(notNullValue()));
        assertThat(customOutputFile.getPath(), is(equalTo(customOutputFile.getPath())));

        ExcelReportWriter writer = new ExcelReportWriter();

        writer.setBaseName("20240723-summary");
        writer.setSourceData(new Website());
        
        // Checks if these values are holding data
        for (HTMLDocument document : writer.website.getHTMLDocuments()) {

            assertEquals(document.getPath(), is(notNullValue()));
            assertEquals(document.getImages(), is(notNullValue()));
            assertEquals(document.getScripts(), is(notNullValue()));
            assertEquals(document.getAnchors(), is(notNullValue()));
            assertEquals(document.getTotalImageSize(), is(notNullValue()));
            assertEquals(document.getStyleSheets(), is(notNullValue()));
        }

        // Checks for sizes of the lists
        for (HTMLDocument document : writer.website.getHTMLDocuments()) {

            assertEquals(document.getPath(), is(notNullValue()));
            assertEquals(document.getImages().size(), is(0));
            assertEquals(document.getScripts().size(), is(0));
            assertEquals(document.getAnchors().size(), is(0));
            assertEquals(document.getTotalImageSize(), is(0));
            assertEquals(document.getStyleSheets().size(), is(0));
        }
    }
}
