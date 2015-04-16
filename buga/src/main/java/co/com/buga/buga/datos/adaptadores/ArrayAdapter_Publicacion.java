package co.com.buga.buga.datos.adaptadores;

/**
 * Created by Alexis on 2015-01-19.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.com.buga.buga.R;

public class ArrayAdapter_Publicacion extends ArrayAdapter {
    int size = 1;
    Context context;
    int[] images;
    String[] titleArray;
    String[] descriptionArray;

    public ArrayAdapter_Publicacion(Context c, String[] titles, int imgs[], String[] desc) {
        super(c, R.layout.relativelayout_publicacion, R.id.textView, titles);
        this.context = c;
        this.images = imgs;
        this.titleArray = titles;
        this.descriptionArray = desc;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        int acf = R.layout.relativelayout_publicacion;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(acf, parent, false);
        ImageView myImage = (ImageView) row.findViewById(R.id.iv_Imagen);
        myImage.setImageResource(images[position]);
        TextView myTitle = (TextView) row.findViewById(R.id.tv_Titulo);
        myTitle.setText(titleArray[position]);
        TextView myDescription = (TextView) row.findViewById(R.id.tv_Resumen);
        myDescription.setText(descriptionArray[position]);
        return row;
    }
}
