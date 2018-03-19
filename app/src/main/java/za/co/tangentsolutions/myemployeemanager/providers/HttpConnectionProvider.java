package za.co.tangentsolutions.myemployeemanager.providers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.activities.BaseAsyncActivity;
import za.co.tangentsolutions.myemployeemanager.presenters.BaseAsyncPresenter;

public class HttpConnectionProvider {

    private BaseAsyncPresenter presenter;
    private Bundle values;
    private String stringUrl;
    private String requestMethod;
    private boolean doInput;
    private boolean doOutput;
    private int connectionTimeout;
    private HttpURLConnection httpConnect;
    private final String BASE_HTTP_LOG = "http_log";

    public HttpConnectionProvider(Bundle... values) {
        if (values != null && values.length > 0)
            this.values = values[0];
    }

    public String makeOathCall(String stringUrl, String requestMethod, boolean doInput, boolean doOutput, int connectionTimeout, BaseAsyncPresenter presenter) throws UnsupportedEncodingException {

        this.presenter = presenter;
        String encoding = "UTF-8";
        String postData = getPostData(encoding);

        if (requestMethod.equals("GET"))
            stringUrl += (postData.isEmpty())? "" : "?"+postData;



        return "";
    }

    public String makeCallForData(String stringUrl, String requestMethod, boolean doInput, boolean doOutput, int connectionTimeout, BaseAsyncPresenter presenter) throws MalformedURLException, IOException {

        this.presenter = presenter;
        String encoding = "UTF-8";
        String postData = getPostData(encoding);

        if (requestMethod.equals("GET"))
            stringUrl += (postData.isEmpty())? "" : "?"+postData;

        setConnectionParams(stringUrl, requestMethod, doInput, doOutput, connectionTimeout);
        Log.i(BASE_HTTP_LOG, "request => " + stringUrl);
        httpConnect = getHttpConnection();

        // OutputStream -------------------------------------------
        OutputStream sout = httpConnect.getOutputStream();
        BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(sout, encoding));

        if (!requestMethod.equals("GET"))
            bWriter.write(postData);

        bWriter.flush();
        bWriter.close();
        sout.close();

        // InputStream -------------------------------------------
        InputStream sin = httpConnect.getInputStream();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(sin, "iso-8859-1"));

        String result = "";
        String line;

        while ((line = bReader.readLine()) != null) {
            result += line;
        }

        // close connection -------------------------------------
        bReader.close();
        sin.close();
        httpConnect.disconnect();

        return result;
    }

    private HttpURLConnection getHttpConnection() throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
        httpConnect.setRequestMethod(requestMethod);
        httpConnect.setDoOutput(doOutput);
        httpConnect.setDoInput(doInput);
        httpConnect.setConnectTimeout(connectionTimeout);

        //Header
        //String authorization = values.getString("username")+":"+values.getString("password");
        //String encodedAuth = "Basic  "+ android.util.Base64.encode(authorization.getBytes(), 0);
        httpConnect.setRequestProperty("Authorization", "Token token" + presenter.getToken());
        httpConnect.setRequestProperty("Accept","*/*");

        return httpConnect;
    }

    private void setConnectionParams(String stringUrl, String requestMethod, boolean doInput, boolean doOutput, int connectionTimeout) {
        this.stringUrl = stringUrl;
        this.requestMethod = requestMethod;
        this.doInput = doInput;
        this.doOutput = doOutput;
        this.connectionTimeout = connectionTimeout;
    }

    private String getPostData(String encoding) throws UnsupportedEncodingException {
        String postData = "";

        String key;

        if (values == null)
            return (postData.isEmpty())? "" : postData;

        Set vals = values.keySet();

        for (Object val : vals) {
            key = val.toString();
            postData += ((!postData.isEmpty()) ? "&" : "");
            postData += URLEncoder.encode(key, encoding) + "="
                    + URLEncoder.encode(values.getString(key), encoding);
        }

        return postData;
    }

}