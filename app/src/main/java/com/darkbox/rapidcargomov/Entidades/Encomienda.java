package com.darkbox.rapidcargomov.Entidades;



public class Encomienda {
	private int idEncomienda;
	private String codigoEncomienda;
	private Cliente cliente;
	private Ruta ruta;
	private String fechaRegistro;
	private String fechaLlegada;
	private String nombreDestinatario;
	private String direccionDestinatario;
	private Sucursal sucursalOrigen;
	private Sucursal sucursalDestino;
	private EstadoEncomienda estadoEncomienda;
	private Usuario usuarioCajero;
	private float peso;
	private float montoPago;
	private String descripcionEncomienda;
	private Boolean aDomicilio;
	private EntregaUnica entregaUnica;
	private CargaUnica cargaUnica;
	
	public int getIdEncomienda() {
		return idEncomienda;
	}
	public void setIdEncomienda(int idEncomienda) {
		this.idEncomienda = idEncomienda;
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
	public String getDescripcionEncomienda() {
		return descripcionEncomienda;
	}
	public void setDescripcionEncomienda(String descripcionEncomienda) {
		this.descripcionEncomienda = descripcionEncomienda;
	}
	public Boolean getaDomicilio() {
		return aDomicilio;
	}
	public void setaDomicilio(Boolean aDomicilio) {
		this.aDomicilio = aDomicilio;
	}
	public EntregaUnica getEntregaUnica() {
		return entregaUnica;
	}
	public void setEntregaUnica(EntregaUnica entregaUnica) {
		this.entregaUnica = entregaUnica;
	}
	public CargaUnica getCargaUnica() {
		return cargaUnica;
	}
	public void setCargaUnica(CargaUnica cargaUnica) {
		this.cargaUnica = cargaUnica;
	}
	public String getCodigoEncomienda() {
		return codigoEncomienda;
	}
	public void setCodigoEncomienda(String codigoEncomienda) {
		this.codigoEncomienda = codigoEncomienda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	public String getDireccionDestinatario() {
		return direccionDestinatario;
	}
	public void setDireccionDestinatario(String direccionDestinatario) {
		this.direccionDestinatario = direccionDestinatario;
	}
	public Sucursal getSucursalOrigen() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}
	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}
	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}
	public EstadoEncomienda getEstadoEncomienda() {
		return estadoEncomienda;
	}
	public void setEstadoEncomienda(EstadoEncomienda estadoEncomienda) {
		this.estadoEncomienda = estadoEncomienda;
	}
	public Usuario getUsuarioCajero() {
		return usuarioCajero;
	}
	public void setUsuarioCajero(Usuario usuarioCajero) {
		this.usuarioCajero = usuarioCajero;
	}
}
