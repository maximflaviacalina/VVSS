package mfir2142.tests.F01_Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAddQuestionFunctionality_EnuntInvalid.class,
		TestAddQuestionFunctionality_QuestionValid.class,
		TestAddQuestionFunctionality_VCInvalid.class,
		TestAddQuestionFunctionality_Valid.class,
		TestAddQuestionFunctionality_VariantaIncorecta.class})
public class AddQuestionFunctionalityTests {

}
