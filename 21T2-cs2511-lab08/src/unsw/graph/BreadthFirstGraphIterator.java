package unsw.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstGraphIterator<N extends Comparable<N>> implements Iterator<N> {
    
    private Graph<N> graph;
    private Set<N> visited;
    private Queue<N> queue;

    public BreadthFirstGraphIterator(Graph<N> graph, N node) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.queue = new LinkedList<>();
        queue.offer(node);
        visited.add(node);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public N next() {
        N node = queue.poll();
        for (N next : graph.getAdjacentNodes(node)) {
            if (!visited.contains(next)) {
                queue.offer(next);
                visited.add(next);
            }
        }
        return node;
    }
    
}
