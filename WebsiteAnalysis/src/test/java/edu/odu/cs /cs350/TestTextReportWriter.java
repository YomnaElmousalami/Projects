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
 *@author yomnaE1
 * 
 * 
*/

public class TestTextReportWriter 
{
    @ParameterizedTest
    @NullSource
    public void testSetSourceData(Website site) 
    {
        TextReportWriter reportWriter = new TextReportWriter();
        reportWriter.setSourceData(site);

        assertEquals(site, reportWriter.getSourceData());
    }

    @ParameterizedTest
    @EmptySource
    public void testSetBaseName(String baseFileName) 
    {
        TextReportWriter reportWriter = new TextReportWriter();
        reportWriter.setBaseName("");
        
        assertEquals(baseFileName, reportWriter.getBaseFileName());
    }

    @Test
    public void testWrite() 
    {
        File customOutputFile = new File("report.txt");

        assertThat(customOutputFile, is(notNullValue()));
        assertThat(customOutputFile.getPath(), is(equalTo(customOutputFile.getPath())));

        TextReportWriter writer = new TextReportWriter();
        writer.setBaseName("report");
        writer.setSourceData(new Website());

        // Checks if these values are holding data
        for(HTMLDocument localPage : writer.website.getHTMLDocuments())
        {
            assertEquals(localPage.getPath(), is(notNullValue()));
            assertEquals(localPage.getImages(), is(notNullValue()));
            assertEquals(localPage.getScripts(), is(notNullValue()));
            assertEquals(localPage.getAnchors(), is(notNullValue()));
            assertEquals(localPage.getTotalImageSize(), is(notNullValue()));
            assertEquals(localPage.getStyleSheets(), is(notNullValue()));
        }

        // Checks for sizes of the lists
        for (HTMLDocument localPage : writer.website.getHTMLDocuments()) {

            assertEquals(localPage.getPath(), is(notNullValue()));
            assertEquals(localPage.getImages().size(), is(0));
            assertEquals(localPage.getScripts().size(), is(0));
            assertEquals(localPage.getAnchors().size(), is(0));
            assertEquals(localPage.getTotalImageSize(), is(0));
            assertEquals(localPage.getStyleSheets().size(), is(0));
        }
    }   
}
