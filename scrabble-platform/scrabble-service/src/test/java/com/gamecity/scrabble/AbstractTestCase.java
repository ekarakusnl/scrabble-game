package com.gamecity.scrabble;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Defines basic methods, variables and configurations to use in unit/integration tests.
 * 
 * @author ekarakus
 *
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTestCase {

    protected static final String TEST_USERNAME = "friedrich";
    protected static final String TEST_EMAIL = "friedrich@wilhelm.de";
    protected static final String TEST_PASSWORD = "Test.123";

}
