class Solution {
    boolean[] suspicious;
    boolean[] visited;
    List<Integer>[] g;
    List<Integer>[] ug;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        suspicious = new boolean[n];
        visited = new boolean[n];

        g = new ArrayList[n];
        ug = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            ug[i] = new ArrayList<>();
        }

        for(int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];

            g[u].add(v);

            ug[u].add(v);
            ug[v].add(u);
        }

        dfsSuspicious(k);

        for(int i = 0; i < n; i++) {
            if(!suspicious[i] && !visited[i]) {
                dfsRestore(i);
            }
        }

        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfsSuspicious(int u) {
        suspicious[u] = true;

        for(int v : g[u]) {
            if(!suspicious[v]) {
                dfsSuspicious(v);
            }
        }
    }

    private void dfsRestore(int u) {
        visited[u] = true;
        suspicious[u] = false;

        for(int v : ug[u]) {
            if(!visited[v]) {
                dfsRestore(v);
            }
        }
    }
}