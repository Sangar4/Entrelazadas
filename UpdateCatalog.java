package es.aplicaciones.alvaro.entrelazadas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
 * Created by �lvaro on 11/08/2015.
 */



public class UpdateCatalog extends AsyncTask <Void,Integer,Void> {
    String dwnload_file_path = "http://servidoralvaro.ddns.net/images/Catalogo.xml";
    String exception, urlStatusMessage;
    private Context mContext;


    public UpdateCatalog(Context context) {
            mContext = context;
    }

    public int HttpCtatusCode;
    @Override
    protected Void doInBackground(Void... x) {

       try {
            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();
           HttpCtatusCode   = urlConnection.getResponseCode();
           urlStatusMessage = urlConnection.getResponseMessage();
            if( HttpCtatusCode == 200 || HttpCtatusCode == 202 )
            {

               //set the path where we want to save the file
               String root = Environment.getExternalStorageDirectory().toString();
               File SDCardRoot = new File(root + "/Entrelazadas");
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
           else{
                publishProgress(5);
            }
        }
        catch (final MalformedURLException e) {
            exception = e.toString();
            publishProgress(2);
            e.printStackTrace();
        } catch (final IOException e) {
           publishProgress(3);
           e.printStackTrace();
        } catch (final Exception e) {
           publishProgress(4);
           exception = e.toString();

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
                case 2:
                    LoadActivity.txtStatus.setText(R.string.url_malformed + ":" + exception);
                    LoadActivity.txtStatus.setTextColor((Color.rgb(255, 0, 0)));
                    break;
                case 3:
                    LoadActivity.txtStatus.setText(R.string.io_exception+":"+exception);
                    LoadActivity.txtStatus.setTextColor((Color.rgb(255, 0, 0)));
                    break;
                case 4:
                    LoadActivity.txtStatus.setText(R.string.exception+":"+exception);
                    LoadActivity.txtStatus.setTextColor((Color.rgb(255, 0, 0)));
                    break;
                case 5:
                    LoadActivity.txtStatus.setText(R.string.http_status_error + HttpCtatusCode+":"+urlStatusMessage);
                    LoadActivity.txtStatus.setTextColor((Color.rgb(255, 0, 0)));
                    break;
                default:

            }
        }
    }
    @Override
    protected void onPostExecute(final Void result) {
        // Update your views here
        LoadActivity.progressStatus.setVisibility(View.GONE);
        Intent intent = new Intent(mContext, DownloadImages.class);
        mContext.startActivity(intent);
    }
}




