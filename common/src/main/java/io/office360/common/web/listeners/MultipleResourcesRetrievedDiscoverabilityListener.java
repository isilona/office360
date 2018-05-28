package io.office360.common.web.listeners;

import com.google.common.net.HttpHeaders;
import io.office360.common.util.LinkUtil;
import io.office360.common.web.IUriMapper;
import io.office360.common.web.events.MultipleResourcesRetrievedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static io.office360.common.web.WebConstants.PATH_SEP;

@SuppressWarnings({"unchecked", "rawtypes"})
@Component
final class MultipleResourcesRetrievedDiscoverabilityListener extends AbstractOffice360Listener<MultipleResourcesRetrievedEvent> {

    @Autowired
    private IUriMapper uriMapper;

    public MultipleResourcesRetrievedDiscoverabilityListener() {
        super();
    }

    @Override
    protected void addLinkHeader(MultipleResourcesRetrievedEvent ev) {

        final UriComponentsBuilder uriBuilder = ev.getUriBuilder();
        final HttpServletResponse response = ev.getResponse();
        final Class clazz = ev.getClazz();

        final String uriForResourceCreation = uriBuilder.path(PATH_SEP + uriMapper.getUriBase(clazz) + "/search?name=something").build().encode().toUriString();

        final String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, LinkUtil.REL_COLLECTION);
        response.addHeader(HttpHeaders.LINK, linkHeaderValue);

    }
}
