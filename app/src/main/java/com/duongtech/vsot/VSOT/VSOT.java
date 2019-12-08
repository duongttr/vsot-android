package com.duongtech.vsot.VSOT;

import android.media.MediaPlayer;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class VSOT implements VSOTCode {

    /**
     * Created by Duong Tran (1:23 PM - 12 Aug 2019)
     * --------
     * VSOT is a alternative solution for TTS engine using SoundOfText API
     * SOT API supports 55 languages - Access https://soundoftext.com/docs for more details
     * --------
     * Visit my blog: https://www.duongtech.com/
     */

    private static final String API_URL = "https://api.soundoftext.com/sounds";
    private static MediaPlayer player;

    public static void speak(String text, String lang) {
        try {
            JSONObject object = new JSONObject();
            object.put("engine", "Google");
            JSONObject data = new JSONObject();
            data.put("text", text);
            data.put("voice", lang);
            object.put("data", data);

            new PostTask().execute(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        if (player != null){
            if (player.isPlaying()){
                player.pause();
                player.release();
                player = null;
            }
        }
    }

    public static boolean isSpeaking(){
        if (player != null){
            return player.isPlaying();
        }
        return false;
    }

    static class PostTask extends AsyncTask<JSONObject, Void, String>{

        @Override
        protected String doInBackground(JSONObject... jos) {
            if (jos != null){
                if (jos.length > 0){
                    JSONObject jo = jos[0];
                    try {

                        URL url = new URL(API_URL);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("CONTENT-TYPE", "application/json; charset=utf-8");
                        conn.setDoOutput(true);


                        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                        BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(dos, StandardCharsets.UTF_8));
                        bwr.write(jo.toString());
                        bwr.close();
                        dos.flush();
                        dos.close();


                        int responseCode = conn.getResponseCode();
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        StringBuilder builder = new StringBuilder();

                        if (responseCode == HttpURLConnection.HTTP_OK){
                            while ((line = br.readLine()) != null){
                                builder.append(line);
                            }
                        }

                        return builder.toString();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null){
                try {
                    JSONObject jo = new JSONObject(s);
                    if (jo.getBoolean("success")){
                        String id = jo.getString("id");
                        new GetTask().execute(API_URL + "/" + id);
                    }else{
                        throw new VSOTException("Failed to return data");
                    }
                } catch (JSONException | VSOTException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    throw new VSOTException("Data returned is NULL");
                } catch (VSOTException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class GetTask extends AsyncTask<String, Void, String>{
        String url;
        @Override
        protected String doInBackground(String... strings) {
            if (strings != null){
                if(strings.length > 0){
                    try {
                        this.url = strings[0];
                        URL url = new URL(this.url);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");

                        int responseCode = conn.getResponseCode();
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        StringBuilder builder = new StringBuilder();

                        if (responseCode == HttpURLConnection.HTTP_OK){
                            while ((line = br.readLine()) != null){
                                builder.append(line);
                            }
                        }

                        return builder.toString();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null){
                try {
                    JSONObject jo = new JSONObject(s);
                    if (jo.getString("status").equals("Done")){
                        String MP3_Url = jo.getString("location");
                        if (player != null){
                            if (player.isPlaying()){
                                player.pause();
                                player.release();
                            }
                        }
                        player = new MediaPlayer();
                        player.setDataSource(MP3_Url);
                        player.prepare();
                        player.start();
                    }else if (jo.getString("status").equals("Pending")){
                        Thread.sleep(100);
                        new GetTask().execute(this.url);
                    }else{
                        throw new VSOTException("Failed to get audio url");
                    }
                } catch (JSONException | VSOTException | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    throw new VSOTException("Data returned is null");
                } catch (VSOTException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
