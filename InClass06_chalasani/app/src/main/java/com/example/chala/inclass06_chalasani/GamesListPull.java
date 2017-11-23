package com.example.chala.inclass06_chalasani;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by chala on 2/20/2017.
 */

public class GamesListPull {
    static public class gamesPullParser{
        static ArrayList<Games> parseGames(InputStream in) throws XmlPullParserException,IOException {
            Games game=null;
            XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            ArrayList<Games> gamesList=new ArrayList<>();
            int event=parser.getEventType();
            while(event!=XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("Game")) {
                            game = new Games();
                        } else if (parser.getName().equals("id")) {
                            game.setId(parser.nextText().trim());
                        } else if (parser.getName().equals("GameTitle")) {
                            game.setTitle(parser.nextText().trim());
                        } else if (parser.getName().equals("ReleaseDate")) {
                            game.setDate(parser.nextText().trim());
                        } else if (parser.getName().equals("Platform")) {
                            game.setPlatform(parser.nextText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Game")) {
                            gamesList.add(game);
                            game=null;
                        }
                        break;
                    default:
                        break;
                }
                event=parser.next();
            }
            return gamesList;
        }
    }
}
