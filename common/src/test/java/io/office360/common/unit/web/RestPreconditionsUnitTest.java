package io.office360.common.unit.web;

import io.office360.common.web.RestPreconditions;
import io.office360.common.web.exception.Office360BadRequestException;
import io.office360.common.web.exception.Office360ConflictException;
import io.office360.common.web.exception.Office360ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class RestPreconditionsUnitTest {

    @Test(expected = Office360ResourceNotFoundException.class)
    public void testValidatesThatMethodCheckNotNull_ThrowsExceptionWithNullParameter() {

        Object reference = null;
        RestPreconditions.checkNotNull(reference);

    }

    @Test
    public void testValidatesThatMethodCheckNotNull_ReturnsSameObject() {

        Object reference = new Object();
        Object result = RestPreconditions.checkNotNull(reference);

        Assert.assertSame("reference and result are not same", result,
                reference);
    }

    @Test(expected = Office360BadRequestException.class)
    public void testValidatesThatMethodCheckRequestElementNotNull_ThrowsExceptionWithNullParameter() {

        Object reference = null;
        RestPreconditions.checkRequestElementNotNull(reference);

    }

    @Test
    public void testValidatesThatMethodCheckRequestElementNotNull_ReturnsSameObject() {

        Object reference = new Object();
        Object result = RestPreconditions.checkRequestElementNotNull(reference);

        Assert.assertSame("reference and result are not same", result,
                reference);
    }

    @Test(expected = Office360ConflictException.class)
    public void testValidatesThatMethodCheckRequestState_ThrowsExceptionWithFalseExpression() {

        boolean expression = false;
        RestPreconditions.checkRequestState(expression);

    }

    @Test
    public void testValidatesThatMethodCheckRequestState_PassesWithFalseExpression() {

        boolean expression = true;
        RestPreconditions.checkRequestState(expression);
    }

}
