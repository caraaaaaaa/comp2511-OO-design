package unsw.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class DepthFirstGraphIterator<N extends Comparable<N>> implements Iterator<N> {
    private Graph<N> graph;
    private Set<N> visited = new HashSet<>();
    private Stack<Iterator<N>> stack = new Stack<>();
    private N next;

    public DepthFirstGraphIterator(Graph<N> g, N node) {
        this.graph = g;
        visited.add(node);
        this.stack.push(g.getAdjacentNodes(node).iterator());
        this.next = node;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return !this.stack.isEmpty();
    }

    @Override
    public N next() {
        // TODO Auto-generated method stub
        try {
            this.visited.add(this.next);
            return this.next;
        } finally {
            this.advance();
        }
    }
    

    private void advance() {
        Iterator<N> neighbors = this.stack.peek();

        while (this.visited.contains(this.next)) {
            while (!neighbors.hasNext()) {
                this.stack.pop();

                if (!this.hasNext()) {
                    this.next = null;
                    return;
                }

                neighbors = this.stack.peek();
            }
            
            this.next = neighbors.next();
        }

        this.stack.push(this.graph.getAdjacentNodes(this.next).iterator());
    }
}
