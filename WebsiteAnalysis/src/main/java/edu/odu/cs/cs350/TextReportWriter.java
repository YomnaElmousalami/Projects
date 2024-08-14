package edu.odu.cs.cs350;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Creates a plain file
 * 
 * @author wbrow004 and yomnaE1
 */
public class TextReportWriter extends ReportWriter {

    // For tests, I'm using the parent class
    // private Website website;
    // private String baseFileName;

    /**
     * Default Constructor that creates an empty TextReportWriter object
     */
    public TextReportWriter() {
        this.website = null;
        this.baseFileName = null;
    }

    /**
     * Constructs a TextReportWriter with the specified source data.
     * 
     * @param sourceData the Website to be used as source data
     */
    public TextReportWriter(Website sourceData) {
        this.website = sourceData;

        LocalDateTime currTime = LocalDateTime.now();
        DateTimeFormatter currTimeFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        this.baseFileName = (currTime.format(currTimeFormat) + "-summary");

    }

    /**
     * Sets the source data (Website).
     * 
     * @param sourceData the Website to be used as source data
     */
    @Override
    public void setSourceData(Website site) {
        this.website = site;
    }

    /**
     * Gets the source data (Website).
     * 
     * @return the Website object representing the source data
     */
    public Website getSourceData() {
        return this.website;
    }

    /**
     * 
     * 
     * Sets the base file name.
     * 
     * @param baseFileName the base file name as a String
     */
    @Override
    public void setBaseName(String baseFieldName) {
        this.baseFileName = baseFieldName;
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
     * Writes an output a website to a text document
     * 
     * @throws IOException if a set of html documents cannot be accessed
     */
    @Override
    public void write() throws IOException {
        Set<HTMLDocument> localPages = this.website.getHTMLDocuments();

        double totalImageSize = getTotalImageSize(localPages);

        Set<HTMLDocument> sortedDocs = getSortedDocs(localPages);

        writeTheOutput(totalImageSize, sortedDocs);
    }

    /**
     * Writes an output a website to a text document 
     * 
     * @param totalImageSize the total image size of all of the Html documents
     * @param sortedDocs a set of sorted HTML Documents from a local copy of a website
     * @throws IOException if a set of html documents cannot be accessed
     */
    private void writeTheOutput(double totalImageSize, Set<HTMLDocument> sortedDocs) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(".txt"));
        for (HTMLDocument document : sortedDocs) {
            writer.write(String.format("%.2f %s", document.getTotalImageSize(), document.getPath().toString()));
            writer.newLine();
        }
        writer.write(String.format("Total file size is: %.2f", totalImageSize));

        writer.close();
    }

    /**
     * Retrieves a sorted set of HTML Documents
     * 
     * @param localPages a set of HTML Documents for a local copy of a website
     * @return a set of sorted HTML Documents from a local copy of a website
     */
    private Set<HTMLDocument> getSortedDocs(Set<HTMLDocument> localPages) {
        Set<HTMLDocument> sortedDocs = localPages.stream()
                .sorted((doc1, doc2) -> doc1.getPath().toString()
                        .compareToIgnoreCase(doc2.getPath().toString()))
                .collect(Collectors.toSet());
        return sortedDocs;
    }

    /**
     * Retrieves the total size of images in HTML documents
     * 
     * @param localPages a set of HTML Documents for a local copy of a website
     * @return the total image size of all of the Html documents
     */
    private double getTotalImageSize(Set<HTMLDocument> localPages) {
        double totalImageSize = localPages.stream()
                .mapToDouble(HTMLDocument::getTotalImageSize)
                .sum();
        return totalImageSize;
    }

}
