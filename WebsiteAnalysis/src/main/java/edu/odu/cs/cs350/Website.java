package edu.odu.cs.cs350;

import java.nio.file.Path;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.net.URL;

/**
 * This class serves as a model of a local copy of a website to be analyzed.
 * Contains a local directory path and a set of URLs.
 * 
 * @author cs-ddjor001
 */
public class Website implements Cloneable {
    private Path localPath;
    private Set<URL> localURls;
    private Set<HTMLDocument> documents;

    /**
     * Create an empty Website.
     */
    public Website() {
        this.localPath = null;
        this.localURls = new HashSet<>();
        this.documents = new HashSet<>();
    }

    /**
     * Create a website with a path to a local directory and a set of URLs.
     * 
     * @param path path to a local directory
     * @param urls set of local URLs
     * @param docs a set of HTML documents
     */
    public Website(Path path, Set<URL> urls, Set<HTMLDocument> docs) {
        this.localPath = path;
        this.localURls = new HashSet<>(urls);
        this.documents = new HashSet<>(docs);
    }

    /**
     * Get the path to a local directory.
     * 
     * @return path to a local directory
     */
    public Path getPath() {
        return this.localPath;
    }

    /**
     * Set the path to a local directory.
     * 
     * @param path path to a local directory
     */
    public void setPath(Path path) {
        this.localPath = path;
    }

    /**
     * Get a set of URLs of a website.
     * 
     * @return set of URLs
     */
    public Set<URL> getURLs() {
        return this.localURls;
    }

    /**
     * Set a set of URLs of a website.
     * 
     * @param urls a set of URLs
     */
    public void setURLs(Set<URL> urls) {
        this.localURls = new HashSet<>(urls);
    }

    /**
     * Get a set of HTML Documents of a website.
     * 
     * @return set of Documents
     */
    public Set<HTMLDocument> getHTMLDocuments() {
        return this.documents;
    }

    /**
     * Set a set of HTML DOcuments of a website.
     * 
     * @param docs a set of HTML Documents
     */
    public void setHTMLDocuments(Set<HTMLDocument> docs) {
        this.documents = docs; 
    }

    /**
     * Add an HTML Document to a Website, if not already present
     * 
     * @param doc an HTML Document
     */
    public void addHTMLDocument(HTMLDocument doc) {
        this.documents.add(doc);
    }

    /**
     * Remove an HTML Document from a Website, could be useful for pruning
     * 
     * @param doc an HTMl Document to remove
     * 
     */
    public void removeHTMLDocument(HTMLDocument doc) {
        this.documents.remove(doc);
    }

    /**
     * Compare two websites' paths and sets of URLs to determine if they are equal.
     * 
     * @param rhs object comparing to
     * @return true if both this and rhs Website paths and URLs are equal, false
     *         otherwise
     */
    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) {
            return true;
        }
        if (!(rhs instanceof Website)) {
            return false;
        }
        Website other = (Website) rhs;
        return Objects.equals(this.localPath, other.localPath) &&
                Objects.equals(this.localURls, other.localURls) &&
                Objects.equals(this.documents, other.documents);
    }

    /**
     * Compute the hashCode based on the path and URLs.
     * 
     * @return integer hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(localPath, localURls, documents);
    }

    /**
     * Create a copy of a website, separate but equal.
     */
    @Override
    public Website clone() {
        try {
            Website cloned = (Website) super.clone();
            cloned.localURls = new HashSet<>(this.localURls);
            cloned.documents = new HashSet<>(this.documents);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }

    /**
     * Print the path and URLs of a website.
     * 
     * @return string representation of the website
     */
    @Override
    public String toString() {
        return "Website{" +
               "localPath=" + localPath +
               ", localURls=" + localURls +
               '}';
    }
}
