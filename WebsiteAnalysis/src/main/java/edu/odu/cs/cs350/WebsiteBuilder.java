package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// citations: https://www.cs.odu.edu/~tkennedy/cs330/sum24/Public/designWebsiteAnalysis03/index.html
//https://github.com/cstkennedy/cs350-examples/blob/master/Office-Hours/OfficeHours-DirectoryWalker/src/main/java/edu/odu/cs/cs350/examples/SimpleDirectoryWalker.java

// TODO: Have yet to finish this class, it's mostly a skeleton copied from above for reference

/**
 * This class serves as a model of how to build a website object.
 * 
 * @author yomnaE1 && cs-ddjor001 
 */
public class WebsiteBuilder {
    private Path path;
    private Set<URL> urls;
    Set<Path> allFiles;
    Set<Path> allDirectories;

    /**
     * Create an empty WebsiteBuilder object
     */
    public WebsiteBuilder() {
        this.path = null;
        this.urls = new HashSet<>();
        this.allFiles = new HashSet<>();
        this.allDirectories = new HashSet<>();
    }

    /**
     * 
     * Returns the path of a local copy of a website
     * 
     * @return the path of a local copy of a website
     * 
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * 
     * Set the path of local copy of a website
     * 
     * @param basePath path to the local copy of a website
     */
    public void setPath(Path basePath) {
        this.path = basePath;
    }

    /**
     * 
     * Returns the URLs of a local copy of a website
     * 
     * @return the URLs of a local copy of a website
     * 
     */
    public Set<URL> getURLs() {
        return this.urls;
    }

    /**
     * 
     * Set the collection of URLs of a local copy of a website
     * 
     * @param baseURLs collection of URLs to a local copy of a website
     */
    public void setURLs(Set<URL> baseURLs) {
        this.urls = baseURLs;
    }

    /**
     * Constructs a Website with a given path.
     * 
     * 
     * @param Path the local directory.
     * @return an instance of a WebsiteBuilder
     * 
     * 
     */
    // https://docs.oracle.com/javase/tutorial/essential/io/check.html
    // https://www.baeldung.com/java-file-directory-exists#:~:text=assertFalse(new%20File(%22invalid,Path%20tempFilePath%20%3D%20Files.
    public WebsiteBuilder withPath(Path path) {
        if (path == null)
            printNonExistentMessage();
        else if (!Files.isReadable(path) && !Files.isWritable(path) && !Files.isExecutable(path))
            printInaccessibleMessage();
        this.path = path;
        return this;
    }

    /**
     * Prints the error message when the user fails
     * to provide a directory that exists
     */
    public static void printNonExistentMessage() {
        System.err.println("The specifed Path is a non-existent directory.");
    }

    /**
     * Prints the error message when the user fails
     * to provide an accessible directory
     */
    public static void printInaccessibleMessage() {
        System.err.println("The specifed Path is an inaccessible directory.");
    }

    /**
     * Constructs a Website with a single given URL.
     * 
     * 
     * @param URL to be stored in a collection.
     * @return an instance of a WebsiteBuilder
     * 
     * 
     */
    public WebsiteBuilder withURL(URL baseURL) {
        if (this.urls == null) {
            this.urls = new HashSet<>();
        }

        this.urls.add(baseURL);
        return this;
    }

    /**
     * Constructs a Website with a collectio of URLs.
     * 
     * 
     * @param baseURLs collection of urls to be stored
     * @return an instance of a WebsiteBuilder
     * 
     * 
     */
    public WebsiteBuilder withURLs(Set<URL> baseURLs) {
        this.urls = baseURLs;
        return this;
    }

    /**
     * Examine a specified directory of interest and collect a list of all files and
     * sub-directories for a specified directory.
     *
     * @thorws IOException if directory could not be read
     * @return all of the files within a specified directory
     * 
     */
    public Set<Path> walkDirectory() throws IOException {
        Files.walk(this.path)
                .forEach((Path p1) -> {
                    if (Files.isRegularFile(p1)) {
                        this.allFiles.add(p1);
                    } else if (Files.isDirectory(p1)) {
                        this.allDirectories.add(p1);
                    }
                });
        return this.allFiles;
    }

    /**
     * Remove Non-HTML files from a specified directory
     * 
     * @param files that will be examined to determine if they are html files
     * @throws IOException if a set of html files cannot be read
     * @return a set of html files
     */
    public Set<Path> pruneNonHTMLFiles(Set<Path> files) throws IOException {
        Set<Path> htmlFiles = new HashSet<>();

        // citation for if statements in for-loop:
        // https://www.geeksforgeeks.org/path-getfilename-method-in-java-with-examples/
        for (Path p : files) {
            List<String> fileContents = Files.readAllLines(p); 
            if (p.getFileName().toString().toLowerCase().endsWith("html")
                    || p.getFileName().toString().toLowerCase().endsWith("htm")) {
                htmlFiles.add(p);
            }
            // in section 4.4.1 of Website Analysis - Requirements Definition
            else if (p.getFileName().toString().toLowerCase().endsWith("php")
                    || p.getFileName().toString().toLowerCase().endsWith("cgi")) {
                htmlFiles.add(p);
            } else if (fileContents.contains("<a href") || fileContents.contains("</a>")) {
                htmlFiles.add(p);
            } else if (fileContents.contains("<img src")) {
                htmlFiles.add(p);
            } else if (fileContents.contains("<script") || fileContents.contains("<script src")
                    || fileContents.contains("</script>")) {
                htmlFiles.add(p);
            } else if (fileContents.contains("<link")) {
                htmlFiles.add(p);
            }
        }

        return htmlFiles;
    }

    /**
     * Create a website given a specified directory, set of urls, and a set of
     * parsed documents
     *
     * @thorws IOException if directory could not be read
     * @throws FileNotFoundException is a file is not found
     * @return a local copy of a Website
     */
    public Website build() throws IOException, FileNotFoundException {
        Set<Path> files = walkDirectory();
        Set<Path> prunedFiles = pruneNonHTMLFiles(files);

        Set<HTMLDocument> parsedDocuments = new HashSet<>();
        for (Path htmlFile : prunedFiles) {
            // citations: https://blog.marcnuri.com/how-to-convert-file-to-path-in-java
            // https://www.geeksforgeeks.org/path-tofile-method-in-java-with-examples/
            BufferedReader buffer = new BufferedReader(new FileReader(htmlFile.toFile()));

            HTMLDocument doc = new HTMLDocumentBuilder()
                    .withContentFrom(buffer)
                    .withBaseDirectory(this.path) // needed for path normalization
                    .withBaseURLs(this.urls) // needed for internal/external classification
                    .extractContent() // exceptions can be thrown by this function
                    .build();

            parsedDocuments.add(doc);
        }

        Website site = new Website(this.path, this.urls, parsedDocuments);

        return site;

    }

}
