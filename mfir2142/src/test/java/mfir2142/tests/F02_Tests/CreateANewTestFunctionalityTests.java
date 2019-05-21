package mfir2142.tests.F02_Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateANewTest_Valid.class,
        CreateANewTest_Valid2.class,
        CreateANewTest_InvalidNumberOfDomains.class,
        CreateANewTest_InvalidNumberOfQuestions.class,})
public class CreateANewTestFunctionalityTests {

}
