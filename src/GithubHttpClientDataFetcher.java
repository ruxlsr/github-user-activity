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
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("Content-Type", "application/json").GET().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
            
            if(response.statusCode() == 200 && response.body() != null){
                client.close();
                //System.err.println(response.body());
                return new JSONArray(response.body());
            }else 
                System.err.println("Une erreur: "+response.statusCode());
        } catch (IOException e) {
            System.err.println("Error:"+e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Error:"+e.getMessage());
        }
        client.close();
        return null;  
    }
}
