package model;

import org.json.JSONArray;

public interface GithubDataFetcher {
    public JSONArray fetchAllEvent(String url);
}
