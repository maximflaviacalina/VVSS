package mfir2142.tests.F01_Tests;

import mfir2142.controller.AppController;
import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.InputValidationFailedException;
import mfir2142.model.Intrebare;
import mfir2142.repository.IntrebariRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestAddQuestionFunctionality_QuestionValid {
	AppController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new AppController();
	}

	@Test
	public void test() {
		String enunt = "Cine?";
		String varianta1="1)a";
		String varianta2="2)b";
		String varianta3="3)c";
		String variantaCorecta = "2";
		String domeniu = "Domeniu";
		
		Intrebare intrebare = null;
		try{
			intrebare = new Intrebare(enunt,varianta1,varianta2,varianta3,variantaCorecta,domeniu);
			controller.addNewIntrebare(intrebare);
		}
		catch (InputValidationFailedException e){
			fail();
		}
		catch(DuplicateIntrebareException e){
			fail();
		}
		finally{
			assertTrue(controller.exists(intrebare));
		}
	}
}
