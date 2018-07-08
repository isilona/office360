package io.office360.common.web.controller;

import io.office360.common.persistence.model.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController<T extends IEntity> extends AbstractOperationsController<T> {

    @Autowired
    public AbstractController(final Class<T> clazzToSet) {
        super(clazzToSet);
    }

    // generic REST operations

    // count

    /**
     * Counts all {@link Privilege} resources in the system
     *
     * @return
     */
    @GetMapping(value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return countInternal();
    }

}
