package es.aplicaciones.alvaro.entrelazadas;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by �lvaro on 06/09/2015.
 */
public class CategoryActivity extends AppCompatActivity {

    ImageView image1, image2, image3;
    GridView gridview ;
    static List<Producto> entries_search;
    List<Producto> entriesCategory = null;
    int family,subfamily;
    boolean button1Clicked , button2Clicked;
    Button button1,button2;
    Drawable buttonBackground, pinkbackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle b       = getIntent().getExtras();
        subfamily      = b.getInt("Subfamily");
        family         = b.getInt("Family");
        if( family==0 && subfamily!=0 ){
                setContentView(R.layout.item_category_list_layout);
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                buttonBackground = this.getDrawable(R.drawable.boton_marcado);
                pinkbackground   = this.getDrawable(R.color.lightpink);

            } else {
                buttonBackground = this.getResources().getDrawable(R.drawable.boton_marcado);
                pinkbackground   = this.getResources().getDrawable(R.color.lightpink);
            }

        } else
             setContentView(R.layout.item_list_layout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        gridview = (GridView) findViewById(R.id.gridview);




    }

    @Override
    protected void onStart() {

        super.onStart();
        if (family == 0 && subfamily!=0) {

                button1Clicked = true ;
                button2Clicked = false;
                button1 = (Button) this.findViewById(R.id.Button);
                button2 = (Button) this.findViewById(R.id.Button2);
                if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    button1.setBackground(buttonBackground);
                    button2.setBackground(pinkbackground);

                } else {
                    button1.setBackgroundDrawable(buttonBackground);
                    button2.setBackgroundDrawable(pinkbackground);
                }
        }

        entries_search = new ArrayList<Producto>();
        for (int x = 0; x < SubfamilyActivity.entries_search.size(); x++) {

            entries_search.add(SubfamilyActivity.entries_search.get(x));
        }

        if (family == 0) {
            switch (subfamily) {
                case 0:
                    gridview.setAdapter(new ItemsAdapter(this, entries_search));
                    break;
                default:
                   selectCategory();


                    break;
            }

        } else {
            gridview.setAdapter(new ItemsAdapter(this, entries_search));
        }
    }



        @Override
        protected void onDestroy(){
        super.onDestroy();
        entries_search.clear();
    }


   void selectCategory (){
        String category;
        if(button1Clicked) {
            entriesCategory = searchItems("Para todos los días");
            gridview.setAdapter(new ItemsAdapter(this, entriesCategory));
        }
        else{
            entriesCategory = searchItems("Días especiales");
            gridview.setAdapter(new ItemsAdapter(this, entriesCategory));
        }

    }


    List<Producto>  searchItems (String category){
        List<Producto> newEntries = new ArrayList<Producto>();
        for (int x = 0; x <entries_search.size(); x++) {
            if (entries_search.get(x).getCategoria().equals(category)) {
                newEntries.add(entries_search.get(x));
            }
        }
        return newEntries;
    }


    public void button1Click(View view){
        if (!button1Clicked) {
            button1Clicked = true;
            button2Clicked = false;
            if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                button1.setBackground(buttonBackground);
                button2.setBackground(pinkbackground);

            } else {
                button1.setBackgroundDrawable(buttonBackground);
                button2.setBackgroundDrawable(pinkbackground);
            }
            entriesCategory = searchItems("Para todos los días");
            gridview.deferNotifyDataSetChanged();
            gridview.setAdapter(new ItemsAdapter(this, entriesCategory));
        }
    }

    public void button2Click(View view){
        if (!button2Clicked) {
            button1Clicked = false;
            button2Clicked = true;
            if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                button2.setBackground(buttonBackground);
                button1.setBackground(pinkbackground);

            } else {
                button2.setBackgroundDrawable(buttonBackground);
                button1.setBackgroundDrawable(pinkbackground);
            }
            entriesCategory = searchItems("Días especiales");
            gridview.setAdapter(new ItemsAdapter(this, entriesCategory));
        }
    }
}
