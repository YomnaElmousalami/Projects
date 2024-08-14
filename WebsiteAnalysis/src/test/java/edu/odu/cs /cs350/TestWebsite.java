package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author cs-ddjor001 and yomnaE1
 * 
*/

public class TestWebsite{
    @Test
    public void testDefualtConstructor() 
    {
        Website website = new Website();

        assertThat(website.getPath(), is(nullValue()));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getURLs().size(), is(0));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
        assertThat(website.getHTMLDocuments().size(), is(0));
        assertThat(website, equalTo(new Website()));
        assertThat(website.hashCode(), equalTo(new Website().hashCode()));
        assertThat(website.toString(), notNullValue());
    }

    @Test
    public void testNonDefaultConstructor() 
    {
        Path path = Paths.get("/path/to/local/directory");
        Set<URL> urls = new HashSet<URL>();
        Set<HTMLDocument> docs = new HashSet<>();

        Website website = new Website(path, urls, docs);

        assertThat(website.getPath(), is(notNullValue()));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
        assertThat(website.getPath(), is(path));
        assertThat(website.getURLs(), is(urls));
        assertThat(website.getHTMLDocuments(), is(docs));
        assertThat(website, not(equalTo(new Website())));
        assertThat(website.hashCode(), not(equalTo(new Website().hashCode())));
        assertThat(website.toString(), notNullValue());
    }

    @Test
    public void testSetPath() 
    {
        Path path = Paths.get("src/test/");
        Website website = new Website();

        website.setPath(path);

        assertThat(website.getPath(), is(notNullValue()));
        assertThat(website.getPath(), is(path));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
        assertThat(website, not(equalTo(new Website())));
        assertThat(website.hashCode(), not(equalTo(new Website().hashCode())));
        assertThat(website.toString(), notNullValue());
    }

    @Test
    public void testSetURLs() throws Exception
    {
        Set<URL> urls = new HashSet<URL>();
        urls.add(new URL("http://www.google.com"));
	Website website = new Website();
        website.setURLs(urls);

        assertThat(website.getPath(), is(nullValue()));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
	assertThat(website.getURLs(), is(urls));
        assertThat(website.getURLs().size(), is(1));
	assertThat(website, not(equalTo(new Website())));
        assertThat(website.hashCode(), not(equalTo(new Website().hashCode())));
        assertThat(website.toString(),  notNullValue());
    }

    @Test
    public void testSetHTMLDocuments(){
        Set<HTMLDocument> docs = new HashSet<HTMLDocument>();
        docs.add(new HTMLDocument());
        Website website = new Website();
        website.setHTMLDocuments(docs);

        assertThat(website.getPath(), is(nullValue()));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(docs));
        assertThat(website.getHTMLDocuments().size(), is(1));
        assertThat(website, not(equalTo(new Website())));
        assertThat(website.hashCode(), not(equalTo(new Website().hashCode())));
        assertThat(website.toString(), notNullValue());
    }

    @Test
    public void testAddHTMLDocument(){
        Website website = new Website();
        HTMLDocument doc = new HTMLDocument();
        website.addHTMLDocument(doc);

        assertThat(website.getPath(), is(nullValue()));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
        assertThat(website.getHTMLDocuments().size(), is(1));
        assertTrue(website.getHTMLDocuments().contains(doc));
        assertThat(website, not(equalTo(new Website())));
        assertThat(website.hashCode(), not(equalTo(new Website().hashCode())));
        assertThat(website.toString(), notNullValue());
    }

    @Test
    public void testRemoveHTMLDocument(){
        Website website = new Website();
        HTMLDocument doc = new HTMLDocument();
        website.addHTMLDocument(doc);
        website.removeHTMLDocument(doc);

        assertThat(website.getPath(), is(nullValue()));
        assertThat(website.getURLs(), is(notNullValue()));
        assertThat(website.getHTMLDocuments(), is(notNullValue()));
        assertThat(website.getHTMLDocuments().size(), is(0));
        assertFalse(website.getHTMLDocuments().contains(doc));
        assertThat(website, equalTo(new Website()));
        assertThat(website.hashCode(), equalTo(new Website().hashCode()));
        assertThat(website.toString(), notNullValue());
    }

    @Test
    public void testClone() throws Exception
    {
        //used similar syntax to 	    
        //citation: https://github.com/cstkennedy/cs350-examples/blob/master/JUnit-2/Example-8/src/test/java/edu/odu/cs/cs350/examples/TestRoster.java
       
        Path path = Paths.get("src/test/");
        Set<URL> urls = new HashSet<URL>();
        Set<HTMLDocument> docs = new HashSet<>();
	urls.add(new URL("http://www.google.com"));
        docs.add(new HTMLDocument());

        Website website = new Website(path, urls, docs);

	//make a copy
	Website website_copy = (Website) website.clone();

        //websites should be identical
	assertThat(website_copy.getPath(), equalTo(website.getPath()));
	assertThat(website_copy.getURLs(), equalTo(website.getURLs()));
        assertThat(website_copy.getHTMLDocuments(), equalTo(website.getHTMLDocuments()));
	assertThat(website_copy, equalTo(website));
	assertThat(website_copy.hashCode(), equalTo(website.hashCode()));
	assertThat(website_copy.toString(), equalTo(website.toString()));

	//but make sure websites are distinct
	assertThat(website_copy, not(sameInstance(website)));

	//set website copy path to equal "src/main/"
	Path path2 = Paths.get("src/main/");
	website_copy.setPath(path2);
	assertThat(website_copy.getPath(), equalTo(path2));

	assertThat(website_copy.hashCode(), not(equalTo(website.hashCode())));
        assertThat(website_copy, not(equalTo(new Website())));
        assertThat(website_copy, not(equalTo(website)));

	//change url of website copy
	Set<URL> sampleURL = new HashSet<URL>();
        sampleURL.add(new URL("http://www.google.com"));
	website_copy.setURLs(sampleURL);
	assertThat(website_copy.getURLs(), equalTo(sampleURL));

	assertThat(website_copy.hashCode(), not(equalTo(website.hashCode())));
        assertThat(website_copy, not(equalTo(new Website())));
        assertThat(website_copy, not(equalTo(website)));
	
        //test toString()
	assertThat(website_copy.toString(), containsString("src/main/"));
        assertThat(website_copy.toString(), containsString("http://www.google.com"));  
	assertThat(website_copy.toString(), not(equalTo(website.toString())));
    }

}
