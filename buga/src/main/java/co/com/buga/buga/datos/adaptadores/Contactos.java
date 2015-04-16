package co.com.buga.buga.datos.adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.com.buga.buga.datos.estructuras.Contacto;
import co.com.buga.buga.datos.tareas.AsyncTask_Directorio;

public class Contactos {

	private static final String TAG = "Contactos";
	private DatabaseHelper dbh;
	private SQLiteDatabase db;
	private final Context contexto;
	private final String[] valores = new String[]{Contacto.CAMPO_CONTACTO + " as _id",
			                                             Contacto.CAMPO_CONTACTO,
			                                             Contacto.CAMPO_NOMBRE,
			                                             Contacto.CAMPO_DIRECCION,
			                                             Contacto.CAMPO_TELEFONO,
			                                             Contacto.CAMPO_MOVIL,
			                                             Contacto.CAMPO_MODIFICACION
	};

	public Contactos (Context contexto) {
		this.contexto = contexto;
	}

	public Contactos open () throws SQLException {
		dbh = new DatabaseHelper (contexto);
		db = dbh.getWritableDatabase ();
		return this;
	}

	public void close () {
		if (dbh != null) {
			dbh.close ();
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
	 * @param c
	 * @return
	 */
	public long Crear (Contacto c) {
		Log.w (TAG, "Metodo Crear");
		ContentValues cv = new ContentValues ();
		cv.put (c.CAMPO_CONTACTO, c.contacto);
		cv.put (c.CAMPO_NOMBRE, c.nombre);
		cv.put (c.CAMPO_DIRECCION, c.direccion);
		cv.put (c.CAMPO_TELEFONO, c.telefono);
		cv.put (c.CAMPO_MOVIL, c.movil);
		cv.put (c.CAMPO_FAX, c.fax);
		cv.put (c.CAMPO_PBX, c.pbx);
		cv.put (c.CAMPO_CATEGORIA, c.categoria);
		cv.put (c.CAMPO_SECTOR, c.sector);
		cv.put (c.CAMPO_CREADOR, c.creador);
		cv.put (c.CAMPO_FECHA, c.fecha);
		cv.put (c.CAMPO_HORA, c.hora);
		cv.put (c.CAMPO_PAGINA, c.pagina);
		cv.put (c.CAMPO_CLAVES, c.claves);
		cv.put (c.CAMPO_WEB, c.web);
		cv.put (c.CAMPO_CORREO, c.correo);
		cv.put (c.CAMPO_CLASE, c.clase);
		cv.put (c.CAMPO_MODIFICACION, c.modificacion);
		long rowid = db.insert (Contacto.TABLE_NAME, null, cv);
		return (rowid);
	}

	/**
	 * Este metodo permite actualizar directamente el contenido de un registro.
	 * Parameters:
	 *
	 * @param c
	 * @return numero de filas afectadas
	 */
	public int Actualizar (Contacto c) {
		ContentValues cv = new ContentValues ();
		cv.put (c.CAMPO_CONTACTO, c.contacto);
		cv.put (c.CAMPO_NOMBRE, c.nombre);
		cv.put (c.CAMPO_DIRECCION, c.direccion);
		cv.put (c.CAMPO_TELEFONO, c.telefono);
		cv.put (c.CAMPO_MOVIL, c.movil);
		cv.put (c.CAMPO_FAX, c.fax);
		cv.put (c.CAMPO_PBX, c.pbx);
		cv.put (c.CAMPO_CATEGORIA, c.categoria);
		cv.put (c.CAMPO_SECTOR, c.sector);
		cv.put (c.CAMPO_CREADOR, c.creador);
		cv.put (c.CAMPO_FECHA, c.fecha);
		cv.put (c.CAMPO_HORA, c.hora);
		cv.put (c.CAMPO_PAGINA, c.pagina);
		cv.put (c.CAMPO_CLAVES, c.claves);
		cv.put (c.CAMPO_WEB, c.web);
		cv.put (c.CAMPO_CORREO, c.correo);
		cv.put (c.CAMPO_CLASE, c.clase);
		cv.put (c.CAMPO_MODIFICACION, c.modificacion);
		return db.update (Contacto.TABLE_NAME, cv, Contacto.KEY_ID + "=?", new String[]{String.valueOf (c.contacto)});
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
		Log.w (TAG, "Metodo Consultar");
		String tabla = Contacto.TABLE_NAME;
		String[] valores = new String[]{
				                               Contacto.CAMPO_CONTACTO + " as _id",
				                               Contacto.CAMPO_CONTACTO,
				                               Contacto.CAMPO_NOMBRE,
				                               Contacto.CAMPO_DIRECCION,
				                               Contacto.CAMPO_TELEFONO,
				                               Contacto.CAMPO_MOVIL
		};
		Cursor c = db.query (tabla, valores, null, null, null, null, null);
		if (c != null) {
			Log.w (TAG, "Datos obtenidos moviendose al primer registro.");
			if (c.moveToFirst () == false) {
				Log.w (TAG, "El cursor esta vacio.");
				return (null);
			} else {
				for (c.moveToFirst (); !c.isAfterLast (); c.moveToNext ()) {
					String contacto = c.getString (c.getColumnIndex (Contacto.CAMPO_CONTACTO));
					String nombre = c.getString (c.getColumnIndex (Contacto.CAMPO_NOMBRE));
					Log.w (TAG, "{" + contacto + "," + nombre + "}");
				}
			}
		} else {
			Log.w (TAG, "No se obtuvieron datos en el Cursor=NULL");
		}
		return (c);
	}

	/**
	 * Este metodo permite sincronizar los contactos registrados en la base de datos interna del dispositivo (SQLite)
	 * con la base de datos en linea (MySQL) al evaluar la actualización o creacion de un registro en la base de datos
	 * tras la consideración de siertos aspectos de la información registrada si el dato(registro) este no existe, o su
	 * estado de sincronizacion difiere del registrado en la base de datos en linea se procede a realizar una actualizacion,
	 * si el registro no existe se crea, ambos procedimientos se realizan mediante sus respectivos metodos.
	 *
	 * @param c
	 * @return
	 */
	public long Sincronizar (Contacto c) {
		String sql = Contacto.CAMPO_CONTACTO + "='" + c.contacto + "'";
		Log.w (TAG, "CONTACTOS-SINCRONIZAR[" + sql + "]");
		Cursor cursor = db.query (true, Contacto.TABLE_NAME, this.valores, sql, null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst ();
		}
		if (cursor.getCount () == 0) {
			Log.w (TAG, "CONTACTOS-SINCRONIZAR-CREAR [" + Integer.toString (c.contacto).toString () + ":" + c.nombre + "]");
			return (this.Crear (c));
		} else {
			/**
			 * El registro existe, ahora corresponde establecer si el dato esta sincronizado si no lo esta
			 * se debe proceder a realizar la actualización caso contrario no se realiza ninguna modificación
			 * el campo que determina el estado de sincronización esta basado en un TIMESTAMP que tiene por nombre
			 * "sincronizacion" si ese campo difiere entre el dato local y el dato remoto se debera
			 * actualizar el dato local mediante una instrucción de update tradicional.
			 * Para evitar excepciones se debe controlar si los datos retornados desde los indices son nulos.
			 ***/
			int indice = cursor.getColumnIndex (Contacto.CAMPO_MODIFICACION);
			if (!cursor.isNull (indice)) {
				int remoto = c.modificacion;
				int local = cursor.getInt (indice);
				if (remoto == local) {
					Log.w (TAG, "CONTACTOS-SINCRONIZAR-ACTUALIZAR-IGUALES [" + Integer.toString (c.contacto).toString () + ":" + c.nombre + "]");
				} else {
					Log.w (TAG, "CONTACTOS-SINCRONIZAR-ACTUALIZAR-DIFERENTES [" + Integer.toString (c.contacto).toString () + ":" + c.nombre + "]");
					return (this.Actualizar (c));
				}
			} else {
				Log.w (TAG, "El indice del cursor que se utiliza para verificar la sincronización es nulo o esta vacio.");
			}
			return (0);
		}
	}

	public Cursor Filtro (String filtro) throws SQLException {
		/**
		 *  Consulta en la base de datos remota y sincroniza en segundo plano del contenido del directorio
		 *  la unica consideracion al respecto el minimo nombre a buscar en la base de datos remota que debe de ser mayor o
		 *  a tres caracteres.
		 **/
		if (filtro.length () >= 3) {
			int demora = 10000;
			int repeticion = 10000;
			int cantidad = 15;
			AsyncTask_Directorio td = new AsyncTask_Directorio (this.contexto, filtro, demora, repeticion, cantidad);
			td.execute ();
		}
		/*****************************************************************/
		Log.w (TAG, "FILTRANDO CONTACTOS");
		Cursor c = null;
		if (filtro == null || filtro.length () == 0) {
			c = db.query (Contacto.TABLE_NAME, valores, null, null, null, null, null);
		} else {
			String sql = Contacto.CAMPO_NOMBRE + " MATCH '" + filtro.toLowerCase () + "'";
			c = db.query (true, Contacto.TABLE_NAME, this.valores, sql, null, null, null, null, null);
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
		//new Contacto(contacto,nombre,direccion,telefono,movil,fax,pbx,categoria,sector,creador,fecha,hora,pagina,claves,web,correo,clase);
		//Crear (new Contacto(1,"Jose Alexis Correa Valencia","Calle 13 Nro 5-50","317-3997946","","","","","",0,"2015-02-03","18:00:00","","","","",""));
		//Crear (new Contacto(2,"Katerine Arenas","Calle 13 Nro 5-50","317-3997946","","","","","",0,"2015-02-03","18:00:00","","","","",""));
		//Crear (new Contacto(3,"Javier Gil","Calle 13 Nro 5-50","317-3997946","","","","","",0,"2015-02-03","18:00:00","","","","",""));
	}

	/**
	 * Borrar todo
	 */
	public boolean Demo_Erradicar () {
		Log.w (TAG, "Metodo Erradicar");
		int doneDelete = 0;
		doneDelete = db.delete (Contacto.TABLE_NAME, null, null);
		return doneDelete > 0;
	}

}
