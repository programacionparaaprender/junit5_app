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

}
