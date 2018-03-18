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

    public String makeOathCall(String stringUrl, String requestMethod, boolean doInput, boolean doOutput, int connectionTimeout, int jsonIndex, BaseAsyncPresenter presenter) throws UnsupportedEncodingException {

        this.presenter = presenter;
        String encoding = "UTF-8";
        String postData = getPostData(encoding);

        if (requestMethod.equals("GET"))
            stringUrl += (postData.isEmpty())? "" : "?"+postData;

        List<String> mockJsons = new ArrayList<>();

        String mockTokeJson = "{\"token\":\"2a3d1af2f3f6d1cddaa3012c1c465fcbdffa3678\"}";

        String userJSon = "[ { \"id\": 12, \"username\": \"pravin.gordhan\", \"email\": \"pravin@axedmps.com\", \"first_name\": \"Pravin\", \"last_name\": \"Gordhan\", \"is_active\": true, \"is_staff\": true } ]";

        String employeesJson = "[ { \"user\": { \"id\": 8, \"username\": \"captain\", \"email\": \"captain@gmail.com\", \"first_name\": \"Captain\", \"last_name\": \"America\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 1, \"name\": \"Front-end Developer\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0824478876\", \"email\": \"captain@gmail.com\", \"github_user\": \"Captain\", \"birth_date\": \"1981-07-30\", \"gender\": \"M\", \"race\": \"B\", \"years_worked\": 3, \"age\": 36, \"days_to_birthday\": 145 }, { \"user\": { \"id\": 11, \"username\": \"employee4\", \"email\": \"gary.player@gmail.com\", \"first_name\": \"Gary\", \"last_name\": \"Player\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 2, \"name\": \"Back-end Developer\", \"level\": \"Junior\", \"sort\": 0 }, \"phone_number\": \"0837788876\", \"email\": \"gary.player@gmail.com\", \"github_user\": \"Gary\", \"birth_date\": \"1990-08-09\", \"gender\": \"M\", \"race\": \"W\", \"years_worked\": 1, \"age\": 27, \"days_to_birthday\": 155 }, { \"user\": { \"id\": 5, \"username\": \"admin\", \"email\": \"ian@tangentsolutions.co.za\", \"first_name\": \"Ian\", \"last_name\": \"Roberts\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 1, \"name\": \"Front-end Developer\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0875677663\", \"email\": \"ian@tangentsolutions.co.za\", \"github_user\": \"TangentSolutions\", \"birth_date\": \"1983-12-23\", \"gender\": \"M\", \"race\": \"W\", \"years_worked\": 7, \"age\": 34, \"days_to_birthday\": 291 }, { \"user\": { \"id\": 4, \"username\": \"jacob.zuma\", \"email\": \"zumu@gov.co.za\", \"first_name\": \"Jacob\", \"last_name\": \"Zuma\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 2, \"name\": \"Back-end Developer\", \"level\": \"Junior\", \"sort\": 0 }, \"phone_number\": \"0832322778\", \"email\": \"zumu@gov.co.za\", \"github_user\": \"zuma\", \"birth_date\": \"1971-09-09\", \"gender\": \"M\", \"race\": \"B\", \"years_worked\": 4, \"age\": 46, \"days_to_birthday\": 186 }, { \"user\": { \"id\": 2, \"username\": \"james.dean\", \"email\": \"james@email.com\", \"first_name\": \"James\", \"last_name\": \"Dean\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 1, \"name\": \"Front-end Developer\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0873722777\", \"email\": \"james@email.com\", \"github_user\": \"JamesDean\", \"birth_date\": \"1980-03-30\", \"gender\": \"M\", \"race\": \"I\", \"years_worked\": 2, \"age\": 37, \"days_to_birthday\": 23 }, { \"user\": { \"id\": 9, \"username\": \"employee1\", \"email\": \"bloggs@gmail.com\", \"first_name\": \"Joe Employee 1\", \"last_name\": \"Bloggs\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 1, \"name\": \"Front-end Developer\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0827767889\", \"email\": \"bloggs@gmail.com\", \"github_user\": \"bloggs\", \"birth_date\": \"1995-01-01\", \"gender\": \"M\", \"race\": \"N\", \"years_worked\": 1, \"age\": 23, \"days_to_birthday\": 300 }, { \"user\": { \"id\": 12, \"username\": \"pravin.gordhan\", \"email\": \"pravin@axedmps.com\", \"first_name\": \"Pravin\", \"last_name\": \"Gordhan\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 3, \"name\": \"Project Manager\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0828899987\", \"email\": \"pravin@axedmps.com\", \"github_user\": \"PravG\", \"birth_date\": \"1951-12-12\", \"gender\": \"M\", \"race\": \"B\", \"years_worked\": 1, \"age\": 66, \"days_to_birthday\": 280 }, { \"user\": { \"id\": 6, \"username\": \"sue.beans\", \"email\": \"sue@tangent.co.za\", \"first_name\": \"Sue\", \"last_name\": \"Beans\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 2, \"name\": \"Back-end Developer\", \"level\": \"Junior\", \"sort\": 0 }, \"phone_number\": \"0872828817\", \"email\": \"sue@tangent.co.za\", \"github_user\": \"suebeans\", \"birth_date\": \"1990-09-21\", \"gender\": \"F\", \"race\": \"C\", \"years_worked\": 2, \"age\": 27, \"days_to_birthday\": 198 }, { \"user\": { \"id\": 3, \"username\": \"employee3\", \"email\": \"super.man@tangent.co.za\", \"first_name\": \"Super\", \"last_name\": \"Man\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 1, \"name\": \"Front-end Developer\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0744925023\", \"email\": \"mike@tangentsolutions.co.za\", \"github_user\": \"SuperGitMan\", \"birth_date\": \"1981-02-17\", \"gender\": \"M\", \"race\": \"W\", \"years_worked\": 1, \"age\": 37, \"days_to_birthday\": 347 }, { \"user\": { \"id\": 10, \"username\": \"employee2\", \"email\": \"thandi@gmail.com\", \"first_name\": \"Thandi\", \"last_name\": \"Plain\", \"is_active\": true, \"is_staff\": true }, \"position\": { \"id\": 3, \"name\": \"Project Manager\", \"level\": \"Senior\", \"sort\": 0 }, \"phone_number\": \"0728998700\", \"email\": \"thandi@gmail.com\", \"github_user\": \"thandi\", \"birth_date\": \"1988-02-08\", \"gender\": \"F\", \"race\": \"B\", \"years_worked\": 1, \"age\": 30, \"days_to_birthday\": 338 } ]";

        String fullProfileJson = "{\"id\": 12, \"user\": {\"id\": 12, \"username\": \"pravin.gordhan\", \"email\": \"pravin@axedmps.com\", \"first_name\": \"Pravin\", \"last_name\": \"Gordhan\", \"is_active\": true, \"is_staff\": true}, \"position\": {\"id\": 3, \"name\": \"Project Manager\", \"level\": \"Senior\", \"sort\": 0}, \"employee_next_of_kin\": [{\"id\": 4, \"name\": \"Mini Ghordan\", \"relationship\": \"Wife\", \"phone_number\": \"0827788877\", \"email\": \"mini@axedmps.com\", \"physical_address\": \"12 Church Street,\\r\\nBluekraans,\\r\\nMidrand,\\r\\nJohannesburg\", \"employee\": 12}], \"employee_review\": [{\"id\": 9, \"date\": \"2016-06-01\", \"salary\": \"100000.00\", \"type\": \"S\"}, {\"id\": 13, \"date\": \"2017-08-30\", \"salary\": \"120000.00\", \"type\": \"P\"}], \"id_number\": \"5112125239088\", \"phone_number\": \"0828899987\", \"physical_address\": \"12 Church Street,\\r\\nBluekraans,\\r\\nMidrand,\\r\\nJohannesburg\", \"tax_number\": \"102998766\", \"email\": \"pravin@axedmps.com\", \"personal_email\": \"pravin@axedmps.com\", \"github_user\": \"PravG\", \"birth_date\": \"1951-12-12\", \"start_date\": \"2016-06-01\", \"end_date\": null, \"id_document\": null, \"visa_document\": null, \"is_employed\": true, \"is_foreigner\": false, \"gender\": \"M\", \"race\": \"B\", \"years_worked\": 1, \"age\": 66, \"next_review\": \"2018-02-28\", \"days_to_birthday\": 280, \"leave_remaining\": \"16.50\"}";

        mockJsons.add(mockTokeJson);
        mockJsons.add(userJSon);
        mockJsons.add(employeesJson);
        mockJsons.add(fullProfileJson);

        return mockJsons.get(jsonIndex).trim();
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
        String authorization = values.getString("username")+":"+values.getString("password");
        String encodedAuth = "Basic  "+ android.util.Base64.encode(authorization.getBytes(), 0);
        httpConnect.setRequestProperty("Authorization", "Basic " + encodedAuth);
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