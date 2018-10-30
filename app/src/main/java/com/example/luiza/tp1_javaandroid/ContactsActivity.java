package com.example.luiza.tp1_javaandroid;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    private ArrayList<String> contatos;
    private ListView listview;
    private ArrayList<ContatoModel> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contatos = new ArrayList<>();

        carregar();

        if (listview != null) {
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ContactsActivity.this, ContactActivity.class);
                    intent.putExtra("contato", listaContatos.get(position));
                    startActivity(intent);
                }
            });
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

    public void carregar() {
        if (isExternalStorageReadable()) {
            String nomeArquivo = "contatos.txt";
            File arq;
            String lstrlinha;
            String[] infoContatos = new String[4];
            listaContatos = new ArrayList<>();

            try {
                arq = new File(Environment.getExternalStorageDirectory(), nomeArquivo);
                if (!arq.isFile()){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    mensagem("Nenhum registro encontrado");
                    return;
                }

                BufferedReader br = new BufferedReader(new FileReader(arq));

                while ((lstrlinha = br.readLine()) != null) {
                    infoContatos = lstrlinha.split(",");
                    listaContatos.add(new ContatoModel(infoContatos[0], infoContatos[1], infoContatos[2], infoContatos[3]));
                    contatos.add(infoContatos[0]);
                }

                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
                listview = findViewById(R.id.lista_contatos);
                listview.setAdapter(itemsAdapter);

            } catch (Exception e) {
                mensagem("Erro : " + e.getMessage());
            }
        }
    }

    private void mensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
