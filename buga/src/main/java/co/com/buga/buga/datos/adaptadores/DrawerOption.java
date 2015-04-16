package co.com.buga.buga.datos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.com.buga.buga.R;

public class DrawerOption extends ArrayAdapter {
    int size = 1;
    Context context;
    int[] images;
    String[] titleArray;
    String[] descriptionArray;

    public DrawerOption(Context c, String[] titles, int imgs[], String[] desc) {
        super(c, R.layout.activity_componentes_fila, R.id.textView, titles);
        this.context = c;
        this.images = imgs;
        this.titleArray = titles;
        this.descriptionArray = desc;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        int acf = R.layout.activity_componentes_fila;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(acf, parent, false);
        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        myImage.setImageResource(images[position]);
        TextView myTitle = (TextView) row.findViewById(R.id.textView);
        myTitle.setText(titleArray[position]);
        TextView myDescription = (TextView) row.findViewById(R.id.textView2);
        myDescription.setText(descriptionArray[position]);
        return row;
    }
}
