package co.com.buga.buga.librerias;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Alexis on 2015-02-15.
 */
public final class Cadenas{

	public static String URLDecode(String encoded){
		String decoded = null;
		try {
			decoded = URLDecoder.decode (encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace ();
		}
		return(decoded);
	}

	public static String URLEncode (String cadena){
		try {
			cadena = URLEncoder.encode (cadena, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace ();
		}
		return(cadena);
	}



}
