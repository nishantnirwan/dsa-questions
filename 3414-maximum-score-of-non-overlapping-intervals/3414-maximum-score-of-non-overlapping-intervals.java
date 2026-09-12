class Solution {
    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    List<Interval> arr;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            ));
        }

        arr.sort((a, b) -> {
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        dp = new State[n][5];

        State res = solve(0, 4);

        return res.indices.stream().mapToInt(Integer::intValue).toArray();
    }

    private State solve(int i, int k) {
        if (i == arr.size() || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        State skip = solve(i + 1, k);

        int next = findNext(i);

        State nextState = solve(next, k - 1);

        List<Integer> selected = new ArrayList<>(nextState.indices);
        selected.add(arr.get(i).idx);
        Collections.sort(selected);

        State take = new State(
            arr.get(i).w + nextState.weight,
            selected
        );

        if (take.weight > skip.weight) {
            return dp[i][k] = take;
        }

        if (take.weight < skip.weight) {
            return dp[i][k] = skip;
        }

        return dp[i][k] = compare(take.indices, skip.indices) < 0 ? take : skip;
    }

    private int findNext(int i) {
        int target = arr.get(i).r;
        int l = i + 1;
        int r = arr.size();

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr.get(mid).l > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}