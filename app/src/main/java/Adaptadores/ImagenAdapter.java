package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hola.R;

import java.util.List;

import Modelos.Producto;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ImagenViewHolder>{

    private List<String> imageUrls;
    private Context Ctx;

    public ImagenAdapter(Context mCtx, List<String>  imageUrls) {
        this.imageUrls = imageUrls;
        Ctx=mCtx;
    }

    @Override
    public ImagenViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_product, null);
        return new ImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);
        Glide.with(Ctx)
                .load(imageUrl)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    class ImagenViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImagenViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImageView);
        }
    }}
