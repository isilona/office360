package io.office360.common.web.listeners;

import com.google.common.net.HttpHeaders;
import io.office360.common.util.LinkUtil;
import io.office360.common.web.IUriMapper;
import io.office360.common.web.events.SingleResourceRetrievedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static io.office360.common.web.WebConstants.PATH_SEP;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component
final class SingleResourceRetrievedDiscoverabilityListener extends AbstractOffice360Listener<SingleResourceRetrievedEvent> {

    @Autowired
    private IUriMapper uriMapper;

    public SingleResourceRetrievedDiscoverabilityListener() {
        super();
    }

    //

    @Override
    protected final void addLinkHeader(final SingleResourceRetrievedEvent ev) {

        final UriComponentsBuilder uriBuilder = ev.getUriBuilder();
        final HttpServletResponse response = ev.getResponse();
        final Class clazz = ev.getClazz();

        final String uriForResourceCreation = uriBuilder.path(PATH_SEP + uriMapper.getUriBase(clazz)).build().encode().toUriString();

        final String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, LinkUtil.REL_COLLECTION);
        response.addHeader(HttpHeaders.LINK, linkHeaderValue);
    }

}
