package io.office360.common.web.listeners;

import io.office360.common.web.IUriMapper;
import io.office360.common.web.events.AfterResourceCreatedEvent;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component
public abstract class ResourceCreatedDiscoverabilityListener extends AbstractOffice360Listener<AfterResourceCreatedEvent> {

    private final IUriMapper uriMapper;

    @Autowired
    public ResourceCreatedDiscoverabilityListener(IUriMapper uriMapper) {
        super();
        this.uriMapper = uriMapper;
    }

    //

    /**
     * - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
     */
    @Override
    protected void addLinkHeader(final AfterResourceCreatedEvent ev) {

        final UriComponentsBuilder uriBuilder = ev.getUriBuilder();
        final HttpServletResponse response = ev.getResponse();
        final String idOfNewEntity = ev.getIdOfNewResource();
        final Class clazz = ev.getClazz();

        final String path = calculatePathToResource(clazz);
        final String locationValue = uriBuilder.path(path).build().expand(idOfNewEntity).encode().toUriString();

        response.setHeader(HttpHeaders.LOCATION, locationValue);
    }

    private String calculatePathToResource(final Class clazz) {
        final String resourceName = uriMapper.getUriBase(clazz);
        return getBase() + resourceName + "/{id}";
    }

    // TODO: #1 Check if this is needed
    protected abstract String getBase();

}
