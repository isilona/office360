package io.office360.common.web.events;

import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Event that is fired when a paginated search is performed.
 * <p/>
 * This event object contains all the information needed to create the URL for the paginated results
 *
 * @param <T> Type of the result that is being handled (commonly Entities).
 */
public final class PaginatedResultsRetrievedEvent<T extends Serializable> extends ResourceEvent {
    private final int page;
    private final int totalPages;
    private final int pageSize;

    public PaginatedResultsRetrievedEvent(final Class<T> clazz, final UriComponentsBuilder uriBuilderToSet, final HttpServletResponse responseToSet, final int pageToSet, final int totalPagesToSet, final int pageSizeToSet) {
        super(clazz, uriBuilderToSet, responseToSet);

        page = pageToSet;
        totalPages = totalPagesToSet;
        pageSize = pageSizeToSet;
    }

    // API

    public final int getPage() {
        return page;
    }

    public final int getTotalPages() {
        return totalPages;
    }

    public final int getPageSize() {
        return pageSize;
    }

}