package co.com.buga.buga.datos.tareas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Timer;

/**
 * Esta clase tiene por objetivo realizar la actualización asincrona de los valores registrados en la base de datos
 * local del dispositivo donde se ejecute la aplicación, se fundaenta en la clase AsynTask para poder ser ejecutada en
 * segundo plano sin interrumpir el flujo normal de la aplicación.
 * El contexto es necesario ya que es requisito para usar el adapatador de la base de datos
 */
public class AsyncTask_Directorio extends AsyncTask<Void, Void, String> {
	private final Context contexto;
	private final String filtro;
	private final int demora;
	private final int repeticion;
	private final int cantidad;
	private TimerTask_Directorio tarea;
	private Timer temporizador = new Timer ();
	private ProgressDialog dialog;

	public AsyncTask_Directorio (Context context,String filtro,int demora,int repeticion,int cantidad) {
		this.contexto = context;
		this.filtro=filtro;
		this.demora=demora;
		this.repeticion=repeticion;
		this.cantidad=cantidad;
		this.tarea = new TimerTask_Directorio (this.contexto,this.filtro,this.cantidad);
		this.dialog = new ProgressDialog (this.contexto);
	}


	protected void onPreExecute() {
		dialog.setProgress(0);
		dialog.setMax(100);
		dialog.show();
	}


	protected String doInBackground (Void... argumentos) {
		Log.w ("INSSIDE", "doInBackground");
		this.temporizador.schedule (tarea,this.demora,this.repeticion);
		dialog.setMessage("Descargando...");
		dialog.setTitle("Progreso");
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(false);
		return ("");
	}

	protected void onPostExecute (String mensaje) {
		Log.w ("INSSIDE", "Ejecución completada con exito.");

		dialog.dismiss();
	}
}
