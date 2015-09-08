package es.aplicaciones.alvaro.entrelazadas;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Álvaro on 05/09/2015.
 */
public class MainActivity extends AppCompatActivity {

    ImageView image1, image2;
    static List<Producto> entries_search = LoadActivity.entries ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_layout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        image1 =  (ImageView) this.findViewById(R.id.image);
        image2 =  (ImageView) this.findViewById(R.id.image2);

        image1.setImageResource(R.drawable.moda);
        image2.setImageResource(R.drawable.accycomp);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int x = 0; x < entries_search.size(); x++) {

                    if (entries_search.get(x).getFamilia().equals("Moda")) {
                        entries_search.remove(x);
                        x--;
                    }
                }

            }
        }) ;
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int x = 0; x < LoadActivity.entries.size(); x++) {

                    if(LoadActivity.entries.get(x).getFamilia().equals("Accesorios y Complementos")){
                        entries_search.remove(x);
                        x--;
                    }
                }

            }
        }) ;



       // GridView gridview = (GridView) findViewById(R.id.gridview);
        //gridview.setAdapter(new ItemsAdapter(this,LoadActivity.entries));

}
}