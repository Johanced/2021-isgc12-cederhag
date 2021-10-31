package com.example.Labb3;

import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    private static final String TAG = "XmlDebunker";
    private List<Artist> artists = new ArrayList<>();
    private Artist artist;
    private Toast toast;

    public XmlParser(Toast toast){
        this.toast = toast;
    }
    

    public List<Artist> parse(String xmlContent) {
        artist = new Artist();
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlContent));

            int eventType = parser.getEventType();
            String tag, text = "";

            while(eventType != XmlPullParser.END_DOCUMENT){
                    tag = parser.getName();
                    Log.d(TAG, "tag = "+tag);

                    switch(eventType){
                        case XmlPullParser.START_TAG:
                            if (tag.equalsIgnoreCase("artist")){
                                artist = new Artist();
                            }
                            else  if(tag.equalsIgnoreCase("lfm")){
                                Log.d(TAG, "getAttribute: "+parser.getAttributeValue(0));
                                if(parser.getAttributeValue(0).equalsIgnoreCase("failed")){
                                    Log.d(TAG, "parse: FAILED");

                                }
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
        Log.d(TAG, "Number of artists: "+artists.size());
        return artists;
    }

}
