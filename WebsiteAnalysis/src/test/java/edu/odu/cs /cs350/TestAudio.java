package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Cgm2002
 */
public class TestAudio {

    @Test
    public void testDefaultConstuctor() {

        Audio audio = new Audio();
        assertThat(audio.getResourceKind(), is(ResourceKind.AUDIO));
    }
}