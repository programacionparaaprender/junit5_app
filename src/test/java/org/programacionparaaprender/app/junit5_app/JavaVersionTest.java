package org.programacionparaaprender.app.junit5_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.Nested;

@Nested
public class JavaVersionTest{
    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    void soloJdk8y11(){
        
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void soloJdk11(){
        
    }

    @Test
    @DisabledOnJre(JRE.JAVA_11)
    void testNoJdk11(){
        
    }
}
