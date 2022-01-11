package chapter3.part3;

import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
    private static int[] dijkstra(Graph graph, int startIndex) {
        //图的顶点数量
        int size = graph.vertices.length;
        //创建距离表，存储从起点到每一个顶点的临时距离
        int[] distances = new int[size];
        //记录顶点遍历状态
        boolean[] access = new boolean[size];
        //初始化最短路径表，到达每个顶点的路径代价默认为无穷大
        for (int i = 1; i < size; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        //遍历起点，刷新距离表
        access[0] = true;
        List<Edge> edgesFromStart = graph.adj[startIndex];
        for (Edge edge : edgesFromStart) {
            distances[edge.index] = edge.weight;
        }
        //主循环，重复遍历最短距离顶点和刷新距离表的操作
        for (int i = 1; i < size; i++) {
            //寻找最短距离顶点
            int minDistanceFromStart = Integer.MAX_VALUE;
            int minDistanceIndex = -1;
            for (int j = 1; j < size; j++) {
                if (!access[j] && (distances[j] < minDistanceFromStart)) {
                    minDistanceFromStart = distances[j];
                    minDistanceIndex = j;
                }
            }
            if (minDistanceIndex == -1) {
                break;
            }
            //遍历顶点，刷新距离表
            access[minDistanceIndex] = true;
            for (Edge edge : graph.adj[minDistanceIndex]) {
                if (access[edge.index]) {
                    continue;
                }
                int weight = edge.weight;
                int preDistance = distances[edge.index];
                if (weight != Integer.MAX_VALUE && minDistanceFromStart + weight < preDistance) {
                    distances[edge.index] = minDistanceFromStart + weight;
                }
            }
        }

        return distances;
    }

    private static void initGraph(Graph graph) {
        graph.vertices[0] = new Vertex("A");
        graph.vertices[1] = new Vertex("B");
        graph.vertices[2] = new Vertex("C");
        graph.vertices[3] = new Vertex("D");
        graph.vertices[4] = new Vertex("E");
        graph.vertices[5] = new Vertex("F");
        graph.vertices[6] = new Vertex("G");
        graph.adj[0].add(new Edge(1, 5));
        graph.adj[0].add(new Edge(2, 2));
        graph.adj[1].add(new Edge(0, 5));
        graph.adj[1].add(new Edge(3, 1));
        graph.adj[1].add(new Edge(4, 6));
        graph.adj[2].add(new Edge(0, 2));
        graph.adj[2].add(new Edge(3, 6));
        graph.adj[2].add(new Edge(5, 8));
        graph.adj[3].add(new Edge(1, 1));
        graph.adj[3].add(new Edge(2, 6));
        graph.adj[3].add(new Edge(4, 1));
        graph.adj[3].add(new Edge(5, 2));
        graph.adj[4].add(new Edge(1, 6));
        graph.adj[4].add(new Edge(3, 1));
        graph.adj[4].add(new Edge(6, 7));
        graph.adj[5].add(new Edge(2, 8));
        graph.adj[5].add(new Edge(3, 2));
        graph.adj[5].add(new Edge(6, 3));
        graph.adj[6].add(new Edge(4, 7));
        graph.adj[6].add(new Edge(5, 3));
    }

    //图的顶点
    private static class Vertex {
        String data;

        Vertex(String data) {
            this.data = data;
        }
    }

    //图的边
    private static class Edge {
        int index;
        int weight;

        Edge(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    //使用邻接表存储图
    private static class Graph {
        private Vertex[] vertices;
        private LinkedList<Edge>[] adj;

        Graph(int size) {
            //初始化顶点和邻接链表
            vertices = new Vertex[size];
            adj = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                adj[i] = new LinkedList<>();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        initGraph(graph);
        int[] distances = dijkstra(graph, 0);
        System.out.println(distances[6]);
    }
}