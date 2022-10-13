import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class SearchHandler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        ArrayList<String> words = new ArrayList<String>();
        if (url.getPath().equals("/")) {
            return "Welcome to my search engine! Use add?s= to add a new word and search?s= to search for all added words.";
        }
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    words.add(parameters[1]);
                    return parameters[1].concat(" added.");
                }
            }
            else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String anOutput = "Your search result is: ";
                if (parameters[0].equals("s")) {
                    String search = parameters[1];
                    for (int i = 0; i < words.size(); i++) {
                            if (words.get(i).indexOf(search) > -1) {
                                anOutput.concat(words.get(i));
                                if ((i + 1) < words.size()) {
                                    anOutput.concat(" and ");
                                }
                            } 
                    }
                    return anOutput;
                }
            }
            return "404 Not Found!";
    
        }
    }

class SearchEngine {

        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }
    
            int port = Integer.parseInt(args[0]);
    
            Server.start(port, new SearchHandler());
        }
    
}

