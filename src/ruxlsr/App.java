package ruxlsr;

import org.json.JSONArray;

import ruxlsr.model.GithubDataFetcher;

public class App {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("no argument passed. Syntax:: github-activity <username> [Eventype]");
            return;
        }

        String apiEndpoint = "https://api.github.com/users/" + args[0] + "/events";

        // System.out.println("the api endpoint: "+apiEndpoint);
        try {
            GithubDataFetcher gh = new GithubHttpClientDataFetcher();
            JSONArray events = gh.fetchAllEvent(apiEndpoint);

            if (args.length == 2 && !args[1].isEmpty())
                DataFormater.specificEventDisplayer(events, args[1]);
            else
                DataFormater.eventsDisplayer(events);
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
        }
    }
}
