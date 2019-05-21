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

public class TestAddQuestionFunctionality_EnuntInvalid {
	AppController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new AppController();
	}

	@Test
	public void test() {
		boolean exceptionCaught = false;
		String enunt = "";
		String varianta1 = "1)var1";
		String varianta2 = "2)var2";
		String varianta3 = "3)var3";
		String variantaCorecta="1";
		String domeniu = "Istorie";
		
		Intrebare intrebare;
		try{
			intrebare = new Intrebare(enunt,varianta1,varianta2,varianta3,variantaCorecta,domeniu);
			controller.addNewIntrebare(intrebare);
		}
		catch (InputValidationFailedException e){
			if(e.getMessage().equals("Enuntul este vid!")) {
				exceptionCaught = true;
			}
			System.out.println(e);
		}
		catch(DuplicateIntrebareException e){
			exceptionCaught=true;
		}
		finally{
			if(exceptionCaught)
				assertTrue(true);
			else
				fail();
		}
	}
}
