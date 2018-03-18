public class Outcast {

    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {
        if (wordnet == null) {
            throw new IllegalArgumentException("Null argument");
        }
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {
        if (nouns == null) {
            throw new IllegalArgumentException("Null argument");
        }
        int maxDistNoun = -1;
        int maxDistSum = 0;
        for (int i = 0; i < nouns.length; i++) {
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
                dist += wordnet.distance(nouns[i], nouns[j]);
            }
            if (maxDistSum < dist) {
                maxDistSum = dist;
                maxDistNoun = i;
            }
        }
        return nouns[maxDistNoun];
    }
}
