import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private final int id;
    private final Set<Vertex> adjList;
    public enum COLOR {WHITE, GRAY, BLACK};
    private COLOR color;
    protected int distanceFromSource; // This has to be initialized as infinity according to dataset max capacity

    public Vertex(int id) {
        this.id = id;
        adjList = new HashSet<>();
        color = COLOR.WHITE;
    }

    public void addAdjacentVertex(Vertex adjVertex) {
        adjList.add(adjVertex);
    }

    public boolean isAdjacent(Vertex v) {
        return adjList.contains(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return this.id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public Set<Vertex> getAdjList() {
        return adjList;
    }
}
