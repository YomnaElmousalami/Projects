package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 * @author bbake017
 * 
*/

public class TestOther {

    @Test
    public void testDefaultConstuctor() {

        Other newOther = new Other();

        assertThat(newOther.getResourceKind(), is(ResourceKind.OTHER));

    }
}