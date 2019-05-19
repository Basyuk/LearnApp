package com.basyuk.development.l79_xmlpullparcer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tmp = "";

        try{
            XmlPullParser xmlPullParser = prepareXmlPullParser();

            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xmlPullParser.getEventType()){
                    //начало документа
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "START_DOCUMENT");
                        break;
                        //начало тега
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "START_TAG: name = " + xmlPullParser.getName() + ", depth = " + xmlPullParser.getDepth() + ", attrCount = " + xmlPullParser.getAttributeCount());
                        tmp = "";
                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++){
                            tmp = tmp + xmlPullParser.getAttributeName(i) + " = " + xmlPullParser.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(tmp))
                            Log.d(TAG, "Attributes: " + tmp);
                        break;

                        //конец тэга
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "END_TAG: name = " + xmlPullParser.getName());
                        break;

                        //содержимое тэга
                    case XmlPullParser.TEXT:
                        Log.d(TAG, "text = " + xmlPullParser.getText());
                        break;

                        default:
                            break;
                }
                //следующий элемент
                xmlPullParser.next();
            }
            Log.d(TAG, "END_DOCUMENT");
        } catch (XmlPullParserException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    XmlPullParser prepareXmlPullParser(){
        return getResources().getXml(R.xml.data);
    }
    */

    XmlPullParser prepareXmlPullParser() throws XmlPullParserException{
        // получаем фабрику
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //включаем поддержку namespace (по умолчанию выключена)
        factory.setNamespaceAware(true);
        //создаем парсер
        XmlPullParser xmlPullParser = factory.newPullParser();
        //даем парсеру на вход Reader
        xmlPullParser.setInput(new StringReader("<data><phone><company>Samsung</company></phone></data>"));
        return xmlPullParser;
    }
}
