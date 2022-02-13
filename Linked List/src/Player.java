public class Player {
    private int playerId;
    private int reflexTime;
    private Player nextPlayer;
    private Player prevPlayer;

    public Player(int playerId, int reflexTime) {
        this.playerId = playerId;
        this.reflexTime = reflexTime;
        this.nextPlayer = null;
        this.prevPlayer = null;
    }

    public Player(int playerId, int reflexTime, Player prevPlayer, Player nextPlayer) {
        this(playerId, reflexTime);
        this.nextPlayer = nextPlayer;
        this.prevPlayer = prevPlayer;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getReflexTime() {
        return reflexTime;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Player getPrevPlayer() {
        return prevPlayer;
    }

    public void setPrevPlayer(Player prevPlayer) {
        this.prevPlayer = prevPlayer;
    }
}
