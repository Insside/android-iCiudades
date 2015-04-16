package co.com.buga.buga.datos.estructuras;

public class Contacto {
	/**
	 * Campos *
	 */
	public static final String TABLE_NAME = "directorio_contactos";
	public static final String CAMPO_CONTACTO = "contacto";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_DIRECCION = "direccion";
	public static final String CAMPO_TELEFONO = "telefono";
	public static final String CAMPO_MOVIL = "movil";
	public static final String CAMPO_FAX = "fax";
	public static final String CAMPO_PBX = "pbx";
	public static final String CAMPO_CATEGORIA = "categoria";
	public static final String CAMPO_SECTOR = "sector";
	public static final String CAMPO_CREADOR = "creador";
	public static final String CAMPO_FECHA = "fecha";
	public static final String CAMPO_HORA = "hora";
	public static final String CAMPO_PAGINA = "pagina";
	public static final String CAMPO_CLAVES = "claves";
	public static final String CAMPO_WEB = "web";
	public static final String CAMPO_CORREO = "correo";
	public static final String CAMPO_CLASE = "clase";
	public static final String CAMPO_MODIFICACION = "modificacion";
	public static final String KEY_ID =CAMPO_CONTACTO;
	/**
	 * Valores *
	 */
	public int contacto;
	public String nombre;
	public String direccion;
	public String telefono;
	public String movil;
	public String fax;
	public String pbx;
	public String categoria;
	public String sector;
	public int creador;
	public String fecha;
	public String hora;
	public String pagina;
	public String claves;
	public String web;
	public String correo;
	public String clase;
	public int modificacion;

	public static final String[] TABLE_VALORES = {
			                                             CAMPO_CONTACTO,
			                                             CAMPO_NOMBRE,
			                                             CAMPO_DIRECCION,
			                                             CAMPO_TELEFONO,
			                                             CAMPO_MOVIL,
			                                             CAMPO_FAX,
			                                             CAMPO_PBX,
			                                             CAMPO_CATEGORIA,
			                                             CAMPO_SECTOR,
			                                             CAMPO_CREADOR,
			                                             CAMPO_FECHA,
			                                             CAMPO_HORA,
			                                             CAMPO_PAGINA,
			                                             CAMPO_CLAVES,
			                                             CAMPO_WEB,
			                                             CAMPO_CORREO,
			                                             CAMPO_CLASE,
			                                             CAMPO_MODIFICACION
	};
	public static final String TABLE_CREATE = " CREATE VIRTUAL TABLE \"directorio_contactos\" USING fts4(\"contacto\" INTEGER PRIMARY KEY, \"nombre\" TEXT, \"direccion\" TEXT, \"telefono\" TEXT, \"movil\" TEXT, \"fax\" TEXT, \"pbx\" TEXT, \"categoria\" TEXT, \"sector\" TEXT, \"creador\" INTEGER, \"fecha\" TEXT, \"hora\" TEXT, \"pagina\" TEXT, \"claves\" TEXT, \"web\" TEXT, \"correo\" TEXT, \"clase\" TEXT, \"modificacion\" INTEGER);";

	public Contacto (int contacto,
	                 String nombre,
	                 String direccion,
	                 String telefono,
	                 String movil,
	                 String fax,
	                 String pbx,
	                 String categoria,
	                 String sector,
	                 int creador,
	                 String fecha,
	                 String hora,
	                 String pagina,
	                 String claves,
	                 String web,
	                 String correo,
	                 String clase,
	                 int modificacion) {
		this.contacto = contacto;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.movil = movil;
		this.fax = fax;
		this.pbx = pbx;
		this.categoria = categoria;
		this.sector = sector;
		this.creador = creador;
		this.fecha = fecha;
		this.hora = hora;
		this.pagina = pagina;
		this.claves = claves;
		this.web = web;
		this.correo = correo;
		this.clase = clase;
		this.modificacion = modificacion;
	}

}
