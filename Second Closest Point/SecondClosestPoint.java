import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SecondClosestPoint {
    public static Point[][] findTwoPairsOfClosestPoints(Point[] points, int startIdx, int endIdx) {
        Point[][] twoPairsOfClosestPoints = new Point[2][2];
        Point[] minDistPoints = new Point[2];
        Point[] secondMinDistPoints = new Point[2];

        double minDistance = Double.MAX_VALUE;
        double secondMinDistance = minDistance;

        for(int i = startIdx; i <= endIdx; i++) {
            for(int j = i + 1; j <= endIdx; j++) {
                double distance = points[i].distance(points[j]);
                if(distance == 0) {
                    // do nothing
                } else if(distance < minDistance) {
                    secondMinDistance = minDistance;
                    minDistance = distance;

                    secondMinDistPoints[0] = minDistPoints[0];
                    secondMinDistPoints[1] = minDistPoints[1];

                    minDistPoints[0] = points[i];
                    minDistPoints[1] = points[j];
                } else if(distance < secondMinDistance && distance != minDistance) {
                    secondMinDistance = distance;

                    secondMinDistPoints[0] = points[i];
                    secondMinDistPoints[1] = points[j];
                }
            }
        }

        twoPairsOfClosestPoints[0] = minDistPoints;
        twoPairsOfClosestPoints[1] = secondMinDistPoints;

        return twoPairsOfClosestPoints;
    }

    public static void mergedClosestIncludingMiddleStrip(Point[] strip, int n, Point[][] mergedClosestPoints) {
        double minDistance = mergedClosestPoints[0][0].distance(mergedClosestPoints[0][1]);
        double secondMinDistance = mergedClosestPoints[1][0].distance(mergedClosestPoints[1][1]);

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n && (strip[j].y - strip[i].y) < secondMinDistance; j++) {
                double distance = strip[i].distance(strip[j]);
                if(distance > minDistance) {
                    secondMinDistance = distance;
                    mergedClosestPoints[1][0] = strip[i];
                    mergedClosestPoints[1][1] = strip[j];
                } else if(distance < minDistance){
                    secondMinDistance = minDistance;
                    minDistance = strip[i].distance(strip[j]);

                    mergedClosestPoints[1][0] = mergedClosestPoints[0][0];
                    mergedClosestPoints[1][1] = mergedClosestPoints[0][1];
                    mergedClosestPoints[0][0] = strip[i];
                    mergedClosestPoints[0][1] = strip[j];
                }
            }
        }
    }

    public static Point[][] divideAndFindSecondClosest(Point[] sortedX, Point[] sortedY, int startIxd, int endIdx) {
        int n = endIdx - startIxd + 1;

        // Base case: if n <= 5 then use bruteforce.
        // As the value is at max 5 and the points are sorted, this will run at max 5 + 4 + 3 + 2 = 14 times
        // So, it's O(1)
        if(n <= 5) {
            return findTwoPairsOfClosestPoints(sortedX, startIxd, endIdx);
        }

        int mid = n / 2;
        int midIdx = startIxd + mid;

        Point[] leftSortedY = new Point[mid];
        Point[] rightSortedY = new Point[n - mid];
        Point midPoint = sortedX[midIdx];

        int leftIdx = 0;
        int rightIdx = 0;

        // Divide Step:
        // Dividing the sorted array of points (along y axis) from the two sides of vertical middle line
        // The complexity is O(n)
        for(int i = 0; i < n; i++) {
            if(sortedY[i].x <= midPoint.x && leftIdx < mid) {
                leftSortedY[leftIdx] = sortedY[i];
                leftIdx++;
            } else {
                rightSortedY[rightIdx] = sortedY[i];
                rightIdx++;
            }
        }

        // Conquer Step:
        // Recursively calling the function on the two divided sets of points
        // Complexity is 2 * T(n / 2)
        Point[][] leftClosestPoints = divideAndFindSecondClosest(sortedX, leftSortedY, startIxd, midIdx - 1);
        Point[][] rightClosestPoints = divideAndFindSecondClosest(sortedX, rightSortedY, midIdx, endIdx);

        Point[] allPoints = {leftClosestPoints[0][0], leftClosestPoints[0][1], leftClosestPoints[1][0], leftClosestPoints[1][1],
                            rightClosestPoints[0][0], rightClosestPoints[0][1], rightClosestPoints[1][0], rightClosestPoints[1][1]};

        // Combine step:
        Point[][] mergedClosestPoints = findTwoPairsOfClosestPoints(allPoints, 0, 7);

        double secondMinDist = mergedClosestPoints[1][0].distance(mergedClosestPoints[1][1]);

        Point[] strip = new Point[n];
        int j = 0;

        // Considering possible closer points on the two sides of the vertical division line
        // As the second min distance can only be possible between the two sub sets
        // at a distance smaller than or equal to the present second closest distance on either sides
        // So we will just evaluate those points inside that strip
        // Note: The strip array is taken in the sorted order of points along y axis
        // To make an array of those points will take O(n)
        for(int i = 0; i < n; i++) {
            if(Math.abs(sortedY[i].x - midPoint.x) < secondMinDist) {
                strip[j++] = sortedY[i];
            }
        }

        // Finding if there is any points of distance in the range of current min and second min distance in the strip
        // As the strip is already sorted along y axis this method will run in O(n) complexity
        mergedClosestIncludingMiddleStrip(strip, j, mergedClosestPoints);

        return mergedClosestPoints;
    }

    public static Point[] findSecondClosestPoints(Point[] points, int n) {
        Point[] sortedX = new Point[n];
        Point[] sortedY = new Point[n];

        // Making two copies of same copy to sort them along both the axis
        for(int i = 0; i < n; i++) {
            sortedX[i] = points[i];
            sortedY[i] = points[i];
        }

        // Sorting the arrays
        // Sorting takes O(nlogn) time
        Arrays.sort(sortedX, Comparator.comparingInt(point -> point.x));
        Arrays.sort(sortedY, Comparator.comparingInt(point -> point.y));

        // Initializing the Divide and Conquer approach
        // Time complexity is O(nlogn)
        Point[][] closestPoints = divideAndFindSecondClosest(sortedX, sortedY, 0, n - 1);

        return closestPoints[1];
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("test.txt");
            Scanner scanner = new Scanner(fis);

            int n = scanner.nextInt();
            scanner.nextLine();

            Point[] points = new Point[n];

            for(int i = 0; i < n; i++) {
                String pointStr = scanner.nextLine();

                try {
                    String[] point = pointStr.split(" ");

                    points[i] = new Point(Integer.parseInt(point[0]), Integer.parseInt(point[1]), i);
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                    System.exit(1);
                }
            }

            Point[] secondClosestPoints = findSecondClosestPoints(points, n);
            System.out.println(secondClosestPoints[0].getId() + " " + secondClosestPoints[1].getId());
            System.out.println(secondClosestPoints[0].distance(secondClosestPoints[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}