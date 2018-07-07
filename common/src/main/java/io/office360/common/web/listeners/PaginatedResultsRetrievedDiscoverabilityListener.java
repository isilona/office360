package io.office360.common.web.listeners;

import com.google.common.net.HttpHeaders;
import io.office360.common.web.IUriMapper;
import io.office360.common.web.events.PaginatedResultsRetrievedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static io.office360.common.util.LinkUtil.*;
import static io.office360.common.web.WebConstants.PATH_SEP;

@SuppressWarnings({"unchecked", "rawtypes"})
@Component
final class PaginatedResultsRetrievedDiscoverabilityListener extends AbstractOffice360Listener<PaginatedResultsRetrievedEvent> {

    private static final String PAGE = "page";

    private final IUriMapper uriMapper;

    @Autowired
    public PaginatedResultsRetrievedDiscoverabilityListener(IUriMapper uriMapper) {
        super();
        this.uriMapper = uriMapper;
    }

    // API

    // - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
    @Override
    protected void addLinkHeader(final PaginatedResultsRetrievedEvent ev) {

        final UriComponentsBuilder uriBuilder = ev.getUriBuilder();
        final HttpServletResponse response = ev.getResponse();
        final Class clazz = ev.getClazz();
        final int page = ev.getPage();
        final int totalPages = ev.getTotalPages();
        final int pageSize = ev.getPageSize();

        plural(uriBuilder, clazz);

        final StringBuilder linkHeader = new StringBuilder();
        if (hasNextPage(page, totalPages)) {
            final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
            linkHeader.append(createLinkHeader(uriForNextPage, REL_NEXT));
        }
        if (hasPreviousPage(page)) {
            final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForPrevPage, REL_PREV));
        }
        if (hasFirstPage(page)) {
            final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForFirstPage, REL_FIRST));
        }
        if (hasLastPage(page, totalPages)) {
            final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForLastPage, REL_LAST));
        }

        if (linkHeader.length() > 0) {
            response.addHeader(HttpHeaders.LINK, linkHeader.toString());
        }
    }

    private String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page + 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    private String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page - 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    private String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, 0).replaceQueryParam("size", size).build().encode().toUriString();
    }

    private String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, totalPages).replaceQueryParam("size", size).build().encode().toUriString();
    }

    private boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }

    private boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    private boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    private boolean hasLastPage(final int page, final int totalPages) {
        return totalPages > 1 && hasNextPage(page, totalPages);
    }

    private void appendCommaIfNecessary(final StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }

    // template

    private void plural(final UriComponentsBuilder uriBuilder, final Class clazz) {
        final String resourceName = uriMapper.getUriBase(clazz);
        uriBuilder.path(PATH_SEP + resourceName);
    }

}
