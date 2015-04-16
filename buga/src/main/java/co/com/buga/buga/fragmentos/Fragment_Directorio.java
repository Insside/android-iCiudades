package co.com.buga.buga.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import co.com.buga.buga.Componentes;
import co.com.buga.buga.R;
import co.com.buga.buga.datos.adaptadores.Contactos;
import co.com.buga.buga.datos.adaptadores.CursorAdapter_Directorio;
import co.com.buga.buga.datos.estructuras.Contacto;

/**
 * Created by Alexis on 2015-01-12.
 */
public class Fragment_Directorio extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private NavigationDrawerCallbacks mCallbacks;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView listado;
	private Contactos Contactos;
	private CursorAdapter_Directorio dataAdapter;

	public Fragment_Directorio () {
	}

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static Fragment_Directorio newInstance (int seleccion) {
		Fragment_Directorio fragment = new Fragment_Directorio ();
		Bundle args = new Bundle ();
		args.putInt (ARG_SECTION_NUMBER, seleccion);
		fragment.setArguments (args);
		return (fragment);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int layout = R.layout.fragment_directorio;
		View vista = inflater.inflate (layout, container, false);
		Context gtc = getActionBar ().getThemedContext ();
		/************************************************/
		Contactos = new Contactos (gtc);
		Contactos.open ();
		//Contactos.Demo_Erradicar ();
		//Contactos.Demo_Insercion ();
		Cursor cursor = Contactos.Consultar ();
		// The desired columns to be bound
		String[] columnas = new String[]{Contacto.CAMPO_NOMBRE, Contacto.CAMPO_DIRECCION, Contacto.CAMPO_TELEFONO};
		int[] to = new int[]{R.id.tv_Nombre, R.id.tv_Direccion, R.id.tv_Telefonos};
		/**
		 * Crear el adaptador utilizando el Cursor así como la información del diseño
		 * relativelayout_publicacion.xml y asignar el adaptador a la vista del listado (listView)
		 */
		int rlp = R.layout.relativelayout_contacto;
		dataAdapter = new CursorAdapter_Directorio(gtc, rlp, cursor, columnas, to, 0);
		ListView listView = (ListView) vista.findViewById (R.id.lvContactos);
		listView.setAdapter (dataAdapter);

		listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
			@Override
			public void onItemClick (AdapterView<?> listView, View view, int position, long id) {
				// Get the cursor, positioned to the corresponding row in the result set
				Cursor cursor = (Cursor) listView.getItemAtPosition (position);
				// Get the state's capital from this row in the database.
				String countryCode = cursor.getString (cursor.getColumnIndexOrThrow ("publicacion"));
			}
		});

		EditText myFilter = (EditText) vista.findViewById (R.id.myFilter);
		myFilter.addTextChangedListener (new TextWatcher () {

			public void afterTextChanged (Editable s) {
				//Log.w ("INSSIDE", "Directorio afterTextChanged: " + s.toString ());
				dataAdapter.getFilter ().filter (s.toString ());
			}

			public void beforeTextChanged (CharSequence s, int start, int count, int after) {
				//Log.w ("INSSIDE", "Directorio beforeTextChanged: " + s.toString ());

			}

			public void onTextChanged (CharSequence s, int start, int before, int count) {

			}



		});

		dataAdapter.setFilterQueryProvider (new FilterQueryProvider () {
			public Cursor runQuery (CharSequence constraint) {
				return (Contactos.Filtro (constraint.toString ()));
			}
		});

		return (vista);
	}

	@Override
	public void onAttach (Activity activity) {
		super.onAttach (activity);
		((Componentes) activity).onSectionAttached (
				                                           getArguments ().getInt (ARG_SECTION_NUMBER));
	}

	private ActionBar getActionBar () {
		return ((ActionBarActivity) getActivity ()).getSupportActionBar ();
	}

	private void selectItem (int position) {
		/**
		 mCurrentSelectedPosition = position;
		 if (listado != null) {
		 listado.setItemChecked (position, true);
		 }
		 if (mDrawerLayout != null) {
		 mDrawerLayout.closeDrawer (mFragmentContainerView);
		 }
		 if (mCallbacks != null) {
		 mCallbacks.onNavigationDrawerItemSelected (position);
		 }
		 **/
	}

	public static interface NavigationDrawerCallbacks {
		/**
		 * Called when an item in the navigation drawer is selected.
		 */
		void onNavigationDrawerItemSelected (int position);
	}

}
