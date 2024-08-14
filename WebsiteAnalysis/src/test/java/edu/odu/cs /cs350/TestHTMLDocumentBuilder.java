package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author cs-ddjor, Cgm2002, yomnaE1
 * 
*/

public class TestHTMLDocumentBuilder {

    private HTMLDocumentBuilder builder;
    private Set<URL> baseUrls;
    private Path baseDirectory;

    @BeforeEach
    public void setUp() throws Exception {
        builder = new HTMLDocumentBuilder();

        baseUrls = new HashSet<>();
        baseUrls.add(new URL("http://example.com"));
        baseUrls.add(new URL("http://example.org"));

        baseDirectory = Paths.get("src/test/resources/");
    }

    @Test
    public void testIntegrationWithBaseDirectoryAndURLs() throws Exception {
        builder.withBaseDirectory(baseDirectory);
        builder.withBaseURLs(baseUrls);
        builder.withContentFrom(new File("src/test/resources/sample.html"));
        builder.extractContent();

        HTMLDocument document = builder.build();

        assertThat(document, is(notNullValue()));
        assertThat(document.getAnchors(), is(not(empty())));
        assertThat(document.getImages(), is(not(empty())));
        assertThat(document.getScripts(), is(not(empty())));
        assertThat(document.getStyleSheets(), is(not(empty())));
    }

    @Test
    public void testIntegrationExtractAnchors() throws Exception {
        builder.withBaseDirectory(baseDirectory);
        builder.withBaseURLs(baseUrls);
        builder.withContentFrom(new File("src/test/resources/sample.html"));
        
        Set<Resource> anchors = builder.extractAnchors();

        assertThat(anchors, is(not(empty())));
        for (Resource anchor : anchors) {
            assertThat(anchor, is(instanceOf(Resource.class)));
        }
    }

    @Test
    public void testIntegrationExtractImages() throws Exception {
        builder.withBaseDirectory(baseDirectory);
        builder.withBaseURLs(baseUrls);
        builder.withContentFrom(new File("src/test/resources/sample.html"));
        
        Set<Resource> images = builder.extractImages();

        assertThat(images, is(not(empty())));
        for (Resource image : images) {
            assertThat(image, is(instanceOf(Resource.class)));
        }
    }

    @Test
    public void testIntegrationExtractScripts() throws Exception {
        builder.withBaseDirectory(baseDirectory);
        builder.withBaseURLs(baseUrls);
        builder.withContentFrom(new File("src/test/resources/sample.html"));
        
        Set<Resource> scripts = builder.extractScripts();

        assertThat(scripts, is(not(empty())));
        for (Resource script : scripts) {
            assertThat(script, is(instanceOf(Resource.class)));
        }
    }

    @Test
    public void testIntegrationExtractStyleSheets() throws Exception {
        builder.withBaseDirectory(baseDirectory);
        builder.withBaseURLs(baseUrls);
        builder.withContentFrom(new File("src/test/resources/sample.html"));
        
        Set<Resource> stylesheets = builder.extractStyleSheets();

        assertThat(stylesheets, is(not(empty())));
        for (Resource stylesheet : stylesheets) {
            assertThat(stylesheet, is(instanceOf(Resource.class)));
        }
    }

    @Test
    public void testIntegrationFullProcess() throws Exception {
        builder.withBaseDirectory(baseDirectory);
        builder.withBaseURLs(baseUrls);
        builder.withContentFrom(new File("src/test/resources/sample.html"));
        builder.extractContent();

        HTMLDocument document = builder.build();

        assertThat(document, is(notNullValue()));
        assertThat(document.getAnchors(), is(not(empty())));
        assertThat(document.getImages(), is(not(empty())));
        assertThat(document.getScripts(), is(not(empty())));
        assertThat(document.getStyleSheets(), is(not(empty())));
    }
}
