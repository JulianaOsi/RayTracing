package common;

import javafx.util.Pair;

public class Partitioner {
    private int pointer;
    private int length;
    private int partsCount;

    public Partitioner(int length, int partsCount) {
        this.pointer = 0;
        this.length = length;
        this.partsCount = partsCount;
    }

    public Pair<Integer, Integer> next() {
        int start = pointer;
        int end = pointer + length / partsCount;
        pointer = end;
        return new Pair<>(start, end);
    }
}
