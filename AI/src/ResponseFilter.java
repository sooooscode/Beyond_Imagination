import java.util.List;
import java.util.ArrayList;

public class ResponseFilter {
    private static final List<String> BAD_WORDS = List.of("욕설1", "욕설2", "비방1");

    public List<String> filterBadWords(List<String> responses) {
        List<String> filteredResponses = new ArrayList<>();
        for (String response : responses) {
            if (!containsBadWords(response)) {
                filteredResponses.add(response);
            }
        }
        return filteredResponses;
    }

    private boolean containsBadWords(String text) {
        for (String badWord : BAD_WORDS) {
            if (text.contains(badWord)) {
                return true;
            }
        }
        return false;
    }
}
