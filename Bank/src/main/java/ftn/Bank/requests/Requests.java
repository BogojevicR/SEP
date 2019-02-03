package ftn.Bank.requests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class Requests {
	
	/**
	 * This method is used for all post requests
	 * @param url - location or rest resource
	 * @param jsonObject - object that is going to be send via post request
	 * @return returns string of whatever rest resource returns
	 */
	
			
	/**
	 * This method is used for all post requests
	 * @param url - location or rest resource
	 * @param jsonObject - object that is going to be send via post request
	 * @return returns string of whatever rest resource returns
	 */
	public String makePostRequest(String url, String jsonString) {
		try{
			
			HttpClient httpClient =  HttpClientBuilder.create().build(); //Use this instead 	
			HttpPost postMethod = new HttpPost(url);
			
			
			if (jsonString == null) {
				return "";
			}
			if (jsonString != null) {
				StringEntity requestEntity = new StringEntity(
					    jsonString,
					    ContentType.APPLICATION_JSON);
				
				postMethod.setEntity(requestEntity);
			}
			
			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).setExpectContinueEnabled(true).setStaleConnectionCheckEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).build();
			
			
//			int CONNECTION_TIMEOUT_MS = 3 * 1000; // Timeout in millis.
//			RequestConfig requestConfig = RequestConfig.custom()
//			    .setConnectionRequestTimeout(CONNECTION_TIMEOUT_MS)
//			    .setConnectTimeout(CONNECTION_TIMEOUT_MS)
//			    .setSocketTimeout(CONNECTION_TIMEOUT_MS)
//			    .build();
			
			postMethod.setConfig(requestConfig);
			
			HttpResponse rawResponse = httpClient.execute(postMethod);
			InputStream inputStream = rawResponse.getEntity().getContent();
		
			
			StringBuilder result = new StringBuilder();  
			
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        result.append(line + "\n");  
		    }
		    br.close();
			
			return result.toString();
		} catch (Exception e) {
			String retVal = "";
//			if (e instanceof SocketTimeoutException) {
//				retVal = new Requests().makePostRequest(url, jsonString);
//			}
			return retVal;
		}

	}
	
	/**
	 * This method is used for all get requests
	 * @param url - location of rest resource
	 * @return returns string of whatever rest resource returns
	 */
	public String makeGetRequest(String url) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			
			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).setExpectContinueEnabled(true).setStaleConnectionCheckEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).build();
			request.setConfig(requestConfig);
		
			HttpResponse response = client.execute(request);
	
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
	
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			return result.toString();
		} catch (Exception e) {
			String retVal = "";
//			if (e instanceof SocketTimeoutException) {
//				retVal = new Requests().makeGetRequest(url);
//			}
			return retVal;
		}

	}
	
	/**
	 * This method is used for all put requests
	 * @param url - location of rest resource
	 * @return returns string of whatever rest resource returns
	 */
	public void makePutRequest(String url) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(url);
			
			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).setExpectContinueEnabled(true).setStaleConnectionCheckEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(10000).build();
			request.setConfig(requestConfig);
		
			client.execute(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This method is used for all delete requests
	 * @param url - location of rest resource
	 * @return returns string of whatever rest resource returns
	 */
	public void makeDeleteRequest(String urlString) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			
			HttpDelete request = new HttpDelete(urlString);
			
			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).setExpectContinueEnabled(true).setStaleConnectionCheckEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(10000).build();
			request.setConfig(requestConfig);
		
			client.execute(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
