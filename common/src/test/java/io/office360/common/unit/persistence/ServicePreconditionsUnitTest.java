package io.office360.common.unit.persistence;

import io.office360.common.persistence.ServicePreconditions;
import io.office360.common.persistence.exception.Office360EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class ServicePreconditionsUnitTest {

    @Test(expected = Office360EntityNotFoundException.class)
    public void testValidatesThatMethodCheckEntityExists_ThrowsExceptionWithNullParameter() {

        Object reference = null;
        ServicePreconditions.checkEntityExists(reference);

    }

    @Test
    public void testValidatesThatMethodCheckEntityExists_ReturnsSameObject() {

        Object reference = new Object();
        Object result = ServicePreconditions.checkEntityExists(reference);

        Assert.assertSame("reference and result are not same", result,
                reference);
    }
}
