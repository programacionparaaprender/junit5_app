package org.programacionparaaprender.app.junit5_app;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.programacionparaaprender.app.models.Banco;
import org.programacionparaaprender.app.models.Cuenta;
import java.util.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest   
{
	@ParameterizedTest(name = "número {index} ejecutando con valor {0} {argumentsWithNames}")
	@CsvSource({"200,100,Pedro,Pedro", "250,200,Jose,Jose", "399,300,Maria,Maria", "600,500,Juana,Juana", "750,700,Pedro,Pedro", "1100.12345,1000.12345,Pedro,Pedro"})
	void testDebitoCuentaCsvSource2(String saldo, String monto, String esperado, String real) {
		System.out.println(saldo + " -> " +  monto);
		Cuenta cuenta = new Cuenta(real, new BigDecimal(saldo));
		cuenta.debito(new BigDecimal(monto));
		BigDecimal real1 = cuenta.getSaldo();
		String real2 = cuenta.getPersona();
		Assertions.assertAll(()->Assertions.assertNotNull(cuenta.getSaldo(), "La cuenta no puede ser nula"),
		()->{
			Assertions.assertTrue(real1.compareTo(BigDecimal.ZERO) > 0,()-> "El valor de la cuenta no es el esperado");	
		},
		()->{
			Assertions.assertNotNull(real2, "La cuenta no puede ser nula");
		},
		()->{
			Assertions.assertTrue(real2.equalsIgnoreCase(esperado), "El valor debe se el esperado esperado");
		});
	}

	@ParameterizedTest(name = "número {index} ejecutando con valor {0} {argumentsWithNames}")
	@MethodSource("montoList")
	void testDebitoCuentaMethodSource(String monto) {
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.debito(new BigDecimal(monto));
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertNotNull(cuenta.getSaldo(), "La cuenta no puede ser nula");
		Assertions.assertTrue(real.compareTo(BigDecimal.ZERO) > 0, "El valor debe se el esperado esperado");
	}
	private static List<String> montoList(){
		/* List<String> lista = new LinkedList<String>();
		lista.add("100");
		lista.add("200");
		lista.add("300");
		lista.add("400");
		lista.add("500");
		lista.add("600");
		lista.add("700");
		lista.add("800");
		lista.add("900");
		lista.add("1000");
		return lista; */
		return Arrays.asList("100", "200", "300", "500", "700", "1000");
	}

	@ParameterizedTest(name = "número {index} ejecutando con valor {0} {argumentsWithNames}")
	@CsvFileSource(resources = "/data.csv")
	void testDebitoCuentaCsvFileSource(String index, String monto) {
		System.out.println(index + " -> " +  monto);
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.debito(new BigDecimal(monto));
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertNotNull(cuenta.getSaldo(), "La cuenta no puede ser nula");
		Assertions.assertTrue(real.compareTo(BigDecimal.ZERO) > 0, "El valor debe se el esperado esperado");
	}

	@ParameterizedTest(name = "número {index} ejecutando con valor {0} {argumentsWithNames}")
	@CsvSource({"1,100", "2,200", "3,300", "4,500", "5,700", "6,1000"})
	void testDebitoCuentaCsvSource(String index, String monto) {
		System.out.println(index + " -> " +  monto);
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.debito(new BigDecimal(monto));
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertNotNull(cuenta.getSaldo(), "La cuenta no puede ser nula");
		Assertions.assertTrue(real.compareTo(BigDecimal.ZERO) > 0, "El valor debe se el esperado esperado");
	}

	@ParameterizedTest(name = "número {index} ejecutando con valor {0} {argumentsWithNames}")
	@ValueSource(strings = {"100", "200", "300", "500", "700", "1000"})
	void testDebitoCuenta(String monto) {
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal(1000.12345));
		cuenta.debito(new BigDecimal(monto));
		BigDecimal real = cuenta.getSaldo();
		Assertions.assertNotNull(cuenta.getSaldo(), "La cuenta no puede ser nula");
		Assertions.assertTrue(real.compareTo(BigDecimal.ZERO) > 0, "El valor debe se el esperado esperado");
	}


	@DisplayName("Probando nombre de la cuenta corriente!")
	@RepeatedTest(value=2, name="{displayName} Repetición número {currentRepetition} de {totalRepetitions}")
	void testNombreCuenta(RepetitionInfo info) {
		if(info.getCurrentRepetition() == 1){
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

	@Nested
	class CuentaOperacionesTest2 {
		//@Disabled hace que no se pueda ejecutar
		@Test
		//@Disabled
		@DisplayName("Probando relaciones entre las cuentas y el banco con assertAll")
		void testTransferirDineroCuentas(){
			//fail();
			Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
			Cuenta cuenta2 = new Cuenta("Andres Doe", new BigDecimal("1500.8989"));
			Banco banco = new Banco("Banco del Estado");
			banco.addCuenta(cuenta1);
			banco.addCuenta(cuenta2);
			
			banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
			
			Assertions.assertAll(()->Assertions.assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(),
			()-> "El valor de la cuenta2 no es el esperado"),
				()->{
					Assertions.assertEquals("3000", cuenta1.getSaldo().toPlainString(),
					()-> "El valor de la cuenta1 no es el esperado");	
			},
			()->{
				Assertions.assertEquals(2, banco.getCuentas().size());
			},
			()->{
				Assertions.assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());
			
			},
			()->{
				Assertions.assertEquals("Andres Doe", banco.getCuentas()
				.stream()
				.filter(c -> c.getPersona()
				.equalsIgnoreCase("Andres Doe"))
				.findFirst()
				.get()
				.getPersona()
				);
			},
			()->{
				Assertions.assertTrue(banco.getCuentas()
				.stream()
				.filter(c -> c.getPersona()
				.equalsIgnoreCase("Andres Doe"))
				.findFirst()
				.isPresent()
				);
			},
			()->{
				Assertions.assertTrue(banco.getCuentas()
				.stream()
				.anyMatch(c -> c.getPersona()
				.equalsIgnoreCase("Andres Doe"))
				);
			}
			);
	
		}
	}

	
}
