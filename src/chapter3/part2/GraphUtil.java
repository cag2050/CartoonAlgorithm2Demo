package chapter3.part2;

import java.util.LinkedList;

public class GraphUtil {
    //深度优先遍历
    private static void depthFirstSearch(Graph graph, int start, boolean[] visited) {
        System.out.println(graph.vertexes[start].data);
        visited[start] = true;
        for (int index : graph.adj[start]) {
            if (!visited[index]) {
                depthFirstSearch(graph, index, visited);
            }
        }
    }

    //广度优先遍历
    private static void breadthFirstSearch(Graph graph, int start, boolean[] visited, LinkedList<Integer> queue) {
        //加入到队列尾部
        queue.offer(start);
        while (!queue.isEmpty()) {
            //得到队列头节点
            int front = queue.poll();
            if (visited[front]) {
                continue;
            }
            System.out.println(graph.vertexes[front].data);
            visited[front] = true;
            for (int index : graph.adj[front]) {
                queue.offer(index);
            }
        }

    }

    //图的顶点
    private static class Vertex {
        int data;

        Vertex(int data) {
            this.data = data;
        }
    }

    private static class Graph {
        int size;
        //顶点
        Vertex[] vertexes;
        //每个顶点对应的链表
        LinkedList<Integer>[] adj;

        Graph(int size) {
            this.size = size;
            //初始化顶点和邻接矩阵
            vertexes = new Vertex[size];
            adj = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                vertexes[i] = new Vertex(i);
                adj[i] = new LinkedList();
            }
        }
    }

    public static void main(String[] args) {
        //初始化3.2.2里面的图
        Graph graph = new Graph(11);
        graph.adj[0].add(1);
        graph.adj[0].add(2);
        graph.adj[0].add(3);
        graph.adj[0].add(4);
        graph.adj[1].add(0);
        graph.adj[1].add(4);
        graph.adj[1].add(7);
        graph.adj[1].add(9);
        graph.adj[2].add(0);
        graph.adj[3].add(0);
        graph.adj[3].add(5);
        graph.adj[3].add(6);
        graph.adj[4].add(0);
        graph.adj[4].add(1);
        graph.adj[4].add(5);
        graph.adj[5].add(3);
        graph.adj[5].add(4);
        graph.adj[6].add(3);
        graph.adj[7].add(1);
        graph.adj[7].add(8);
        graph.adj[7].add(10);
        graph.adj[8].add(7);
        graph.adj[9].add(1);
        graph.adj[10].add(7);
        System.out.println("图的深度优先遍历：");
        depthFirstSearch(graph, 0, new boolean[graph.size]);
        System.out.println("图的广度优先遍历：");
        breadthFirstSearch(graph, 0, new boolean[graph.size], new LinkedList<>());
    }
}
