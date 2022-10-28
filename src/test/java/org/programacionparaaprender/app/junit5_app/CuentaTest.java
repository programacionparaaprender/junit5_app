package org.programacionparaaprender.app.junit5_app;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.programacionparaaprender.app.models.Cuenta;

import junit.framework.TestCase;

class CuentaTest     extends TestCase
{
	@Test
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertEquals(esperado, real);
		
	}
	
	@Test
	void testNombreCuenta2() {
		Cuenta cuenta = new Cuenta();
		//cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertEquals(esperado, real);
		
	}
	
	@Test
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

}
