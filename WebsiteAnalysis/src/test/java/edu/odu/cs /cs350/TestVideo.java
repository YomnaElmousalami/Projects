package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 * @author yomnaE1
 * 
*/

public class TestVideo {

    @Test
    public void testDefaultConstuctor() {

        Video newVideo = new Video();

        assertThat(newVideo.getResourceKind(), is(ResourceKind.VIDEO));

    }
}