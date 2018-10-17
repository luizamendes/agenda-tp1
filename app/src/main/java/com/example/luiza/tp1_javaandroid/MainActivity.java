package com.example.luiza.tp1_javaandroid;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button limpar;
    private Button salvar;
    private Button contatos;
    private EditText nome;
    private EditText telefone;
    private EditText email;
    private EditText cidade;
    private TextView erro_vazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        limpar = findViewById(R.id.btn_limpar);
        salvar = findViewById(R.id.btn_salvar);
        contatos = findViewById(R.id.btn_contatos);

        nome = findViewById(R.id.campo_nome);
        telefone = findViewById(R.id.campo_telefone);
        email = findViewById(R.id.campo_email);
        cidade = findViewById(R.id.campo_cidade);
        erro_vazio = findViewById(R.id.erro_vazio);
        erro_vazio.setVisibility(View.GONE);

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validadorFormulario(nome.getText().toString(), telefone.getText().toString(), email.getText().toString(), cidade.getText().toString())
                        && isExternalStorageWritable()
                        && isExternalStorageReadable()){
                    if(erro_vazio.getVisibility() == View.VISIBLE) erro_vazio.setVisibility(View.GONE);


                } else {
                    erro_vazio.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void limparCampos(){
        nome.setText("");
        telefone.setText("");
        email.setText("");
        cidade.setText("");
    }

    private boolean validadorFormulario(String nome, String telefone, String email, String cidade){
        return nome.equals("") && telefone.equals("") && email.equals("") && cidade.equals("");
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private void createExternalStoragePrivateFile() {
        File file = new File(getExternalFilesDir(null), "contatos.txt");
    }

}
