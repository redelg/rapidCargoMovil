package com.darkbox.rapidcargomov;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.darkbox.rapidcargomov.Adaptadores.TrasladoAdapter;
import com.darkbox.rapidcargomov.Entidades.Ciudad;
import com.darkbox.rapidcargomov.Entidades.Encomienda;
import com.darkbox.rapidcargomov.Entidades.EstadoEncomienda;
import com.darkbox.rapidcargomov.Entidades.Personal;
import com.darkbox.rapidcargomov.Entidades.Ruta;
import com.darkbox.rapidcargomov.Entidades.Sucursal;
import com.darkbox.rapidcargomov.Entidades.TipoUsuario;
import com.darkbox.rapidcargomov.Entidades.Usuario;
import com.darkbox.rapidcargomov.Utiles.VolleyS;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrasladoFragment extends Fragment {

    protected RequestQueue requestCola;
    private VolleyS volley;
    ArrayList<Encomienda> listaEncomiendas;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_traslado, container, false);

        volley = VolleyS.getInstance(getActivity());
        requestCola = volley.getmRequestQueue();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTraslado);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ListarEncomiendasTraslado();

        return view;
    }

    private void ListarEncomiendasTraslado(){
        String url = "http://10.0.2.2:8080/rest/Encomienda/ListarEncomiendasTrasladoBus?" +
                "nombreConductor="+ Usuario.Instancia().getNombreUsuario();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray json) {
                try {
                    listaEncomiendas = new ArrayList<Encomienda>();
                    String result = json.toString();
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject enc = (JSONObject) json.get(i);
                        Encomienda e = new Encomienda();
                        e.setIdEncomienda(Integer.parseInt(enc.get("idEncomienda").toString()));

                        String fechaTra = enc.get("fechaLlegada").toString();
                        String[] divFecha = fechaTra.split("\\ ");
                        String fechaTraslado = divFecha[0];
                        e.setFechaLlegada(fechaTraslado);

                        e.setDescripcionEncomienda(enc.get("descripcionEncomienda").toString());
                        Ruta r = new Ruta();
                        r.setNombreRuta(enc.getJSONObject("ruta").get("nombreRuta").toString());
                        e.setRuta(r);
                        EstadoEncomienda ee = new EstadoEncomienda();
                        ee.setDescripcionEstadoEncomienda(enc.getJSONObject("estado" +
                                "Encomienda").get("descripcionEstadoEncomienda").toString());
                        e.setEstadoEncomienda(ee);
                        listaEncomiendas.add(e);
                    }
                    /*recyclerViewAdapter = new TrasladoAdapter(getActivity(), listaEncomiendas);
                    recyclerView.setAdapter(recyclerViewAdapter);*/

                    final TrasladoAdapter adaptador = new TrasladoAdapter(getActivity(), listaEncomiendas);

                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int posicion = recyclerView.getChildPosition(v);
                            final Encomienda en = listaEncomiendas.get(posicion);

                            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                            alert.setTitle("CONFIRMAR TRASLADO");
                            alert.setMessage("¿Desea confirmar el traslado de "+en.getDescripcionEncomienda()+"?");
                            alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    TrasladarEncomiendaSucursal(en);
                                }
                            });
                            alert.setNegativeButton("Cancelar",null);
                            alert.show();
                        }
                    });
                    recyclerView.setAdapter(adaptador);

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        addCola(request);
    }

    private void TrasladarEncomiendaSucursal(Encomienda en){
        String url = "http://10.0.2.2:8080/rest/Encomienda/TrasladarEncomienda?" +
                "idEncomienda="+ en.getIdEncomienda();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String request) {
                try {
                    if (request.equals("true")) {
                        Toast.makeText(getActivity(), "Traslado confirmado", Toast.LENGTH_SHORT).show();
                        ListarEncomiendasTraslado();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No se pudo confirmar el traslado", Toast.LENGTH_SHORT).show();
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
