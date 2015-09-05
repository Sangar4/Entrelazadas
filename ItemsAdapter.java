package es.aplicaciones.alvaro.entrelazadas;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Álvaro on 05/09/2015.
 */
public class ItemsAdapter extends BaseAdapter {
    private LayoutInflater inflador; //Crea Layouts a partir del XML
    TextView name, description, price;
    String filePath;
    ImageView image;
    List<Producto> objects;
    public ItemsAdapter (Context context, List<Producto> objects) {
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    public View getView(int position, View  convertView, ViewGroup parent){


        if (convertView == null) {
            convertView= inflador.inflate(R.layout.item_layout, null);
        }
        name        = (TextView) convertView.findViewById(R.id.name);
        description = (TextView) convertView.findViewById(R.id.description);
        price       = (TextView) convertView.findViewById(R.id.price);
        image       = (ImageView) convertView.findViewById(R.id.image);

        name.setText(objects.get(position).getNombre());
        description.setText(objects.get(position).getDescripcion());
        price.setText(objects.get(position).getPrecio());
        filePath="/Entrelazadas/Images/"+objects.get(position).getFamilia()+"/"+objects.get(position).getSubFamilia();
        if (objects.get(position).getCategoria().equals("NULL")){}
        else
            filePath  =  filePath +"/"+objects.get(position).getCategoria();

        filePath=Environment.getExternalStorageDirectory().toString()+filePath+"/"+objects.get(position).getReferencia()+".jpg";
        image.setImageURI(Uri.parse(filePath));
       
        return convertView;
    }

    public int getCount() {
        return objects.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
}
