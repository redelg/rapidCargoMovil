package com.darkbox.rapidcargomov.Entidades;

public class Personal {
	private int idPersonal;
	private String DNI;
	private String correoCorporativo;
	private Boolean personalActivo;
	private Usuario usuario;
	private Persona persona;
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getCorreoCorporativo() {
		return correoCorporativo;
	}

	public void setCorreoCorporativo(String correoCorporativo) {
		this.correoCorporativo = correoCorporativo;
	}

	public Boolean getPersonalActivo() {
		return personalActivo;
	}

	public void setPersonalActivo(Boolean personalActivo) {
		this.personalActivo = personalActivo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	

}
