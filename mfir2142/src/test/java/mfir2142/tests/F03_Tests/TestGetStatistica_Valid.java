package mfir2142.tests.F03_Tests;

import mfir2142.controller.AppController;
import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.InputValidationFailedException;
import mfir2142.exception.NotAbleToCreateStatisticsException;
import mfir2142.model.Intrebare;
import mfir2142.model.Statistica;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestGetStatistica_Valid {

    private AppController controller;

    @Before
    public void setUp() throws Exception{
        controller= new AppController();
    }

    @Test
    public void test1() {
        Intrebare intrebare1 = null;
        Statistica statistica = null;
        try {
            intrebare1 = new Intrebare("Enunt1?", "1)v1", "2)v2", "3)v3", "3", "Domeniu1");
            controller.addNewIntrebare(intrebare1);
            statistica = controller.getStatistica();
        }
        catch (NotAbleToCreateStatisticsException ex) {
            if(ex.getMessage().equals("Repository-ul nu contine nicio intrebare!")) {
                fail();
            }
        } catch (DuplicateIntrebareException e) {
            if(e.getMessage().equals("Intrebarea deja exista!")) {
                fail();
            }
        } catch (InputValidationFailedException e) {
            fail();
        } finally {
            assertTrue(statistica.getIntrebariDomenii().containsKey(intrebare1.getDomeniu())
                    && statistica.getIntrebariDomenii().containsValue(1) );
        }
    }
}
