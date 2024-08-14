package edu.odu.cs.cs350;

import java.net.URL;
import java.nio.file.Path;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

/**
 * This class serves as a collection of different resources to be analyzed from
 * the website.
 * 
 * @Author: Cgm2002
 */

public class Resource {

    protected Path localPath;
    protected URL localURL;
    protected Locality location;
    protected ResourceKind typeOfResourceKind;
    protected long sizeOfFile;
    protected Set<HTMLDocument> foundOn;

    /**
     * Create an empty Resource.
     */
    public Resource() {
        this.localPath = null;
        this.localURL = null;
        this.location = null;
        this.typeOfResourceKind = null;
        this.sizeOfFile = 0;
        this.foundOn = new HashSet<>();

    }

    /**
     * Create a website with a path to a local directory and a collection of URLs.
     * 
     * @param path               path to a local directory
     * @param localURL           local URL
     * @param location           locality
     * @param typeOfResourceKind type of resource
     * @param sizeOfFile         size of the file
     * @param foundOn            set of html documents
     */
    public Resource(Path path, URL localURL, Locality location, ResourceKind typeOfResourceKind, long sizeOfFile,
            Set<HTMLDocument> foundOn) {
        this.localPath = path;
        this.localURL = localURL;
        this.location = location;
        this.typeOfResourceKind = typeOfResourceKind;
        this.sizeOfFile = sizeOfFile;
        this.foundOn = new HashSet<>(foundOn);

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
     * Get the URL
     * 
     * @return URL
     */
    public URL getURL() {
        return this.localURL;

    }

    /**
     * Set the URL
     * 
     * @param localURL
     */
    public void setURL(URL localURL) {
        this.localURL = localURL;

    }

    /**
     * Get the location
     * 
     * @return location
     */
    public Locality getLocality() {
        return this.location;

    }

    /**
     * Set the location
     * 
     * @param location
     */
    public void setLocality(Locality location) {
        this.location = location;

    }

    /**
     * Get the resource
     * 
     * @return typeOfResourceKind
     */
    public ResourceKind getResourceKind() {
        return this.typeOfResourceKind;

    }

    /**
     * Set the resource
     * 
     * @param typeOfResourceKind
     */
    public void setResourceKind(ResourceKind typeOfResourceKind) {
        this.typeOfResourceKind = typeOfResourceKind;

    }

    /**
     * Get the size of the file
     * 
     * @return sizeOfFile
     */
    public long getlong() {
        return this.sizeOfFile;

    }

    /**
     * Set the size of the file
     * 
     * @param sizeOfFile
     */
    public void setlong(long sizeOfFile) {
        this.sizeOfFile = sizeOfFile;

    }

    /**
     * Get the HTML documents for the Resource file.
     * 
     * @return get the html documents
     */
    public Set<HTMLDocument> getfoundHtmlDocuments() {
        return this.foundOn;

    }

    /**
     * Set the HTML documents for the Resource file.
     * 
     * @param foundOn set of html documents
     */
    public void setfoundHtmlDocuments(Set<HTMLDocument> foundOn) {
        this.foundOn = foundOn;

    }

    /**
     * Add a HTMl Document.
     * 
     * @param document a html document
     */
    public void addHtmlDocument(HTMLDocument document) {
        this.foundOn.add(document);
    }

    /**
     * Compare two resources to determine if they are equal.
     * 
     * @Author: Cgm2002
     * @param rhs object comparing to
     * @return true if they have the same path, localURL, location,
     *         typeOfResourceKind, sizeOfFile, and foundOn
     *         return false otherwise
     */
    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) {
            return true;
        }
        if (!(rhs instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) rhs;
        return Objects.equals(this.localPath, other.localPath) &&
                Objects.equals(this.localURL, other.localURL) &&
                Objects.equals(this.location, other.location) &&
                Objects.equals(this.sizeOfFile, other.sizeOfFile) &&
                Objects.equals(this.typeOfResourceKind, other.typeOfResourceKind) &&
                Objects.equals(this.foundOn, other.foundOn);
    }

    /**
     * Returns the hashcode of the resources
     * 
     * @Author: Cgm2002
     * @return hashCode representing the state of a resource
     */
    @Override
    public int hashCode() {
        return Objects.hash(localPath, localURL, localPath, sizeOfFile, typeOfResourceKind, foundOn);
    }

}