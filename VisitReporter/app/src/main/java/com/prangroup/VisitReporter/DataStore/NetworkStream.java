package com.prangroup.VisitReporter.DataStore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Ritu on 8/11/2016.
 */
public class NetworkStream {
    public NetworkStream() {
    }

    public String getStream(String url, int method, List<NameValuePair> params) {
        String response = "";
        try {
            DefaultHttpClient e = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            if (method == 2) {
                HttpPost httpGet = new HttpPost(url);
                if (params != null) {
                    httpGet.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = e.execute(httpGet);
            } else if (method == 1) {
                if (params != null) {
                    String httpGet1 = URLEncodedUtils.format(params, "utf-8");
                    url = url + "?" + httpGet1;
                }

                HttpGet httpGet2 = new HttpGet(url);
                httpResponse = e.execute(httpGet2);
            } else if (method == 3) {
                HttpPut httpPut = new HttpPut(url);
                if (params != null) {
                    httpPut.setEntity(new UrlEncodedFormEntity(params));
                }
                httpResponse = e.execute(httpPut);
            }
            if (httpResponse != null) {
                httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
            }
        } catch (UnsupportedEncodingException var8) {
            // var8.printStackTrace();
        } catch (ClientProtocolException var9) {
            // var9.printStackTrace();
        } catch (IOException var10) {
            //  var10.printStackTrace();
        }
        return response;
    }

    public String uploadImage(String urlPath, String imagePath){
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        int resCode = 0;
        String resMessage = "";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(imagePath));
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"filUpload\";filename=\"" + imagePath + "\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            resCode = conn.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK)
            {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = is.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();
                resMessage = new String(result);
            }
            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
        }

        return resMessage;
    }


}
