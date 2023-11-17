package userAPITest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPIGetTest {
	
	private static final int HTTP_CODE_200 = 200;
	private static final int HTTP_CODE_400 = 400;
	private static final int HTTP_CODE_404 = 404;
	
	private static final String HTTP_STATUS_MESSAGE_OK = "OK";
	private static final String HTTP_STATUS_MESSAGE_CLIENT_DATA_ISSUE = "CLIENT DATA ISSUE";
	private static final String HTTP_STATUS_MESSAGE_NOT_FOUND = "NOT FOUND";
	
@Test
	public void testWithCorrectUserId() throws ParseException, IOException {
	String url = "https://gorest.co.in/public/v2/users/5714920";
	HttpResponse response = sendAndReceiveGetMessage(url);
	
	System.out.println(response.getStatusLine().getStatusCode());
	System.out.println(response.getStatusLine().getReasonPhrase());
	String responseMsg = getStringMessageFromResponseObject(response);
	
	System.out.println(responseMsg);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), HTTP_CODE_200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), HTTP_STATUS_MESSAGE_OK);
	Assert.assertTrue(responseMsg.contains("Paramartha Gill"));
	
}
private HttpResponse sendAndReceiveGetMessage(String url) throws IOException {
	HttpGet request = new HttpGet(url);
	HttpClient client = HttpClientBuilder.create().build();
	HttpResponse response = client.execute(request);
	return response;
}

private String getStringMessageFromResponseObject(HttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();
    return EntityUtils.toString(entity);
}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
