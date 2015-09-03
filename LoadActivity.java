package es.aplicaciones.alvaro.entrelazadas;




import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import 	android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class LoadActivity extends AppCompatActivity {

    public static TextView txtStatus;
    public static ProgressBar progressStatus;
    static List<Producto> entries=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtStatus  = (TextView) findViewById(R.id.progressStatus);
        progressStatus = (ProgressBar) findViewById(R.id.progressBar);
        if(CheckCatalog()){
            ShowUpdateDialog();

        }
        else
            new UpdateCatalog(this).execute();
    }
    @Override
    protected void onStart() {


        super.onStart();

    }

     protected Boolean CheckCatalog () {
        String fileUrl = "/Entrelazadas/catalogo.xml";
        String fileRoot = android.os.Environment.getExternalStorageDirectory().getPath() + fileUrl;
        File f = new File(fileRoot);
        if(f.exists())
            return true;
        else
            return false;
    }
    protected void ShowUpdateDialog (){
        FragmentManager fm = getSupportFragmentManager();
        UpdateDialogFragment editNameDialog = new UpdateDialogFragment();
        editNameDialog.show(fm,"Sample dialog");

    }

    protected static void GetImages(Context context) {

        EntrelazadasXMLParser XmlParser = new EntrelazadasXMLParser();
        //List<Producto> entries =null;
        String root = Environment.getExternalStorageDirectory().toString()+ "/Entrelazadas";
        InputStream is;
        File file = new File (root,"catalogo.xml");
        int count=0, count1=0;
        int i=0;
        try {

            is = new BufferedInputStream(new FileInputStream(file));
            entries = XmlParser.parse(is,context);

        } catch (FileNotFoundException e){
            Toast.makeText(context, "Fichero no encontrado: " +e.toString(),
                    Toast.LENGTH_LONG).show();

        } catch (IOException e){
            Toast.makeText(context, "Error de IO: " +e.toString(),
                    Toast.LENGTH_LONG).show();
        }


        for(int x=0 ;x<entries.size() ; x++) {

            if(x%20==0){
                Toast.makeText(context, "Se est�n descargando las imagenes, esta operaci�n puede tardar unos minutos",
                        Toast.LENGTH_LONG).show();
            }
            count = CompareEntries((x), count1);
            if (count != 0 && x != 0) {

                x--;
            }
        }

    }

    protected  static int CompareEntries (int x  , int count){

        if(x>0){
            if(entries.get(x-(1)).getId().equals(entries.get(x).getId())){
                switch (count){
                    case 0:
                        entries.get(x-1).setImage2(entries.get(x).getImage1());
                        count++;
                        new DownloadImages(entries.get(x), count).execute();
                        entries.remove(x);

                            break;

                    case 1:
                        entries.get(x-1).setImage3(entries.get(x).getImage1());
                        count++;
                        new DownloadImages(entries.get(x), count).execute();
                        entries.remove(x);
                        break;

                    case 2:
                        entries.get(x-1).setImage4(entries.get(x).getImage1());
                        count++;
                        new DownloadImages(entries.get(x), count).execute();
                        entries.remove(x);
                        break;

                    case 3:
                        entries.get(x-1).setImage5(entries.get(x).getImage1());
                        count++;
                        new DownloadImages(entries.get(x), count).execute();
                        entries.remove(x);
                        break;

                    case 4:
                        entries.get(x-1).setImage2(entries.get(x).getImage1());
                            count++;
                            break;

                    default: break;
                }

            }
            else {
                count = 0;
                new DownloadImages(entries.get(x), 0).execute();
            }

            new DownloadImages(entries.get(0), 0).execute();
        }

        return count;
    }
}
