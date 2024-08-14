package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/**
 * @author cs-ddjor001 
 * 
 */
public class TestArchive {

    @Test
    public void testDefaultConstuctor() {

        Archive archive = new Archive();
        assertThat(archive.getResourceKind(), is(ResourceKind.ARCHIVE));
    }
}