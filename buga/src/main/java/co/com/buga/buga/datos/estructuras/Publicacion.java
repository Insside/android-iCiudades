package co.com.buga.buga.datos.estructuras;

import android.content.Context;

public class Publicacion {
    // Labels table name
    public static final String TABLE_NAME = "publicaciones";
    // Labels Table Columns names
    public static final String CAMPO_PUBLICACION= "publicacion";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_RESUMEN = "resumen";
    public static final String CAMPO_CONTENIDO = "contenido";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_HORA= "hora";
    public static final String CAMPO_CREADOR = "creador";
    public static final String[] TABLE_VALORES={
            CAMPO_PUBLICACION,
            CAMPO_TITULO,
            CAMPO_RESUMEN,
            CAMPO_CONTENIDO,
            CAMPO_FECHA,
            CAMPO_HORA,
            CAMPO_CREADOR,
    };
    public static final String TABLE_CREATE= "CREATE TABLE \"publicaciones\" (\"publicacion\" INTEGER PRIMARY KEY, \"titulo\" TEXT, \"resumen\" TEXT, \"contenido\" TEXT, \"fecha\" DATE, \"hora\" TIME, \"creador\" INTEGER);";

    public int publicacion;
    public String titulo;
    public String resumen;
    public String contenido;
    public String fecha;
    public String hora;
    public int creador;

    public Publicacion(int publicacion,
                       String titulo,
                       String resumen,
                       String contenido,
                       String fecha,
                       String hora,
                       int creador) {
        this.publicacion=publicacion;
        this.titulo=titulo;
        this.resumen=resumen;
        this.contenido=contenido;
        this.fecha=fecha;
        this.hora=hora;
        this.creador=creador;
    }




}
