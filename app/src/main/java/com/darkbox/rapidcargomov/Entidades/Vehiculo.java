package com.darkbox.rapidcargomov.Entidades;

public class Vehiculo {
	private int idVehiculo;
	private String numeroPlaca;
	private TipoVehiculo tipoVehiculo;
	private float capacidadKilos;
	private Boolean vehiculoActivo;
	
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getNumeroPlaca() {
		return numeroPlaca;
	}
	public void setNumeroPlaca(String numeroPlaca) {
		this.numeroPlaca = numeroPlaca;
	}
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public float getCapacidadKilos() {
		return capacidadKilos;
	}
	public void setCapacidadKilos(float capacidadKilos) {
		this.capacidadKilos = capacidadKilos;
	}
	public Boolean getVehiculoActivo() {
		return vehiculoActivo;
	}
	public void setVehiculoActivo(Boolean vehiculoActivo) {
		this.vehiculoActivo = vehiculoActivo;
	}
}
