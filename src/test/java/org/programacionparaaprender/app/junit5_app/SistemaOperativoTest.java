package org.programacionparaaprender.app.junit5_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.Nested;

@Nested
public class SistemaOperativoTest
{
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testSoloWindows(){

    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testSoloLinuxMac(){

    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testNoWindows(){
        
    }
}
