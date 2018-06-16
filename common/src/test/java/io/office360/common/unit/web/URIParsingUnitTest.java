package io.office360.common.unit.web;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertTrue;

public class URIParsingUnitTest {

    @Test
    public final void whenURIIsParsed_thenResultIsCorrect() {
        final String uri = "http://localhost:8080/privilege?q=name%3DTestname";

        // When
        final UriComponents uriComponents = UriComponentsBuilder.fromUriString(uri).build();

        // Then
        assertTrue(uriComponents.getQueryParams().size() == 1);
    }

}
