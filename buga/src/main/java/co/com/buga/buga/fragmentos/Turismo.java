package co.com.buga.buga.fragmentos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.com.buga.buga.Componentes;
import co.com.buga.buga.R;

/**
 * Created by Alexis on 2015-01-12.
 */
public class Turismo extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Turismo newInstance(int seleccion) {
        Turismo fragment= new Turismo();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, seleccion);
        fragment.setArguments(args);
        return(fragment);
    }

    public Turismo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v;
        int layout=R.layout.fragment_turismo;
        v = inflater.inflate(layout,container, false);
        return(v);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Componentes) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}