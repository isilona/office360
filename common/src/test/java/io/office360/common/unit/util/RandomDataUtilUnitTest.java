package io.office360.common.unit.util;

import io.office360.common.util.RandomDataUtil;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class RandomDataUtilUnitTest {

    private static final String emailRegEx = "([A-Za-z0-9_]{6})@([A-Za-z0-9_]{4})(.com)";

    @Test
    public final void whenGenerateRandomEmail_thenMatchesRegEx() {

        String randomEmail = RandomDataUtil.randomEmail();
        assertTrue("Random email does not match regex", Pattern.matches(emailRegEx, randomEmail));

    }

}
