package io.office360.common.web.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class ResourceEvent<T extends Serializable> extends ApplicationEvent {

    private final transient UriComponentsBuilder uriBuilder;
    private final transient HttpServletResponse response;

    public ResourceEvent(final Class<T> clazz, final UriComponentsBuilder uriBuilderToSet, final HttpServletResponse responseToSet) {
        super(clazz);

        uriBuilder = uriBuilderToSet;
        response = responseToSet;
    }


    public final UriComponentsBuilder getUriBuilder() {
        return uriBuilder;
    }

    public final HttpServletResponse getResponse() {
        return response;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    @SuppressWarnings("unchecked")
    public final Class<T> getClazz() {
        return (Class<T>) getSource();
    }
}
