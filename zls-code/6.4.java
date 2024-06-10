class Solution {
    //在带权树网络中统计可连接服务器对数目
//    给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。同时给你一个数组 edges ，其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，边的权值为 weighti 。再给你一个整数 signalSpeed 。
//
//    如果两个服务器 a ，b 和 c 满足以下条件，那么我们称服务器 a 和 b 是通过服务器 c 可连接的 ：
//
//    a < b ，a != c 且 b != c 。
//    从 c 到 a 的距离是可以被 signalSpeed 整除的。
//    从 c 到 b 的距离是可以被 signalSpeed 整除的。
//    从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
//    请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length+1;
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph,i -> new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int pre = 0;
            for (int[] e : graph[i]) {
                int cnt = dfs(e[0],i,e[1]% signalSpeed,signalSpeed,graph);
                res[i] += pre * cnt;
                pre += cnt;
            }
        }
        return  res;
    }

    private int dfs(int p, int root, int curr, int signalSpeed, List<int[]>[] graph) {
        int res =0;
        if (curr == 0){
            res++;
        }
        for (int[] e : graph[p]) {
            int v = e[0];
            int cost = e[1];
            if (v != root){
                res += dfs(v,p,(curr+cost) % signalSpeed,signalSpeed,graph);
            }
        }
        return res;
    }

}