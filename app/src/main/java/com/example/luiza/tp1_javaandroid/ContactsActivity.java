package com.example.luiza.tp1_javaandroid;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        File arq;
        String linha;
        try {
            arq = new File(Environment.getExternalStorageDirectory(), "contatos.txt");
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((linha = br.readLine()) != null) {
                String[] array = new String[4];
                array = linha.split(",");
                nome.setText(array[0]);
                telefone.setText(array[1]);
            }
        }
        catch (Exception e){
            System.out.print("fudeu");
        }
    }


}
