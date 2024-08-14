package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author yomnaE1, cs-ddjor001, Cgm2002, wbrow004, bbake017
 * 
 */

public class TestWebsiteBuilder {

     private WebsiteBuilder builder;
     private Path basePath;
     private Set<URL> base_urls;

     // Wes
     @Test
     public void testDefaultConstructor() throws FileNotFoundException, IOException {
          // Constructor contains: path, urls, allFiles, allDirectories
          WebsiteBuilder web1 = new WebsiteBuilder();
          assertThat(web1.getURLs(), is(notNullValue()));
          assertThat(web1.getPath(), is(nullValue()));
          assertThat(web1.allFiles, is(notNullValue()));
          assertThat(web1.allDirectories, is(notNullValue()));

          WebsiteBuilder web2 = new WebsiteBuilder();
          assertThat(web2.getURLs(), is(notNullValue()));
          assertThat(web2.getPath(), is(nullValue()));
          assertThat(web2.allFiles, is(notNullValue()));
          assertThat(web2.allDirectories, is(notNullValue()));
     }

     @Test
     public void testSetPath() throws MalformedURLException, IOException {
          Path path = Paths.get("src/test/");
          WebsiteBuilder web1 = new WebsiteBuilder();

          web1.setPath(path);

          assertThat(web1.getPath(), is(notNullValue()));
          assertThat(web1.getPath(), is(path));

          assertThat(web1.getURLs(), is(notNullValue()));

          WebsiteBuilder web2 = new WebsiteBuilder();
          web2.setPath(path);
          assertThat(web1.withPath(path), is(web2));

          WebsiteBuilder web3 = new WebsiteBuilder();
          WebsiteBuilder web4 = new WebsiteBuilder();

          URL baseURL = new URL("https://www.google.com");
          assertThat(web1.withURL(baseURL), equalTo(web3.withURL(baseURL)));
          assertThat(web1.withURLs(web4.getURLs()), equalTo(web3.withURLs(web4.getURLs())));
          assertThat(web1.build(), is(notNullValue()));
     }

     @Test
     public void testSetURLs() throws IllegalArgumentException, MalformedURLException, IOException {
          Set<URL> urls = new HashSet<>();
          urls.add(new URL("https://www.google.com"));
          WebsiteBuilder web1 = new WebsiteBuilder();

          web1.setURLs(urls);

          assertThat(web1.getPath(), is(nullValue()));
          assertThat(web1.getURLs(), is(notNullValue()));
          assertThat(web1.getURLs(), is(urls));

          WebsiteBuilder web2 = new WebsiteBuilder();

          // referenced this website to learn how to test expected exceptions:
          // https://www.geeksforgeeks.org/junit-5-expected-exception/
          IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
               web1.withPath(web2.getPath());
          });
          assertThat("The specifed Path is not a directory", equalTo(exception.getMessage()));

          URL baseURL = new URL("https://www.google.com");
          WebsiteBuilder web3 = new WebsiteBuilder();
          web3.setURLs(urls);
          assertThat(web1.withURL(baseURL), equalTo(web3));
          assertThat(web1.withURLs(urls), equalTo(web3));
          assertThat(web1.build(), is(notNullValue()));
     }

     @Test
     public void testIntegrationWithPath() throws Exception {
          builder = new WebsiteBuilder();
          basePath = Paths.get("src/test/resources/");

          builder.withPath(basePath);

          Website web = builder.build();

          assertThat(web, is(notNullValue()));
          assertThat(web.getPath(), is(not(null)));
          assertThat(web.getHTMLDocuments(), is(not(empty())));
     }

     @Test
     public void testIntegrationWithURL() throws Exception {
          builder = new WebsiteBuilder();
          URL baseURL = new URL("https://www.google.com");

          builder.withURL(baseURL);

          Website web = builder.build();

          assertThat(web.getURLs(), is(not(empty())));

     }

     // Bryan
     @Test
     public void testWithURLs() throws MalformedURLException, IOException {
          builder = new WebsiteBuilder();
          base_urls = new HashSet<>();
          base_urls.add(new URL("http://example.com"));
          base_urls.add(new URL("http://google.com"));

          builder.withURLs(base_urls);

          Website web = builder.build();

          assertThat(web.getURLs(), is(not(empty())));
          assertThat(web.getURLs().size(), is(2));
          assertTrue(web.getURLs().toString().contains("http://example.com"));
          assertTrue(web.getURLs().toString().contains("http://google.com"));
     }

     @Test
     public void testIntegrationWalkDirectory() throws IOException {
         builder = new WebsiteBuilder();
         basePath = Paths.get("src/test/resources/");
         builder.withPath(basePath);
 
         Set<Path> files = builder.walkDirectory();

         assertThat(files, is(not(empty())));
     }

     @Test
     public void testpruneNonHTMLFiles() throws IOException {
          // Set up the builder with a base path and walk the directory
          WebsiteBuilder builder = new WebsiteBuilder();
          Path basePath = Paths.get("src/test/resources/");
          builder.withPath(basePath);
          Set<Path> files = builder.walkDirectory();

          // Prune non-HTML files
          Set<Path> prunedFiles = builder.pruneNonHTMLFiles(files);

          // Verify the pruned files only contain HTML or relevant files
          for (Path file : prunedFiles) {
               String fileName = file.getFileName().toString().toLowerCase();
               List<String> fileContents = Files.readAllLines(file);
               assertThat(
                         fileName.endsWith(".html") || fileName.endsWith(".htm") ||
                                   fileName.endsWith(".php") || fileName.endsWith(".cgi"),
                         is(true));
               assertThat(fileContents.contains("<a href") ||
                         fileContents.contains("</a>") ||
                         fileContents.contains("<img src") ||
                         fileContents.contains("<script") ||
                         fileContents.contains("<script src") ||
                         fileContents.contains("</script>") ||
                         fileContents.contains("<link"), is(true));
          }
     }

     // cs-ddjor001
     @Test
     public void testBuild() throws IOException {
          // Set up the builder with a base path and URLs
          WebsiteBuilder builder = new WebsiteBuilder();
          Path basePath = Paths.get("src/test/resources/");
          Set<URL> baseUrls = new HashSet<>();
          baseUrls.add(new URL("http://example.com"));

          builder.withPath(basePath);
          builder.withURLs(baseUrls);

          Website website = builder.build();

          assertThat(website, is(notNullValue()));
          assertThat(website.getPath(), is(equalTo(basePath)));
          assertThat(website.getURLs(), is(equalTo(baseUrls)));
          assertThat(website.getHTMLDocuments(), is(not(empty())));

     }
}
