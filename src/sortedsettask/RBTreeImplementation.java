package sortedsettask;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is (mostly) an implementation of Red-Black tree from
 * "Introduction to Algorithms" book written by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein
 *
 * @see <a href="http://ressources.unisciel.fr/algoprog/s00aaroot/aa00module1/res/%5BCormen-AL2011%5DIntroduction_To_Algorithms-A3.pdf">"Introduction to Algorithms" book</a>
 */
public class RBTreeImplementation<T extends Comparable<T>> implements Iterable<T> {
    private boolean isDuplicatesAllowed = true;
    private final Node<T> nil = new Node<>(null, Node.Color.BLACK);
    private Node<T> root = nil;
    private int size = 0;

    /**
     * Create an empty Red-Black tree.
     * Duplicates are allowed by default.
     */
    public RBTreeImplementation() {
    }

    /**
     * Create an empty Red-Black tree.
     * Sets duplicates policy.
     *
     * @param isDuplicatesAllowed <code>true</code> if duplicates <b>are</b> allowed,
     *                            <code>false</code> otherwise
     */
    public RBTreeImplementation(boolean isDuplicatesAllowed) {
        this.isDuplicatesAllowed = isDuplicatesAllowed;
    }

    /**
     * We change the pointer structure through rotation, which is a local operation in a search tree that preserves
     * the binary-search-tree property. When we do a left rotation on a node x, we assume that its right child y is not nil;
     * x may be any node in the tree whose right child is not nil. The left rotation "pivots" around the link from x to y.
     * It makes y the new root of the subtree, with @param x as y's left child and y's left child as x's right child.
     * Both leftRotate and rightRotate run in O(1) time.
     * Only  pointers  are  changed  by  a  rotation;  all  other attributes in a node remain the same.
     *
     * @param x
     */
    private void leftRotate(Node<T> x) {
        Node<T> y = x.rightChild;
        x.rightChild = y.leftChild;
        if (y.leftChild != nil) {
            y.leftChild.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else {
            if (x == x.parent.leftChild) {
                x.parent.leftChild = y;
            } else {
                x.parent.rightChild = y;
            }
        }
        y.leftChild = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.leftChild;
        x.leftChild = y.rightChild;
        if (y.rightChild != nil) {
            y.rightChild.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else {
            if (x == x.parent.rightChild) {
                x.parent.rightChild = y;
            } else {
                x.parent.leftChild = y;
            }
        }
        y.rightChild = x;
        x.parent = y;
    }

    private boolean rbInsert(Node<T> z) {
        Node<T> y = nil;
        Node<T> x = root;
        while (x != nil) {
            y = x;
            if (z.data.compareTo(x.data) < 0) {
                x = x.leftChild;
            } else {
                if (!isDuplicatesAllowed && z.data.compareTo(x.data) == 0) {
                    return false;
                } else {
                    x = x.rightChild;
                }
            }
        }
        z.parent = y;
        if (y == nil) {
            root = z;
        } else {
            if (z.data.compareTo(y.data) < 0) {
                y.leftChild = z;
            } else {
                y.rightChild = z;
            }
        }
        z.leftChild = nil;
        z.rightChild = nil;
        z.color = Node.Color.RED;
        rbInsertFixup(z);
        ++size;
        return true;
    }

    private void rbInsertFixup(Node<T> z) {
        while (z.parent.color == Node.Color.RED) {
            if (z.parent == z.parent.parent.leftChild) {
                Node<T> y = z.parent.parent.rightChild;
                if (y.color == Node.Color.RED) {
                    z.parent.color = Node.Color.BLACK;
                    y.color = Node.Color.BLACK;
                    z.parent.parent.color = Node.Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.rightChild) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Node.Color.BLACK;
                    z.parent.parent.color = Node.Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                if (z.parent == z.parent.parent.rightChild) {
                    Node<T> y = z.parent.parent.leftChild;
                    if (y.color == Node.Color.RED) {
                        z.parent.color = Node.Color.BLACK;
                        y.color = Node.Color.BLACK;
                        z.parent.parent.color = Node.Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.leftChild) {
                            z = z.parent;
                            rightRotate(z);
                        }
                        z.parent.color = Node.Color.BLACK;
                        z.parent.parent.color = Node.Color.RED;
                        leftRotate(z.parent.parent);
                    }
                }
            }
        }
        root.color = Node.Color.BLACK;
    }

    private void rbTransplant(Node<T> u, Node<T> v) {
        if (u.parent == nil) {
            root = v;
        } else {
            if (u == u.parent.leftChild) {
                u.parent.leftChild = v;
            } else {
                u.parent.rightChild = v;
            }
        }
        v.parent = u.parent;
    }

    private void rbDelete(Node<T> z) {
        Node<T> y = z;
        Node<T> x;
        Node.Color yOriginalColor = y.color;
        if (z.leftChild == nil) {
            x = z.rightChild;
            rbTransplant(z, z.rightChild);
        } else {
            if (z.rightChild == nil) {
                x = z.leftChild;
                rbTransplant(z, z.leftChild);
            } else {
                y = minimum(z.rightChild);
                yOriginalColor = y.color;
                x = y.rightChild;
                if (y.parent == z) {
                    x.parent = z;
                } else {
                    rbTransplant(y, y.rightChild);
                    y.rightChild = z.rightChild;
                    y.rightChild.parent = y;
                }
                rbTransplant(z, y);
                y.leftChild = z.leftChild;
                y.leftChild.parent = y;
                y.color = z.color;
            }
        }
        if (yOriginalColor == Node.Color.BLACK) {
            rbDeleteFixup(x);
        }
        --size;
    }

    private void rbDeleteFixup(Node<T> x) {
        while (x == root && x.color == Node.Color.BLACK) {
            if (x == x.parent.leftChild) {
                Node<T> w = x.parent.rightChild;
                if (w.color == Node.Color.RED) {
                    w.color = Node.Color.BLACK;
                    x.parent.color = Node.Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.rightChild;
                }
                if (w.leftChild.color == Node.Color.BLACK && w.rightChild.color == Node.Color.BLACK) {
                    w.color = Node.Color.RED;
                    x = x.parent;
                } else {
                    if (w.rightChild.color == Node.Color.BLACK) {
                        w.leftChild.color = Node.Color.BLACK;
                        w.color = Node.Color.RED;
                        rightRotate(w);
                        w = x.parent.rightChild;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Node.Color.BLACK;
                    w.rightChild.color = Node.Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node<T> w = x.parent.leftChild;
                if (w.color == Node.Color.RED) {
                    w.color = Node.Color.BLACK;
                    x.parent.color = Node.Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.leftChild;
                }
                if (w.rightChild.color == Node.Color.BLACK && w.leftChild.color == Node.Color.BLACK) {
                    w.color = Node.Color.RED;
                    x = x.parent;
                } else {
                    if (w.leftChild.color == Node.Color.BLACK) {
                        w.rightChild.color = Node.Color.BLACK;
                        w.color = Node.Color.RED;
                        leftRotate(w);
                        w = x.parent.leftChild;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Node.Color.BLACK;
                    w.leftChild.color = Node.Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
            x.color = Node.Color.BLACK;
        }
    }

    private void inOrderTreeWalk(Node<T> x) {
        if (x != nil) {
            inOrderTreeWalk(x.leftChild);
            System.out.println(x.data);
            inOrderTreeWalk(x.rightChild);
        }
    }

    private Node<T> minimum(Node<T> x) {
        while (x.leftChild != nil) {
            x = x.leftChild;
        }
        return x;
    }

    private Node<T> iterativeSearch(Node<T> x, T data) {
        while (x != nil && data != x.data) {
            if (data.compareTo(x.data) < 0) {
                x = x.leftChild;
            } else {
                x = x.rightChild;
            }
        }
        return x;
    }

    /**
     * Adds the specified element to this tree if it is not already present.
     *
     * @param data data to be inserted into this tree
     * @return <code>false</code> if duplicates are not allowed AND if this tree already contains the specified data,
     * <code>true</code> otherwise
     * @throws IllegalArgumentException - if the specified data is null
     */
    public boolean add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return rbInsert(new Node<>(data));
    }

    /**
     * Removes the specified data from this tree if it is present.
     *
     * @param data data to be removed from this tree if present.
     * @return <code>true</code> if this tree contains the specified data,
     * <code>false</code> otherwise
     * @throws IllegalArgumentException - if the specified data is null
     */
    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> nodeToRemove = iterativeSearch(root, data);
        if (nodeToRemove == nil)
            return false;
        else {
            rbDelete(nodeToRemove);
            return true;
        }
    }

    /**
     * Checks if this tree contains specified data.
     *
     * @param data data to be examined
     * @return <code>true</code> if this tree contains specified data,
     * <code>false</code> otherwise
     * @throws IllegalArgumentException - if the specified data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return iterativeSearch(root, data) != nil;
    }

    /**
     * Returns the number of elements in this tree.
     *
     * @return the number of elements in this tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Prints tree's data. Traversing type: inOrder.
     */
    public void print() {
        inOrderTreeWalk(root);
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<T> {
        private Node<T> next;

        public TreeIterator() {
            next = root;
            if(next == nil)
                return;

            while (next.leftChild != nil)
                next = next.leftChild;
        }

        public boolean hasNext(){
            return next != nil;
        }

        public T next(){
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> current = next;
            if(next.rightChild != nil) {
                next = next.rightChild;
                while (next.leftChild != nil)
                    next = next.leftChild;
                return current.data;
            }
            while(true) {
                if(next.parent == nil) {
                    next = nil;
                    return current.data;
                }
                if(next.parent.leftChild == next) {
                    next = next.parent;
                    return current.data;
                }
                next = next.parent;
            }
        }
    }

    /**
     * This class is a basic unit of a Red-Black tree data structure.
     */
    private static final class Node<T> {
        T data;
        Node<T> leftChild;
        Node<T> rightChild;
        Node<T> parent;
        Color color;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Color color) {
            this.data = data;
            this.color = color;
        }

        private enum Color {
            RED,
            BLACK
        }
    }
}
