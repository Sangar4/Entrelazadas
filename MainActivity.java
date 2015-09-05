package es.aplicaciones.alvaro.entrelazadas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Álvaro on 05/09/2015.
 */
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_layout);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ItemsAdapter(this,LoadActivity.entries));
    }
}