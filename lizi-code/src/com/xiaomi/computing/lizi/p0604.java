package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。同时给你一个数组 edges ，其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，边的权值为 weighti 。再给你一个整数 signalSpeed 。
 * 如果两个服务器 a ，b 和 c 满足以下条件，那么我们称服务器 a 和 b 是通过服务器 c 可连接的 ：
 * a < b ，a != c 且 b != c 。
 * 从 c 到 a 的距离是可以被 signalSpeed 整除的。
 * 从 c 到 b 的距离是可以被 signalSpeed 整除的。
 * 从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
 * 请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-04 11:33
 **/
public class p0604 {
    int count = 0;
    boolean[] visited;

    /**
     * 总是习惯用int[][] graph,下次图用这个表示 List<int[]>[] g  方便一点
     * DFS遍历
     *
     * 需要注意的点是，可连接的一对，不能是同一条路径上的！也就是说答案要从不同的路径里算。
     * 对于一个节点 root
     * 它有c个children，每个children上有Ci个可以被signalSpeed整除的路径花费
     * 那么对于当前节点i，可以从i里选一个，前面遍历过的c里面选一个，凑一对，也就是ci*前面的sum
     * 只看前面统计出来的 这样可以避免重复。
     *
     * 平时习惯的是从ci里选一个，后面的sum里选一个，但是这样比较麻烦。前面的sum在便利的时候就可以得到了。
     * @param edges
     * @param signalSpeed
     * @return
     */
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        visited = new boolean[n];
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]]=edge[2];
            graph[edge[1]][edge[0]]=edge[2];
        }
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            Arrays.fill(visited,false);
            ArrayList<Integer> list = new ArrayList<>();
            visited[i]=true;
            int sum=0;//左边的
            int ways=0;
            for(int j=0;j<graph.length;j++){
                count=0;
                if(graph[i][j]!=0) {
                    visited[j]=true;
                    dfs(j, graph, graph[i][j], signalSpeed);
                    ways=count*sum;
                    sum+=count;
                }
            }
            res[i]=ways;
        }
        return res;
    }
    public void dfs(int index,int[][] graph,int cost,int signalSpeed){
        if(cost>0 && cost%signalSpeed==0)
            count++;
        for(int i=0;i<graph.length;i++){
            if(graph[index][i]!=0 && !visited[i]){
                visited[i]=true;
                dfs(i,graph,cost+graph[index][i],signalSpeed);
                visited[i]=false;
            }
        }
    }

    @Test
    public void test(){
        countPairsOfConnectableServers(new int[][]{{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}},3);
    }
}
