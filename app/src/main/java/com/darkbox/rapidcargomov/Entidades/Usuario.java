package com.darkbox.rapidcargomov.Entidades;

import java.sql.Date;

public class Usuario {

	private String nombreUsuario;
	private String contrasena;
	private TipoUsuario tipoUsuario;
	private Personal personal;
	private Sucursal sucursal;
	private Date ultimaSesion;

    private static Usuario _instancia;

	public static synchronized Usuario Instancia(){
		if(_instancia==null){
			_instancia = new Usuario();
		}
		return _instancia;
	}
	private Usuario(){}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Date getUltimaSesion() {
		return ultimaSesion;
	}

	public void setUltimaSesion(Date ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}
	
}
