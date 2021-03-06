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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Transactional
public abstract class AbstractOperationsService
        implements IOperationsService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractOperationsService() {
        super();
    }

    // API - CRUD

    // CREATE

    @Override
    public <D extends IDto> D create(final D dto) {
        Preconditions.checkNotNull(dto);
        IEntity toSave = getMapper().dtoToEntity(dto);
        IEntity saved = getDao().save(toSave);

        return (D) getMapper().entityToDto(saved);
    }

    // READ

    @Override
    @Transactional(readOnly = true)
    public <D extends IDto> D findOne(final long id) {
        IEntity found = getDao().findById(id).orElse(null);
        return (D) getMapper().entityToDto(found);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IDto> findAll() {
        List<IEntity> found = Lists.newArrayList(getDao().findAll());
        List<IDto> toReturn = new LinkedList<>();
        for (IEntity item : found) {
            toReturn.add(getMapper().entityToDto(item));
        }
        return toReturn;
    }

    // UPDATE
    @Override
    public <D extends IDto> void update(final D dto) {
        Preconditions.checkNotNull(dto);
        IEntity toSave = getMapper().dtoToEntity(dto);
        getDao().save(toSave);
    }

    // DELETE

    @Override
    public void delete(final long id) {
        final Optional<IEntity> entity = getDao().findById(id);
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
    public Page<IDto> findAllPaginated(final int page, final int size) {
        Page<IEntity> found = getDao().findAll(PageRequest.of(page, size));
        Page<IDto> toReturn = found.map(new Function<IEntity, IDto>() {
            @Override
            public IDto apply(IEntity entity) {
                return getMapper().entityToDto(entity);
            }
        });

        return toReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IDto> findAllSorted(final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);

        List<IEntity> found = Lists.newArrayList(getDao().findAll(sortInfo));
        List<IDto> toReturn = new LinkedList<>();
        for (IEntity item : found) {
            toReturn.add(getMapper().entityToDto(item));
        }

        return toReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IDto> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);

        Page<IEntity> found = getDao().findAll(PageRequest.of(page, size, sortInfo));
        Page<IDto> toReturn = found.map(new Function<IEntity, IDto>() {
            @Override
            public IDto apply(IEntity entity) {
                return getMapper().entityToDto(entity);
            }
        });

        return toReturn;
    }


    // template method

    protected abstract <T extends IEntity> PagingAndSortingRepository<T, Long> getDao();

    protected abstract <D extends IDto, T extends IEntity> IMapper<D, T> getMapper();

    // template

    protected final Sort constructSort(final String sortBy, final String sortOrder) {
        Sort sortInfo = null;
        if (sortBy != null) {
            sortInfo = new Sort(Direction.fromString(sortOrder), sortBy);
        }
        return sortInfo;
    }

}
