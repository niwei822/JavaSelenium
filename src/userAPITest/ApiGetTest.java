package userAPITest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiGetTest {

    private HttpClient client;
    private HttpGet validRequest;
    private HttpGet invalidRequest;

    @BeforeClass
    public void setUp() {
        client = HttpClientBuilder.create().build();
        validRequest = new HttpGet("https://gorest.co.in/public/v2/users/5676831");
        invalidRequest = new HttpGet("https://gorest.co.in/public/v2/users/12345");
    }

    @Test
    public void testValidUserStatusCode() throws IOException {
        HttpResponse response = client.execute(validRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_OK, "Unexpected status code for valid user");
    }

    @Test
    public void testValidUserReasonPhrase() throws IOException {
        HttpResponse response = client.execute(validRequest);
        String reasonPhrase = response.getStatusLine().getReasonPhrase();
        Assert.assertEquals(reasonPhrase, "OK", "Unexpected reason phrase for valid user");
    }

    @Test
    public void testInvalidUserStatusCode() throws IOException {
        HttpResponse response = client.execute(invalidRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND, "Unexpected status code for invalid user");
    }

    @Test
    public void testInvalidUserReasonPhrase() throws IOException {
        HttpResponse response = client.execute(invalidRequest);
        String reasonPhrase = response.getStatusLine().getReasonPhrase();
        Assert.assertEquals(reasonPhrase, "Not Found", "Unexpected reason phrase for invalid user");
    }

    @AfterClass
    public void tearDown() {
        // Close the HttpClient to release resources
        HttpClientUtils.closeQuietly(client);
    }
}
