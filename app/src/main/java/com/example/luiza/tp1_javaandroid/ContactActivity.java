package com.example.luiza.tp1_javaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {

    private TextView nome;
    private TextView telefone;
    private TextView email;
    private TextView cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        nome = findViewById(R.id.nome);
        telefone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);
        cidade = findViewById(R.id.cidade);

        ContatoModel contato = (ContatoModel) getIntent().getSerializableExtra("contato");

        nome.setText("Nome: " + contato.getNome());
        telefone.setText("Telefone: " + contato.getTelefone());
        email.setText("E-mail: " + contato.getEmail());
        cidade.setText("Cidade: " + contato.getCidade());
    }
}
