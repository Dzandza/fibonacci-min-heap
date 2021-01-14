package algorithms.fibonacci_min_heap;

public class Node<Type extends Comparable>{
    private Node<Type> parent, left, right, child;
    private Type value;
    private int degree = 0;
    private boolean isMarked = false;


    public Node(Type value) {
        this.value = value;
    }

    public Node(Type value, Node<Type> parent, Node<Type> left, Node<Type> right, Node<Type> child, int degree, boolean isMarked) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.child = child;
        this.value = value;
        this.degree = degree;
        this.isMarked = isMarked;
    }

    public Node<Type> getParent() {
        return parent;
    }

    public void setParent(Node<Type> parent) {
        this.parent = parent;
    }

    public Node<Type> getLeft() {
        return left;
    }

    public void setLeft(Node<Type> left) {
        this.left = left;
    }

    public Node<Type> getRight() {
        return right;
    }

    public void setRight(Node<Type> right) {
        this.right = right;
    }

    public Node<Type> getChild() {
        return child;
    }

    public void setChild(Node<Type> child) {
        this.child = child;
    }

    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
