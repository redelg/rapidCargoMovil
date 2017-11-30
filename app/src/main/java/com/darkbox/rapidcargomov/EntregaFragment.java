package com.darkbox.rapidcargomov;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.android.volley.toolbox.StringRequest;
import com.darkbox.rapidcargomov.Adaptadores.EntregaAdapter;
import com.darkbox.rapidcargomov.Adaptadores.TrasladoAdapter;
import com.darkbox.rapidcargomov.Entidades.Encomienda;
import com.darkbox.rapidcargomov.Entidades.EstadoEncomienda;
import com.darkbox.rapidcargomov.Entidades.Usuario;
import com.darkbox.rapidcargomov.Utiles.VolleyS;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EntregaFragment extends Fragment {

    protected RequestQueue requestCola;
    private VolleyS volley;
    ArrayList<Encomienda> listaEncomiendas;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entrega, container, false);

        volley = VolleyS.getInstance(getActivity());
        requestCola = volley.getmRequestQueue();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEntrega);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ListarEncomiendasEntrega();

        return view;
    }

    private void ListarEncomiendasEntrega(){
        String url = "http://10.0.2.2:8080/rest/Encomienda/ListarEncomiendasEntregaMinivan?" +
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
                        e.setDescripcionEncomienda(enc.get("descripcionEncomienda").toString());
                        e.setNombreDestinatario(enc.get("nombreDestinatario").toString());
                        e.setCodigoEncomienda(enc.get("codigoEncomienda").toString());

                        EstadoEncomienda ee = new EstadoEncomienda();
                        ee.setDescripcionEstadoEncomienda(enc.getJSONObject("estado" +
                                "Encomienda").get("descripcionEstadoEncomienda").toString());
                        e.setEstadoEncomienda(ee);
                        listaEncomiendas.add(e);
                    }
                    final EntregaAdapter adaptador = new EntregaAdapter(getActivity(), listaEncomiendas);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int posicion = recyclerView.getChildPosition(v);
                            final Encomienda en = listaEncomiendas.get(posicion);

                            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                            alert.setTitle("CONFIRMAR ENTREGA");
                            alert.setMessage("¿Desea confirmar la entrega de "+en.getDescripcionEncomienda()+"?");
                            alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    TrasladarEncomiendaDomicilio(en);
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

    private void TrasladarEncomiendaDomicilio(Encomienda en){
        String url = "http://10.0.2.2:8080/rest/Entrega/EntregarEnDomicilio?" +
                "idEncomienda="+ en.getIdEncomienda()+
                "&nombreUsuario="+ Usuario.Instancia().getNombreUsuario();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String request) {
                try {
                    if (request.equals("true")) {
                        Toast.makeText(getActivity(), "Entrega confirmada", Toast.LENGTH_SHORT).show();
                        ListarEncomiendasEntrega();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No se pudo confirmar la entrega", Toast.LENGTH_SHORT).show();
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