package mfir2142.tests.F02_Tests;

import mfir2142.controller.AppController;
import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.InputValidationFailedException;
import mfir2142.exception.NotAbleToCreateTestException;
import mfir2142.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CreateANewTest_Valid2 {

    private AppController controller;

    @Before
    public void setUp() throws Exception{
        controller= new AppController();
    }

    @Test
    public void test1() {
        Intrebare intrebare1 ; Intrebare intrebare2; Intrebare intrebare3; Intrebare intrebare4; Intrebare intrebare5; Intrebare intrebare6;
        mfir2142.model.Test test = null;
        try {
            intrebare1 = new Intrebare("Enunt1?", "1)v1", "2)v2", "3)v3", "3", "Domeniu1");
            intrebare2 = new Intrebare("Enunt2?", "1)v1", "2)v2", "3)v3", "1", "Domeniu2");
            intrebare3 = new Intrebare("Enunt3?", "1)v1", "2)v2", "3)v3", "2", "Domeniu3");
            intrebare4 = new Intrebare("Enunt4?", "1)v1", "2)v2", "3)v3", "2", "Domeniu4");
            intrebare5 = new Intrebare("Enunt5?", "1)v1", "2)v2", "3)v3", "3", "Domeniu4");
            intrebare6 = new Intrebare("Enunt6?", "1)v1", "2)v2", "3)v3", "3", "Domeniu5");
            controller.addNewIntrebare(intrebare1);
            controller.addNewIntrebare(intrebare2);
            controller.addNewIntrebare(intrebare3);
            controller.addNewIntrebare(intrebare4);
            controller.addNewIntrebare(intrebare5);
            controller.addNewIntrebare(intrebare6);
            test = controller.createNewTest();
        }
        catch(InputValidationFailedException ex){
            fail();
        } catch (DuplicateIntrebareException e) {
            if(e.getMessage().equals("Intrebarea deja exista!")) {
                fail();
            }
        } catch (NotAbleToCreateTestException e) {
            fail();
        }
        finally {
             assertTrue(test!=null );
        }
    }
}
