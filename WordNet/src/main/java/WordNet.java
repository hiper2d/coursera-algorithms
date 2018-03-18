import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class WordNet {

    private static final String COMA = ",";
    private static final String SPACE = " ";

    private final Map<String, List<Integer>> nounMap;
    private final Map<Integer, String> idToNounMap;
    private final Digraph digraph;
    private final SAP sap;

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Null input");
        }

        nounMap = new HashMap<>();
        idToNounMap = new HashMap<>();

        In in = new In(synsets);
        int lastId = readSynsets(in);

        in = new In(hypernyms);
        digraph = new Digraph(lastId + 1);
        readHypernyms(in);

        sap = new SAP(digraph);
    }

    private int readSynsets(In in) {
        int lastId = 0;
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] lineParts = line.split(COMA);
            int id = Integer.parseInt(lineParts[0]);
            String[] nounsLinePart = lineParts[1].split(SPACE);
            idToNounMap.put(id, lineParts[1]);
            Arrays.stream(nounsLinePart).forEach(noun -> {
                nounMap.computeIfAbsent(noun, key -> new ArrayList<>());
                nounMap.get(noun).add(id);
            });
            lastId = id;
        }
        return lastId;
    }

    private void readHypernyms(In in) {
        while (in.hasNextLine()) {
            String line = in.readLine();
            if (!line.contains(COMA)) {
                continue;
            }
            String[] lineParts = line.split(COMA);
            int hyponymId = Integer.parseInt(lineParts[0]);
            for (int i = 1; i < lineParts.length; i++) {
                int hypernymId = Integer.parseInt(lineParts[i]);
                digraph.addEdge(hyponymId, hypernymId);
            }
        }
    }

    public Iterable<String> nouns() {
        return nounMap.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        return nounMap.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Noa a noun input");
        }
        return sap.length(nounMap.get(nounA), nounMap.get(nounB));
    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Noa a noun input");
        }

        int minAncestorId = sap.ancestor(nounMap.get(nounA), nounMap.get(nounB));
        return idToNounMap.get(minAncestorId);
    }
}
