package org.tomaszkowalczyk94.simplesearch.seachengine;

import lombok.Value;

@Value
class LvlOfSimilarity {

    public static final LvlOfSimilarity THE_SAME = new LvlOfSimilarity(1.0);
    public static final LvlOfSimilarity DIFFERENT = new LvlOfSimilarity(0.0);
    private static final double ABOVE_PERCENT_FOR_THE_SAME = 0.99;
    private static final double ABOVE_PERCENT_FOR_MINOR_DIFFERENCE = 0.70;

    public enum Level {
        THE_SAME,
        MINOR_DIFFERENCE,
        DIFFERENT;
    }

    LvlOfSimilarity(double percent) {
        this.percent = percent;

        if(percent > ABOVE_PERCENT_FOR_THE_SAME) {
            level = Level.THE_SAME;
        }else if(percent > ABOVE_PERCENT_FOR_MINOR_DIFFERENCE) {
            level = Level.MINOR_DIFFERENCE;
        } else {
            level = Level.DIFFERENT;
        }
    }

    double percent;
    Level level;
}
