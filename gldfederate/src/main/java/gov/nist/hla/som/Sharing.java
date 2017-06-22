package gov.nist.hla.som;

public class Sharing {
    private static final String PUBLISHED = "publish";
    private static final String SUBSCRIBED = "subscribe";

    private String text;

    // accepts malformed strings
    public Sharing(String text) {
        this.text = text.toLowerCase();
    }

    public Sharing(Sharing sharing) {
        this.text = sharing.text;
    }
    
    // constructs the union of the two sharing arguments
    public Sharing(Sharing s1, Sharing s2) {
        String theText = "";

        if (s1.isPublished() || s2.isPublished()) {
            theText += PUBLISHED;
        }
        if (s1.isSubscribed() || s2.isSubscribed()) {
            theText += SUBSCRIBED;
        }
        this.text = theText;
    }

    public boolean isPublished() {
        return text.contains(PUBLISHED);
    }

    public boolean isSubscribed() {
        return text.contains(SUBSCRIBED);
    }

    public String getText() {
        return text;
    }
    
    @Override
    public String toString() {
        String result = "";
        if (this.isPublished()) {
            result += "Publish";
        }
        if (this.isSubscribed()) {
            result += "Subscribe";
        }
        if (result.isEmpty()) {
            result = "Neither";
        }
        return result;
    }
}
