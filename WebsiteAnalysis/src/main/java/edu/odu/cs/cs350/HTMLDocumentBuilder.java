package edu.odu.cs.cs350;

import java.util.Set;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashSet;

import java.io.*; 

// citation: https://www.cs.odu.edu/~tkennedy/cs330/sum24/Public/designWebsiteAnalysis03/index.html

/** 
 * This class serves as a model of how to build an HTML Document
 * 
 * @author yomnaE1, bbake017, wbrow004
 * 
*/

public class HTMLDocumentBuilder {

    private Set<Resource> anchors;
    private Set<Resource> images;
    private Set<Resource> scripts;
    private Set<Resource> stylesheets;

    private Set<URL> baseUrls;
    private Path baseDirectory;

    private BufferedReader readBuffer;

    
    /**
     * Create an empty HTMLDocumentBuilder object
     */
    public HTMLDocumentBuilder() {
        this.anchors = new HashSet<>();
        this.images = new HashSet<>();
        this.scripts = new HashSet<>();
        this.stylesheets = new HashSet<>();

        this.baseUrls = new HashSet<>();
        this.baseDirectory = null;
        this.readBuffer = null;
    }

    /**
     * Constructs an HTML Document given a buffered reader
     * 
     * @author wbrow004
     * @param reader buffered reader to read the input
     * @return an instance of HTMLDocumentBuilder
    */
    public HTMLDocumentBuilder withContentFrom(BufferedReader reader) {
        this.readBuffer = reader;
        return this;
    }

    // Is this function being used?
    // https://www.cs.odu.edu/~tkennedy/cs330/latest/Public/designWebsiteAnalysis01/
    /**
     * Constructs an HTML Document given a file
     * 
     * @param file to be read from
    */
    public HTMLDocumentBuilder withContentFrom(File file) {
        FileReader reader;
        try {
            reader = new FileReader(file.getPath());

            this.readBuffer = new BufferedReader(reader);
            return this;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Constructs an HTML Document given a path
     * 
     * @param siteRoot a path to a specified directory
     * @return an instance of HTMLDocumentBuilder
     */
    public HTMLDocumentBuilder withBaseDirectory(Path siteRoot) {
        this.baseDirectory = siteRoot;
        return this;
    }

    /**
     * Constructs an HTML Document given a set of urls
     * 
     * @param urls a set of urls associated with a local copy of a website
     * @return an instance of HTMLDocumentBuilder
     */
    public HTMLDocumentBuilder withBaseURLs(Set<URL> urls) {
        this.baseUrls = urls;
        return this;
    }

    /**
     * Extracts Anchor tags from an HTML Document
     * 
     * @author yomnaE1
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if a file cannot be found
     * @return A set of anchor tags
    */
    public Set<Resource> extractAnchors() throws IOException, FileNotFoundException
    {
        SimpleHTMLParser parser = new SimpleHTMLParser("a", "href");
        Set<String> extractedStrings = new HashSet<>();
        extractedStrings.addAll(parser.extractAllURIs(this.readBuffer));

        // The URIs (URLs and Paths) are currently in string form.
        // As part of the analysis, they need to be converted to Resource objects

        for (String uriAsString : extractedStrings) {
            Resource anchor = new ResourceBuilder()
                .withType(ResourceKind.ANCHOR)
                .withURI(uriAsString)
                .usingURLContext(this.baseUrls)
                .usingSiteRootContext(this.baseDirectory)
                .determineLocality() // uriAsString was already supplied
                .determineFileSizeIfLocal()
                .normalizePathAndURL() // baseSiteDirectory was already supplied
                .classifyFiles()
                .build();

            this.anchors.add(anchor);
        }

        return this.anchors;
    }

    /**
     * Extracts Image tags from an HTML Document
     * 
     * @author yomnaE1
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if a file cannot be found
     * @return A set of image tags
    */
    public Set<Resource> extractImages() throws IOException, FileNotFoundException
    {
        SimpleHTMLParser parser = new SimpleHTMLParser("img", "src");
        Set<String> extractedStrings = new HashSet<>();
        extractedStrings.addAll(parser.extractAllURIs(this.readBuffer));

        // The URIs (URLs and Paths) are currently in string form.
        // As part of the analysis, they need to be converted to Resource objects

        for (String uriAsString : extractedStrings) {
            Resource image = new ResourceBuilder()
                .withType(ResourceKind.IMAGE)
                .withURI(uriAsString)
                .usingURLContext(this.baseUrls)
                .usingSiteRootContext(this.baseDirectory)
                .determineLocality() // uriAsString was already supplied
                .determineFileSizeIfLocal()
                .normalizePathAndURL() // baseSiteDirectory was already supplied
                .build();

            this.images.add(image);
        }

        return this.images;
    }

     /**
     * Extracts the Script tags from an HTML Document
     * 
     * @author bbake017
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if a file cannot be found
     * @return A set of script tags
    */
    public Set<Resource> extractScripts() throws IOException, FileNotFoundException
    {
        SimpleHTMLParser parser = new SimpleHTMLParser("script", "src");
        Set<String> extractedStrings = new HashSet<>();
        extractedStrings.addAll(parser.extractAllURIs(this.readBuffer));

        // The URIs (URLs and Paths) are currently in string form.
        // As part of the analysis, they need to be converted to Resource objects

        for (String uriAsString : extractedStrings) {
            Resource script = new ResourceBuilder()
                .withType(ResourceKind.SCRIPT)
                .withURI(uriAsString)
                .usingURLContext(this.baseUrls)
                .usingSiteRootContext(this.baseDirectory)
                .determineLocality() // uriAsString was already supplied
                .determineFileSizeIfLocal()
                .normalizePathAndURL() // baseSiteDirectory was already supplied
                .build();

            this.scripts.add(script);
        }

        return this.scripts;
    }

    /**
     * Extracts the StyleSheet tags from an HTML Document
     * 
     * @author bbake017
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if a file cannot be found
     * @return A list of stylesheet tags
    */
    public Set<Resource> extractStyleSheets() throws IOException, FileNotFoundException
    {
        SimpleHTMLParser parser = new SimpleHTMLParser("link", "href");
        Set<String> extractedStrings = new HashSet<>();
        extractedStrings.addAll(parser.extractAllURIs(this.readBuffer));

        // The URIs (URLs and Paths) are currently in string form.
        // As part of the analysis, they need to be converted to Resource objects

        for (String uriAsString : extractedStrings) {
            Resource styleSheet = new ResourceBuilder()
                .withType(ResourceKind.STYLESHEET)
                .withURI(uriAsString)
                .usingURLContext(this.baseUrls)
                .usingSiteRootContext(this.baseDirectory)
                .determineLocality() // uriAsString was already supplied
                .determineFileSizeIfLocal()
                .normalizePathAndURL() // baseSiteDirectory was already supplied
                .build();

            this.stylesheets.add(styleSheet);
        }

        return this.stylesheets;
    }

    /**
     * Extract html tags 
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if a file cannot be found
     * @return an instance of an HTMLDocumentBuilder
     */
    public HTMLDocumentBuilder extractContent() throws IOException, FileNotFoundException {
        this.extractAnchors();
        this.extractImages();
        this.extractScripts();
        this.extractStyleSheets();
        return this;
    }

    /**
     * Create an HTML Document given a set of script tags, stylesheet tags, image tags, anchor tags,
     * and a path to a base directory
     * @return a new HTML Document
     */
    public HTMLDocument build() {
        return new HTMLDocument(this.scripts, this.stylesheets,
        this.images, this.anchors, this.baseDirectory);
    }
}
