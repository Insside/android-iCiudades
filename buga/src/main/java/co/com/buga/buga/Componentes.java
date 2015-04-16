package co.com.buga.buga;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import co.com.buga.buga.fragmentos.Fragment_Actualidad;
import co.com.buga.buga.fragmentos.Comercio;
import co.com.buga.buga.fragmentos.Configuracion;
import co.com.buga.buga.fragmentos.Cultura;
import co.com.buga.buga.fragmentos.Fragment_Directorio;
import co.com.buga.buga.fragmentos.Gobierno;
import co.com.buga.buga.fragmentos.Listado;
import co.com.buga.buga.fragmentos.Mapas;
import co.com.buga.buga.fragmentos.Marcador;
import co.com.buga.buga.fragmentos.Perfil;
import co.com.buga.buga.fragmentos.Turismo;
import co.com.buga.buga.fragmentos.Utilidades;

public class Componentes extends ActionBarActivity implements Listado.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private Listado listado;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes);

        listado = (Listado)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        int idnd = R.id.navigation_drawer;
        View iddl = findViewById(R.id.drawer_layout);
        listado.setUp(idnd,(DrawerLayout)iddl);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        /**
         * Aqui se actualiza el contenido principal remplazandolo por los fragmentos correspondientes
         */
        position=position+1;
        FragmentManager fragmentManager = getSupportFragmentManager();
        int contenedor = R.id.container;
        Fragment fragmento;
        if(position==1) {
            fragmento = Fragment_Actualidad.newInstance (position);
            Toast.makeText(this, "Actualidad & Eventos", Toast.LENGTH_SHORT).show();
        }else if(position==2){
            fragmento= Cultura.newInstance(position);
            Toast.makeText(this, "Historia & Patrimonio", Toast.LENGTH_SHORT).show();
        }else if(position==3){
            fragmento= Turismo.newInstance(position);
            Toast.makeText(this, "Sitios Turisticos", Toast.LENGTH_SHORT).show();
        }else if(position==4){
            fragmento= Comercio.newInstance(position);
            Toast.makeText(this, "Comercio", Toast.LENGTH_SHORT).show();
        }else if(position==5){
            fragmento= Gobierno.newInstance(position);
            Toast.makeText(this, "Gobierno", Toast.LENGTH_SHORT).show();
        }else if(position==6){
	        fragmento= Mapas.newInstance(position);
	        Toast.makeText(this, "Mapas", Toast.LENGTH_SHORT).show();
        }else if(position==7){
	        fragmento= Fragment_Directorio.newInstance (position);
	        Toast.makeText(this, "Guía Telefónica", Toast.LENGTH_SHORT).show();
        }else if(position==8){
            fragmento= Utilidades.newInstance(position);
            Toast.makeText(this, "Utilidades", Toast.LENGTH_SHORT).show();
        }else if(position==9){
            fragmento= Perfil.newInstance(position);
            Toast.makeText(this, "Perfil Personal", Toast.LENGTH_SHORT).show();
        }else if(position==10){
            fragmento= Configuracion.newInstance(position);
            Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
        }else{
            fragmento= Marcador.newInstance(position);
        }
        fragmentManager.beginTransaction().replace(contenedor,fragmento).commit();



    }



    public void onSectionAttached(int number) {
        String[] titulos=getResources().getStringArray(R.array.titulos);
        switch (number) {
            case 1:
                mTitle =titulos[number-1];
                break;
            case 2:
                mTitle =titulos[number-1];
                break;
            case 3:
                mTitle =titulos[number-1];
                break;
            case 4:
                mTitle =titulos[number-1];
                break;
            case 5:
                mTitle =titulos[number-1];
                break;
            case 6:
                mTitle =titulos[number-1];
                break;
            case 7:
                mTitle =titulos[number-1];
                break;
            case 8:
                mTitle =titulos[number-1];
                break;
            case 9:
                mTitle =titulos[number-1];
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!listado.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.componentes, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
