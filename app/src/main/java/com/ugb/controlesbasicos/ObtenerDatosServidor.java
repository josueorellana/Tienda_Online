package com.ugb.controlesbasicos;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerDatosServidor extends AsyncTask<String,String,String> {
    HttpURLConnection httpURLConnection;
    @Override
    protected String doInBackground(String... voids) {
        StringBuilder resul = new StringBuilder();
        try {
            URL url = new URL(utilidades.urlConsulta);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Authorization","Basic"+ utilidades.credencialesCodificadas);

            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            while ((linea=bufferedReader.readLine())!=null){
                resul.append(linea);
            }
        }catch (Exception e){
            return e.getMessage();
    }finally {
          httpURLConnection.disconnect();
        }
        return resul.toString();
        }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
