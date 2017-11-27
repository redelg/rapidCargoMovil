package com.darkbox.rapidcargomov.Entidades;

public class DetalleEncomienda {
	private Encomienda encomienda;
	private float peso;
	private float montoPago;
	private String descripcionDetalle;
	private Boolean aDomicilio;
	public Encomienda getEncomienda() {
		return encomienda;
	}
	public void setEncomienda(Encomienda encomienda) {
		this.encomienda = encomienda;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getMontoPago() {
		return montoPago;
	}
	public void setMontoPago(float montoPago) {
		this.montoPago = montoPago;
	}
	public String getDescripcionDetalle() {
		return descripcionDetalle;
	}
	public void setDescripcionDetalle(String descripcionDetalle) {
		this.descripcionDetalle = descripcionDetalle;
	}
	public Boolean getaDomicilio() {
		return aDomicilio;
	}
	public void setaDomicilio(Boolean aDomicilio) {
		this.aDomicilio = aDomicilio;
	}
	
	
}
