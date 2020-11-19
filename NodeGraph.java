import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class NodeGraph {
    class Node {
        double x;
        double y;
        boolean visited = false;

        public Node () {
            Random random = new Random();
            this.x = random.nextDouble() * 100;
            this.y = random.nextDouble() * 100;
        }

        public Node (double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Node[] nodes;

    public NodeGraph (int num) {
        nodes = new Node[num];
        for (int i=0; i<num; i++) {
            nodes[i] = new Node();
            System.out.println("Node " + (i+1) + " (" + nodes[i].x + ", " + nodes[i].y + ")" );
        }

        Random rand = new Random();
        int i = rand.nextInt(num);
        nodes[i].visited = true;

        LinkedList<Integer> order = new LinkedList<>();
        order.add(i+1);

        double shortestDistance = 0;
        boolean allVisited = false;
        do {
            double minDistance = 999999999;
            
            for (int j=0; j<num; j++) {
                if (!nodes[j].visited) {
                    double distance = findDistance(nodes[i], nodes[j]);
                    if (minDistance > distance) {
                        minDistance = distance;
                        i = j;
                    }
                }
            }
            nodes[i].visited = true;
            order.add(i+1);
            shortestDistance += minDistance;

            for (int t=0; t<num; t++) {
                if (nodes[t].visited == true) {
                    allVisited = true;
                }
                else if (nodes[t].visited == false) {
                    allVisited = false;
                    break;
                }
            }
        }
        while(!allVisited);
        System.out.println();
        System.out.println("Distance of shortest path: " + shortestDistance);
        System.out.print("Shortest path: " + order.pop());
        for (int x : order){
            System.out.print(" -> " + x);
        }
    }

    public double findDistance (Node A, Node B) {
        return (Math.sqrt(Math.pow((A.x-B.x),2) + Math.pow(A.y-B.y,2)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int numNode = scanner.nextInt();
        NodeGraph graph = new NodeGraph(numNode);
        scanner.close();
    }
}
