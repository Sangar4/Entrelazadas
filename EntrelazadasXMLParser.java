package es.aplicaciones.alvaro.entrelazadas;

import android.content.Context;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Álvaro on 31/08/2015.
 */
public class EntrelazadasXMLParser {
    // We don't use namespaces
    private static final String ns = null;
    String Id = null;;
    String Referencia = null;;
    String Nombre = null;;
    String Categoria = null;;
    String SubFamilia = null;;
    String Familia = null;;
    String Descripcion = null;;
    String Precio = null;;
    String Image1 = null;
    String Image2 = null;
    String Image3 = null;
    String Image4 = null;
    String Image5 = null;

    public List parse(InputStream in, Context context) throws /*XmlPullParserException,*/ IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser,context);

        }catch (XmlPullParserException e){

            Toast.makeText(context, "Error en el parser: " + e.toString(),
                    Toast.LENGTH_LONG).show();
            return null;
        }
        finally {
            in.close();
        }

    }

    private List readFeed(XmlPullParser parser, Context context) throws /*XmlPullParserException,*/ IOException {
        List entries = new ArrayList();
        try {
            parser.require(XmlPullParser.START_TAG, ns, "database");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                // Starts by looking for the table tag
                if (name.equals("table")) {
                    entries.add(readEntry(parser));
                } else {
                    skip(parser);
                }
            }
            return entries;
        }
        catch (XmlPullParserException e){

            Toast.makeText(context, "Error en el read feed: " + e.toString(),
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private Producto readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "table");

        Producto Objeto =null;
        String summary = null;
        String link = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("column")) {
                Objeto = readColumn(parser);
            } else {
                skip(parser);

            }
        }
        return Objeto;
    }

    private Producto readColumn(XmlPullParser parser) throws IOException, XmlPullParserException {


        parser.require(XmlPullParser.START_TAG, ns, "column");
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "name");
        Producto Objeto;
            if (relType.equals("Id")){
                Id = readId(parser);
            } else if(relType.equals("Precio")){
                Precio = readPrecio(parser);
            }else if(relType.equals("Referencia")){
                Referencia = readReferencia(parser);
            }else if(relType.equals("Categoria")){
                Categoria = readCategoria(parser);
            }else if(relType.equals("Nombre")){
                Nombre = readNombre(parser);
            }else if(relType.equals("Subfamilia")){
                SubFamilia = readSubfamilia(parser);
            }else if(relType.equals("Familia")){
                Familia = readFamilia(parser);
            }else if(relType.equals("Descripcion")){
                Descripcion = readDescripcion(parser);
            }else{
                Image1 = readImage(parser);
            }

        Objeto =new Producto(Id,Referencia,Nombre,Categoria,SubFamilia,Familia,Descripcion,Precio,Image1,Image2,Image3,Image4,Image5);

        parser.require(XmlPullParser.END_TAG, ns, "column");
        return Objeto;
    }

    // Processes Id tags in the feed.
    private String readId (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readPrecio (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readReferencia (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readCategoria (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readNombre (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readSubfamilia (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readFamilia (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readDescripcion (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }

    private String readImage (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "column");
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "column");
        return value;
    }
    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}