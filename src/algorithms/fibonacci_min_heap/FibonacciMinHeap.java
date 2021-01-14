package algorithms.fibonacci_min_heap;

import java.util.ArrayList;

public class FibonacciMinHeap<Type extends Comparable> {
    private Node<Type> minNode = null;
    private int numberOfNodes = 0;
    private Type lowestValue;

    private void print(Node<Type> currentNode, StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);

        if (currentNode != null) {
            Node<Type> node = currentNode;
            do {

                buffer.append(node.getValue() + "\n");
                if (node.getRight() != currentNode) {
                    print(node.getChild(), buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                    buffer.append(childrenPrefix);
                } else print(node.getChild(), buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
                node = node.getRight();
            } while (node != currentNode);
        } else {
            buffer.append("\n");
        }
    }


    private void linkNodes(Node<Type> parent, Node<Type> child) {
        if (child == minNode) minNode = parent;
        child.getLeft().setRight(child.getRight());
        child.getRight().setLeft(child.getLeft());

        child.setParent(parent);
        parent.setDegree(parent.getDegree() + 1);

        if (parent.getChild() == null) {
            child.setLeft(child);
            child.setRight(child);
            parent.setChild(child);
        } else {
            Node<Type> existingChild = parent.getChild();

            child.setRight(existingChild);
            child.setLeft(existingChild.getLeft());

            existingChild.getLeft().setRight(child);
            existingChild.setLeft(child);
        }

        child.setMarked(false);
    }

    private void consolidate() {
        ArrayList<Node<Type>> A = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++)
            A.add(null);

        Node<Type> w = minNode;
        do {
            Node<Type> currentNode = w;
            int degree = currentNode.getDegree();

            while (A.get(degree) != null && A.get(degree) != currentNode) {
                Node<Type> sameDegreeNode = A.get(degree);
                if (currentNode.getValue().compareTo(sameDegreeNode.getValue()) > 0) {
                    Node<Type> temp = currentNode;
                    currentNode = sameDegreeNode;
                    sameDegreeNode = temp;
                }
                linkNodes(currentNode, sameDegreeNode);
                A.set(degree, null);
                degree++;
            }
            A.set(degree, currentNode);
            w = currentNode.getRight();
        } while (w != minNode);

        minNode = null;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != null) {
                if (minNode == null) {
                    minNode = A.get(i);
                    minNode.setLeft(minNode);
                    minNode.setRight(minNode);
                } else {
                    A.get(i).setRight(minNode);
                    A.get(i).setLeft(minNode.getLeft());

                    minNode.getLeft().setRight(A.get(i));
                    minNode.setLeft(A.get(i));

                    if (A.get(i).getValue().compareTo(minNode.getValue()) < 0)
                        minNode = A.get(i);
                }
            }
        }
    }

    private void cut(Node<Type> node, Node<Type> parent) {
        if (node.getRight() == node) parent.setChild(null);
        else if (parent.getChild() == node) parent.setChild(node.getRight());

        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());

        node.setLeft(minNode.getLeft());
        node.setRight(minNode);

        minNode.getLeft().setRight(node);
        minNode.setLeft(node);

        node.setMarked(false);
        node.setParent(null);
    }

    private void cascadingCut(Node<Type> node) {
        Node<Type> parent = node.getParent();
        if(parent != null) {
            if(node.isMarked()) node.setMarked(true);
            else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }

    public FibonacciMinHeap(Type lowestValue) {
        this.lowestValue = lowestValue;
    }

    public Node<Type> getNodeByValue(Type value, Node<Type> currentNode) {
        if (currentNode == null) currentNode = minNode;
        if (currentNode != null) {
            Node<Type> node = currentNode;
            do {
                if (node.getValue().compareTo(value) == 0) return node;
                if (node.getChild() != null) {
                    Node<Type> foundNode = getNodeByValue(value, node.getChild());
                    if (foundNode != null) return foundNode;
                }
                node = node.getRight();
            } while (node != currentNode);
        }
        return null;
    }

    public void insert(Type value) {
        Node<Type> newNode = new Node<>(value);
        if (minNode == null) {
            minNode = newNode;
            minNode.setLeft(newNode);
            minNode.setRight(newNode);
        } else {
            newNode.setRight(minNode);
            newNode.setLeft(minNode.getLeft());

            minNode.getLeft().setRight(newNode);
            minNode.setLeft(newNode);

            if (newNode.getValue().compareTo(minNode.getValue()) < 0)
                minNode = newNode;
        }
        numberOfNodes++;
    }

    public void union(FibonacciMinHeap<Type> heap) {
        if (heap.minNode == null) return;
        else if (minNode == null) {
            minNode = heap.minNode;
            return;
        }
        Node<Type> minNode2 = heap.minNode;
        Node<Type> temp = minNode2.getRight();

        minNode2.setRight(minNode);
        minNode.getLeft().setRight(temp);

        temp.setLeft(minNode.getLeft());
        minNode.setLeft(minNode2);

        if (heap.minNode.getValue().compareTo(minNode.getValue()) < 0) {
            minNode = heap.minNode;
        }

        numberOfNodes += heap.numberOfNodes;
    }

    public Type extractMin() {
        Node<Type> min = minNode;
        if (min != null) {
            Node<Type> child = min.getChild();
            if (child != null)
                do {

                    Node<Type> temp = child;
                    child = child.getRight();

                    temp.setLeft(min.getLeft());
                    min.getLeft().setRight(temp);

                    temp.setRight(min);
                    min.setLeft(temp);
                    temp.setParent(null);

                } while (child != min.getChild());


            minNode.getLeft().setRight(minNode.getRight());
            minNode.getRight().setLeft(minNode.getLeft());
            minNode.setChild(null);

            if (minNode == minNode.getRight()) minNode = null;
            else {
                minNode = minNode.getRight();
                consolidate();
            }
            numberOfNodes--;
        }

        return min != null ? min.getValue() : null;
    }


    public void decreaseValue(Type newValue, Node<Type> node) {
        if (node.getValue().compareTo(newValue) < 0)
            throw new IllegalArgumentException("New value must be less than the old value");

        node.setValue(newValue);
        Node<Type> parent = node.getParent();
        if (parent != null && node.getValue().compareTo(parent.getValue()) < 0) {
            cut(node, parent);
            cascadingCut(parent);
        }
        if (node.getValue().compareTo(minNode.getValue()) < 0)
            minNode = node;
    }

    public void deleteNode(Node<Type> node) {
        decreaseValue(lowestValue, node);
        extractMin();
    }



    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(minNode, buffer, "", "");
        return buffer.toString();
    }
}
