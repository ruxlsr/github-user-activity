public class App {
    
    public static void main(String[] args) throws Exception {
        if(args.length==0){
            System.err.println("no argument passed. Syntax:: github-activity <username>");
            return;
        }

        String apiEndpoint = "https://api.github.com/users/"+args[0]+"/events";
        System.out.println("the api endpoint: "+apiEndpoint);
        GithubHttpClientDataFetcher gh = new GithubHttpClientDataFetcher();
        DataFormater.format(gh.fetchAllEvent(apiEndpoint));
        
    }
}
