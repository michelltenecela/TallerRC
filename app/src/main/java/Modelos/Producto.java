package Modelos;

import android.content.Intent;

import com.example.hola.MainActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String titulo;
    private String descripcion;
    private String precio;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    private String imagen;

    private List<String> lista;

    public Producto(JSONObject a) throws JSONException {
        titulo = a.getString("title").toString();
        descripcion = a.getString("description").toString() ;
        precio = a.getString("price").toString() ;
        imagen = a.getString("thumbnail").toString() ;
        JSONArray imagesArray = a.getJSONArray("images");
        lista = new ArrayList<>();
        for (int i = 0; i < imagesArray.length(); i++) {
            lista.add(imagesArray.getString(i));
    }
    }
    public static ArrayList<Producto> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Producto> productos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            productos.add(new Producto(datos.getJSONObject(i)));
        }
        return productos;
    }
}