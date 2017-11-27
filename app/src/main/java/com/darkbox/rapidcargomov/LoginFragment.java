package com.darkbox.rapidcargomov;

import android.app.AlertDialog;
import android.content.Intent;
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
import com.darkbox.rapidcargomov.Entidades.Personal;
import com.darkbox.rapidcargomov.Entidades.Sucursal;
import com.darkbox.rapidcargomov.Entidades.TipoUsuario;
import com.darkbox.rapidcargomov.Entidades.Usuario;
import com.darkbox.rapidcargomov.Utiles.VolleyS;

import org.json.JSONObject;

public class LoginFragment extends Fragment {

    protected RequestQueue requestCola;
    private VolleyS volley;
    EditText txtUsuario, txtPassword;
    Button btnLogin;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtUsuario = (EditText) view.findViewById(R.id.txtUsuario);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        volley = VolleyS.getInstance(getActivity());
        requestCola = volley.getmRequestQueue();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtUsuario.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Ingrese un usuario",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtPassword.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Ingrese una contraseña",Toast.LENGTH_SHORT).show();
                    return;
                }
                VerificarAcceso();
            }
        });
        return view;
    }

    private void VerificarAcceso(){
        String url = "http://10.0.2.2:8080/rest/Usuario/VerificarAcceso?" +
                "nombreUsuario="+txtUsuario.getText().toString()+
                "&contrasena="+txtPassword.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    if(jsonObject!=null){

                        if(jsonObject.getJSONObject("tipoUsuario").getInt("idTipoUsuario")==4){
                            Usuario.Instancia().setNombreUsuario(jsonObject.getString("nombreUsuario"));
                            Usuario.Instancia().setContrasena(jsonObject.getString("contrasena"));

                            TipoUsuario tu = new TipoUsuario();
                            tu.setIdTipoUsuario(jsonObject.getJSONObject("tipoUsuario").getInt("idTipoUsuario"));
                            Usuario.Instancia().setTipoUsuario(tu);

                            Personal p = new Personal();
                            p.setIdPersonal(jsonObject.getJSONObject("personal").getInt("idPersonal"));
                            Usuario.Instancia().setPersonal(p);

                            Sucursal s = new Sucursal();
                            s.setIdSucursal(jsonObject.getJSONObject("sucursal").getInt("idSucursal"));
                            Ciudad c = new Ciudad();
                            c.setIdCiudad(jsonObject.getJSONObject("sucursal").
                                    getJSONObject("ciudadSucursal").getInt("idCiudad"));
                            s.setCiudadSucursal(c);
                            s.setNombreAgencia(jsonObject.getJSONObject("sucursal").getString("nombreAgencia"));
                            Usuario.Instancia().setSucursal(s);

                            Toast.makeText(getActivity(),
                                    "Bienvenido " + Usuario.Instancia().getNombreUsuario(),
                                    Toast.LENGTH_SHORT).show();

                            /*Intent tabEntregas = new Intent(getActivity(), TabEntregasActivity.class);
                            startActivity(tabEntregas);*/
                        }
                        else {
                            Toast.makeText(getActivity(), "Este usuario no tiene permiso para ingresar", Toast.LENGTH_SHORT).show();
                        }
                    }
                    /*else
                    {
                        Toast.makeText(getActivity(), "Este usuario no existe", Toast.LENGTH_SHORT).show();
                    }*/
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Este usuario no existe", Toast.LENGTH_SHORT).show();
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

