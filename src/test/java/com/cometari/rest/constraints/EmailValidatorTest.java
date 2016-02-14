package com.cometari.rest.constraints;

import static org.junit.Assert.*;

import javax.validation.ConstraintValidatorContext;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(DataProviderRunner.class)
public class EmailValidatorTest {

    private final EmailValidator validator = new EmailValidator();

    @Mock
    ConstraintValidatorContext context;

    @DataProvider
    public static Object[][] emailAddresses() {
        return new Object[][] {
            {"wp@wp.pl", true},
            {"kaczka@kaczka", false}
        };
    }

    @Test
    @UseDataProvider("emailAddresses")
    public void testValidator(String candidate, Boolean expectedOutput) {
         assertEquals(
             expectedOutput,
             validator.isValid(candidate, context)
         );
    }
}

