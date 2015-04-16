package co.com.buga.buga.redes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by Alexis on 2015-02-21.
 * Esta clase permite comprobar la Conexion de Internet en Android antes de realizar determinada accion que necesite
 * acceso a internet, permite revisar tanto "Wifi" como el acceso a datos de la "RedMovil" y mostrar un mensaje
 * de advertencia.
 * Required permissions
 * To access the Internet your application requires the android.permission.INTERNET permission.
 * To check the network state your application requires the android.permission.ACCESS_NETWORK_STATE permission.
 */
public class Conectividad {
	private final Context contexto;

	public Conectividad (Context contexto) {
		this.contexto = contexto;
	}

	public Boolean Estado() {
		if (Wifi ()) {
			showAlertDialog (this.contexto, "Conexion a Internet","Tu Dispositivo tiene Conexion a Wifi.", true);
			return(true);
		} else {
			if (RedMovil ()) {
				showAlertDialog (this.contexto, "Conexion a Internet","Tu Dispositivo tiene Conexion Movil.", true);
				return(true);
			} else {
				showAlertDialog (this.contexto, "Conexion a Internet","Tu Dispositivo no tiene Conexion a Internet.", false);
				return false;
			}
		}
	}

	protected Boolean Wifi () {
		ConnectivityManager connectivity = (ConnectivityManager) this.contexto.getSystemService (Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getNetworkInfo (ConnectivityManager.TYPE_WIFI);
			if (info != null) {
				if (info.isConnected ()) {
					return true;
				}
			}
		}
		return false;
	}

	protected Boolean RedMovil () {
		ConnectivityManager connectivity = (ConnectivityManager) this.contexto.getSystemService (Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getNetworkInfo (ConnectivityManager.TYPE_MOBILE);
			if (info != null) {
				if (info.isConnected ()) {
					return true;
				}
			}
		}
		return false;
	}

	public void showAlertDialog (Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder (context).create ();
		alertDialog.setTitle (title);
		alertDialog.setMessage (message);
		//alertDialog.setIcon ((status) ? R.drawable.success : R.drawable.fail);
		alertDialog.setButton ("OK", new DialogInterface.OnClickListener () {
			public void onClick (DialogInterface dialog, int which) {
			}
		});
		alertDialog.show ();
	}
}
