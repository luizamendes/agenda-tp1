package com.example.luiza.tp1_javaandroid;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    ArrayList<String> contatos;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contatos = new ArrayList<>();

        carregar();
    }

    public void carregar() {

        String nomeArquivo = "contatos.txt";
        File arq;
        String lstrlinha;
        String[] infoContatos = new String[4];

        try {
            arq = new File(Environment.getExternalStorageDirectory(), nomeArquivo);
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null) {
                infoContatos = lstrlinha.split(",");
                contatos.add(infoContatos[0]);
            }

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
            listview = findViewById(R.id.lista_contatos);
            listview.setAdapter(itemsAdapter);

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
