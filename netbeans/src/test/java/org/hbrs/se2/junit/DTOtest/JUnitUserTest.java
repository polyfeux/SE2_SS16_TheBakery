package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.junit.data.DataUser;
import org.hbrs.se2.model.objects.dto.User;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitUserTest {
    
    private static User testUser;
    
    @Before
    public void setUp(){
        testUser = DataUser.getUser1();
    }
    
    @Test 
    public void Test10_GetMethoden(){
        
        assertEquals(DataUser.LOGIN1, testUser.getLogin());
        assertEquals(DataUser.VORNAME1, testUser.getVorname());
        
    }
}