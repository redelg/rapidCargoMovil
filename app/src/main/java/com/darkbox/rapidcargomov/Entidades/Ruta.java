package com.darkbox.rapidcargomov.Entidades;

public class Ruta {
	private int idRuta;
	private String nombreRuta;
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private String descripcionRuta;
	private Boolean rutaActiva;
	
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public String getNombreRuta() {
		return nombreRuta;
	}
	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}
	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}
	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}
	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}
	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}
	public String getDescripcionRuta() {
		return descripcionRuta;
	}
	public void setDescripcionRuta(String descripcionRuta) {
		this.descripcionRuta = descripcionRuta;
	}
	public Boolean getRutaActiva() {
		return rutaActiva;
	}
	public void setRutaActiva(Boolean rutaActiva) {
		this.rutaActiva = rutaActiva;
	}
}
