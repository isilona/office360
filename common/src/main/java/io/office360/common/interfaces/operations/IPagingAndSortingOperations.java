package io.office360.common.interfaces.operations;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IPagingAndSortingOperations<T extends Serializable> {


    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    Page<T> findAllPaginated(final int page, final int size);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<T> findAllSorted(final String sortBy, final String sortOrder);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    Page<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);


}
