package com.example.hola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adaptadores.ProductoAdapter;
import Modelos.Producto;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements com.example.myapplication.WebService.Asynchtask {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://dummyjson.com/products",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }
    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Producto> lstUsuarios = new ArrayList<Producto> ();
        try {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("products");
            lstUsuarios = Producto.JsonObjectsBuild(JSONlistaUsuarios);
            int resId = R.anim.layout_animation_down_to_up;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                    resId);
            recyclerView.setLayoutAnimation(animation);
            ProductoAdapter adapatorUsuario = new ProductoAdapter(this, lstUsuarios);
            recyclerView.setAdapter(adapatorUsuario);
        }
        catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}