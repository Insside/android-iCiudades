package co.com.buga.buga.datos.adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.com.buga.buga.datos.estructuras.Publicacion;

public class Publicaciones {

	private static final String TAG = "Publicaciones";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private final Context mCtx;

	public Publicaciones (Context ctx) {
		this.mCtx = ctx;
	}

	public Publicaciones open () throws SQLException {
		mDbHelper = new DatabaseHelper (mCtx);
		mDb = mDbHelper.getWritableDatabase ();
		return this;
	}

	public void close () {
		if (mDbHelper != null) {
			mDbHelper.close ();
		}
	}

	/**
	 * Creamos un método "insertar" y como parámetros los datos que queremos insertar
	 * en la tabla (id, nombre, telefono, email). Dentro del método creamos una instancia de la
	 * clase "SQLiteDatabase" y usamos su método "getWritableDatabase()" para poder escribir en
	 * la base de datos. Encapsulamos en un if por si acaso la base de datos no existe
	 * y ya dentro del if creamos una instancia de la clase "ContentValues" que como su nombre
	 * indica es un almacenador de un conjunto de datos. Usamos el metodo "put(key, value)"
	 * que nos pide como primer parámetro "key" el nombre donde establecer el valor almacenado
	 * y como segundo parámetro el valor que queremos almacenar. Una vez almacenamos los datos
	 * insertamos una fila en la tabla usamos el método "insert(table, nullColumnHack, values)"
	 * que nos pide el nombre de la tabla "table", un segundo parámetro en caso de que
	 * necesitemos insertar valores nulos en la tabla "nullColumnHack" en este caso lo
	 * dejaremos pasar ya que no lo vamos a usar y por lo tanto lo ponemos a null y como
	 * tercer parámetro "values" nos pide un ContentValues. Para concluir deberemos cerrar
	 * la base de datos con el método "close()".
	 *
	 * @param publicacion
	 * @return
	 */
	public long Crear (Publicacion publicacion) {
		Log.w (TAG, "Metodo Crear");
		ContentValues valores = new ContentValues ();
		valores.put (Publicacion.CAMPO_PUBLICACION, publicacion.publicacion);
		valores.put (Publicacion.CAMPO_TITULO, publicacion.titulo);
		valores.put (Publicacion.CAMPO_RESUMEN, publicacion.resumen);
		valores.put (Publicacion.CAMPO_CONTENIDO, publicacion.contenido);
		valores.put (Publicacion.CAMPO_FECHA, publicacion.fecha);
		valores.put (Publicacion.CAMPO_HORA, publicacion.hora);
		valores.put (Publicacion.CAMPO_CREADOR, publicacion.creador);
		long rowid = mDb.insert (Publicacion.TABLE_NAME, null, valores);
		return (rowid);
	}

	/**
	 * El método que usamos en este casp es muy similar al de leer un registro pero en
	 * este caso no especificamos que registro queremos recuperar, por lo tanto ponemos
	 * su parámetro a null. A parte creamos una variable "lista_contactos" donde almacenaremos
	 * todos los registros de la tabla en objetos contactos. En el bucle do-while usamos
	 * el método "moveToNext()" como parámetro que se encargara de pasar al siguiente
	 * registro de la tabla y por lo tanto recorrer todos los registros de la tabla.
	 *
	 * @return
	 */
	public Cursor Consultar () {
		Log.w (TAG, "Metodo Consultar2");
		String tabla = Publicacion.TABLE_NAME;
		String[] valores = new String[]{
				    Publicacion.CAMPO_PUBLICACION + " as _id",
				    Publicacion.CAMPO_PUBLICACION,
				    Publicacion.CAMPO_TITULO,
				    Publicacion.CAMPO_RESUMEN};
		Cursor c = mDb.query (tabla, valores, null, null, null, null, null);
		if (c != null) {
			Log.w (TAG, "Datos obtenidos moviendose al primer registro.");
			if (c.moveToFirst () == false) {
				Log.w (TAG, "El cursor esta vacio.");
				return (null);
			} else {
				for (c.moveToFirst (); !c.isAfterLast (); c.moveToNext ()) {
					String publicacion = c.getString (c.getColumnIndex (Publicacion.CAMPO_PUBLICACION));
					String titulo = c.getString (c.getColumnIndex (Publicacion.CAMPO_TITULO));
					Log.w (TAG, "{" + publicacion + "," + titulo + "}");
				}
			}
		} else {
			Log.w (TAG, "No se obtuvieron datos en el Cursor=NULL");
		}
		return (c);
	}

	public Cursor Filtro (String inputText) throws SQLException {
		Log.w (TAG, "Metodo fetchCountriesByName");
		String tabla = Publicacion.TABLE_NAME;
		String[] valores = new String[]{
				                               Publicacion.CAMPO_PUBLICACION + " as _id",
				                               Publicacion.CAMPO_PUBLICACION,
				                               Publicacion.CAMPO_TITULO,
				                               Publicacion.CAMPO_RESUMEN};
		Cursor c = null;
		if (inputText == null || inputText.length () == 0) {
			c = mDb.query (tabla, valores, null, null, null, null, null);
		} else {
			String sql = Publicacion.CAMPO_RESUMEN + " like '%" + inputText + "%'";
			c = mDb.query (true, tabla, valores, sql, null, null, null, null, null);
		}
		if (c != null) {
			c.moveToFirst ();
		}
		return (c);
	}

	/**
	 * Insercion de valores de prueba
	 */
	public void Demo_Insercion () {
		Log.w (TAG, "Metodo Insercion");
		Crear (new Publicacion (1, "Aguas de Buga S.A. E.S.P.", "Cl 3 9-38 Buga, Colombia Teléfono: (57) (2) 2280909", "C1", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (2, "Hotel Casa del Peregrino", "Cl 4 14-45 Buga, Colombia, Teléfono: (57) (2) 2280308", "C2", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (3, "Hotel Casa Real", "Cr14 2-39 Buga, Colombia, Teléfono: (57) (2) 2280381", "C3", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (4, "Almacen Buga Fashion", "Gorras, estamos ubicados en la carrera 14 # 20-19, al lado de la peluqueria arte urbano.", "C4", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (5, "Pollos Asados Mario", "Cr12 7-02 Buga, Colombia, Teléfono: (57) (2) 2282471", "C5", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (6, "EPSA S.A. E.S.P.", "Cl 4 5-33 Buga, Colombia Teléfono: (57) (2) 2375700", "C6", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (7, "Publicar Multimedia S.A.S", "R7", "C7", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (8, "Edictores de Occidente", "Hacemos que nuestros anunciantes se den a conocer a los más de 6 millones de usuarios que nos consultan: Guía Telefónica, Paginasamarillas.info.ec, Guía Multimedia y Aplicativo Móvil", "C8", "2015-02-01", "13:00:00", 01));
		Crear (new Publicacion (9, "Producciones Gil", "R9", "C9", "2015-02-01", "13:00:00", 01));
	}
	/**
	 * Borrar todo
	 */
	public boolean Demo_Erradicar () {
		Log.w (TAG, "Metodo Erradicar");
		int doneDelete = 0;
		doneDelete = mDb.delete (Publicacion.TABLE_NAME, null, null);
		return doneDelete > 0;
	}

}
