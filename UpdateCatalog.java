package es.aplicaciones.alvaro.entrelazadas;

import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Álvaro on 11/08/2015.
 */



public class UpdateCatalog extends AsyncTask <Void,Integer,Void> {
    String dwnload_file_path = "http://servidoralvaro.ddns.net/images/Catalogo.xml";

    public UpdateCatalog() {
    }

    @Override
    protected Void doInBackground(Void... x) {

       try {
            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();

            //set the path where we want to save the file
            String root = Environment.getExternalStorageDirectory().toString();
            File SDCardRoot =new File(root+"/Entrelazadas");
           SDCardRoot.mkdirs();
            //create a new file, to save the downloaded file
            File file = new File(SDCardRoot, "catalogo.xml");

            FileOutputStream fileOutput = new FileOutputStream(file);

            //Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();
            //create a buffer...
            byte[] buffer = new byte[4 * 1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) < 0) {
                fileOutput.write(buffer, 0, bufferLength);
                publishProgress(0);
            }
            fileOutput.flush();
            //close the output stream when complete //
            fileOutput.close();
            publishProgress(1);
        }
        catch (final MalformedURLException e) {

            //showError("Error : MalformedURLException " + e);
            e.printStackTrace();
        } catch (final IOException e) {
            //showError("Error : IOException " + e);
            e.printStackTrace();
        } catch (final Exception e) {
            //showError("Error : Please check your internet connection " + e);
        }
     return null;
    }

    protected void onProgressUpdate(Integer... option) {
        for(int i=0; i<option.length ; i++) {
            switch (option[i]) {
                case 0:
                    LoadActivity.txtStatus.setText(R.string.downloading);
                    break;
                case 1:
                    LoadActivity.txtStatus.setText(R.string.complet);
                    break;
                default:

            }
        }
    }
    @Override
    protected void onPostExecute(final Void result) {
        // Update your views here
        LoadActivity.progressStatus.setVisibility(View.GONE);
    }
}




