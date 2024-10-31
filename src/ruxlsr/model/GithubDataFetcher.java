package ruxlsr.model;

import org.json.JSONArray;

public interface GithubDataFetcher {
    public JSONArray fetchAllEvent(String url) throws Exception;
}
