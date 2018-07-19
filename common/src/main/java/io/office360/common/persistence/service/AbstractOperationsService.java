package io.office360.common.persistence.service;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.office360.common.interfaces.IDto;
import io.office360.common.persistence.ServicePreconditions;
import io.office360.common.persistence.exception.Office360EntityNotFoundException;
import io.office360.common.persistence.model.IEntity;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractOperationsService<T extends IEntity, D extends IDto> implements IOperationsService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractOperationsService() {
        super();
    }

    // API - CRUD

    // CREATE

    @Override
    public T create(final T entity) {
        Preconditions.checkNotNull(entity);
        return getDao().save(entity);
    }

    // READ

    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id) {
        T toReturn = getDao().findById(id).orElse(null);

        // TODO : update this and also the other methods to use mapper
        System.out.println(getMapper().entityToDto(toReturn));

        return toReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    // UPDATE

    @Override
    public void update(final T entity) {
        Preconditions.checkNotNull(entity);

        getDao().save(entity);
    }

    // DELETE

    @Override
    public void delete(final long id) {
        final Optional<T> entity = getDao().findById(id);
        ServicePreconditions.checkEntityExists(entity);
        if (entity.isPresent()) {
            getDao().delete(entity.get());
        } else {
            throw new Office360EntityNotFoundException();
        }
    }

    @Override
    public void deleteAll() {
        getDao().deleteAll();
    }

    // COUNT

    @Override
    public long count() {
        return getDao().count();
    }


    // API - PAGING & SORTING

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAllPaginated(final int page, final int size) {
        return getDao().findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAllSorted(final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return Lists.newArrayList(getDao().findAll(sortInfo));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return getDao().findAll(PageRequest.of(page, size, sortInfo));
    }


    // template method

    protected abstract PagingAndSortingRepository<T, Long> getDao();

    protected abstract IMapper<D, T> getMapper();

    // template

    protected final Sort constructSort(final String sortBy, final String sortOrder) {
        Sort sortInfo = null;
        if (sortBy != null) {
            sortInfo = new Sort(Direction.fromString(sortOrder), sortBy);
        }
        return sortInfo;
    }

}
