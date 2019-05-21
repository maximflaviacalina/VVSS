package mfir2142.tests.F03_Tests;

import mfir2142.controller.AppController;
import mfir2142.exception.NotAbleToCreateStatisticsException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestGetStatistica_Invalid {

    private AppController controller;

    @Before
    public void setUp() throws Exception{
        controller= new AppController();
    }

    @Test
    public void test1() {
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
}
