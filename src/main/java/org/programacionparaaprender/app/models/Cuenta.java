package org.programacionparaaprender.app.models;

import lombok.Data;
import java.math.BigDecimal;

import org.programacionparaaprender.app.exceptions.DineroInsuficienteException;

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
		//Double resultado = this.saldo.doubleValue() - monto.doubleValue();
		//this.saldo = new BigDecimal(resultado.toString());
		BigDecimal nuevoSaldo = this.saldo.subtract(monto);
		if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
			throw new DineroInsuficienteException("Dinero Insuficiente");
		}
		this.saldo = nuevoSaldo;
	}

	public void credito(BigDecimal monto){
		//Double resultado = this.saldo.doubleValue() + monto.doubleValue();
		//this.saldo = new BigDecimal(resultado.toString());
		this.saldo = this.saldo.add(monto);
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
