package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 * @author Cgm2002
 * 
*/

public class TestImage {

    @Test
    public void testDefaultConstuctor() {

        Image newImage = new Image();

        assertThat(newImage.getResourceKind(), is(ResourceKind.IMAGE));

    }
}