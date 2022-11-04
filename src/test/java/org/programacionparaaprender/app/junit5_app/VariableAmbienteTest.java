package org.programacionparaaprender.app.junit5_app;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.programacionparaaprender.app.exceptions.DineroInsuficienteException;
import org.programacionparaaprender.app.models.Cuenta;
import org.junit.jupiter.api.Nested;

@Nested
public class VariableAmbienteTest{
    @Test
    void testEnviromment(){
        Map<String, String> getenv = System.getenv();
        getenv.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    //@EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = "C:\\Program Files\\Java\\jdk-11.0.5")
    @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*jdk-11.0.5")
    void testJavaHome(){
        Map<String, String> getenv = System.getenv();
        getenv.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }


    @Test
    @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "8")
    void testProcesadores(){
        Map<String, String> getenv = System.getenv();
        getenv.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

        @Test
        @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "dev")
        void testEnv(){
            Map<String, String> getenv = System.getenv();
            getenv.forEach((k, v)->{
                System.out.println(k + ":" + v);
            });
        }

        @Test
        @DisabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "prod")
        void testEnvProdDisabled(){
            Map<String, String> getenv = System.getenv();
            getenv.forEach((k, v)->{
                System.out.println(k + ":" + v);
            });
        }


        @Test
        @DisplayName("probando el saldo de la cuenta corriente")
        void testDineroInsuficienteExceptionCuentaNoEsProd() {
            boolean noEsProd = "prod".equals(System.getProperty("PROD"));

            Assumptions.assumeTrue(noEsProd);

            Cuenta cuenta = new Cuenta("John Doe",  new BigDecimal("1000.12345"));
            Assertions.assertNotNull(cuenta.getSaldo());
            
            Exception ex  = Assertions.assertThrows(DineroInsuficienteException.class, ()-> {
                cuenta.debito(new BigDecimal(1500));
            });
            String actual = ex.getMessage();
            String esperado = "Dinero Insuficiente";
            Assertions.assertTrue(esperado.equalsIgnoreCase(actual));
    
        }

        @Test
        @DisplayName("probando el saldo de la cuenta corriente 2")
        void testDineroInsuficienteExceptionCuentaNoEsProd2() {
            boolean noEsProd = !"prod".equals(System.getProperty("PROD"));
            Assumptions.assumingThat(noEsProd, ()->{
                Cuenta cuenta = new Cuenta("John Doe",  new BigDecimal("1000.12345"));
                Assertions.assertNotNull(cuenta.getSaldo());
                
                Exception ex  = Assertions.assertThrows(DineroInsuficienteException.class, ()-> {
                    cuenta.debito(new BigDecimal(1500));
                });
                String actual = ex.getMessage();
                String esperado = "Dinero Insuficiente";
                Assertions.assertTrue(esperado.equalsIgnoreCase(actual));
            });
        }
}
