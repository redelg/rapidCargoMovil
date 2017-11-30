package com.darkbox.rapidcargomov.Entidades;

public class CargaUnica {
	
	private Encomienda encomienda;
	private Vehiculo vehiculo;
	private Usuario usuarioConductor;
	private Usuario usuarioAlmacenero;
	
	public Encomienda getEncomienda() {
		return encomienda;
	}
	public void setEncomienda(Encomienda encomienda) {
		this.encomienda = encomienda;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Usuario getUsuarioConductor() {
		return usuarioConductor;
	}
	public void setUsuarioConductor(Usuario usuarioConductor) {
		this.usuarioConductor = usuarioConductor;
	}
	public Usuario getUsuarioAlmacenero() {
		return usuarioAlmacenero;
	}
	public void setUsuarioAlmacenero(Usuario usuarioAlmacenero) {
		this.usuarioAlmacenero = usuarioAlmacenero;
	}
}
