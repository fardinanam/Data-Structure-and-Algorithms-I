public class Point {
    public int x;
    public int y;
    private int id;

    public Point(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double distance(Point other) {
        int xDiff = x - other.x;
                int yDiff = y - other.y;
        return Math.pow(xDiff * xDiff + yDiff * yDiff, 0.5);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
