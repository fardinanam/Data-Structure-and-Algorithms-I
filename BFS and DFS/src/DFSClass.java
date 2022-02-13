import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DFSClass {
    private static int DFSVisit(City source) {
        int piecesFound = source.getNoOfPieces();
        source.setColor(Vertex.COLOR.GRAY);

        for(Vertex currentCity : source.getAdjList()) {
            if(currentCity.getColor() == Vertex.COLOR.WHITE) {
                piecesFound += DFSVisit((City) currentCity);
            }
        }
        source.setColor(Vertex.COLOR.BLACK);
        return piecesFound;
    }
    public static int piecesFoundByAFriendByDFS(City source) {
        int piecesFound = 0;
        if(source.getColor() == Vertex.COLOR.WHITE) {
            piecesFound += DFSVisit(source);
        }
        return piecesFound;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int C, R, F, L, totalPieces = 0, piecesFound = 0;


        String firstLine = scanner.nextLine();
        String[] CRLF = firstLine.split(" ");
        C = Integer.parseInt(CRLF[0]);
        R = Integer.parseInt(CRLF[1]);
        L = Integer.parseInt(CRLF[2]);
        F = Integer.parseInt(CRLF[3]);

        City[] cities = new City[C];
        int[] startingCitiesOfFriends = new int[F];
        int[] collectedPiecesByFriends = new int[F];

        for(int i = 0; i < C; i++) {
            cities[i] = new City(i);
        }

        for(int i = 0; i < R; i++) {
            String edgeVertices = scanner.nextLine();
            String[] edgeVerticesIDs = edgeVertices.split(" ");
            int C1 = Integer.parseInt(edgeVerticesIDs[0]);
            int C2 = Integer.parseInt(edgeVerticesIDs[1]);

            cities[C1].addAdjacentVertex(cities[C2]);
            cities[C2].addAdjacentVertex(cities[C1]);
        }

        for(int i = 0; i < L; i++) {
            String cityPieces = scanner.nextLine();
            String[] noOfPiecesInCities = cityPieces.split(" ");
            int Cx = Integer.parseInt(noOfPiecesInCities[0]);
            int Px = Integer.parseInt(noOfPiecesInCities[1]);

            cities[Cx].setNoOfPieces(Px);
            totalPieces += Px;
        }

        for(int i = 0; i < F; i++) {
            String startingCityOfFriends = scanner.nextLine();
            String[] startingCityIdOfFriend = startingCityOfFriends.split(" ");
            int Cy = Integer.parseInt(startingCityIdOfFriend[0]);
            int Fy = Integer.parseInt(startingCityIdOfFriend[1]);

            startingCitiesOfFriends[Fy] = Cy;
        }

        for(int i = 0; i < F; i++) {
            collectedPiecesByFriends[i] = piecesFoundByAFriendByDFS(cities[startingCitiesOfFriends[i]]);
            piecesFound += collectedPiecesByFriends[i];
        }

        try {
            FileWriter fw = new FileWriter("DFSResult.txt");
            if(piecesFound < totalPieces) {
//                System.out.println("Mission Impossible");
                fw.append("Mission Impossible\n");
            } else {
//                System.out.println("Mission Accomplished");
                fw.append("Mission Accomplished\n");
            }

//            System.out.println(piecesFound + " out of " + totalPieces + " pieces are collected");
            fw.append(piecesFound + " out of " + totalPieces + " pieces are collected\n");
            for(int i = 0; i < F; i++) {
//                System.out.println(i + " collected " + collectedPiecesByFriends[i] + " pieces");
                fw.append(i + " collected " + collectedPiecesByFriends[i] + " pieces\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

