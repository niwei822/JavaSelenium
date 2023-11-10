package userAPITest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class UserAPITest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
//https://gorest.co.in/public/v2/users/5676831
		// step1 create request message or URL
		String url = "https://gorest.co.in/public/v2/users/5676831";
		HttpGet request = new HttpGet(url);

		// step2 send and receive response msg
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);
		
		//step3 validate
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		
		HttpEntity entity = response.getEntity();
		String responseMsg = "";
        if (entity != null) {
            // return it as a String
            responseMsg = EntityUtils.toString(entity);
            //System.out.println(result);
        }
        System.out.println(responseMsg);
        
        if(response.getStatusLine().getStatusCode() == 200) {
        	System.out.println("status code test passed");
        } else {
        	System.out.println("status code test failed");
        }
        if(response.getStatusLine().getReasonPhrase() == "OK") {
        	System.out.println("status phrase test passed");
        } else {
        	System.out.println("status phrase test failed");
        }


	}
}
