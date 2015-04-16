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
import co.com.buga.buga.datos.adaptadores.Publicaciones;
import co.com.buga.buga.datos.estructuras.Publicacion;

/**
 * Created by Alexis on 2015-01-12.
 */
public class Fragment_Actualidad extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private NavigationDrawerCallbacks mCallbacks;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView listado;
	private Publicaciones publicaciones;
	private SimpleCursorAdapter dataAdapter;

	public Fragment_Actualidad () {
	}

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static Fragment_Actualidad newInstance (int seleccion) {
		Fragment_Actualidad fragment = new Fragment_Actualidad ();
		Bundle args = new Bundle ();
		args.putInt (ARG_SECTION_NUMBER, seleccion);
		fragment.setArguments (args);
		return (fragment);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int layout = R.layout.fragment_actualidad;
		View vista = inflater.inflate (layout, container, false);
		Context gtc = getActionBar ().getThemedContext ();
		/************************************************/
		publicaciones = new Publicaciones (gtc);
		publicaciones.open ();
		publicaciones.Demo_Erradicar ();
		//publicaciones.Demo_Insercion ();
		Cursor cursor = publicaciones.Consultar();
		// The desired columns to be bound
		String[] columnas = new String[]{
				                                Publicacion.CAMPO_PUBLICACION,
				                                Publicacion.CAMPO_TITULO,
				                                Publicacion.CAMPO_RESUMEN
		};
		int[] to = new int[]{
				                    R.id.tv_Publicacion,
				                    R.id.tv_Titulo,
				                    R.id.tv_Resumen
		};
		/**
		 * Crear el adaptador utilizando el Cursor así como la información del diseño
		 * relativelayout_publicacion.xml y asignar el adaptador a la vista del listado (listView)
		 */
		int rlp = R.layout.relativelayout_publicacion;
		dataAdapter = new SimpleCursorAdapter (gtc, rlp, cursor, columnas, to, 0);
		ListView listView = (ListView) vista.findViewById (R.id.lvPublicaciones);
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
			}

			public void beforeTextChanged (CharSequence s, int start,
			                               int count, int after) {
			}

			public void onTextChanged (CharSequence s, int start, int before, int count) {
				dataAdapter.getFilter ().filter (s.toString ());
			}
		});

		dataAdapter.setFilterQueryProvider (new FilterQueryProvider () {
			public Cursor runQuery (CharSequence constraint) {
				return (publicaciones.Filtro(constraint.toString ()));
			}
		});

		/***********************************************/

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
