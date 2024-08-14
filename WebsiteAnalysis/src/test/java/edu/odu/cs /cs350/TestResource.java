package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @Author: Cgm2002
 */

public class TestResource {

    @Test
    public void testDefualtConstructor() {
        Resource resource = new Resource();
        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource, is(new Resource()));
        assertThat(resource.hashCode(), equalTo(new Resource().hashCode()));
        assertThat(resource.toString(), notNullValue());
    }

    @Test
    public void testNonDefaultConstructor() {
        Path path = Paths.get("src/test/resources/cs-landing-page");
        URL localURL = null;
        Locality location = Locality.EXTERNAL;
        ResourceKind typeOfResourceKind = ResourceKind.ANCHOR;
        long sizeOfFile = 1;
        Set<HTMLDocument> foundHtmlDocument = new HashSet<>();

        Resource resource = new Resource(path, localURL, location, typeOfResourceKind, sizeOfFile, foundHtmlDocument);

        assertThat(resource.getPath(), is(path));
        assertThat(resource.getURL(), is(localURL));
        assertThat(resource.getLocality(), is(location));
        assertThat(resource.getResourceKind(), is(typeOfResourceKind));
        assertThat(resource.getlong(), is(1L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource.getfoundHtmlDocuments(), is(foundHtmlDocument));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());

    }

    @Test
    public void testSetPath() {
        Path path = Paths.get("src/test/");
        Resource resource = new Resource();

        resource.setPath(path);

        assertThat(resource.getPath(), is(notNullValue()));
        assertThat(resource.getPath(), is(path));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());
    }

    @Test
    public void testSetURL() throws Exception {
        URL localUrl = new URL("http://www.google.com");
        Resource resource = new Resource();

        resource.setURL(localUrl);

        assertThat(resource.getURL(), is(notNullValue()));
        assertThat(resource.getURL(), is(localUrl));
        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());

    }

    @Test
    public void testSetLocality() {
        Locality location = Locality.INTERNAL;
        Resource resource = new Resource();

        resource.setLocality(location);

        assertThat(resource.getLocality(), is(notNullValue()));
        assertThat(resource.getLocality(), is(location));
        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());

    }

    @Test
    public void testSetResourceKind() {
        ResourceKind typeOfResourceKind = ResourceKind.AUDIO;
        Resource resource = new Resource();

        resource.setResourceKind(typeOfResourceKind);

        assertThat(resource.getResourceKind(), is(notNullValue()));
        assertThat(resource.getResourceKind(), is(typeOfResourceKind));
        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());

    }

    @Test
    public void testSetlong() {
        long sizeOfFile = 1;
        Resource resource = new Resource();

        resource.setlong(sizeOfFile);

        assertThat(resource.getlong(), is(1L));
        assertThat(resource.getlong(), is(sizeOfFile));
        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(0));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());

    }

    @Test
    public void testSetHTMLDocuments() {
        Set<HTMLDocument> docs = new HashSet<HTMLDocument>();
        docs.add(new HTMLDocument());
        Resource resource = new Resource();
        resource.setfoundHtmlDocuments(docs);

        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(1));
        assertThat(resource.getfoundHtmlDocuments(), is(docs));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());
    }

    @Test
    public void testAddHTMLDocument() {
        Resource resource = new Resource();
        HTMLDocument doc = new HTMLDocument();
        resource.addHtmlDocument(doc);

        assertThat(resource.getPath(), is(nullValue()));
        assertThat(resource.getURL(), is(nullValue()));
        assertThat(resource.getLocality(), is(nullValue()));
        assertThat(resource.getResourceKind(), is(nullValue()));
        assertThat(resource.getlong(), is(0L));
        assertThat(resource.getfoundHtmlDocuments(), is(notNullValue()));
        assertThat(resource.getfoundHtmlDocuments().size(), is(1));
        assertThat(resource, not(equalTo(new Resource())));
        assertThat(resource.hashCode(), not(equalTo(new Resource().hashCode())));
        assertThat(resource.toString(), notNullValue());
    }
}