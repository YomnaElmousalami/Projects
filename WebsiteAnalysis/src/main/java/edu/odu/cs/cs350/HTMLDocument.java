package edu.odu.cs.cs350;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.nio.file.Path;

/**
 * This class reprisent a template for an HTML Document.
 * It contains the collection of scripts, stylesheets, images and anchor tags
 * It also has a path associated with it
 * 
 * @author cs-ddjor001
 * 
 */
public class HTMLDocument implements Cloneable {
    private Set<Resource> scripts;
    private Set<Resource> stylesheets;
    private Set<Resource> images;
    private Set<Resource> anchors;
    private Path fromRootDirectory;

    /**
     * Initialize an empty HTML Document
     */
    public HTMLDocument() {
        this.scripts = new HashSet<>();
        this.stylesheets = new HashSet<>();
        this.images = new HashSet<>();
        this.anchors = new HashSet<>();
        this.fromRootDirectory = null;
    }

    /**
     * Initialize an HTML Document with a provided set of scripts, stylesheets,
     * images and anchortags
     * 
     * @param new_scripts     a set of scripts
     * @param new_stylesheets a set of stylesheets
     * @param new_images      a set of images
     * @param new_anchors     a set of anchors
     * @param path            a path associated with the HTML document
     * 
     */
    public HTMLDocument(Set<Resource> new_scripts, Set<Resource> new_stylesheets,
            Set<Resource> new_images, Set<Resource> new_anchors, Path path) {
        this.scripts = new HashSet<>(new_scripts);
        this.stylesheets = new HashSet<>(new_stylesheets);
        this.images = new HashSet<>(new_images);
        this.anchors = new HashSet<>(new_anchors);
        this.fromRootDirectory = path;
    }

    /**
     * Get the path of the HTML Document
     * @return the path of the HTML Document
     */

   public Path getPath(){
       return this.fromRootDirectory;
    }

    /**
     * Set scripsts to a given set of scripts
     * 
     * @param path the path of the HTML Document
     */
    public void setPath(Path path){
        this.fromRootDirectory = path;
    }

    /**
     * Get a set of scritps
     * 
     * @return a set of scripts
     */
    public Set<Resource> getScripts() {
        return this.scripts;
    }

    /**
     * Set scripsts to a given set of scripts
     * 
     * @param new_scripts a set of scripts
     */
    public void setScripts(Set<Resource> new_scripts) {
        this.scripts = new_scripts;
    }

    /**
     * Get a set of stylesheets
     * 
     * @return a set of stylesheets
     */
    public Set<Resource> getStyleSheets() {
        return this.stylesheets;
    }

    /**
     * Set stylesheets to a given set of stylesheets
     * 
     * @param new_stylesheets a set of stylesheets
     */
    public void setStyleSheets(Set<Resource> new_stylesheets) {
        this.stylesheets = new_stylesheets;
    }

    /**
     * Get a set of images
     * 
     * @return a set of images
     */
    public Set<Resource> getImages() {
        return this.images;
    }

    /**
     * Set images to a given set of images
     * 
     * @param new_images a set of images
     */
    public void setImages(Set<Resource> new_images) {
        this.images = new_images;
    }

    /**
     * Get a set of anchor tags
     * 
     * @return a set of anchor tags
     */
    public Set<Resource> getAnchors() {
        return this.anchors;
    }

    /**
     * Set anchor tags to a given set of anchor tags
     * 
     * @param new_anchors a set of anchor tags
     */
    public void setAnchors(Set<Resource> new_anchors) {
        this.anchors = new_anchors;
    }

    /**
     * Calculate the total size of all images in this HTML document
     * 
     * @return the total size of all images in MiB
     */
    public double getTotalImageSize() {
        double totalSize = 0.0;
        for (Resource image : this.images) {
            totalSize += image.getlong();
        }
        return totalSize;
    }

    /**
     * Compare two HTML Documents for equvilanece.
     * To be considered equal, they must have the same set of images
     * stylesheets, scripts and anchor tags
     * 
     * @param rhs an Object to compare to
     * @return true if they have the same images, stylesheets, scripts and anchor
     *         tags, false otherwise
     * 
     */
    @Override
    public boolean equals(Object rhs) {
        if (this == rhs)
            return true;

        if (!(rhs instanceof HTMLDocument))
            return false;

        HTMLDocument other = (HTMLDocument) rhs;

        return  Objects.equals(this.scripts, other.scripts) &&
                Objects.equals(this.stylesheets, other.stylesheets) &&
                Objects.equals(this.images, other.images) &&
                Objects.equals(this.anchors, other.anchors) && 
                Objects.equals(this.fromRootDirectory, other.fromRootDirectory);
    }

    /**
     * Returns the hashcode of an html document
     * 
     * @return hashCode representing the state of an html document
     */
    @Override
    public int hashCode() {
        return Objects.hash(scripts, stylesheets, images, anchors, fromRootDirectory);
    }

    /**
     * Create a copy of a html document, separate but equal.
     */
    @Override
    public HTMLDocument clone() {
        try {
            HTMLDocument cloned = (HTMLDocument) super.clone();
            cloned.scripts = new HashSet<>(this.scripts);
            cloned.stylesheets = new HashSet<>(this.stylesheets);
            cloned.images = new HashSet<>(this.images);
            cloned.anchors = new HashSet<>(this.anchors);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }

}