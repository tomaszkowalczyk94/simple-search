package org.tomaszkowalczyk94.simplesearch.seachengine;

class WordComparator {

    /**
     * @return percentage
     */
    LvlOfSimilarity countLevelOfSimilarity(String s1Param, String s2Param) {
        String s1 = s1Param.toLowerCase();
        String s2 = s2Param.toLowerCase();

        if (!haveSimilarLengths(s1.length(), s2.length())) {
            return LvlOfSimilarity.DIFFERENT;
        }

        //for optimisations
        if (s1.equals(s2)) {
            return LvlOfSimilarity.THE_SAME;
        }

        double similarity = similarity(s1, s2);
        return new LvlOfSimilarity(similarity);

    }
    private boolean haveSimilarLengths(int length1, int length2) {
        return Math.abs(length1 - length2) < 4;
    }

    private double similarity(String s1, String s2) {
        String longer = s1;
        String shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0; /* both strings are zero length */
        }
        return (longerLength - levenshteinDistance(longer, shorter)) / (double) longerLength;

    }

    /**
     * source http://rosettacode.org/wiki/Levenshtein_distance#Java
     */
    private int levenshteinDistance(String a, String b) {
        // i == 0
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

}
