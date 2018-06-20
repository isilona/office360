package io.office360.common.unit;

import io.office360.common.util.LinkUtil;
import io.office360.common.util.QueryConstants;
import io.office360.common.util.RandomDataUtil;
import io.office360.common.web.RestPreconditions;
import io.office360.common.web.WebConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;

public class UtilClassUnitTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
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
                fail("constructor is not private");
            }
        }
    }

    @Test
    public void testValidatesThatUtilClass_IsNotInstantiable() throws
            IllegalAccessException, InstantiationException, NoSuchMethodException {

        for (Class clazz : utilClassesList) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            try {
                constructor.newInstance();
                fail("Instantiating Unit class didn't throw Exception");
            } catch (InvocationTargetException expectedException) {}
        }
    }

    @Test
    public void testValidatesThatUtilClass_DoesNotHaveNonStaticMethods() {

        for (Class clazz : utilClassesList) {
            for (final Method method : clazz.getMethods()) {
                if (!Modifier.isStatic(method.getModifiers())
                        && method.getDeclaringClass().equals(clazz)) {
                    fail("there exists a non-static method:" + method);
                }
            }
        }
    }
}
