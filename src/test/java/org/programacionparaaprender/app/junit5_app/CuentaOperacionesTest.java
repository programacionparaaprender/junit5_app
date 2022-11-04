package org.programacionparaaprender.app.junit5_app;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.programacionparaaprender.app.models.Banco;
import org.programacionparaaprender.app.models.Cuenta;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuentaOperacionesTest {
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
