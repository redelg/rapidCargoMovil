package com.darkbox.rapidcargomov.Entidades;

public class Sucursal {
	
	private int idSucursal;
	private String nombreAgencia;
	private String direccionSucursal;
	private Ciudad ciudadSucursal;
	private Boolean sucursalActiva;
	
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNombreAgencia() {
		return nombreAgencia;
	}
	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	public Ciudad getCiudadSucursal() {
		return ciudadSucursal;
	}
	public void setCiudadSucursal(Ciudad ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}
	public Boolean getSucursalActiva() {
		return sucursalActiva;
	}
	public void setSucursalActiva(Boolean sucursalActiva) {
		this.sucursalActiva = sucursalActiva;
	}
	
	
}
