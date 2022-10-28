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
	public void debito(BigDecimal monto){
		Double resultado = this.saldo.doubleValue() - monto.doubleValue();
		this.saldo = new BigDecimal(resultado.toString());
	}

	public void credito(BigDecimal monto){

	}
	
	@Override
	public String toString(){
		return "persona: " + persona + " saldo: " + saldo.toString();
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null || this.persona == null || this.saldo == null){
			return false;
		}
		Cuenta cuenta = (Cuenta)obj;
		
		return this.persona.equalsIgnoreCase(cuenta.getPersona()) && this.saldo.equals(cuenta.getSaldo());
	}

}
