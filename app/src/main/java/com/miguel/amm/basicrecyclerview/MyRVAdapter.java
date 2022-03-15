package com.miguel.amm.basicrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.MyViewHolder> {

    private List<String> autores;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // La actividad que contenga el RecyclerViewer va a tener que implementar esta interfaz
    // para recibir los clicks en los items de nuestro recycler view.
    public interface ItemClickListener {
        void onRVItemClick(View view, int position);
    }

    // El Activity que incluya el Recycler View que utilice este adapter llamará a este metodo para indicar que es el listener.
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Se llamara desde el ItemClickListener implementado en la Actividad contenedora
    public String getItem(int position) {
        return autores.get(position);
    }

    // Constructor, los datos los recibimos en el constructor.
    public MyRVAdapter(Context context, List<String> autores) {
        super();
        this.mInflater = LayoutInflater.from(context);
        this.autores = autores;
    }

    // ----------METODOS QUE HAY QUE SOBRECARGAR DE LA CLASE RecyclerView.Adapter<> ----------
    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.autor_item, parent, false);
        return new MyViewHolder(view);
    }

    // Binds (vincula) los datos al Textview para cada item
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String autor = autores.get(position);
        holder.tvAutor.setText(autor);
    }

    // Número total de items
    @Override
    public int getItemCount() {
        return autores.size();
    }
    // ---------------------------------------------------------------------------------------


    // --------- IMPLEMENTACION DE NUESTRO VIEW HOLDER ESPECÍFICO ----------------------------
    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvAutor;

        MyViewHolder(View itemView) {
            super(itemView);
            tvAutor = itemView.findViewById(R.id.tvAutor);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onRVItemClick(view, getAdapterPosition());
        }
    }
    // ---------------------------------------------------------------------------------------


}