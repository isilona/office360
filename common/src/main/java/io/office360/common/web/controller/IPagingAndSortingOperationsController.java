package io.office360.common.web.controller;

import io.office360.common.interfaces.IDto;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IPagingAndSortingOperationsController {

    <D extends IDto> List<D> findPaginatedInternal(final int page, final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    <D extends IDto> List<D> findSortedInternal(final String sortBy, final String sortOrder);

    <D extends IDto> List<D> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

}