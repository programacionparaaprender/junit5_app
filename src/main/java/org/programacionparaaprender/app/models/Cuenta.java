package org.programacionparaaprender.app.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Cuenta {
	private String persona;
	private BigDecimal saldo;
	public Cuenta() {
		
	}
	public Cuenta(String persona, BigDecimal saldo) {
		super();
		this.persona = persona;
		this.saldo = saldo;
	}
	
}
