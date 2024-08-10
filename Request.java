//imports
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private final HttpClient client; //creating a client variable
    private final String BASE_URL = "https://random-word-api.vercel.app/api?words=1&length=5"; //API base URL

    public Request() {
        client = HttpClient.newHttpClient(); //initializing new Http client
    }
    public String find() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL)) //set URI
            .GET() //perform GET request
            .build(); //build the object
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); //send request, get response as a string
        return response.body(); //returns the string, which is the random word
    }

}
