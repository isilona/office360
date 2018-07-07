package io.office360.common.web.controller;

import io.office360.common.persistence.model.IEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ISortingController<T extends IEntity> {

    List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    List<T> findAllPaginated(final int page, final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    List<T> findAllSorted(final String sortBy, final String sortOrder);

    List<T> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

}