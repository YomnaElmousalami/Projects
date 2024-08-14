package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 * @author Cgm2002
 * 
*/

public class TestStylseSheet {

    @Test
    public void testDefaultConstuctor() {

        StyleSheet newStyleSheet = new StyleSheet();

        assertThat(newStyleSheet.getResourceKind(), is(ResourceKind.STYLESHEET));

    }
}