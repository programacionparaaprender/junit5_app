package org.programacionparaaprender.app.junit5_app;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.programacionparaaprender.app.exceptions.DineroInsuficienteException;
import org.programacionparaaprender.app.models.Cuenta;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;

@Tag("cuenta")
@Nested
public class CuentaTestNombreSaldo {
    Cuenta cuenta;
	/*
	 * @BeforeAll ejecuta al inicio
	 * @BeforeEach antes de cada método
	 * @AfterEach
	 * @AfterAll ejecuta al final
	*/
	@BeforeEach
	@Test
	void initMetodoTest(){
		this.cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		this.cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = this.cuenta.getPersona();
		Assertions.assertNotNull(real, "La cuenta no puede ser nula");
		Assertions.assertEquals(esperado, real, "El nombre de la cuenta debe ser el que esperado");
		
		System.out.println("Iniciando el método");
	}

	@AfterEach
	@Test
	protected
	void tearDown(){
		System.out.println("Finalizando el método de prueba");
	}

	//@TestInstance(TestInstance.Lifecycle.PER_CLASS) por esta configuración en la clase no se coloca static
	@Test
	@BeforeAll
	static void beforeAll(){
		System.out.println("Inicializando el test");
	
	}

	//@TestInstance(TestInstance.Lifecycle.PER_CLASS) por esta configuración en la clase no se coloca static
	@Test
	@AfterAll
	static void afterAll(){
		System.out.println("Finalizando el test");
	
	}

	@Test
	@DisplayName("Probando nombre de la cuenta corriente!")
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertNotNull(real, "La cuenta no puede ser nula");
		Assertions.assertEquals(esperado, real, "El nombre de la cuenta debe ser el que esperado");
		Assertions.assertTrue(real.equalsIgnoreCase(esperado), "El valor debe se el esperado esperado");
		
	}
	
	@Test
	@DisplayName("Probando el saldo de la cuenta corriente, que no sea null, mayor que cero, valor esperado")
	void testNombreCuenta2() {
		Cuenta cuenta = new Cuenta();
		//cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertNull(real, "La cuenta no puede ser nula");
		//Assertions.assertEquals(esperado, real);
		
	}
	
	@Test
	@DisplayName("testeando referencias que sean iguales con el método equals.")
	void testSaldoCuenta() {
		Cuenta cuenta = new Cuenta("Andres",  new BigDecimal("1000.12345"));
		BigDecimal esperado = new BigDecimal("1000.12345");
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertEquals(esperado, real);
		Assertions.assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		Assertions.assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
	}

	@Test
	void testReferenciaCuenta() {
		Cuenta cuenta = new Cuenta("John Doe",  new BigDecimal("8900.9997"));
		Cuenta cuenta2 = new Cuenta("John Doe",  new BigDecimal("8900.9997"));
		Assertions.assertNotNull(cuenta.getSaldo());
		Assertions.assertNotNull(cuenta2.getSaldo());
		Assertions.assertEquals(cuenta, cuenta2);
		Assertions.assertTrue(cuenta.equals(cuenta2));
	}

	@Test
	void testDebitoCuenta() {
		Cuenta cuenta = new Cuenta("John Doe",  new BigDecimal("1000.12345"));
		Assertions.assertNotNull(cuenta.getSaldo());
		cuenta.debito(new BigDecimal(100));
		BigDecimal esperado = new BigDecimal("900.12345");
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertEquals(esperado, real);
		Assertions.assertEquals("900.12345", real.toPlainString());
	}

	@Test
	void testCreditoCuenta() {
		Cuenta cuenta = new Cuenta("John Doe",  new BigDecimal("1000.12345"));
		Assertions.assertNotNull(cuenta.getSaldo());
		cuenta.credito(new BigDecimal(100));
		BigDecimal esperado = new BigDecimal("1100.12345");
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertEquals(esperado, real);
		Assertions.assertEquals("1100.12345", real.toPlainString());
	}

	@Test
	void testDineroInsuficienteExceptionCuenta() {
		Cuenta cuenta = new Cuenta("John Doe",  new BigDecimal("1000.12345"));
		Assertions.assertNotNull(cuenta.getSaldo());
		
		Exception ex  = Assertions.assertThrows(DineroInsuficienteException.class, ()-> {
			cuenta.debito(new BigDecimal(1500));
		});
		String actual = ex.getMessage();
		String esperado = "Dinero Insuficiente";
		Assertions.assertTrue(esperado.equalsIgnoreCase(actual));
		/* try{
			cuenta.debito(new BigDecimal(1500));
		}catch(DineroInsuficienteException ex){
			String actual = ex.getMessage();
			String esperado = "Dinero Insuficiente";
			Assertions.assertTrue(esperado.equalsIgnoreCase(actual));
		} */
		
	}
}
