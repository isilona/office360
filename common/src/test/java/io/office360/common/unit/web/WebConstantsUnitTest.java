package io.office360.common.unit.web;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class WebConstantsUnitTest {

    @Test
    public void testValidatesThatClassWebConstants_IsFinal() throws
            ClassNotFoundException {

        Class clazz = Class.forName("io.office360.common.web.WebConstants");

        Assert.assertTrue("class must be final",
                Modifier.isFinal(clazz.getModifiers()));

    }

    @Test
    public void testValidatesThatClassWebConstants_HasOnlyOneConstructor() throws
            ClassNotFoundException {

        Class clazz = Class.forName("io.office360.common.web.WebConstants");

        Assert.assertEquals("There must be only one constructor", 1,
                clazz.getDeclaredConstructors().length);

    }

    @Test
    public void testValidatesThatClassWebConstants_HasPrivateConstructor() throws
            ClassNotFoundException, NoSuchMethodException {

        Class clazz = Class.forName("io.office360.common.web.WebConstants");

        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        if (constructor.isAccessible() ||
                !Modifier.isPrivate(constructor.getModifiers())) {
            Assert.fail("constructor is not private");
        }

    }

    @Test(expected = java.lang.IllegalAccessException.class)
    public void testValidatesThatClassWebConstants_IsNotInstantiable() throws
            ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class clazz = Class.forName("io.office360.common.web.WebConstants");

        clazz.newInstance();
    }


}
