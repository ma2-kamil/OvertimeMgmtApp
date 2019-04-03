package com.example.overtimemgmtapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BackgroundWorker extends AsyncTask<String, String, String> {
    private Context context;

    BackgroundWorker (Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        // Change to ip to your own local server and where your php files are located. mine are in a folder called "ot"
        String login_url = "http://192.168.0.10/ot/login.php";
        String register_url = "http://192.168.0.10/ot/register.php";
        String addshift_url = "http://192.168.0.10/ot/addshift.php";
        String bookshift_url = "http://192.168.0.10/ot/book.php";
        String deleteshift_url = "http://192.168.0.10/ot/delete.php";
        String addtime_url = "http://192.168.0.10/ot/addtime.php";
        String deletetime_url = "http://192.168.0.10/ot/deletetime.php";

        if(type.equals("login")) {
            try {
                String username = params[1]; // the stringnames are the same on the db
                String password = params[2];
                URL url = new URL(login_url); // url is the url for the php file. php does all the queries.
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST"); // posting data to the server
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register")){
            try {
                String fullname = params[1];
                String username = params[2];
                String password = params[3];
                String email = params[4];
                String shiftmanager = params[5];
                String uniquecode = params[6];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));


                String post_data = URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fullname,"UTF-8")+"&"
                + URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                + URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                + URLEncoder.encode("shiftmanager","UTF-8")+"="+URLEncoder.encode(shiftmanager,"UTF-8")+"&"
                        + URLEncoder.encode("uniquecode","UTF-8")+"="+URLEncoder.encode(uniquecode,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("addshift")){
            try {
                String shifttitle = params[1];
                String description = params[2];
                String hourlyrate = params[3];
                String date = params[4];
                String ftime = params[5];
                String ttime = params[6];
                String uniquecode = params[7];
                String employee = params[8];


                URL url = new URL(addshift_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));


                String post_data = URLEncoder.encode("shifttitle","UTF-8")+"="+URLEncoder.encode(shifttitle,"UTF-8")+"&"
                        + URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&"
                        + URLEncoder.encode("hourlyrate","UTF-8")+"="+URLEncoder.encode(hourlyrate,"UTF-8")+"&"
                        + URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        + URLEncoder.encode("ftime","UTF-8")+"="+URLEncoder.encode(ftime,"UTF-8")+"&"
                        + URLEncoder.encode("ttime","UTF-8")+"="+URLEncoder.encode(ttime,"UTF-8")+"&"
                        + URLEncoder.encode("uniquecode","UTF-8")+"="+URLEncoder.encode(uniquecode,"UTF-8")+"&"
                            + URLEncoder.encode("employee","UTF-8")+"="+URLEncoder.encode(employee,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("BookShift")){
            try {
                String employee = params[1];
                String id = params[2];

                URL url = new URL(bookshift_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));


                String post_data = URLEncoder.encode("employee","UTF-8")+"="+URLEncoder.encode(employee,"UTF-8")+"&"
                        + URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("DeleteShift")){
            try {
                String id = params[1];

                URL url = new URL(deleteshift_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));


                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("addtime")){
            try {
                String date = params[1];
                String fromtime = params[2];
                String totime = params[3];
                String uniquecode = params[4];
                String employee = params[5];


                URL url = new URL(addtime_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));


                String post_data = URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        + URLEncoder.encode("fromtime","UTF-8")+"="+URLEncoder.encode(fromtime,"UTF-8")+"&"
                        + URLEncoder.encode("totime","UTF-8")+"="+URLEncoder.encode(totime,"UTF-8")+"&"
                        + URLEncoder.encode("uniquecode","UTF-8")+"="+URLEncoder.encode(uniquecode,"UTF-8")+"&"
                        + URLEncoder.encode("employee","UTF-8")+"="+URLEncoder.encode(employee,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("DeleteTime")){
            try {
                String id = params[1];

                URL url = new URL(deletetime_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder result= new StringBuilder(); //STRINGBUFFER CHANGED
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;

    }


    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Connecting", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
       Toast.makeText(context,result, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onProgressUpdate(String... values) { super.onProgressUpdate(values);
    }


}

