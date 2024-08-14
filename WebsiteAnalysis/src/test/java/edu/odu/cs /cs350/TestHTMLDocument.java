package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.sameInstance;

import java.util.HashSet;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * cs-ddjor001 && yomnaE1
 * 
 * 
 */

public class TestHTMLDocument {

    @Test
    public void testDefualtConstructor() {
        HTMLDocument new_html_document = new HTMLDocument();

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(nullValue()));

        assertThat(new_html_document.getScripts().size(), is(0));
        assertThat(new_html_document.getStyleSheets().size(), is(0));
        assertThat(new_html_document.getImages().size(), is(0));
        assertThat(new_html_document.getAnchors().size(), is(0));

        assertThat(new_html_document, equalTo(new HTMLDocument()));
        assertThat(new_html_document.hashCode(), equalTo(new HTMLDocument().hashCode()));
    }

    @Test
    public void testNonDefaultConstructor() {
        Set<Resource> scripts = new HashSet<>();
        Set<Resource> styleSheets = new HashSet<>();
        Set<Resource> images = new HashSet<>();
        Set<Resource> anchors = new HashSet<>();

        Script script = new Script();
        StyleSheet styleSheet = new StyleSheet();
        Image image = new Image();
        Anchor anchor = new Anchor();

        scripts.add(script);
        styleSheets.add(styleSheet);
        images.add(image);
        anchors.add(anchor);

        Path path = Paths.get("/path/to/local/directory");

        HTMLDocument new_html_document = new HTMLDocument(scripts, styleSheets, images, anchors, path);

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(notNullValue()));
        assertThat(new_html_document.getPath(), equalTo(path));

        assertThat(new_html_document.getScripts().size(), is(1));
        assertThat(new_html_document.getStyleSheets().size(), is(1));
        assertThat(new_html_document.getImages().size(), is(1));
        assertThat(new_html_document.getAnchors().size(), is(1));

        assertTrue(new_html_document.getScripts().contains(script));
        assertTrue(new_html_document.getStyleSheets().contains(styleSheet));
        assertTrue(new_html_document.getImages().contains(image));
        assertTrue(new_html_document.getAnchors().contains(anchor));

        assertThat(new_html_document, not(equalTo(new HTMLDocument())));
        assertThat(new_html_document.hashCode(), not(equalTo(new HTMLDocument().hashCode())));

    }

    @Test
    public void testSetScripts() {
        HTMLDocument new_html_document = new HTMLDocument();
        Set<Resource> scripts = new HashSet<>();
        Script script = new Script();
        scripts.add(script);

        new_html_document.setScripts(scripts);

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(nullValue()));

        assertThat(new_html_document.getScripts().size(), is(1));
        assertThat(new_html_document.getStyleSheets().size(), is(0));
        assertThat(new_html_document.getImages().size(), is(0));
        assertThat(new_html_document.getAnchors().size(), is(0));

        assertTrue(new_html_document.getScripts().contains(script));

        assertThat(new_html_document, not(equalTo(new HTMLDocument())));
        assertThat(new_html_document.hashCode(), not(equalTo(new HTMLDocument().hashCode())));
    }

    @Test
    public void testSetStyleSheets() {
        HTMLDocument new_html_document = new HTMLDocument();
        Set<Resource> styleSheets = new HashSet<>();
        StyleSheet styleSheet = new StyleSheet();
        styleSheets.add(styleSheet);

        new_html_document.setStyleSheets(styleSheets);

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(nullValue()));

        assertThat(new_html_document.getScripts().size(), is(0));
        assertThat(new_html_document.getStyleSheets().size(), is(1));
        assertThat(new_html_document.getImages().size(), is(0));
        assertThat(new_html_document.getAnchors().size(), is(0));

        assertTrue(new_html_document.getStyleSheets().contains(styleSheet));

        assertThat(new_html_document, not(equalTo(new HTMLDocument())));
        assertThat(new_html_document.hashCode(), not(equalTo(new HTMLDocument().hashCode())));

    }

    @Test
    public void testSetImages() {
        HTMLDocument new_html_document = new HTMLDocument();
        Set<Resource> images = new HashSet<>();
        Image image = new Image();
        images.add(image);

        new_html_document.setImages(images);

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(nullValue()));

        assertThat(new_html_document.getScripts().size(), is(0));
        assertThat(new_html_document.getStyleSheets().size(), is(0));
        assertThat(new_html_document.getImages().size(), is(1));
        assertThat(new_html_document.getAnchors().size(), is(0));

        assertTrue(new_html_document.getImages().contains(image));

        assertThat(new_html_document, not(equalTo(new HTMLDocument())));
        assertThat(new_html_document.hashCode(), not(equalTo(new HTMLDocument().hashCode())));

    }

    @Test
    public void testSetAnchors() {
        HTMLDocument new_html_document = new HTMLDocument();
        Set<Resource> anchors = new HashSet<>();
        Anchor anchor = new Anchor();
        anchors.add(anchor);

        new_html_document.setAnchors(anchors);

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(nullValue()));

        assertThat(new_html_document.getScripts().size(), is(0));
        assertThat(new_html_document.getStyleSheets().size(), is(0));
        assertThat(new_html_document.getImages().size(), is(0));
        assertThat(new_html_document.getAnchors().size(), is(1));

        assertTrue(new_html_document.getAnchors().contains(anchor));

        assertThat(new_html_document, not(equalTo(new HTMLDocument())));
        assertThat(new_html_document.hashCode(), not(equalTo(new HTMLDocument().hashCode())));

    }

    @Test
    public void testSetpath() {
        HTMLDocument new_html_document = new HTMLDocument();
        Path path = Paths.get("/path/to/local/directory");

        new_html_document.setPath(path);

        assertThat(new_html_document.getScripts(), notNullValue());
        assertThat(new_html_document.getStyleSheets(), notNullValue());
        assertThat(new_html_document.getImages(), notNullValue());
        assertThat(new_html_document.getAnchors(), notNullValue());
        assertThat(new_html_document.getPath(), is(notNullValue()));

        assertThat(new_html_document.getScripts().size(), is(0));
        assertThat(new_html_document.getStyleSheets().size(), is(0));
        assertThat(new_html_document.getImages().size(), is(0));
        assertThat(new_html_document.getAnchors().size(), is(0));

        assertThat(new_html_document.getPath(), equalTo(path));

        assertThat(new_html_document, not(equalTo(new HTMLDocument())));
        assertThat(new_html_document.hashCode(), not(equalTo(new HTMLDocument().hashCode())));

    }

    @Test
    public void testClone() throws Exception {
        Set<Resource> scripts = new HashSet<>();
        Set<Resource> styleSheets = new HashSet<>();
        Set<Resource> images = new HashSet<>();
        Set<Resource> anchors = new HashSet<>();

        Script script = new Script();
        StyleSheet styleSheet = new StyleSheet();
        Image image = new Image();
        Anchor anchor = new Anchor();

        scripts.add(script);
        styleSheets.add(styleSheet);
        images.add(image);
        anchors.add(anchor);

        Path path = Paths.get("/path/to/local/directory");

        HTMLDocument new_html_document = new HTMLDocument(scripts, styleSheets, images, anchors, path);

        HTMLDocument cloned = (HTMLDocument) new_html_document.clone();

        assertThat(cloned.getScripts(), equalTo(new_html_document.getScripts()));
        assertThat(cloned.getStyleSheets(), equalTo(new_html_document.getStyleSheets()));
        assertThat(cloned.getImages(), equalTo(new_html_document.getImages()));
        assertThat(cloned.getAnchors(), equalTo(new_html_document.getAnchors()));
        assertThat(cloned.getPath(), equalTo(new_html_document.getPath()));
        assertThat(cloned.getTotalImageSize(), equalTo(new_html_document.getTotalImageSize()));
        assertThat(cloned, equalTo(new_html_document));
        assertThat(cloned.hashCode(), equalTo(new_html_document.hashCode()));

        assertThat(cloned, not(sameInstance(new_html_document)));

        Path path2 = Paths.get("src/main/");
        cloned.setPath(path2);
        assertThat(cloned.getPath(), equalTo(path2));

        assertThat(cloned.hashCode(), not(equalTo(new_html_document.hashCode())));
        assertThat(cloned, not(equalTo(new HTMLDocument())));
        assertThat(cloned, not(equalTo(new_html_document)));

        Set<Resource> scripts2 = new HashSet<>();
        cloned.setScripts(scripts2);
        assertThat(cloned.getScripts(), equalTo(scripts2));

        assertThat(cloned.hashCode(), not(equalTo(new_html_document.hashCode())));
        assertThat(cloned, not(equalTo(new HTMLDocument())));
        assertThat(cloned, not(equalTo(new_html_document)));
    }

}