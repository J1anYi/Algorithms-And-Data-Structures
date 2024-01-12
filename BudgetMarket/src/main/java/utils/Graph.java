package utils;

public class Graph
{
    public class Node implements Comparable
    {
        private String info;
        private Vector edges;
        
        public Node(String label)
        {
            info = label;
            edges = new Vector();
        }
        
        public void addEdge(Edge e)
        {
            edges.addLast(e);
        }
        
        public int compareTo(Object o)
        {
            // two nodes are equal if they have the same label
            Node n = (Node)o;
            return n.info.equals(info) ? 0 : 1;
        }

        private Vector getEdges()
        {
            return edges;
        }

        /**
         * calculate the distance between two nodes
         * using BFS
         * @param target
         * @return
         */
        public int calculateDistance(Node target)
        {
            // same node
            if (this == target) {
                return 0;
            }

            Vector<Edge> queue = new Vector<>();
            Dictionary distanceMap = new Dictionary();
            Vector<Node> visited = new Vector<>();

            // add the start node to the queue
            queue.add(new Edge(this, 0));
            visited.add(this);
            distanceMap.add(this, 0);

            while (queue.size() > 0) {
                Edge edge = queue.remove(0);
                Node currentNode = edge.toNode;
                int currentDistance = edge.distance;

                // find the target node
                if (currentNode == target) {
                    return currentDistance;
                }

                Vector<Edge> edges = currentNode.getEdges();
                for (int i = 0; i < edges.size(); i++) {
                    Object edgeObject = edges.get(i);
                    Edge adjacentEdge = (Edge) edgeObject;
                    Node adjacentNode = adjacentEdge.toNode;

                    if (!visited.contains(adjacentNode)) {
                        visited.add(adjacentNode);
                        int newDistance = currentDistance + adjacentEdge.distance;
                        distanceMap.add(adjacentNode, newDistance);
                        queue.add(new Edge(adjacentNode, newDistance));
                    }
                }
            }

            // cannot find the target node
            return -1;
        }
        
        public String getLabel()
        {
            return info;
        }
        
    }
    
    private class Edge implements Comparable
    {
        private Node toNode;
        private int distance;
        
        public Edge(Node to, int distance)
        {
            toNode = to;
            this.distance = distance;
        }
        
        public int compareTo(Object o)
        {
            // two edges are equal if they point
            // to the same node.
            // this assumes that the edges are
            // starting from the same node !!!
            Edge n = (Edge)o;
            return n.toNode.compareTo(toNode);
        }
    }
    
    private Vector nodes;
    
    public Graph()
    {
        nodes = new Vector();
    }
    
    public void addNode(String label)
    {
        if (containsNode(label)) {
            return;
        }
        nodes.addLast(new Node(label));
    }

    public boolean containsNode(String label)
    {
        return nodes.contains(new Node(label.toString()));
    }
    
    private Node findNode(String nodeLabel)
    {
        Node res = null;
        for (int i=0; i<nodes.size(); i++)
        {
            Node n = (Node)nodes.get(i);
            if(n.getLabel().equals(nodeLabel))
            {
                res = n;
                break;
            }
        }
        return res;
    }
    
    public void addEdge(String nodeLabel1,
                        String nodeLabel2, int distance)
    {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        n1.addEdge(new Edge(n2, distance));
        n2.addEdge(new Edge(n1, distance));
    }

    public Node getNode(String label)
    {
        return findNode(label);
    }

    public int getDistance(String nodeLabel1,
                           String nodeLabel2)
    {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        return n1.calculateDistance(n2);
    }

}
