package mfir2142.tests.F03_Tests;

import mfir2142.controller.AppController;
import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.InputValidationFailedException;
import mfir2142.exception.NotAbleToCreateStatisticsException;
import mfir2142.exception.NotAbleToCreateTestException;
import mfir2142.model.Intrebare;
import mfir2142.model.Statistica;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


/*P-> aplicatia completa
  A-> modulul asociat functionalitatii F01 : adaugarea unei intrebari
  B-> modulul asociat functionalitatii F02 : crearea unui test
  C-> modulul asociat functionalitatii F03 : statistica
*/


public class TopDownIntegrationTest {
    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
    }

    //Testare unitara A, enunt valid
    @Test
    public void testA() {
        Intrebare intrebare;
        try{
            intrebare = new Intrebare("Enunt?","1)var1","2)var2","3)var3","1","Istorie");
            controller.addNewIntrebare(intrebare);
        }
        catch (InputValidationFailedException e){
            if(e.getMessage().equals("Enuntul este vid!")) {
                fail();
            }
        }
        catch(DuplicateIntrebareException e){
            fail();
        }
        finally{
            assertEquals(1, controller.getAll().size());
        }
    }

    //Testare unitara B
    @Test
    public void testB() {
        boolean exceptionCaught = false;
        try {
            controller.createNewTest();
        }
        catch (NotAbleToCreateTestException e) {
            if(e.getMessage().equals("Nu exista suficiente intrebari pentru crearea unui test!(5)")) {
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

    //Testare unitara C, C invalid( nu exista nicio intrebare)
    @Test
    public void testC() {
        boolean exceptionCaught = false;
        try {
            controller.getStatistica();
        }
        catch (NotAbleToCreateStatisticsException ex) {
            if (ex.getMessage().equals("Repository-ul nu contine nicio intrebare!")) {
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

    //Testare de integrare a modulului A, adică P -> A
    @Test
    public void testPA() {
        Intrebare intrebare;
        try{
            intrebare = new Intrebare("Enunt?","1)var1","2)var2","3)var3","1","Istorie");
            controller.addNewIntrebare(intrebare);
        }
        catch (InputValidationFailedException e){
            if(e.getMessage().equals("Enuntul este vid!")) {
                fail();
            }
        }
        catch(DuplicateIntrebareException e){
            fail();
        }
        finally{
            assertEquals(1, controller.getAll().size());
        }
    }

    //Testare de integrare a modulului B, scenariu : P -> A -> B
    @Test
    public void testPAB(){
        mfir2142.model.Test test = new mfir2142.model.Test();
        try{
            //P
            Intrebare intrebare = new Intrebare("Enunt?","1)var1","2)var2","3)var3","1","Istorie");
            //A
            controller.addNewIntrebare(intrebare);
            //B
            test = controller.createNewTest();
        }
        catch (InputValidationFailedException e){
            if(!e.getMessage().equals("Enuntul este vid!")) {
                fail();
            }
        }
        catch(DuplicateIntrebareException e){
            fail();
        }
        catch (NotAbleToCreateTestException e) {
            if(!e.getMessage().equals("Nu exista suficiente intrebari pentru crearea unui test!(5)")) {
                fail();
            }
        }
        finally{
            assertEquals(1, controller.getAll().size());
            assertEquals(true, test.getIntrebari().isEmpty() );
        }
    }


    //Testare de integrare a modulului C, scenariu : P -> A -> C -> B
    @Test
    public void testPABC() {
        Statistica statistica = new Statistica();
        mfir2142.model.Test test = new mfir2142.model.Test();
        try{
            //P
            Intrebare intrebare = new Intrebare("Enunt?","1)var1","2)var2","3)var3","1","Istorie");
            //A
            controller.addNewIntrebare(intrebare);
            //C
            statistica = controller.getStatistica();
            //B
            test = controller.createNewTest();
        }
        catch (InputValidationFailedException e){
            if(!e.getMessage().equals("Enuntul este vid!")) {
                fail();
            }
        }
        catch(DuplicateIntrebareException e){
            fail();
        }
        catch (NotAbleToCreateStatisticsException e) {
            if(!e.getMessage().equals("Repository-ul nu contine nicio intrebare!")) {
                fail();
            }
        }
        catch (NotAbleToCreateTestException e) {
            if(!e.getMessage().equals("Nu exista suficiente intrebari pentru crearea unui test!(5)")) {
                fail();
            }
        }
        finally{
            assertEquals(1, controller.getAll().size());
            assertEquals(true, test.getIntrebari().isEmpty() );
            assertEquals(true, statistica.getIntrebariDomenii().containsKey("Istorie") && statistica.getIntrebariDomenii().containsValue(1));
        }
    }
}
