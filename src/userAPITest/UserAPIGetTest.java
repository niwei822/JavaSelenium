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

public class UserAPIGetTest extends BaseTest implements UserAPIConstant {
	
@Test
	public void testWithCorrectUserId() throws ParseException, IOException {
	String url = base_URL + "/2";
	HttpResponse response = sendAndReceiveGetMessage(url);
	
	System.out.println(response.getStatusLine().getStatusCode());
	System.out.println(response.getStatusLine().getReasonPhrase());
	String responseMsg = getStringMessageFromResponseObject(response);
	
	System.out.println(responseMsg);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), HTTP_CODE_200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), HTTP_STATUS_MESSAGE_OK);
	Assert.assertTrue(responseMsg.contains("Janet"));
	
}

private String getStringMessageFromResponseObject(HttpResponse response) throws IOException {
	HttpEntity entity = response.getEntity();
	String responseMsg = "";
    if (entity != null) {
        // return it as a String
        responseMsg = EntityUtils.toString(entity);
        //System.out.println(result);
    }
    return responseMsg;
}

@Test
private HttpResponse sendAndReceiveGetMessage(String url) throws IOException {
	HttpGet request = new HttpGet(url);
	HttpClient client = HttpClientBuilder.create().build();
	HttpResponse response = client.execute(request);
	return response;
}

@Test
public void testWithNonExistingtUserId() throws ParseException, IOException {
	String url = base_URL + "/200";
	HttpResponse response = sendAndReceiveGetMessage(url);
	
	System.out.println(response.getStatusLine().getStatusCode());
	System.out.println(response.getStatusLine().getReasonPhrase());
	String responseMsg = getStringMessageFromResponseObject(response);
	
	System.out.println(responseMsg);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), HTTP_CODE_404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), HTTP_STATUS_MESSAGE_NOT_FOUND);
	//Assert.assertTrue(responseMsg.contains("Govinda Mahajan"));
	
}

@Test
public void testWithSpecialChar() throws ParseException, IOException {
	String url = base_URL + "/&&&";
	HttpResponse response = sendAndReceiveGetMessage(url);
	
	System.out.println(response.getStatusLine().getStatusCode());
	System.out.println(response.getStatusLine().getReasonPhrase());
	String responseMsg = getStringMessageFromResponseObject(response);

	System.out.println(responseMsg);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), HTTP_CODE_404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), HTTP_STATUS_MESSAGE_NOT_FOUND);
	//Assert.assertTrue(responseMsg.contains("Govinda Mahajan"));
	
}

@Test
public void testWithBlankId() throws ParseException, IOException {
	String url = base_URL + "/";
	HttpResponse response = sendAndReceiveGetMessage(url);
	
	System.out.println(response.getStatusLine().getStatusCode());
	System.out.println(response.getStatusLine().getReasonPhrase());
	String responseMsg = getStringMessageFromResponseObject(response);
	
	System.out.println(responseMsg);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), HTTP_CODE_200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), HTTP_STATUS_MESSAGE_OK);
	//Assert.assertTrue(responseMsg.contains("Govinda Mahajan"));
	
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
