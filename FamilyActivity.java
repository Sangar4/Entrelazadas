package es.aplicaciones.alvaro.entrelazadas;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by �lvaro on 05/09/2015.
 */
public class FamilyActivity extends AppCompatActivity {

    ImageView image1, image2;
    static List<Producto> entries_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_layout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        // image1 =  (ImageView) this.findViewById(R.id.image);
        // image2 =  (ImageView) this.findViewById(R.id.image2);

        //image1.setImageResource(R.drawable.moda);
        //image2.setImageResource(R.drawable.accycomp);
    }


    @Override
    protected void onStart() {

        super.onStart();
        entries_search = new ArrayList<Producto>();
       for (int x = 0; x < LoadActivity.entries.size(); x++) {

            entries_search.add(LoadActivity.entries.get(x));
        }
        image1 =  (ImageView) this.findViewById(R.id.image);
        image2 =  (ImageView) this.findViewById(R.id.image2);

        image1.setImageResource(R.drawable.moda);
        image2.setImageResource(R.drawable.accycomp);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int x = 0; x < entries_search.size(); x++) {

                    if (!entries_search.get(x).getFamilia().equals("Moda")) {
                        entries_search.remove(x);
                        x--;
                    }
                }
                Intent intent = new Intent(getApplicationContext(), SubfamilyActivity.class);
                Bundle b = new Bundle();
                b.putInt("Family", 0);
                intent.putExtras(b);
                startActivity(intent);

            }
        }) ;

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int x = 0; x < entries_search.size(); x++) {

                    if (!entries_search.get(x).getFamilia().equals("Accesorios y Complementos")) {
                        entries_search.remove(x);
                        x--;
                    }
                }
                Intent intent = new Intent(getApplicationContext(), SubfamilyActivity.class);
                Bundle b = new Bundle();
                b.putInt("Family", 1);
                intent.putExtras(b);
                startActivity(intent);

            }
        }) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        entries_search.clear();
    }
}