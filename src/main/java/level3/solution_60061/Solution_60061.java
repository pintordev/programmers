package level3.solution_60061;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

class Solution_60061 {

    public static void main(String[] args) {

        Solution_60061 s = new Solution_60061();

        int[] n = {5, 5};
        int[][][] build_frame = {{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}},
                {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}};
        int[][][] result = {{{1, 0, 0}, {1, 1, 1}, {2, 1, 0}, {2, 2, 1}, {3, 2, 1}, {4, 2, 1}, {5, 0, 0}, {5, 1, 0}},
                {{0, 0, 0}, {0, 1, 1}, {1, 1, 1}, {2, 1, 1}, {3, 1, 1}, {4, 0, 0}}};

        for (int i = 0, t = n.length; i < t; i++) {
            System.out.println(Arrays.deepEquals(s.solution(n[i], build_frame[i]), result[i]));
        }
    }

    public static Set<Frame> frames;

    public int[][] solution(int n, int[][] build_frame) {
        frames = new LinkedHashSet<>();

        for (int[] bf : build_frame) {
            Frame frame = new Frame(bf[0], bf[1], bf[2]);
            if (bf[3] == 1 && canBuild(frame)) frames.add(frame);
            else if (bf[3] == 0 && canRemove(frame)) frames.remove(frame);
        }

        return frames.stream()
                .sorted()
                .map(b -> new int[]{b.x, b.y, b.type})
                .toArray(int[][]::new);
    }

    public boolean canBuild(Frame frame) {
        if (frame.type == 0) {
            if (frame.y == 0
                    || frames.contains(new Frame(frame.x, frame.y - 1, 0))
                    || frames.contains(new Frame(frame.x - 1, frame.y, 1))
                    || frames.contains(new Frame(frame.x, frame.y, 1))) {
                return true;
            }
        } else {
            if (frames.contains(new Frame(frame.x, frame.y - 1, 0))
                    || frames.contains(new Frame(frame.x + 1, frame.y - 1, 0))
                    || (frames.contains(new Frame(frame.x - 1, frame.y, 1)) && frames.contains(new Frame(frame.x + 1, frame.y, 1)))) {
                return true;
            }
        }
        return false;
    }

    public boolean canRemove(Frame frame) {
        frames.remove(frame);
        for (Frame f : frames) {
            if (!canBuild(f)) {
                frames.add(frame);
                return false;
            }
        }
        return true;
    }
}

class Frame implements Comparable<Frame> {
    int x;
    int y;
    int type;

    public Frame(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frame)) return false;
        Frame f = (Frame) o;
        return this.x == f.x && this.y == f.y && this.type == f.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, type);
    }

    @Override
    public int compareTo(Frame o) {
        if (this.x == o.x && this.y == o.y) return this.type - o.type;
        if (this.x == o.x) return this.y - o.y;
        return this.x - o.x;
    }
}