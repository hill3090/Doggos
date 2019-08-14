package reader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

  private String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    
	  /*
	   *  Creates an InputStream object. Uses a URL object + .openStream() 
	   *  to return an InputStream object.
	   */
	  InputStream is = new URL(url).openStream();
    try {
      /*
       * Use of BufferedReader object to efficiently read the characters
       * within the InputStreamReader object. UTF-8 is the charset that is
       * usually accepted.
       */
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      /*
       * Send buffered reader to readAll method 
       */
      String jsonText = readAll(rd);
      /*
       * 
       */
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
}


