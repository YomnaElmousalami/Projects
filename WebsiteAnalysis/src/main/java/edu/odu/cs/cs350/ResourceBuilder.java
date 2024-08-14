package edu.odu.cs.cs350;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import java.net.URI;
import java.nio.file.Paths;

import java.util.HashSet;

/**
 * Builder class for creating Resource objects.
 * 
 * @author cs-ddjor001 & Cgm2002
 */
public class ResourceBuilder {
    private Path localPath;
    private URL localURL;
    private Locality locality;
    private long sizeOfFile;
    private Set<HTMLDocument> foundOn;
    private ResourceKind resourceType;

    private URL contextURL;
    private Path contextDirectory;

    /**
     * FIRST 4 FUNCTIONS TO Cgm2002
     * 
     * @author Cgm2002
     */

    /**
     * Default Constructor
     */
    public ResourceBuilder() {
        this.localPath = null;
        this.localURL = null;
        this.locality = null;
        this.sizeOfFile = 0;
        this.foundOn = new HashSet<>();
        this.resourceType = null;
        this.contextURL = null;
        this.contextDirectory = null;

    }

    /**
     * Create a certain kind of resource
     * 
     * @author Cgm2002
     * @param type kind of resource
     * @return an instance of a resource builder
     */
    public ResourceBuilder withType(ResourceKind type) {
        this.resourceType = type;
        return this;

    }

    /**
     * Create a resource from an URI as a string
     * 
     * @author Cgm2002
     * @param URI as a string
     * @return an instance of a resource builder
     */
    public ResourceBuilder withURI(String URI) {
        try {
            URI uri = new URI(URI);
            if (uri.isAbsolute()) {
                this.localURL = uri.toURL();
            } else {
                this.localPath = Paths.get(URI);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return this;
    }

    /**
     * Create a resource from an a list of URLs
     * 
     * @author Cgm2002
     * @param contextURL a set of URLs
     * @return an instance of a resource builder
     */
    public ResourceBuilder usingURLContext(Set<URL> contextURLs) {
        if (contextURLs != null && !contextURLs.isEmpty()) {
            this.contextURL = contextURLs.iterator().next();
        }
        return this;
    }

    /**
     * Create a resource from an Path directory
     * 
     * @author cs-ddjor001
     * @param contextDirectory path directory
     * @return an instance of a resource builder
     */
    public ResourceBuilder usingSiteRootContext(Path contextDirectory) {
        this.contextDirectory = contextDirectory;
        return this;
    }

    /**
     * Determine the locality of a file
     * 
     * @author cs-ddjor001
     * @return an instance of a resource builder
     */
    public ResourceBuilder determineLocality() {
        if (this.localURL != null) {
            this.locality = Locality.EXTERNAL;
        } else if (this.localPath != null && this.contextDirectory != null) {
            if (this.localPath.startsWith(this.contextDirectory)) {
                this.locality = Locality.INTERNAL;
            } else {
                this.locality = Locality.INTRAPAGE;
            }
        }

        return this;
    }

    /**
     * Determine the size of a file if it's local
     * 
     * @author cs-ddjor001
     * @return an instance of a resource builder
     */
    public ResourceBuilder determineFileSizeIfLocal() {
        if (this.localPath != null) {
            try {
                this.sizeOfFile = Files.size(this.localPath);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return this;

    }

    /**
     * Normaline Path and URL
     * 
     * @author cs-ddjor001
     * @return an instance of a resource builder
     */
    public ResourceBuilder normalizePathAndURL() {
        if (this.localPath != null && this.contextDirectory != null) {
            this.localPath = this.contextDirectory.resolve(this.localPath).normalize();
        }
        if (this.localURL == null && this.localPath != null) {
            try {
                this.localURL = this.localPath.toUri().toURL();
            } catch (MalformedURLException malformed) {
                malformed.printStackTrace();
            }
        }
        return this;
    }

    
    /**
     * Classify files as archive, video, audio or non-categorized
     * 
     * @author cs-ddjor001 && yomnaE1 && wes && Cgm2002
     * @return an instance of a resource builder
     * @throws IOException
     */
    public ResourceBuilder classifyFiles() throws IOException {
        String fileName = this.localPath.getFileName().toString().toLowerCase();
        if (fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".svg")) {
            this.resourceType = ResourceKind.IMAGE;
        }
        else if (fileName.endsWith(".js")) {
            this.resourceType = ResourceKind.SCRIPT;
        }
        else if (fileName.endsWith(".css")) {
            this.resourceType = ResourceKind.STYLESHEET;
        }
        else if (fileName.endsWith(".html")) {
            this.resourceType = ResourceKind.HTMLDOCUMENT;
        }
        else if (fileName.endsWith(".zip") || fileName.endsWith(".tar.gz") || fileName.endsWith(".tar") || fileName.endsWith(".7z")) {
            this.resourceType = ResourceKind.ARCHIVE;
        }
        else if (fileName.endsWith(".mkv") || fileName.endsWith(".mp4")) { 
            this.resourceType = ResourceKind.VIDEO;
        }
        else if (fileName.endsWith(".m4a") || fileName.endsWith(".mka") || fileName.endsWith(".ogg")) { 
            this.resourceType = ResourceKind.AUDIO;
        }
        else { // non-catogorized section
            this.resourceType = ResourceKind.OTHER;
        }
        return this;
    }

    /**
     * Build a resource object
     * 
     * @author cs-ddjor001
     * @return an instance of a resource itself
     */
    public Resource build() {
        return new Resource(this.localPath, this.localURL, this.locality, this.resourceType, this.sizeOfFile,
                this.foundOn);
    }
}