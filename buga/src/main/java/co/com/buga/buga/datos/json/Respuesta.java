package co.com.buga.buga.datos.json;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Respuesta {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public Respuesta () {

	}

	/**
	 * Se conecta a la URL especificada retornando un objeto JSON.
	 *
	 * @param url
	 * @param metodo
	 * @param params
	 * @return
	 */
	public static JSONObject JSON (String url, String metodo, List<NameValuePair> params) {
		try {
			Log.w ("INSSIDE", "JSON[URL]: "+url);
			Log.w ("INSSIDE", "JSON[METODO]: "+metodo);
			DefaultHttpClient dhc = new DefaultHttpClient ();
			HttpResponse hr;
			HttpEntity httpEntity;
			if (metodo == "GET") {
				Log.e ("INSSIDE", "JSON[EJECUTANDO] METODO GET");
				String paramString = URLEncodedUtils.format (params, "utf-8");
				url += "?" + paramString;
				HttpGet hg = new HttpGet (url);
				hr = dhc.execute (hg);
				httpEntity = hr.getEntity ();
				is = httpEntity.getContent ();
			}else{
				Log.w ("INSSIDE", "JSON[EJECUTANDO] METODO POST - POR DEFECTO");
				HttpPost hp = new HttpPost (url);
				hp.setHeader("Accept", "application/json");
				hp.setHeader("Content-type", "application/json");
				hp.setEntity (new UrlEncodedFormEntity (params));
				hr = dhc.execute (hp);
				httpEntity = hr.getEntity ();
				is = httpEntity.getContent ();
			}
			Log.w("INSSIDE", "JSON[RECIBIENDO] INPUSTREAM(IS): "+is.toString ());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace ();
		} catch (ClientProtocolException e) {
			e.printStackTrace ();
		} catch (IOException e) {
			e.printStackTrace ();
		}

		try {
			BufferedReader reader = new BufferedReader (new InputStreamReader (is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder ();
			String line = null;
			while ((line = reader.readLine ()) != null) {
				sb.append (line + "\n");
			}
			is.close ();
			json = sb.toString ();
			Log.w ("INSSIDE", "cadena(json) recibida desde la url:"+json);
		} catch (Exception e) {
			Log.e ("INSSIDE", "Error converting result " + e.toString ());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject (json);
		} catch (JSONException e) {
			Log.e ("INSSIDE", "JSONPARSER-Error parsing data " + e.toString ());
		}

		// return JSON String
		return jObj;

	}

	public static JSONObject getjObj () {
		return jObj;
	}

	public static void setjObj (JSONObject jObj) {
		Respuesta.jObj = jObj;
	}

	/**
	 * Esta cargaba los datos previamente
	 */
	public String respuestaold () {
		StringBuffer bufferCadena = new StringBuffer ("");
		try {
			HttpClient cliente = new DefaultHttpClient ();
			HttpGet peticion = new HttpGet ("http://www.buga.com.co/modulos/directorio/consultas/directorio.json.php");
			HttpResponse respuesta = cliente.execute (peticion);
			BufferedReader entrada = new BufferedReader (new InputStreamReader (respuesta.getEntity ().getContent ()));
			String separador = "";
			String NL = System.getProperty ("line.separator");
			while ((separador = entrada.readLine ()) != null) {
				bufferCadena.append (separador + NL);
			}
			entrada.close ();
		} catch (Exception e) {
			//e.printStackTrace ();
			Log.w ("INSSIDE", "Error Clase Respuesta: " + e.toString ());
		}
		String resultado = bufferCadena.toString ();
		Log.w ("INSSIDE", "Resultado: " + resultado);
		return (resultado);
	}

}
