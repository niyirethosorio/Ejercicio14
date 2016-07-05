package com.example.claudia.ejercicio14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        String []archivos=fileList();
        if (existe(archivos, "notas.txt")){
            try {
                InputStreamReader archivo=new InputStreamReader(openFileInput("notas.txt"));

                BufferedReader br=new BufferedReader(archivo);
                String linea=br.readLine();
                String todo="";

                while (linea!=null){
                    todo=todo+linea+"\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                et1.setText(todo);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean existe(String [] archivos, String archbusca ){
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
    public void grabar(View v) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt", MainActivity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();
        finish();
    }
}
