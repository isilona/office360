package io.office360.common.web.events;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Event that is fired when a resource was created.
 * <p/>
 * This event object contains all the information needed to create the URL for access the new resource created
 *
 * @param <T> Type of the result that is being handled (commonly Entities).
 */
public final class AfterResourceCreatedEvent<T extends Serializable> extends ResourceEvent {
    private final String idOfNewResource;

    public AfterResourceCreatedEvent(final Class<T> clazz, final UriComponentsBuilder uriBuilderToSet, final HttpServletResponse responseToSet, final String idOfNewResourceToSet) {
        super(clazz, uriBuilderToSet, responseToSet);

        Preconditions.checkNotNull(uriBuilderToSet);
        Preconditions.checkNotNull(responseToSet);
        Preconditions.checkNotNull(idOfNewResourceToSet);

        idOfNewResource = idOfNewResourceToSet;
    }

    //

    public final String getIdOfNewResource() {
        return idOfNewResource;
    }

}
