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
public class SubfamilyActivity extends AppCompatActivity {

    ImageView image1, image2, image3;
    static List<Producto> entries_search ;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_layout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        entries_search = FamilyActivity.entries_search ;
        image1 =  (ImageView) this.findViewById(R.id.image);
        image2 =  (ImageView) this.findViewById(R.id.image2);
        image3 =  (ImageView) this.findViewById(R.id.image3);

        Bundle b = getIntent().getExtras();
        value = b.getInt("Family");

        switch (value) {
            case 0:
                image1.setImageResource(R.drawable.logo);
                image2.setImageResource(R.drawable.logo);
                image3.setImageResource(R.drawable.logo);
                break;
            case 1:
                image1.setImageResource(R.drawable.moda);
                image2.setImageResource(R.drawable.moda);
                image3.setImageResource(R.drawable.moda);
                break;
            default:
                    break;
        }




        // GridView gridview = (GridView) findViewById(R.id.gridview);
        //gridview.setAdapter(new ItemsAdapter(this,LoadActivity.entries));

    }

    @Override
    protected void onStart() {

        super.onStart();
        entries_search = new ArrayList<Producto>();
        for (int x = 0; x <FamilyActivity.entries_search.size(); x++) {

            entries_search.add(FamilyActivity.entries_search.get(x));
        }

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItems("Camisetas","Pulseras",0);
            }
        }) ;
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItems("Vestidos","Llaveros",1);
            }
        }) ;

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItems("Faldas","Collares",2);
            }
        }) ;

    }

  public void searchItems  (String subfamily1,String subfamily2, int subfamilyInt){
      String subfamily;
        Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
        Bundle b = new Bundle();
        b.putInt("Family", value);
        switch (value) {
            case 0:
                subfamily = subfamily1;
                b.putInt("Subfamily", subfamilyInt);
                break;
            case 1:
                subfamily = subfamily2;
                b.putInt("Subfamily", subfamilyInt);
                break;
            default:
                subfamily = null;
                break;
        }

      for (int x = 0; x < entries_search.size(); x++) {

          if (!entries_search.get(x).getSubFamilia().equals(subfamily)) {
              entries_search.remove(x);
              x--;
          }
      }
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        entries_search.clear();
    }
}