package com.example.lakshay.mydictionary;

import android.content.Context;
import android.os.AsyncTask;
import android.service.notification.ConditionProviderService;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.app.PendingIntent.getActivity;

public class fetchData extends AsyncTask<Void,Void,Void> {

    private String data="";
    private String dataparsed="";
    private String singleparsed="";
private String AccessWord;

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            String word=MainActivity.myWord.getText().toString();
            URL url=new URL("https://googledictionaryapi.eu-gb.mybluemix.net/?define="+word+"&lang=en");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line="";
            while(line!=null)
            {
                line=bufferedReader.readLine();
                data=data+line;
            }


            JSONArray JA=new JSONArray(data);
            for(int i=0;i<JA.length();i++) {
                JSONObject JO = (JSONObject) JA.get(i);



                    JSONObject jo = JO.getJSONObject("meaning");



                JSONArray ja=new JSONArray();
ja=(JSONArray) jo.toJSONArray(ja);



                if(jo.has("noun")) {
                        ja = jo.getJSONArray("noun");
                        AccessWord="Meaning: ";
                   }
                   else if(jo.has("verb"))
                   {
                       ja=jo.getJSONArray("verb");
                       AccessWord="Meaning/Used As: ";
                   }

                   else if(jo.has("adjective"))
                   {
                       ja=jo.getJSONArray("adjective");
                       AccessWord="Meaning/Used As: ";

                   }

                   else if(jo.has("adverb"))
                   {
                       ja=jo.getJSONArray("adverb");
                       AccessWord="Meaning/Used As: ";
                   }

                   else if(jo.has("exclamation"))
                   {
                       ja=jo.getJSONArray("exclamation");
                       AccessWord="Meaning/Used As: ";
                   }
else if(jo.has("pronoun"))
                   {
                       ja=jo.getJSONArray("pronoun");
                       AccessWord="Meaning/Used As: ";
                   }

                   else if(jo.has("conjunction"))
                   {
                       ja=jo.getJSONArray("conjunction");
                       AccessWord="Meaning/Used As: ";
                   }
                   else if(jo.has("preposition"))
                   {
                       ja=jo.getJSONArray("preposition");
                       AccessWord="Meaning/Used As: ";
                   }







                        for (int j = 0; j < ja.length(); j++) {

                            JSONObject Jo = (JSONObject) ja.get(j);

                            singleparsed = AccessWord + Jo.get("definition") + "\n";

                            dataparsed = dataparsed + singleparsed + "\n";

                        }





            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("ERROR",e.getMessage(),e);

        }


        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


       MainActivity.data.setText(dataparsed);
    }


}
