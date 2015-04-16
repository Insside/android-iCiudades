package co.com.buga.buga.datos.tareas;

import android.content.Context;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import co.com.buga.buga.datos.adaptadores.Contactos;
import co.com.buga.buga.datos.estructuras.Contacto;
import co.com.buga.buga.datos.json.Respuesta;
import co.com.buga.buga.librerias.Cadenas;

/**
 * Created by Alexis on 2015-02-15.
 */
public class TimerTask_Directorio extends TimerTask {
	private final Context contexto;
	private Contactos contactos;
	private int actual = 1;
	private int siguiente = 1;
	private int cantidad;
	private int total = 0;
	private int ejecuciones = 0;
	private String filtro = new String ("");
	private String estado="";

	public TimerTask_Directorio (Context context,String filtro,int cantidad) {
		this.contexto = context;
		this.cantidad=cantidad;
		this.contactos = new Contactos (context);
		this.filtro=filtro;
	}


	public void run() {
		this.actualizacion ();
	}


	protected void actualizacion(){
		this.ejecuciones++;
		Log.w ("INSSIDE", "TAREA TEMPORIZADA: "+Integer.toString (this.ejecuciones));
		if(this.ejecuciones==1||this.total>=this.siguiente) {
			this.contactos.open ();
			try {
				String url = new String ("http://www.buga.com.co/modulos/directorio/consultas/directorio.json.php" +
						                         "?filtro=" + Cadenas.URLEncode(this.filtro)+
						                         "&actual=" + this.siguiente +
						                         "&cantidad=" + this.cantidad);
				String metodo = new String ("POST");
				List<NameValuePair> parametros = new ArrayList<NameValuePair> ();
				JSONObject jor = Respuesta.JSON (url, metodo, parametros);
				/** Conteo de total de registros existentes **/
				this.actual = jor.getInt ("actual");
				this.siguiente = jor.getInt ("siguiente");
				this.cantidad = jor.getInt ("cantidad");
				this.total = jor.getInt ("total");

				JSONArray ja = jor.getJSONArray ("datos");
				for (int i = 0; i < ja.length (); i++) {
					JSONObject jo = ja.getJSONObject (i);
					int contacto = jo.optInt ("contacto", 0);
					String nombre = jo.optString ("nombre");
					String direccion = jo.optString ("direccion");
					String telefono = jo.optString ("telefono");
					String movil = jo.optString ("movil");
					String fax = jo.optString ("fax");
					String pbx = jo.optString ("pbx");
					String categoria = jo.optString ("creador");
					String sector = jo.optString ("sector");
					int creador = jo.optInt ("creador", 0);
					String fecha = jo.optString ("fecha");
					String hora = jo.optString ("hora");
					String pagina = jo.optString ("pagina");
					String claves = jo.optString ("claves");
					String web = jo.optString ("web");
					String correo = jo.optString ("correo");
					String clase = jo.optString ("clase");
					int modificacion = jo.optInt ("modificacion", 0);
					Log.w ("INSSIDE", "JSON[" + i + "]" + Integer.toString (contacto) + nombre);
					contactos.Sincronizar (new Contacto (contacto, nombre, direccion, telefono, movil, fax, pbx, categoria, sector, creador, fecha, hora, pagina, claves, web, correo, clase, modificacion));
				}
			} catch (JSONException e) {
				//e.printStackTrace ();
				Log.w ("INSSIDE", "ERROR JSON DEL UPDATE DEL DIRECTORIO: " + e.toString ());
			}
		}
	}



}


