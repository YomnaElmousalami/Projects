package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author cs-ddjor001 && Cgm2002
 * 
 */

public class TestResourceBuilder {

    private ResourceBuilder builder;
    private Set<URL> baseUrls;
    private Path baseDirectory;
    private Set<HTMLDocument> foundOn;

    @BeforeEach
    public void setUp() throws Exception {
        builder = new ResourceBuilder();

        baseUrls = new HashSet<>();
        baseUrls.add(new URL("http://example.com"));
        baseUrls.add(new URL("http://example.org"));

        baseDirectory = Paths.get("src/test/resources/");
        foundOn = new HashSet<>();
        foundOn.add(new HTMLDocument()); // Assuming HTMLDocument has a default constructor
    }

    @Test
    public void testIntegrationWithURLContext() throws Exception {
        Resource resource = builder
                .withType(ResourceKind.IMAGE)
                .withURI("http://example.com/image.png")
                .usingURLContext(baseUrls)
                .usingSiteRootContext(baseDirectory)
                .determineLocality()
                .determineFileSizeIfLocal()
                .normalizePathAndURL()
                .build();

        assertThat(resource, is(notNullValue()));
        assertThat(resource.getResourceKind(), is(ResourceKind.IMAGE));
        assertThat(resource.getURL(), is(notNullValue()));
        assertThat(resource.getLocality(), is(Locality.EXTERNAL));
    }

    @Test
    public void testIntegrationWithPathContext() throws Exception {
        Resource resource = builder
                .withType(ResourceKind.SCRIPT)
                .withURI("src/test/resources/script.js")
                .usingSiteRootContext(baseDirectory)
                .determineLocality()
                .determineFileSizeIfLocal()
                .normalizePathAndURL()
                .build();

        assertThat(resource, is(notNullValue()));
        assertThat(resource.getResourceKind(), is(ResourceKind.SCRIPT));
        assertThat(resource.getPath(), is(notNullValue()));
        assertThat(resource.getLocality(), is(Locality.INTERNAL));
    }

    @Test
    public void testIntegrationFullProcess() throws Exception {
        Resource resource = builder
                .withType(ResourceKind.ANCHOR)
                .withURI("http://example.com")
                .usingURLContext(baseUrls)
                .usingSiteRootContext(baseDirectory)
                .determineLocality()
                .determineFileSizeIfLocal()
                .normalizePathAndURL()
                .build();

        assertThat(resource, is(notNullValue()));
        assertThat(resource.getResourceKind(), is(ResourceKind.ANCHOR));
        assertThat(resource.getURL(), is(notNullValue()));
        assertThat(resource.getLocality(), is(Locality.EXTERNAL));
    }

    @Test
    public void testClassifyFiles() {
        ResourceBuilder resourceBuilder = new ResourceBuilder();
        try {
            var videoFile = resourceBuilder.withURI("test.mp4");
            videoFile = resourceBuilder.classifyFiles();
            assertEquals(videoFile, ResourceKind.VIDEO);

            var videoFile2 = resourceBuilder.withURI("test.mkv");
            videoFile2 = resourceBuilder.classifyFiles();
            assertEquals(videoFile2, ResourceKind.VIDEO);

            var archiveFile = resourceBuilder.withURI("test.zip");
            archiveFile = resourceBuilder.classifyFiles();
            assertEquals(archiveFile, ResourceKind.ARCHIVE);
            
            var archiveFile2 = resourceBuilder.withURI("test.tar.gz");
            archiveFile2 = resourceBuilder.classifyFiles();
            assertEquals(archiveFile2, ResourceKind.ARCHIVE);

            var archiveFile3 = resourceBuilder.withURI("test.tar");
            archiveFile3 = resourceBuilder.classifyFiles();
            assertEquals(archiveFile3, ResourceKind.ARCHIVE);

            var archiveFile4 = resourceBuilder.withURI("test.7z");
            archiveFile4 = resourceBuilder.classifyFiles();
            assertEquals(archiveFile4, ResourceKind.ARCHIVE);

            var audioFile = resourceBuilder.withURI("test.m4a");
            audioFile = resourceBuilder.classifyFiles();
            assertEquals(audioFile, ResourceKind.AUDIO);

            var audioFile2 = resourceBuilder.withURI("test.mka");
            audioFile2 = resourceBuilder.classifyFiles();
            assertEquals(audioFile2, ResourceKind.AUDIO);

            var audioFile3 = resourceBuilder.withURI("test.ogg");
            audioFile3 = resourceBuilder.classifyFiles();
            assertEquals(audioFile3, ResourceKind.AUDIO);

            var otherFile = resourceBuilder.withURI("test.xml");
            otherFile = resourceBuilder.classifyFiles();
            assertEquals(otherFile, ResourceKind.OTHER);

            var otherFile2 = resourceBuilder.withURI("test.cpp");
            otherFile2 = resourceBuilder.classifyFiles();
            assertEquals(otherFile2, ResourceKind.OTHER);

            var otherFile3 = resourceBuilder.withURI("test.exe");
            otherFile3 = resourceBuilder.classifyFiles();
            assertEquals(otherFile3, ResourceKind.OTHER);

            var otherFile4 = resourceBuilder.withURI("test.deb");
            otherFile4 = resourceBuilder.classifyFiles();
            assertEquals(otherFile4, ResourceKind.OTHER);

            var imageFile = resourceBuilder.withURI("test.jpg");
            imageFile = resourceBuilder.classifyFiles();
            assertEquals(imageFile, ResourceKind.IMAGE);

            var imageFile2 = resourceBuilder.withURI("test.png");
            imageFile2 = resourceBuilder.classifyFiles();
            assertEquals(imageFile2, ResourceKind.IMAGE);

            var imageFile3 = resourceBuilder.withURI("test.gif");
            imageFile3 = resourceBuilder.classifyFiles();
            assertEquals(imageFile3, ResourceKind.IMAGE);

            var imageFile4 = resourceBuilder.withURI("test.svg");
            imageFile4 = resourceBuilder.classifyFiles();
            assertEquals(imageFile4, ResourceKind.IMAGE);

            var scriptFile = resourceBuilder.withURI("test.js");
            scriptFile = resourceBuilder.classifyFiles();
            assertEquals(scriptFile, ResourceKind.SCRIPT);

            var styleSheetFile = resourceBuilder.withURI("test.css");
            styleSheetFile = resourceBuilder.classifyFiles();
            assertEquals(styleSheetFile, ResourceKind.STYLESHEET);

            var htmlFile = resourceBuilder.withURI("test.html");
            htmlFile = resourceBuilder.classifyFiles();
            assertEquals(htmlFile, ResourceKind.HTMLDOCUMENT);

            var noExtension = resourceBuilder.withURI("test");
            noExtension = resourceBuilder.classifyFiles();
            assertEquals(noExtension, ResourceKind.OTHER);
        
        } catch (IOException e) {
            e.printStackTrace();

            fail();
        }
    }
}
