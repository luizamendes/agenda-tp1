package com.example.luiza.tp1_javaandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
                String name = nome.getText().toString();
                String phone = telefone.getText().toString();
                String emailAddress = email.getText().toString();
                String city = cidade.getText().toString();

                gravarContato(name, phone, emailAddress, city);
            }
        });

        contatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarContatos();
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

    private void gravarContato(String nome, String telefone, String email, String cidade) {
        String contato = String.format("%s,%s,%s,%s", nome, telefone, email, cidade);
        String nomeArquivo = "contatos.txt";
        File arq;
        byte[] dados;
        try
        {
            arq = new File(Environment.getExternalStorageDirectory(), nomeArquivo);
            FileOutputStream fos;

            dados = contato.getBytes();

            fos = new FileOutputStream(arq);
            fos.write(dados);
            fos.flush();
            fos.close();
            mensagem("Texto Salvo com sucesso!");
        }
        catch (Exception e)
        {
            mensagem("Erro : " + e.getMessage());
        }

        //        String contato = String.format("%s,%s,%s,%s", nome, telefone, email, cidade);
//
//        if (isExternalStorageWritable() && checarPermissao(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//            File file = new File(Environment.getExternalStorageDirectory(), "contatos.txt");
//            try {
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(contato.getBytes());
//                fos.close();
//
//                Toast.makeText(this, "Arquivo salvo", Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Toast.makeText(this, "Não foi possível salvar arquivos", Toast.LENGTH_SHORT).show();
//
//        }
    }

    private boolean checarPermissao(String permissao){
        int check = ContextCompat.checkSelfPermission(this, permissao);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private void carregarContatos() {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    private void mensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private String ObterDiretorio() {
        File root = android.os.Environment.getExternalStorageDirectory();
        return root.toString();
    }

}
