package org.programacionparaaprender.app.models;


import java.math.BigDecimal;
import lombok.Data;
import java.util.*;

@Data
public class Banco {
	private String nombre;
    private List<Cuenta> cuentas;
	public Banco() {
		this.cuentas = new LinkedList<Cuenta>();
	}
	public Banco(String nombre) {
		super();
		this.nombre = nombre;
        this.cuentas = new LinkedList<Cuenta>();
	}

    public void addCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

	public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto){
		origen.debito(monto);
        destino.credito(monto);
	}

	
	@Override
	public String toString(){
		return "nombre: " + nombre;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null || this.nombre == null ){
			return false;
		}
		Banco banco = (Banco)obj;
		return this.nombre.equalsIgnoreCase(banco.getNombre());
	}

}
