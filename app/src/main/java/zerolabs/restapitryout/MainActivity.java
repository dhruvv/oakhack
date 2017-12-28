package zerolabs.restapitryout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask ;
//import android.net.*;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView json_data = (TextView)findViewById(R.id.json_data);
        final Button button = (Button)findViewById(R.id.startNet);
        final TextView json_key = (TextView)findViewById(R.id.jsonKey);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                AsyncTask.execute(new Runnable(){
                    @Override
                    public void run() {
                        URL endpoint = null;
                        try {
                            endpoint = new URL("http://192.168.0.5:5000/");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            //json_data.setText((CharSequence) e);
                        }
                        HttpURLConnection myConnection = null;
                        try {
                            myConnection = (HttpURLConnection) endpoint.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                            //json_data.setText((CharSequence) e);
                        }

                        try {
                            if (myConnection.getResponseCode() == 200) {
                                InputStream responseBody = myConnection.getInputStream();
                                //StringWriter writer = new StringWriter();
                                //IOUtils.copy(responseBody, writer, "UTF-8");
                                //final String theString = writer.toString();
                                //unOnUiThread(new Runnable() {
                                   // @Override
                                   //public void run(){
                                     //   json_data.setText(theString);

                                  //  }
                                //});

                                try {
                                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()){
                                        final String key = jsonReader.nextName();
                                        if (key.equals("Tanush")){
                                           final String jsonStr;
                                            jsonStr = jsonReader.nextString();
                                            System.out.println(jsonStr);
                                            //System.out.println(key);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run(){
                                                    json_data.setText(jsonStr);
                                                    json_key.setText(key);

                                                }
                                            });


                                        }
                                    }


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

            });
        }




            });


        }


        }


