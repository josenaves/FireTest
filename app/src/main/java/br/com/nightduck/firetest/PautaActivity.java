package br.com.nightduck.firetest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PautaActivity extends AppCompatActivity {
    private EditText  mTitulo;
    private EditText mDescrição;
    private Button mCriar;
    private ProgressDialog mProgresso;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pauta);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Pauta");
        mTitulo = (EditText) findViewById(R.id.activity_pauta_Edt_titulo);
        mCriar = (Button) findViewById(R.id.activity_pauta_Btn_enviarpauta);
        mDescrição = (EditText) findViewById(R.id.activity_pauta_Edt_descricaoPauta);

        mCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPautas();

            }

        });

    }

    private void startPautas(){

        String titulo_val= mTitulo.getText().toString().trim();
        String descricao_val = mDescrição.getText().toString().trim();

        if(!TextUtils.isEmpty(titulo_val)&& !TextUtils.isEmpty(descricao_val)){
            DatabaseReference newPauta = mDatabase.push();

            newPauta.child("titulo").setValue(titulo_val);
            newPauta.child("Descrição").setValue(descricao_val);




            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

    }

}
