public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insertItem(int item) {
        this.root = insertItemAtSubTree(this.root, item);
    }

    private Node insertItemAtSubTree(Node root, int item) {
        if(root == null) {
            return new Node(item);
        }

        if(item < root.getValue()) {
            root.setLeftChild(insertItemAtSubTree(root.getLeftChild(), item));
        } else if(item > root.getValue()) {
            root.setRightChild(insertItemAtSubTree(root.getRightChild(), item));
        } else {
            System.out.println(item + " is already in the tree");
        }
        return root;
    }

    public boolean searchItem(int item) {
        return searchItemFromSubTree(this.root, item);
    }

    private boolean searchItemFromSubTree(Node root, int item) {
        if(root == null) {
            System.out.println(item + " has not been found");
            return false;
        } else if(item == root.getValue()) {
            System.out.println(item + " has been found");
            return true;
        } else if(item < root.getValue()) {
            return searchItemFromSubTree(root.getLeftChild(), item);
        } else {
            return searchItemFromSubTree(root.getRightChild(), item);
        }
    }

    public int getInOrderSuccessor(int item) {
        Node parent = this.root;
        Node lastAncestorOfLeftChild = null;
        while (true) {
            if(parent == null) {
                break;
            }
            int parentValue = parent.getValue();

            if(item < parentValue) {
                lastAncestorOfLeftChild = parent;
                parent = parent.getLeftChild();
            } else if(item > parentValue) {
                parent = parent.getRightChild();
            } else if(item == parentValue) {
                if(parent.getRightChild() != null) {
                    return minFromSubTree(parent.getRightChild()).getValue();
                } else if(lastAncestorOfLeftChild != null){
                    return lastAncestorOfLeftChild.getValue();
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public int getInOrderPredecessor(int item) {
        Node parent = this.root;
        Node lastAncestorOfRightChild = null;
        while (true) {
            if(parent == null) {
                break;
            }
            int parentValue = parent.getValue();

            if(item < parentValue) {
                parent = parent.getLeftChild();
            } else if(item > parentValue) {
                lastAncestorOfRightChild = parent;
                parent = parent.getRightChild();
            } else if(item == parentValue) {
                if(parent.getLeftChild() != null) {
                    return maxFromSubTree(parent.getLeftChild()).getValue();
                } else if(lastAncestorOfRightChild != null){
                    return lastAncestorOfRightChild.getValue();
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public void deleteItem(int item) {
        this.root = deleteItemFromSubTree(this.root, item);
    }

    private Node deleteItemFromSubTree(Node root, int item) {
        if(root == null) {
            return null;
        } else if(item < root.getValue()) {
            root.setLeftChild(deleteItemFromSubTree(root.getLeftChild(), item));
        } else if(item > root.getValue()) {
            root.setRightChild(deleteItemFromSubTree(root.getRightChild(), item));
        } else if(item == root.getValue()){
            if(root.isLeaf()) {
                // If the subtree has no child then it can safely be deleted and
                // returning null means it's parent will allocate null to its corresponding child position.
                return null;
            } else if(root.getRightChild() == null) {
                // If it has only the left child then returning left child will automatically set the
                // node as a child of the root's parent
                return root.getLeftChild();
            } else if(root.getLeftChild() == null) {
                // If it has only the right child then returning right child will automatically set the
                // node as a child of the root's parent
                return root.getRightChild();
            } else {
                // If it has two children then the max value from the left sub tree will be the next largest number
                // Find the max node of left subtree
                // Allocate the children of the root node to the children of the max node
                Node nextLargerNode = root.getRightChild();
                Node parentOfNextLargerNode = root;

                while (nextLargerNode.getLeftChild() != null) {
                    parentOfNextLargerNode = nextLargerNode;
                    nextLargerNode = nextLargerNode.getLeftChild();
                }
                // The nextLargerNode doesn't have a right child
                // So allocate the left child of nextLargerNode to the right of its parent (in place of the maxNode)
                // Unless the root itself is the parent of nextLargerNode
                if(parentOfNextLargerNode == root) {
                    parentOfNextLargerNode.setRightChild(nextLargerNode.getRightChild());
                } else {
                    parentOfNextLargerNode.setLeftChild(nextLargerNode.getRightChild());
                }
                root.setValue(nextLargerNode.getValue());
            }
        }
        return root;
    }

    public int getItemDepth(int item) {
        if(this.root == null) {
            return -1;
        }
        int itemDepth = 0;
        Node nextSubTreeRoot = this.root;

        while (true) {
            if(item == nextSubTreeRoot.getValue()) {
                return itemDepth;
            } else if(item < nextSubTreeRoot.getValue() && nextSubTreeRoot.getLeftChild() != null) {
                itemDepth++;
                nextSubTreeRoot = nextSubTreeRoot.getLeftChild();
            } else if(item > nextSubTreeRoot.getValue() && nextSubTreeRoot.getRightChild() != null) {
                itemDepth++;
                nextSubTreeRoot = nextSubTreeRoot.getRightChild();
            } else {
                return -1;
            }
        }
    }

    public int getMaxItem() throws EmptyTreeException{
        if(this.root == null) {
            throw new EmptyTreeException();
        }

        return maxFromSubTree(this.root).getValue();
    }

    private Node maxFromSubTree(Node root) {
        while (root.getRightChild() != null) {
            root = root.getRightChild();
        }
        return root;
    }

    public int getMinItem() throws EmptyTreeException {
        if(this.root == null) {
            throw new EmptyTreeException();
        }

        return minFromSubTree(this.root).getValue();
    }

    private Node minFromSubTree(Node root) {
        while (root.getLeftChild() != null) {
            root = root.getLeftChild();
        }
        return root;
    }

    public int getHeight() {
        if(this.root == null) {
            return -1;
        }
        return heightOfSubTree(this.root);
    }

    private int heightOfSubTree(Node root) {
        if(root.isLeaf()) {
            return 0;
        } else if(root.getRightChild() == null) {
            return heightOfSubTree(root.getLeftChild()) + 1;
        } else if(root.getLeftChild() == null) {
            return heightOfSubTree(root.getRightChild()) + 1;
        } else {
            int leftChildHeight = heightOfSubTree(root.getLeftChild());
            int rightChildHeight = heightOfSubTree(root.getRightChild());

            return leftChildHeight > rightChildHeight ?
                    leftChildHeight + 1 : rightChildHeight + 1;
        }
    }

    public void printInOrder() throws EmptyTreeException {
        if(root == null) {
            throw new EmptyTreeException();
        }
        printInOrderOfSubTree(this.root);
    }

    private void printInOrderOfSubTree(Node root) {
        if(root.isLeaf()) {
            System.out.print(root.getValue() + " ");
            return;
        }

        if(root.getLeftChild() != null) {
            printInOrderOfSubTree(root.getLeftChild());
        }
        System.out.print(root.getValue() + " ");
        if(root.getRightChild() != null) {
            printInOrderOfSubTree(root.getRightChild());
        }
    }

    public void printPreOrder() throws EmptyTreeException {
        if(root == null) {
            throw new EmptyTreeException();
        }
        printPreOrderOfSubTree(this.root);
    }

    private void printPreOrderOfSubTree(Node root) {
        if(root.isLeaf()) {
            System.out.print(root.getValue() + " ");
            return;
        }

        System.out.print(root.getValue() + " ");
        if(root.getLeftChild() != null) {
            printPreOrderOfSubTree(root.getLeftChild());
        }
        if(root.getRightChild() != null) {
            printPreOrderOfSubTree(root.getRightChild());
        }
    }

    public void printPostOrder() throws EmptyTreeException {
        if(root == null) {
            throw new EmptyTreeException();
        }
        printPostOrderOfSubTree(this.root);
    }

    private void printPostOrderOfSubTree(Node root) {
        if(root.isLeaf()) {
            System.out.print(root.getValue() + " ");
            return;
        }

        if(root.getLeftChild() != null) {
            printPostOrderOfSubTree(root.getLeftChild());
        }
        if(root.getRightChild() != null) {
            printPostOrderOfSubTree(root.getRightChild());
        }
        System.out.print(root.getValue() + " ");
    }

    public int getSize() {
        if(root == null) {
            return 0;
        }
        return getSizeOfSubTree(this.root);
    }

    private int getSizeOfSubTree(Node root) {
        if(root.isLeaf()) {
            return 1;
        }

        int size = 1;
        Node leftChild = root.getLeftChild();
        Node rightChild = root.getRightChild();
        if(leftChild != null) {
            size += getSizeOfSubTree(leftChild);
        }
        if(rightChild != null) {
            size += getSizeOfSubTree(rightChild);
        }
        return size;
    }
}
