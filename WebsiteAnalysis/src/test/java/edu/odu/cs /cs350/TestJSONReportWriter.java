package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

/**
 * 
 * @author bbake017
 * 
*/

public class TestJSONReportWriter 
{
    
    @Test
    public void testDefaultConstuctor() {

        JSONReportWriter writer = new JSONReportWriter();
        assertThat(writer.getBaseName(), is(null));
        assertThat(writer.getSourceData(), is(null));
    }

    @ParameterizedTest
    @NullSource
    public void testSetSourceData(Website site) 
    {
        JSONReportWriter reportWriter = new JSONReportWriter();
        reportWriter.setSourceData(site);

        assertEquals(site, reportWriter.getSourceData());
    }

    @ParameterizedTest
    @EmptySource
    public void testSetBaseName(String baseFileName) 
    {
        JSONReportWriter reportWriter = new JSONReportWriter();
        reportWriter.setBaseName(baseFileName);
        
        assertEquals(baseFileName, reportWriter.getBaseName());
    }


    // Thinking more about the way we implemented the ReportWriter classes could have done differently to make this test a lot easier to solve
    @Test
    public void testWrite() throws Exception
    {
        Website website = new Website();
        JSONReportWriter writer = new JSONReportWriter();
        writer.setBaseName("Report");
        writer.setSourceData(website);

        writer.write();

        assertEquals("Report", writer.getBaseName());
    }
}
