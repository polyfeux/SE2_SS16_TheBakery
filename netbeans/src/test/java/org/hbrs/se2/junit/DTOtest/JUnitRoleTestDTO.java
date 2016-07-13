package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.model.objects.dto.Role;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitRoleTestDTO {
    
    private static Role testRole;
    
    @Before
    public void setUp(){
        testRole = new Role();
        
        testRole.setBezeichnung("Test Role");
    }
    
    @Test
    public void testGetMethoden(){
        
        assertEquals("Test Role", testRole.getBezeichnung());
    }
}