package com.darkbox.rapidcargomov;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.darkbox.rapidcargomov.Entidades.Ciudad;
import com.darkbox.rapidcargomov.Entidades.Cliente;
import com.darkbox.rapidcargomov.Entidades.Encomienda;
import com.darkbox.rapidcargomov.Entidades.EntregaUnica;
import com.darkbox.rapidcargomov.Entidades.EstadoEncomienda;
import com.darkbox.rapidcargomov.Entidades.Persona;
import com.darkbox.rapidcargomov.Entidades.Personal;
import com.darkbox.rapidcargomov.Entidades.Ruta;
import com.darkbox.rapidcargomov.Entidades.Sucursal;
import com.darkbox.rapidcargomov.Entidades.TipoUsuario;
import com.darkbox.rapidcargomov.Entidades.Usuario;
import com.darkbox.rapidcargomov.Utiles.VolleyS;

import org.json.JSONObject;

public class SeguimientoFragment extends Fragment {

    protected RequestQueue requestCola;
    private VolleyS volley;

    EditText txtCodigoSeg, txtDniCliente, txtRemitente, txtRuta, txtFechaEnvio, txtAgenciaEnvio,
            txtAgenciaLlegada, txtDestinatario, txtEstado, txtEntregadoPor,
            txtFechaEntrega, txtRecibidoPor;

    Button btnBuscarEncomienda, btnLimpiar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seguimiento, container, false);

        txtCodigoSeg = (EditText) view.findViewById(R.id.txtCodigoSeg);
        txtDniCliente = (EditText) view.findViewById(R.id.txtDniCliente);
        txtRemitente = (EditText) view.findViewById(R.id.txtRemitente);
        txtRuta = (EditText) view.findViewById(R.id.txtRuta);
        txtFechaEnvio = (EditText) view.findViewById(R.id.txtFechaEnvio);
        txtAgenciaEnvio = (EditText) view.findViewById(R.id.txtAgenciaEnvio);
        txtAgenciaLlegada = (EditText) view.findViewById(R.id.txtAgenciaLlegada);
        txtDestinatario = (EditText) view.findViewById(R.id.txtDestinatario);
        txtEstado = (EditText) view.findViewById(R.id.txtEstado);
        txtEntregadoPor = (EditText) view.findViewById(R.id.txtEntregadoPor);
        txtFechaEntrega = (EditText) view.findViewById(R.id.txtFechaEntrega);
        btnBuscarEncomienda = (Button) view.findViewById(R.id.btnBuscarEncomienda);
        btnLimpiar = (Button) view.findViewById(R.id.btnLimpiar);

        volley = VolleyS.getInstance(getActivity());
        requestCola = volley.getmRequestQueue();

        btnBuscarEncomienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCodigoSeg.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Ingrese un codigo de seguimiento",Toast.LENGTH_SHORT).show();
                    return;
                }
                DevolverEncomienda();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCodigoSeg.setText("");
                txtDniCliente.setText("");
                txtRemitente.setText("");
                txtRuta.setText("");
                txtFechaEnvio.setText("");
                txtAgenciaEnvio.setText("");
                txtAgenciaLlegada.setText("");
                txtDestinatario.setText("");
                txtEstado.setText("");
                txtEntregadoPor.setText("");
                txtFechaEntrega.setText("");
            }
        });
        return view;
    }

    private void DevolverEncomienda(){
        String url = "http://10.0.2.2:8080/rest/Encomienda/DevolverEncomienda?" +
                "codigoEncomienda="+txtCodigoSeg.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    if(jsonObject!=null){

                        Encomienda e = new Encomienda();
                        e.setIdEncomienda(jsonObject.getInt("idEncomienda"));
                        e.setCodigoEncomienda(jsonObject.getString("codigoEncomienda"));

                        Cliente c = new Cliente();
                        c.setDniCliente(jsonObject.getJSONObject("cliente").getString("dniCliente"));
                        Persona p = new Persona();
                        p.setNombres(jsonObject.getJSONObject("cliente").getJSONObject("persona").getString("nombres"));
                        p.setApellidos(jsonObject.getJSONObject("cliente").getJSONObject("persona").getString("apellidos"));
                        c.setPersona(p);
                        e.setCliente(c);

                        Ruta r = new Ruta();
                        r.setNombreRuta(jsonObject.getJSONObject("ruta").getString("nombreRuta"));
                        e.setRuta(r);

                        e.setFechaRegistro(jsonObject.getString("fechaRegistro"));

                        Sucursal sucursalOrigen = new Sucursal();
                        sucursalOrigen.setNombreAgencia(jsonObject.getJSONObject("sucursalOrigen").getString("nombreAgencia"));
                        e.setSucursalOrigen(sucursalOrigen);

                        Sucursal sucursalDestino = new Sucursal();
                        sucursalDestino.setNombreAgencia(jsonObject.getJSONObject("sucursalDestino").getString("nombreAgencia"));
                        e.setSucursalDestino(sucursalDestino);

                        e.setNombreDestinatario(jsonObject.getString("nombreDestinatario"));

                        EstadoEncomienda ee = new EstadoEncomienda();
                        ee.setDescripcionEstadoEncomienda(jsonObject.getJSONObject("estado" +
                                "Encomienda").getString("descripcionEstadoEncomienda"));
                        e.setEstadoEncomienda(ee);

                        EntregaUnica eu = new EntregaUnica();
                        eu.setFechaEntrega(jsonObject.getJSONObject("entregaUnica").getString("fechaEntrega"));
                        Usuario u = Usuario.Instancia();
                        Personal pe = new Personal();
                        Persona pp = new Persona();
                        pp.setNombres(jsonObject.getJSONObject("entregaUnica").getJSONObject("usuario").
                                getJSONObject("personal").getJSONObject("persona").getString("nombres"));
                        pp.setApellidos(jsonObject.getJSONObject("entregaUnica").getJSONObject("usuario").
                                getJSONObject("personal").getJSONObject("persona").getString("apellidos"));
                        pe.setPersona(pp);
                        u.setPersonal(pe);
                        eu.setUsuario(u);
                        e.setEntregaUnica(eu);

                        Toast.makeText(getActivity(), "Encomienda encontrada", Toast.LENGTH_SHORT).show();

                        txtDniCliente.setText(e.getCliente().getDniCliente());
                        txtRemitente.setText(e.getCliente().getPersona().getNombres() + " "
                                + e.getCliente().getPersona().getApellidos());
                        txtRuta.setText(e.getRuta().getNombreRuta());

                        String fechaReg = e.getFechaRegistro();
                        String[] divisor = fechaReg.split("\\.");
                        String fechaRegistro = divisor[0];
                        txtFechaEnvio.setText(fechaRegistro);

                        txtAgenciaEnvio.setText(e.getSucursalOrigen().getNombreAgencia());
                        txtAgenciaLlegada.setText(e.getSucursalDestino().getNombreAgencia());
                        txtDestinatario.setText(e.getNombreDestinatario());
                        txtEstado.setText(e.getEstadoEncomienda().getDescripcionEstadoEncomienda());

                        if (e.getEntregaUnica().getUsuario().getPersonal().getPersona().getNombres().toString().equals("null")
                                || e.getEntregaUnica().getUsuario().getPersonal().getPersona().getApellidos().toString().equals("null")) {
                            txtEntregadoPor.setText("Sin datos");
                        }
                        else{
                            txtEntregadoPor.setText(e.getEntregaUnica().getUsuario().getPersonal().getPersona().getNombres()+
                                    " "+e.getEntregaUnica().getUsuario().getPersonal().getPersona().getApellidos());
                        }

                        if (e.getEntregaUnica().getFechaEntrega().toString().equals("null")){
                            txtFechaEntrega.setText("Sin datos");
                        }
                        else {
                            String fechaEnt = e.getEntregaUnica().getFechaEntrega();
                            String[] divisor2 = fechaEnt.split("\\.");
                            String fechaEntrega = divisor2[0];
                            txtFechaEntrega.setText(fechaEntrega);
                        }
                    }
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Este codigo no corresponde a ninguna encomienda", Toast.LENGTH_SHORT).show();
            }
        });
        addCola(request);
    }

    public void addCola(Request request){
        if(request!=null){
            request.setTag(this);
            if(requestCola==null) {
                requestCola = volley.getmRequestQueue();
            }
            request.setRetryPolicy(new DefaultRetryPolicy(
                    6000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            requestCola.add(request);
        }
    }

}
