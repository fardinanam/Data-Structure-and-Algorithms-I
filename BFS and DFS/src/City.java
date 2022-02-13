public class City extends Vertex{
    private int noOfPieces;

    public City(int id) {
        super(id);
        this.noOfPieces = 0;
    }

    public void setNoOfPieces(int noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public int getNoOfPieces() {
        return noOfPieces;
    }
}
