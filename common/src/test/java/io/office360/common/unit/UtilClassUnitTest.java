package io.office360.common.unit;

import io.office360.common.util.LinkUtil;
import io.office360.common.util.QueryConstants;
import io.office360.common.util.RandomDataUtil;
import io.office360.common.web.RestPreconditions;
import io.office360.common.web.WebConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

public class UtilClassUnitTest {

    private List<Class> utilClassesList;

    @Before
    public void initObjects() {
        utilClassesList = new LinkedList<>();
        utilClassesList.add(RestPreconditions.class);
        utilClassesList.add(WebConstants.class);
        utilClassesList.add(LinkUtil.class);
        utilClassesList.add(RandomDataUtil.class);
        utilClassesList.add(QueryConstants.class);
    }

    @Test
    public void testValidatesThatUtilClass_IsFinal() {

        for (Class clazz : utilClassesList) {
            Assert.assertTrue("class must be final",
                    Modifier.isFinal(clazz.getModifiers()));
        }
    }

    @Test
    public void testValidatesThatUtilClass_HasOnlyOneConstructor() {

        for (Class clazz : utilClassesList) {
            Assert.assertEquals("There must be only one constructor", 1,
                    clazz.getDeclaredConstructors().length);
        }
    }

    @Test
    public void testValidatesThatUtilClass_HasPrivateConstructor() throws
            NoSuchMethodException {

        for (Class clazz : utilClassesList) {
            final Constructor<?> constructor = clazz.getDeclaredConstructor();
            if (constructor.isAccessible() ||
                    !Modifier.isPrivate(constructor.getModifiers())) {
                Assert.fail("constructor is not private");
            }
        }
    }

    @Test(expected = InvocationTargetException.class)
    public void testValidatesThatUtilClass_IsNotInstantiable() throws
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        for (Class clazz : utilClassesList) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }
    }

    @Test
    public void testValidatesThatUtilClass_DoesNotHaveNonStaticMethods() {

        for (Class clazz : utilClassesList) {
            for (final Method method : clazz.getMethods()) {
                if (!Modifier.isStatic(method.getModifiers())
                        && method.getDeclaringClass().equals(clazz)) {
                    Assert.fail("there exists a non-static method:" + method);
                }
            }
        }
    }
}
