package edu.odu.cs.cs350;

import java.io.IOException;

/**
 * Base class to create Excel, JSON, Text, etc. output files
 * 
 * @author wbrow004 & Cgm2002
 */
public class ReportWriter {

    protected Website website;
    protected String baseFileName;

    /**
     * Default Constructor
     */
    public ReportWriter() {
        this.website = new Website();
        this.baseFileName = "";
    }

    /**
     * Sets the source data from a website object
     * 
     * @param site website information that can be used to report data
     */
    public void setSourceData(Website site) {
        this.website = site;
    }

    /**
     * Sets the name of the report
     * 
     * @param baseFileName name of the report passed from the website data
     */
    public void setBaseName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    /**
     * Outputs the report
     */
    public void write() throws IOException{

    }
         
}
