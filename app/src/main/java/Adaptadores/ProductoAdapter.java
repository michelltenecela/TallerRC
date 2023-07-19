package Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hola.MainActivity2;
import com.example.hola.R;

import java.util.ArrayList;
import java.util.List;

import Modelos.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>{
    private Context Ctx;
    private List<Producto> lstProductos;
    public ProductoAdapter(Context mCtx, List<Producto> productos) {
        this.lstProductos = productos;
        Ctx=mCtx;
    }
    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lyitemlayout, null);
        return new ProductoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }


    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        Producto producto = lstProductos.get(position);
        holder.textViewName.setText(producto.getTitulo());
        holder.textViewMail.setText(producto.getPrecio());
        holder.textViewURLAvatar.setText(producto.getDescripcion());
        Glide.with(Ctx)
                .load(producto.getImagen())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                Bundle b = new Bundle();
                intent.putStringArrayListExtra("imageUrls", (ArrayList<String>) producto.getLista());
                intent.putExtras(b);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() { return lstProductos.size(); }

    class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewURLAvatar, textViewMail;
        ImageView imageView;
        public ProductoViewHolder(View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.txtName);
            textViewURLAvatar = itemView.findViewById(R.id.txtAvatar);
            textViewMail = itemView.findViewById(R.id.txtMail);
            imageView = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
