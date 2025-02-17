// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {

            System.out.println("MD Length: " + markdown.length());
            System.out.println("Current Index: " + currentIndex);

            int nextOpenBracket = markdown.indexOf("[", currentIndex);

            if (nextOpenBracket == -1) return toReturn;

            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            
            if (nextCloseBracket + 1 == openParen) {
                if (nextOpenBracket == 0 || 
                    !(markdown.substring(nextOpenBracket - 1, nextOpenBracket).equals("!"))) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            }

            if (nextOpenBracket < 0 || nextCloseBracket < 0 || openParen < 0 || closeParen < 0) {
                return toReturn;
            }

            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}

// added a line