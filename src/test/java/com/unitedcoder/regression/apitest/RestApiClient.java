package com.unitedcoder.regression.apitest;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestApiClient {
    //httpMethod--Get request without header
    public CloseableHttpResponse getRequest(String url){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse httpResponse=null;
        try {
            httpResponse=httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int statusCode=httpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code is: "+statusCode);
        String responseString=null;
        try {
            responseString= EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Json response is: "+responseJson);
        Header[] responseHeader=httpResponse.getAllHeaders();
        HashMap<String,String> allheaders=new HashMap<>();
        for(Header header:responseHeader){
            allheaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: "+allheaders);
        return httpResponse;
    }
    //Get request with Header
    public CloseableHttpResponse getRequestWithHeader(String url,HashMap<String,String> requestHeader)
    {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        //for request header
        for(Map.Entry<String,String> entry:requestHeader.entrySet()){
            httpGet.addHeader(entry.getKey(),entry.getValue());
        }
        CloseableHttpResponse httpResponse=null;
        try {
            httpResponse=httpClient.execute(httpGet);//click on send
        } catch (IOException e) {
            e.printStackTrace();
        }

        int statusCode=httpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code is: "+statusCode);
        String responseString=null;
        try {
            responseString= EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Json response is: "+responseJson);
        Header[] responseHeader=httpResponse.getAllHeaders();
        HashMap<String,String> allheaders=new HashMap<>();
        for(Header header:responseHeader){
            allheaders.put(header.getName(),header.getValue());
        }
        System.out.println("Response Headers: "+allheaders);
        return httpResponse;
    }
    //post request
    public CloseableHttpResponse postRequest(String url,String entityString,HashMap<String,String> requestHeader){
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        //Json payload(Body)
        try {
            httpPost.setEntity(new StringEntity(entityString));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //headers
        for(Map.Entry<String,String> entry:requestHeader.entrySet()){
            httpPost.addHeader(entry.getKey(),entry.getValue());
        }
        CloseableHttpResponse response= null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}




