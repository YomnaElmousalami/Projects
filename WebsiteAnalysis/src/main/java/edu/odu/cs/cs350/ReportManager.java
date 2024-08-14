package edu.odu.cs.cs350;

import java.io.BufferedWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//citation: https://www.cs.odu.edu/~tkennedy/cs330/sum24/Public/designWebsiteAnalysis03/index.html

/**
 * Report manager class that sets source Data, determines base filename and creates and manages a Report Writer
 * 
 * @author cs-ddjorr0 & wbrow004 & bbake017
 */
public class ReportManager 
{
    private String baseFileName;
    private Website site;
    
    /**
     * Default constructor.
     */
    public ReportManager()
    {
        this.baseFileName = null;
        this.site = null;
    }

    /**
     * Constructs a ReportManager with the specified source data.
     * 
     * @param sourceData the Website to be used as source data
     */
    public ReportManager(Website sourceData) 
    {
        this.site = sourceData;

        LocalDateTime currTime = LocalDateTime.now();
        DateTimeFormatter currTimeFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        
        this.baseFileName = (currTime.format(currTimeFormat) + "-summary");

    }

    /**
     * Gets the source data (Website).
     * 
     * @return the Website object representing the source data
     */
    public Website getSourceData(){
        return this.site;
    }

    /**
     * Sets the source data (Website).
     * 
     * @param sourceData the Website to be used as source data
     */
    public void setSourceData(Website sourceData)
    {
        this.site = sourceData;
    }

    /**
     * Gets the base file name.
     * 
     * @return the base file name as a String
     */
    public String getBaseFileName() {
        return this.baseFileName;
    }

    /**
     * Sets the base file name.
     * 
     * @param baseFileName the base file name as a String
     */
    public void setBaseFileName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    /**
     * Determines the base file name based on the current date and time.
     */
    public void determineBaseFileName()
    {
        this.baseFileName = null;

        LocalDateTime currTime = LocalDateTime.now();
        DateTimeFormatter currTimeFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        this.baseFileName = (currTime.format(currTimeFormat) + "-summary");
    }

    /**
     * Writes the report names to the specified BufferedWriter.
     * 
     * @param nameWriter the BufferedWriter to write the report names to
     * @throws IOException if an I/O error occurs
     */
    public void writeReportNames(BufferedWriter nameWriter)
        throws IOException
    {
        String reportName = String.format("%s.txt", this.baseFileName);
        nameWriter.write(reportName);

        reportName = String.format("%s.json", this.baseFileName);
        nameWriter.write(reportName);

        reportName = String.format("%s.xlsx", this.baseFileName);
        nameWriter.write(reportName);

        nameWriter.flush();
    }

    /**
     * Writes all reports using different formats.
     * 
     * @throws IOException if an I/O error occurs
     */
    public void writeAll() throws IOException
    {
        ReportWriter writer = null;
        
        writer = new TextReportWriter();
        writer.setSourceData(this.site);
        writer.setBaseName(this.baseFileName);
        writer.write();

        writer = new JSONReportWriter();
        writer.setSourceData(this.site);
        writer.setBaseName(this.baseFileName);
        writer.write();

        writer = new ExcelReportWriter();
        writer.setSourceData(this.site);
        writer.setBaseName(this.baseFileName);
        writer.write();
    }
}
