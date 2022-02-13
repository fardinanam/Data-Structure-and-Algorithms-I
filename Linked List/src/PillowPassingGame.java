import java.util.Locale;
import java.util.Scanner;

public class PillowPassingGame {
    private int length;
    private int totalPlayersCount;
    private Player head;
    private Player currentPlayer;
    private int nextPassingTime;
    private boolean forwardOrder;

    public PillowPassingGame() {
        length = 0;
        totalPlayersCount = 0;
        forwardOrder = true;
    }

    public void addPlayerBeforeGameStarts(int reflexTime) {
        Player newPlayer = new Player(++totalPlayersCount, reflexTime);
        if(length == 0) {
            head = new Player(++length, reflexTime);
            currentPlayer = head;
            nextPassingTime = currentPlayer.getReflexTime();
            head.setPrevPlayer(head);
            head.setNextPlayer(head);
        } else {
            Player temp = head;
            while(temp.getNextPlayer() != head) {
                temp = temp.getNextPlayer();
            }
            newPlayer.setPrevPlayer(temp);
            temp.setNextPlayer(newPlayer);
            newPlayer.setNextPlayer(head);
            head.setPrevPlayer(newPlayer);
        }
        length++;
    }

    public void addPlayerDuringGame(int reflexTime) {
        Player newPlayer = new Player(++totalPlayersCount, reflexTime);
        System.out.println(length);
        if(forwardOrder) {
            newPlayer.setPrevPlayer(currentPlayer.getPrevPlayer());
            newPlayer.setNextPlayer(currentPlayer);
            currentPlayer.setPrevPlayer(newPlayer);
        } else {
            newPlayer.setPrevPlayer(currentPlayer);
            newPlayer.setNextPlayer(currentPlayer.getNextPlayer());
            currentPlayer.setNextPlayer(newPlayer);
        }
        length++;
        System.out.println(newPlayer.getPrevPlayer().getPlayerId() + " " + newPlayer.getPlayerId() + " " + newPlayer.getNextPlayer().getPlayerId());
    }

    public void changeCurrentPlayer(int time) {
        while (time > nextPassingTime) {
            if(forwardOrder) {
                currentPlayer = currentPlayer.getNextPlayer();
            } else {
                currentPlayer = currentPlayer.getPrevPlayer();
            }
            nextPassingTime += currentPlayer.getReflexTime();
        }
    }

    public void reverseDirection() {
        forwardOrder = !forwardOrder;
    }

    public Player eliminatePlayer(int time) {
        Player temp = currentPlayer;
        if(forwardOrder) {
            nextPassingTime = time + currentPlayer.getNextPlayer().getReflexTime();
            currentPlayer = currentPlayer.getNextPlayer(); // Setting the current player to its next player
            currentPlayer.setPrevPlayer(temp.getPrevPlayer()); // Setting the prev player to its prev prev player
            currentPlayer.getPrevPlayer().setNextPlayer(currentPlayer); // Setting the new current player to the next player of its new prev player
        } else {
            nextPassingTime = time + currentPlayer.getPrevPlayer().getReflexTime();
            currentPlayer = currentPlayer.getPrevPlayer();
            currentPlayer.setNextPlayer(temp.getNextPlayer());
            currentPlayer.getNextPlayer().setPrevPlayer(currentPlayer);
        }
        length--;
        temp.setNextPlayer(null);
        temp.setPrevPlayer(null);
        return temp;
    }

    public void endGame(int time) {
        System.out.print("Game over : Player " + currentPlayer.getPlayerId() +
                "is holding the pillow at t=" + time + ", pillow passing sequence = Player ");
        Player temp = currentPlayer;
        if(forwardOrder) {
            while (temp.getNextPlayer() != currentPlayer) {
                System.out.println(temp.getPlayerId() + ", ");
                temp = temp.getNextPlayer();
            }
        } else {
            while (temp.getPrevPlayer() != currentPlayer) {
                System.out.print(temp.getPlayerId() + ", ");
                temp = temp.getPrevPlayer();
            }
        }
        System.out.print(temp.getPlayerId() + "\n");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while(!end) {
            if(length == 1) {
                System.out.println("Game Over : Player " + currentPlayer.getPlayerId() +
                        " wins!!");
                break;
            }

            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            int time = Integer.parseInt(commands[0]);
            String todo = commands[1];

            changeCurrentPlayer(time);

            switch (Character.toUpperCase(todo.charAt(0))) {
                case 'P':
                    System.out.println("Player " + currentPlayer.getPlayerId() +
                            " is holding the pillow at t=" + time);
                    break;
                case 'R':
                    reverseDirection();
                    break;
                case 'I':
                    int reflexTime = Integer.parseInt(commands[2]);
                    addPlayerDuringGame(reflexTime);
                    break;
                case 'M':
                    Player eliminated = eliminatePlayer(time);
                    System.out.println("Player " + eliminated.getPlayerId() +
                            " has been eliminated at t=" + time);
                    break;
                case 'F':
                    endGame(time);
                    end = true;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public static void main(String[] args) {
        int n;
        PillowPassingGame game = new PillowPassingGame();
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            int reflex = scanner.nextInt();
            game.addPlayerBeforeGameStarts(reflex);
        }
        game.start();
    }
}
