class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int size = 4 * n;

        char[] str = s.toCharArray();
        char[] left = new char[size];
        char[] right = new char[size];
        int[] pre = new int[size];
        int[] suf = new int[size];
        int[] best = new int[size];

        build(1, 0, n - 1, str, left, right, pre, suf, best);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(1, 0, n - 1, queryIndices[i],
                   queryCharacters.charAt(i), str,
                   left, right, pre, suf, best);

            ans[i] = best[1];
        }

        return ans;
    }

    private void build(int node, int l, int r, char[] str,
                       char[] left, char[] right,
                       int[] pre, int[] suf, int[] best) {

        if (l == r) {
            left[node] = right[node] = str[l];
            pre[node] = suf[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, str, left, right, pre, suf, best);
        build(node * 2 + 1, mid + 1, r, str, left, right, pre, suf, best);

        merge(node, node * 2, node * 2 + 1,
              mid - l + 1, r - mid,
              left, right, pre, suf, best);
    }

    private void update(int node, int l, int r, int pos, char c,
                        char[] str, char[] left, char[] right,
                        int[] pre, int[] suf, int[] best) {

        if (l == r) {
            str[pos] = c;
            left[node] = right[node] = c;
            pre[node] = suf[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid) {
            update(node * 2, l, mid, pos, c, str,
                   left, right, pre, suf, best);
        } else {
            update(node * 2 + 1, mid + 1, r, pos, c, str,
                   left, right, pre, suf, best);
        }

        merge(node, node * 2, node * 2 + 1,
              mid - l + 1, r - mid,
              left, right, pre, suf, best);
    }

    private void merge(int node, int a, int b, int lenA, int lenB,
                       char[] left, char[] right,
                       int[] pre, int[] suf, int[] best) {

        left[node] = left[a];
        right[node] = right[b];

        pre[node] = pre[a];
        suf[node] = suf[b];
        best[node] = Math.max(best[a], best[b]);

        if (right[a] == left[b]) {
            best[node] = Math.max(best[node], suf[a] + pre[b]);

            if (pre[a] == lenA) {
                pre[node] = lenA + pre[b];
            }

            if (suf[b] == lenB) {
                suf[node] = lenB + suf[a];
            }
        }
    }
}