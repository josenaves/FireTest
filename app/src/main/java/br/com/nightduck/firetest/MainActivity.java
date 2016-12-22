package br.com.nightduck.firetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mPautaLista;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Pauta");
        mPautaLista = (RecyclerView) findViewById(R.id.pauta_lista);
        mPautaLista.setHasFixedSize(true);
        mPautaLista.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Pauta,PautaViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Pauta, PautaViewHolder>(Pauta.class,R.layout.pauta_row,PautaViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(PautaViewHolder viewHolder, Pauta model, int position) {
              viewHolder.setTitulo(model.getTitulo());
              viewHolder.setDescricao(model.getDescricao());
            }
        };

        mPautaLista.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PautaViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public PautaViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setTitulo(String titulo){
            TextView pauta_titulo = (TextView) mView.findViewById(R.id.pauta_titulo);
            pauta_titulo.setText(titulo);
        }

        public void setDescricao(String descricao){
            TextView pauta_descricao = (TextView) mView.findViewById(R.id.pauta_descricao);
            pauta_descricao.setText(descricao);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add){
            Intent intent = new Intent(getApplicationContext(),PautaActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
