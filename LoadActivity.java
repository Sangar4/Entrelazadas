package es.aplicaciones.alvaro.entrelazadas;




import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import 	android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;
import java.io.File;

public class LoadActivity extends AppCompatActivity {

    public static TextView txtStatus;
    public static ProgressBar progressStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtStatus  = (TextView) findViewById(R.id.progressStatus);
        progressStatus = (ProgressBar) findViewById(R.id.progressBar);

    }
    @Override
    protected void onStart() {


        super.onStart();
       if(CheckCatalog()){
        ShowUpdateDialog();
       }
        new UpdateCatalog().execute();
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

}
