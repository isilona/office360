package io.office360.common.unit.web;

import io.office360.common.web.RestPreconditions;
import io.office360.common.web.exception.Office360BadRequestException;
import io.office360.common.web.exception.Office360ConflictException;
import io.office360.common.web.exception.Office360ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RestPreconditionsUnitTest {

    @Test
    public void testValidatesThatClassRestPreconditions_IsFinal() throws
            ClassNotFoundException {

        Class clazz = Class.forName("io.office360.common.web.RestPreconditions");

        Assert.assertTrue("class must be final",
                Modifier.isFinal(clazz.getModifiers()));

    }

    @Test
    public void testValidatesThatClassRestPreconditions_HasOnlyOneConstructor() throws
            ClassNotFoundException {

        Class clazz = Class.forName("io.office360.common.web.RestPreconditions");

        Assert.assertEquals("There must be only one constructor", 1,
                clazz.getDeclaredConstructors().length);

    }

    @Test
    public void testValidatesThatClassRestPreconditions_HasPrivateConstructor() throws
            ClassNotFoundException, NoSuchMethodException {

        Class clazz = Class.forName("io.office360.common.web.RestPreconditions");

        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        if (constructor.isAccessible() ||
                !Modifier.isPrivate(constructor.getModifiers())) {
            Assert.fail("constructor is not private");
        }

    }

    @Test(expected = InvocationTargetException.class)
    public void testValidatesThatClassRestPreconditions_IsNotInstantiable() throws
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Constructor<RestPreconditions> constructor = RestPreconditions.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testValidatesThatClassRestPreconditions_DoesNotHaveNonStaticMethods() throws
            ClassNotFoundException {

        Class clazz = Class.forName("io.office360.common.web.RestPreconditions");

        for (final Method method : clazz.getMethods()) {
            if (!Modifier.isStatic(method.getModifiers())
                    && method.getDeclaringClass().equals(clazz)) {
                Assert.fail("there exists a non-static method:" + method);
            }
        }
    }

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
