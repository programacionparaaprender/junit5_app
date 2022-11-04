package org.programacionparaaprender.app.junit5_app;


import java.util.Properties;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.Nested;

@Nested
public class SystemPropertiesTest{
    @Test
    void imprimimtTemProperties(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    @EnabledIfSystemProperty(named = "java.vm.version", matches = "11.0.*")
    void testJavaVersion(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    @DisabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
    void testNo32(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void testSolo64(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    @DisabledIfSystemProperty(named = "user.name", matches = "lacorrea")
    void testDisabledUsername(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    @EnabledIfSystemProperty(named = "user.name", matches = "lacorrea")
    void testUsername(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }


    @Test
    @EnabledIfSystemProperty(named = "ENV", matches = "dev")
    void testDev(){
        Properties properties = System.getProperties();
        properties.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }
}


