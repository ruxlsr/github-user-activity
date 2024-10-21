import org.json.JSONArray;
import org.json.JSONObject;

public class DataFormater {
    public static void format(JSONArray jsonEventsArray){
        if (jsonEventsArray==null) {
            System.out.println("the arrqy is empty");
            return;
        }
        //System.out.println(jsonEventsArray);
        for(int i = 0; i < jsonEventsArray.length(); i++){
            JSONObject event = jsonEventsArray.getJSONObject(i);
            JSONObject eventPayLoad = event.getJSONObject("payload");
            String repoName = event.getJSONObject("repo").getString("name");
            String createdAt = event.getString("created_at");

            switch (event.getString("type")) {
                case "PushEvent" -> {
                    System.out.println(createdAt+":: Pushed "+eventPayLoad.getInt("distinct_size")+" commit(s) to "+repoName);
                }
                case "CreateEvent"->{
                    System.out.println(createdAt+":: created a "+eventPayLoad.getString("ref_type")+" to "+repoName);
                }
                case "CommitCommentEvent"->{
                    System.out.println(createdAt+":: Comment a commit on "+repoName);
                }
                case "DeleteEvent"->{
                    System.out.println(createdAt+":: deleted a "+eventPayLoad.getString("ref_type")+" to "+repoName);
                }
                case "ForkEvent"->{
                    System.out.println(createdAt+":: Forked "+repoName);
                }
                case "WatchEvent" -> {
                    System.out.println(createdAt+":: Stared "+repoName);
                }
                case "GollumEvent"->{
                    JSONObject page = eventPayLoad.getJSONArray("page").getJSONObject(0);
                    
                    System.out.println(createdAt+":: "+page.getString("action")+" --"+page.getString("page_name")+"-- wiki page to "+repoName);
                }
                case "IssueCommentEvent"->{
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" a comment on the issues --"+eventPayLoad.getJSONObject("issue").getString("title")+"-- on"+repoName);
                }
                case "IssuesEvent"->{
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" the issue --"+eventPayLoad.getJSONObject("issue").getString("title")+"-- on"+repoName);
                }
                case "MemberEvent"->{
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" a member on +"+repoName);
                }
                case "PublicEvent"->{
                    System.out.println(createdAt+":: Made --"+repoName+"-- public 🥳🥳🥳");
                }
                case "PullRequestReviewEvent"->{
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" a pull request review on "+repoName);
                }
                case "PullRequestReviewCommentEvent"->{
                    System.out.println(createdAt+":: Commented a pull request review on "+repoName);
                }
                case "PullRequestReviewThreadEvent"->{
                    System.out.println(createdAt+":: Mark a thread as "+eventPayLoad.getString("action")+" on "+repoName);
                }
                case "PullRequestEvent"->{
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" a pull request on"+repoName);
                }
                case "ReleaseEvent" -> {
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" a release on "+repoName);
                }
                case "SponsorshipEvent"->{
                    System.out.println(createdAt+":: "+eventPayLoad.getString("action")+" Sponsorship action on "+repoName);
                }
                             
                default->{
                    System.err.println("argument not recognized => "+event.getString("type"));
                }
            }
        }
    }
}
