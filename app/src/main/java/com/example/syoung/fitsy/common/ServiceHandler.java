package com.example.syoung.fitsy.common;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Created by HyunJoo on 15. 7. 9..
 */
public class ServiceHandler {
    private final String TAG = "ServiceHandler";
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public ServiceHandler() {

    }

    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }

    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // request 형식이 POST 인지 GET 인지 구별
            if (method == POST) {
                URL _url = new URL(url);

                HttpPost httpRequest = null;

                httpRequest = new HttpPost(_url.toURI());
                //HttpPost httpPost = new HttpPost(url);
                // post 파라미터들 추가
                if (params != null) {
                    httpRequest.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = (HttpResponse) httpClient.execute(httpRequest);

            } else if (method == GET) {
                // 파라미터들을 주어진 URL 에 붙여서 보냄
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            Log.e(TAG, "response : " + response);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JSON 형태의 response
        return response;

    }

    public String makeJSONServiceCall(String url, int method, String json) {
        try {
            // http client
            HttpClient httpclient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            //StringEntity se = new StringEntity(json);

            //Log.e(TAG, "보내는 정보 : " + se);

            URL _url = new URL(url);

            HttpPost httpPost = null;
            httpPost = new HttpPost(_url.toURI());

            //httpPost.setEntity(se);

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("course", json));

            Log.e(TAG, "params : " + params);

            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(params, "utf-8");

            httpPost.setEntity(entityRequest);

            //httpPost.setHeader("Accept", "application");
            //httpPost.setHeader("Content-type", "application");

            Log.e(TAG, "httpPost : " + httpPost);

            httpResponse = (HttpResponse) httpclient.execute(httpPost);

            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            Log.e(TAG, "response : " + response);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JSON 형태의 response
        return response;

    }


}