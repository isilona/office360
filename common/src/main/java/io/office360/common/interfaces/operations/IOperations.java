package io.office360.common.interfaces.operations;

import java.io.Serializable;

public interface IOperations<T extends Serializable> extends
        ICrudOperations<T>,
        IPagingAndSortingOperations<T> {
}
