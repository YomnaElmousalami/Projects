package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/**
 * @author Cgm2002
 * 
*/

public class TestScript {

    @Test
    public void testDefaultConstuctor() {

        Script newScript = new Script();

        assertThat(newScript.getResourceKind(), is(ResourceKind.SCRIPT));

    }
}