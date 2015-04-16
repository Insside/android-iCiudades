package co.com.buga.buga.datos.adaptadores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import co.com.buga.buga.datos.estructuras.Contacto;
import co.com.buga.buga.datos.estructuras.Publicacion;

public class DatabaseHelper extends SQLiteOpenHelper {
	/**
	 * Constantes *
	 */
	private static final int DATABASE_VERSION = 14;
	private static final String DATABASE_NAME = "wwwbugacomco.sqlite";
	private static final String TAG = "DB";

	DatabaseHelper (Context context) {
		super (context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate (SQLiteDatabase db) {
		db.execSQL (Publicacion.TABLE_CREATE);
		db.execSQL (Contacto.TABLE_CREATE);
	}

	@Override
	/**
	 *
	 */
	public void onUpgrade (SQLiteDatabase db, int ov, int nv) {
		Log.w (TAG, "Actualizando la versión de la base de datos de  " + ov + " a " + nv + ", " +
				            "" + " todos los datos antiguos seran eliminados.");
		db.execSQL ("DROP TABLE IF EXISTS " + Publicacion.TABLE_NAME);
		db.execSQL ("DROP TABLE IF EXISTS " + Contacto.TABLE_NAME);
		onCreate (db);
	}
}
