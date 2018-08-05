package io.office360.common.web.controller;

import io.office360.common.interfaces.IDto;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ICrudOperationsController {


    // CREATE

    <D extends IDto> void createInternal(final D resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    // READ

    <D extends IDto> D findOneInternal(final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    <D extends IDto> List<D> findAllInternal(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    // UPDATE

    <D extends IDto> void updateInternal(final long id, final D resource);

    // DELETE

    void deleteInternal(final long id);

    // COUNT

    long countInternal();

}
