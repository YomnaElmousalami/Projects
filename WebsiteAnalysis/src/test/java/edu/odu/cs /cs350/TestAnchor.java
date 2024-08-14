package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 * @author Cgm2002 
 * 
 */
public class TestAnchor {

    @Test
    public void testDefaultConstuctor() {

        Anchor newAnchor = new Anchor();

        assertThat(newAnchor.getResourceKind(), is(ResourceKind.ANCHOR));

    }
}