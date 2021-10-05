package com.example.f8_weatherapi;

import android.provider.MediaStore;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlDebunker {

    private final String TAG = "XmlDebunker";
    private List<Artist> artists = new ArrayList<Artist>();
    private Artist artist;
    private String text;

    public List<Artist> getArtists(){
        return artists;
    }


    // Change input to String

    public List<Artist> parse(String xmlContent) {
        artist = new Artist();
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlContent));

            int eventType = parser.getEventType();
            String tag = "", text = "";

            while(eventType != XmlPullParser.END_DOCUMENT){
                    tag = parser.getName();
                    Log.d(TAG, "tag = "+tag);

                    switch(eventType){
                        case XmlPullParser.START_TAG:
                            if (tag.equalsIgnoreCase("artist")){
                                artist = new Artist();
                            }
                            break;

                        case XmlPullParser.TEXT:
                            text = parser.getText();
                            Log.d(TAG, "parse: text: "+text);
                            break;

                        case XmlPullParser.END_TAG:
                            if("artist".equalsIgnoreCase(tag)){
                                artists.add(artist);
                            }else if ("name".equalsIgnoreCase(tag)){
                                artist.setName(text);
                            }
                            break;

                        default:
                            break;
                    }
                    eventType = parser.next();

            }


        }catch (XmlPullParserException e) {
            Log.d(TAG, "XmlPullParserException Error: "+e);
            e.printStackTrace();
        }
        catch (IOException e){
            Log.d(TAG, "IOException Error: "+e);
            e.printStackTrace();
        }
        for (Artist a: artists) {
            Log.d(TAG, "Artists in list: "+a.getName());
        }
        return artists;
    }






    private String readFeed(XmlPullParser parser) {

        return null;
    }
}
