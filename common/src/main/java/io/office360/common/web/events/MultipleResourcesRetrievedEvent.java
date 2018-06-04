package io.office360.common.web.events;

import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Event that is fired when a single resource was retrieved.
 * <p/>
 * This event object contains all the information needed to create the URL for direct access to the resource
 *
 * @param <T> Type of the result that is being handled (commonly Entities).
 */
public final class MultipleResourcesRetrievedEvent<T extends Serializable> extends ResourceEvent {

    public MultipleResourcesRetrievedEvent(final Class<T> clazz, final UriComponentsBuilder uriBuilderToSet, final HttpServletResponse responseToSet) {
        super(clazz, uriBuilderToSet, responseToSet);
    }
}
