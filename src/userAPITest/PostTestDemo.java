package userAPITest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostTestDemo {

	public static void main(String[] args) {
		// Create a CloseableHttpClientÏ
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

			// Define the URL of the POST request
			String postUrl = "https://reqres.in/api/users/2";

			// Create an HttpPost object
			HttpPost httpPost = new HttpPost(postUrl);

			// Create a list to store POST parameters
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("name", "cici"));
			params.add(new BasicNameValuePair("job", "student"));

			// Encode the parameters and set them in the request entity
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			

			// Execute the POST request
			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				// Get the response entity
				HttpEntity entity = response.getEntity();
				System.out.println(response.getStatusLine().getStatusCode());
				System.out.println(response.getStatusLine().getReasonPhrase());

				// Print the response content
				if (entity != null) {
					System.out.println(EntityUtils.toString(entity));
				}
				if(response.getStatusLine().getStatusCode() == 201) {
		        	System.out.println("status code test passed");
		        } else {
		        	System.out.println("status code test failed");
		        }
		        if("Created".equals(response.getStatusLine().getReasonPhrase())) {
		        	System.out.println("status phrase test passed");
		        } else {
		        	System.out.println("status phrase test failed");
		        }

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
