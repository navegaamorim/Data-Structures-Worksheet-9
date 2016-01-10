/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_09;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyQueueException;
import Exceptions.NotSupportComparable;
import Interfaces.QueueADT;
import Interfaces.UnorderedListADT;
import Queues.LinkedQueue;
import SortedArrays.ArrayUnorderedList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Navega
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root of the new tree
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree. Throws a NoSuchElementException if the specified target
     * element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        }
        if (!found) {
            throw new ElementNotFoundException("binary tree");
        }
        return temp;

    }

    @Override
    public T getRoot() {
        return this.tree[0];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) throws NotSupportComparable {
        if (targetElement instanceof Comparable) {
            try {
                if (this.find(targetElement) != null) {
                    return true;
                }
            } catch (ElementNotFoundException ex) {
                return false;
            }

        } else {
            throw new NotSupportComparable("Not support comparable.");
        }
        return false;
    }

    @Override
    public Iterator<T> iteratorInOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder(0, templist);
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder(0, templist);
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        postorder(0, templist);
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        try {
            levelorder(0, templist);
        } catch (EmptyQueueException ex) {
        }
        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     * @throws Exceptions.NotSupportComparable
     */
    protected void inorder(int node, ArrayUnorderedList<T> templist) throws NotSupportComparable {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist);
            }
        }
    }

    /**
     * Performs a recursive preorder traversal
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     * @throws NotSupportComparable
     */
    protected void preorder(int node, ArrayUnorderedList<T> templist) throws NotSupportComparable {
        if (node < tree.length) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                inorder(node * 2 + 1, templist);
                inorder((node + 1) * 2, templist);
            }
        }
    }

    /**
     * Performs a recursive postorder traversal
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     * @throws NotSupportComparable
     */
    protected void postorder(int node, ArrayUnorderedList<T> templist) throws NotSupportComparable {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                inorder((node + 1) * 2, templist);
                templist.addToRear(tree[node]);

            }
        }
    }

    /**
     * Performs a recursive levelorder traversal
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     * @throws EmptyQueueException
     * @throws NotSupportComparable
     */
    protected void levelorder(int node, ArrayUnorderedList<T> templist) throws EmptyQueueException, NotSupportComparable {
        QueueADT nodes = new LinkedQueue();
        nodes.enqueue(this.tree[0]);

        while (!nodes.isEmpty()) {
            T element = (T) nodes.dequeue();
            if (element != null) {
                templist.addToRear(element);
                nodes.enqueue(node * 2 + 1);
                nodes.enqueue((node + 1) * 2);

            } else {
                templist.addToRear(null);
            }
        }
    }

}
