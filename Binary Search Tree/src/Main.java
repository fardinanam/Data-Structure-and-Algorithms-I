import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu: ");
            System.out.println("\t1.  Insert Item\n" +
                    "\t2.  Search Item\n" +
                    "\t3.  Get In Order Successor\n" +
                    "\t4.  Get In Order Predecessor\n" +
                    "\t5.  Delete Item\n" +
                    "\t6.  Get Item Depth\n" +
                    "\t7.  Get Max Item\n" +
                    "\t8.  Get Min Item\n" +
                    "\t9.  Get Height\n" +
                    "\t10. Print In Order\n" +
                    "\t11. Print Pre Order\n" +
                    "\t12. Print Post Order\n" +
                    "\t13. Get Size\n");
            int operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    System.out.println("Item to insert: ");
                    bst.insertItem(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Item to search: ");
                    bst.searchItem(scanner.nextInt());
                    break;
                case 3:
                    System.out.println("In order successor of: ");
                    System.out.println(bst.getInOrderSuccessor(scanner.nextInt()));
                    break;
                case 4:
                    System.out.println("In order predecessor of: ");
                    System.out.println(bst.getInOrderPredecessor(scanner.nextInt()));
                    break;
                case 5:
                    System.out.println("Item to be deleted: ");
                    bst.deleteItem(scanner.nextInt());
                    break;
                case 6:
                    System.out.println("Depth of: ");
                    System.out.println(bst.getItemDepth(scanner.nextInt()));
                    break;
                case 7:
                    try {
                        System.out.println("Max item: " + bst.getMaxItem());
                    } catch (EmptyTreeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        System.out.println("Min item: " + bst.getMinItem());
                    } catch (EmptyTreeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Height: " + bst.getHeight());
                    break;
                case 10:
                    try {
                        bst.printInOrder();
                        System.out.println();
                    } catch (EmptyTreeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        bst.printPreOrder();
                        System.out.println();
                    } catch (EmptyTreeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    try {
                        bst.printPostOrder();
                        System.out.println();
                    } catch (EmptyTreeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 13:
                    System.out.println("Size: " + bst.getSize());
                    break;
            }
        }
    }
}
