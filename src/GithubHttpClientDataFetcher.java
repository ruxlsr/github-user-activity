import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;

import model.GithubDataFetcher;

public class GithubHttpClientDataFetcher implements GithubDataFetcher{

    @Override
    public JSONArray fetchAllEvent(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("Accept", "application/vnd.github+json" ).GET().build();
        HttpResponse<String> response;

        try {
            response = client.send(request, BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 200 -> {
                    System.out.println("Status : OK");
                    return new JSONArray(response.body());
                }
                case 304 -> {
                    System.out.println("Status: not modified");
                }
                case 404 -> {
                    System.err.println("Error: not Found");
                }
                 case 403 -> {
                    System.err.println("Error: forbiden");
                }
                default-> {
                    System.err.println("not Handled Error: "+response.statusCode());
                }
                    
            }
        } catch (IOException e) {
            System.err.println("Error:"+e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Error:"+e.getMessage());
        }
        client.close();
        return null;  
    }
}