package mfir2142.tests.F02_Tests;

import mfir2142.controller.AppController;
import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.InputValidationFailedException;
import mfir2142.exception.NotAbleToCreateTestException;
import mfir2142.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CreateANewTest_InvalidNumberOfDomains {
    private AppController controller;

    @Before
    public void setUp() throws Exception{
        controller= new AppController();
    }

    @Test
    public void test1() {
        boolean exceptionCaught = false;
        try {
            Intrebare intrebare1 = new Intrebare("Enunt1?", "1)v1", "2)v2", "3)v3", "3", "Domeniu1");
            Intrebare intrebare2 = new Intrebare("Enunt2?", "1)v1", "2)v2", "3)v3", "1", "Domeniu2");
            Intrebare intrebare3 = new Intrebare("Enunt3?", "1)v1", "2)v2", "3)v3", "2", "Domeniu3");
            Intrebare intrebare4 = new Intrebare("Enunt4?", "1)v1", "2)v2", "3)v3", "2", "Domeniu4");
            Intrebare intrebare5 = new Intrebare("Enunt5?", "1)v1", "2)v2", "3)v3", "1", "Domeniu4");
            controller.addNewIntrebare(intrebare1);
            controller.addNewIntrebare(intrebare2);
            controller.addNewIntrebare(intrebare3);
            controller.addNewIntrebare(intrebare4);
            controller.addNewIntrebare(intrebare5);
            controller.createNewTest();
        }
        catch(InputValidationFailedException ex){
            exceptionCaught = true;
        } catch (DuplicateIntrebareException e) {
            if(e.getMessage().equals("Intrebarea deja exista!")) {
                exceptionCaught = true;
            }
        } catch (NotAbleToCreateTestException e) {
            if(e.getMessage().equals("Nu exista suficiente domenii pentru crearea unui test!(5)")){
                exceptionCaught = true;
            }
        }
        finally {
            if(exceptionCaught)
                assertTrue(true);
            else
                fail();
        }
    }
}
