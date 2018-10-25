package com.example.luiza.tp1_javaandroid;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContactsActivity extends AppCompatActivity {

    TextView nome;
    TextView telefone;
    TextView cidade;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        nome = findViewById(R.id.teste);
        telefone = findViewById(R.id.teste2);

        carregar();
    }

    public void carregar() {

        String nomeArquivo = "contatos.txt";
        File arq;
        String lstrlinha;
        try {
            nome.setText("");

            arq = new File(Environment.getExternalStorageDirectory(), nomeArquivo);
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null) {
                if (!nome.getText().toString().equals("")) {
                    nome.append("\n");
                }
                nome.append(lstrlinha);
            }

            mensagem("Texto Carregado com sucesso!");

        } catch (Exception e) {
            mensagem("Erro : " + e.getMessage());
        }
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

    private void mensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
