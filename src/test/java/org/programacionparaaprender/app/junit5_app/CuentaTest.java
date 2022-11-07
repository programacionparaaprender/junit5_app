package org.programacionparaaprender.app.junit5_app;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.programacionparaaprender.app.models.Banco;
import org.programacionparaaprender.app.models.Cuenta;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest {
	

	

	@Test
	@Tag("pruebas")
	@DisplayName("Probando nombre de la cuenta corriente 3!")
	void testNombreCuenta3(TestInfo testInfo, TestReporter testReporter) {
		testReporter.publishEntry("ejecutando " + testInfo.getDisplayName() + " " 
		 + testInfo.getTestMethod().orElse(null).getName()
		+ " con las etiquetas: " + testInfo.getTags());
		//System.out.println("estamos repitiendo " + info.getCurrentRepetition());


		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertNotNull(real, "La cuenta no puede ser nula");
		Assertions.assertEquals(esperado, real, "El nombre de la cuenta debe ser el que esperado");
		Assertions.assertTrue(real.equalsIgnoreCase(esperado), "El valor debe se el esperado esperado");

	}

	@Test
	@Tag("pruebas")
	@DisplayName("Probando nombre de la cuenta corriente 2!")
	void testNombreCuenta2(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("ejecutando " + testInfo.getDisplayName() + " " 
		 + testInfo.getTestMethod().orElse(null).getName()
		+ " con las etiquetas: " + testInfo.getTags());
		//System.out.println("estamos repitiendo " + info.getCurrentRepetition());


		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertNotNull(real, "La cuenta no puede ser nula");
		Assertions.assertEquals(esperado, real, "El nombre de la cuenta debe ser el que esperado");
		Assertions.assertTrue(real.equalsIgnoreCase(esperado), "El valor debe se el esperado esperado");

	}



	@Tag("cuenta")
	@DisplayName("Probando nombre de la cuenta corriente!")
	@RepeatedTest(value = 2, name = "{displayName} Repetición número {currentRepetition} de {totalRepetitions}")
	void testNombreCuenta(RepetitionInfo info) {
		if (info.getCurrentRepetition() == 1) {
			System.out.println("estamos repitiendo " + info.getCurrentRepetition());
		}
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.setPersona("Andres");
		String esperado = "Andres";
		String real = cuenta.getPersona();
		Assertions.assertNotNull(real, "La cuenta no puede ser nula");
		Assertions.assertEquals(esperado, real, "El nombre de la cuenta debe ser el que esperado");
		Assertions.assertTrue(real.equalsIgnoreCase(esperado), "El valor debe se el esperado esperado");

	}

	@Tag("param")
	@Nested
	class CuentaOperacionesTest2 {
		// @Disabled hace que no se pueda ejecutar
		@Test
		// @Disabled
		@DisplayName("Probando relaciones entre las cuentas y el banco con assertAll")
		void testTransferirDineroCuentas() {
			// fail();
			Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
			Cuenta cuenta2 = new Cuenta("Andres Doe", new BigDecimal("1500.8989"));
			Banco banco = new Banco("Banco del Estado");
			banco.addCuenta(cuenta1);
			banco.addCuenta(cuenta2);

			banco.transferir(cuenta2, cuenta1, new BigDecimal(500));

			Assertions.assertAll(() -> Assertions.assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(),
					() -> "El valor de la cuenta2 no es el esperado"),
					() -> {
						Assertions.assertEquals("3000", cuenta1.getSaldo().toPlainString(),
								() -> "El valor de la cuenta1 no es el esperado");
					},
					() -> {
						Assertions.assertEquals(2, banco.getCuentas().size());
					},
					() -> {
						Assertions.assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());

					},
					() -> {
						Assertions.assertEquals("Andres Doe", banco.getCuentas()
								.stream()
								.filter(c -> c.getPersona()
										.equalsIgnoreCase("Andres Doe"))
								.findFirst()
								.get()
								.getPersona());
					},
					() -> {
						Assertions.assertTrue(banco.getCuentas()
								.stream()
								.filter(c -> c.getPersona()
										.equalsIgnoreCase("Andres Doe"))
								.findFirst()
								.isPresent());
					},
					() -> {
						Assertions.assertTrue(banco.getCuentas()
								.stream()
								.anyMatch(c -> c.getPersona()
										.equalsIgnoreCase("Andres Doe")));
					});

		}
	}

}
