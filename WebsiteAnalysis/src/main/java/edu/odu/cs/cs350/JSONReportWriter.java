package edu.odu.cs.cs350;

import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.cedarsoftware.util.io.*;

/**
 *  Creates an JSON file
 * 
 *  @author wbrow004 and bbake017
*/
public class JSONReportWriter extends ReportWriter {

    // Base attributes
    private Website website;
    private String baseFileName;

    /**
     * Create an empty JSONReportWriter
     */
    public JSONReportWriter() 
    {
        this.website = null;
        this.baseFileName = null;
    }

    /**
     * Initalize a JSONReportWriter with a given website
     * @param w a local copy of a website
     */
    public JSONReportWriter(Website w) {
        this.website = w;
        this.baseFileName = null;
    }

    /**
     * Get the local copy of a website
     * @return a local copy of a website
     */
    public Website getSourceData() {
        return this.website;
    }

    /**
     * Sets the source data from a website object
     * @param site website information that can be used to report data
     */
    @Override
    public void setSourceData(Website site) 
    {
        this.website = site;
    }

     /**
      * Get the baseFileName
      * @return the object's base name
      *  
      */
    public String getBaseName() {
        return this.baseFileName;
    }

    /**
     * Set this JSONReportWriter's baseFileName
     * @param baseFieldName name of the report passed from the website data
     */
    @Override
    public void setBaseName(String baseFieldName) 
    {
        this.baseFileName  = baseFieldName;
    }

    /**
     * Writes an output a website to a JSON document 
     */ 
    
    @Override
    public void write() 
    throws IOException
    {
        BufferedWriter writer;
        FileWriter file;
        int resourceCount = 0;

        //Retrieve a copy of the HTMLDocuments from the local copy of the website
        Set<HTMLDocument> localPages = this.website.getHTMLDocuments();

        /**
         * Formats a JSON string to display the statistics of each page detailing
         *  - Number of local images
         *  - Number of external images
         *  - Number of scripts referenced
         *  - Number of stylesheets utilized
         *  - Listing of images
         *  - Listing of scripts
         *  - Listing of stylesheets
         *  - Number of intra-page links
         *  - Number of inter-site links
         *  - Number of external links
         *  
         * And then displays the statistics of each image with
         * - Number of pages on which it is displayed
         * - Listing of pages on which it is displayed
         * 
         * and for archive, audio, video, or other non-categorized file shows
         * - File size
         * - Path to resource (relative to local site root)
         */

        Map<String, Object> outerLayer = new HashMap<>();
        List<Object> resourceEntries = new Vector<>();

        // Iterate through all the local pages
        for(HTMLDocument h : localPages) {

            Map<String, Object> pageEntry = new HashMap<>();

            // For determining the count of local and external images
            int localImageCount = 0;
            int externalImageCount = 0;
            for(Resource i : h.getImages()) {
                switch(i.getLocality()) {
                    case INTERNAL:
                        localImageCount++;
                        break;
                    case INTRAPAGE:
                        localImageCount++;
                        break;
                    case EXTERNAL:
                        externalImageCount++;
                        break;
                }
            }

            pageEntry.put("Local image count", localImageCount);
            pageEntry.put("External image count", externalImageCount);
            pageEntry.put("Script reference count", h.getScripts().size());
            pageEntry.put("Stylesheet count", h.getStyleSheets().size());

            // Used to list images on this page
            String img = "";
            for(Resource i: h.getImages()) {
                img += (i.getURL() + ", ");
            }
            pageEntry.put("Images used", img);

            // Used to list scripts on this page
            String scr = "";
            for(Resource s: h.getScripts()) {
                scr += (s.getURL() + ", ");
            }
            pageEntry.put("Scripts used", scr);

            // Used to list stylesheets on this page
            String sty = "";
            for(Resource s: h.getStyleSheets()) {
                sty += (s.getURL() + ", ");
            }
            pageEntry.put("Stylesheets used", sty);

            // For determining the count of local and external links
            int localLinkCount = 0;
            int intraLinkCount = 0;
            int externalLinkCount = 0;
            for(Resource a : h.getAnchors()) {
                switch(a.getLocality()) {
                    case INTERNAL:
                        localLinkCount++;
                        break;
                    case INTRAPAGE:
                        intraLinkCount++;
                        break;
                    case EXTERNAL:
                        externalLinkCount++;
                        break;
                }
            }

            pageEntry.put("Local link count", localLinkCount);
            pageEntry.put("Intrapage link count", intraLinkCount);
            pageEntry.put("External link count", externalLinkCount);

            resourceEntries.add(pageEntry);

            // First iterate through the images
            for(Resource i: h.getImages()) {
                Map<String, Object> resourceEntry = new HashMap<>();

                resourceEntry.put("Type of resource", "Image");
                resourceEntry.put("Number of appearances", i.getfoundHtmlDocuments().size());
                String docs = "";
                for(HTMLDocument docu : i.getfoundHtmlDocuments()) {
                    docs += (docu.getPath() + ", ");
                }
                resourceEntry.put("Appearances", docs);
                
                resourceEntries.add(resourceEntry);
                resourceCount++;
            }
            
            // Finally iterate through the anchors (Archives, audios, videos, and other stuff)
            for(Resource a: h.getAnchors()) {

                Map<String, Object> resourceEntry = new HashMap<>();

                switch(a.getResourceKind()) {
                    case ARCHIVE: 

                        resourceEntry.put("Type of resource", "Archive");
                        resourceEntry.put("File size", a.getlong());
                        resourceEntry.put("Path to resource", a.getPath());

                        resourceEntries.add(resourceEntry);
                        resourceCount++;
                        break;
                    case AUDIO:                       

                        resourceEntry.put("Type of resource", "Audio");
                        resourceEntry.put("File size", a.getlong());
                        resourceEntry.put("Path to resource", a.getPath());

                        resourceEntries.add(resourceEntry);
                        resourceCount++;
                        break;
                    case VIDEO:

                        resourceEntry.put("Type of resource", "Video");
                        resourceEntry.put("File size", a.getlong());
                        resourceEntry.put("Path to resource", a.getPath());

                        resourceEntries.add(resourceEntry);
                        resourceCount++;
                        break;
                    case OTHER:
                        resourceEntry.put("Type of resource", "Non-categorized file");
                        resourceEntry.put("File size", a.getlong());
                        resourceEntry.put("Path to resource", a.getPath());

                        resourceEntries.add(resourceEntry);
                        resourceCount++;
                    
                }
            }
        }

        // The contents of what will be turned into a JSON standard
        outerLayer.put("", resourceEntries);
        outerLayer.put("Total # of resources", resourceCount);

        // The arguments for the json file so it's readable
        Map<String, Object> args = new HashMap<>();
        args.put(JsonWriter.PRETTY_PRINT, true);
        args.put(JsonWriter.TYPE, false);

        // The final output to be put onto the JSON file
        String json = JsonWriter.objectToJson(outerLayer, args); 

        // Configure the BufferedWriters destination to be a JSON file
        file = new FileWriter(baseFileName + ".JSON");
        writer = new BufferedWriter(file);

        // Write the JSON contents and close
        writer.write(json);
        writer.close();
    }
}
