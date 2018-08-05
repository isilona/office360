package io.office360.common.persistence.service;

import io.office360.common.interfaces.IDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPagingAndSortingOperationsService {

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    <D extends IDto> Page<D> findAllPaginated(final int page, final int size);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    <D extends IDto> List<D> findAllSorted(final String sortBy, final String sortOrder);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    <D extends IDto> Page<D> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);

}
