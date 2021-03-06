/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_09;

import Exceptions.NotSupportComparable;
import SortedArrays.ArrayUnorderedList;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyQueueException;
import Interfaces.QueueADT;
import Queues.LinkedQueue;
import java.util.Iterator;

/**
 *
 * @author navega
 */
/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 *
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary
     * tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @Override
    public T getRoot() {
        return (T) this.root;
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

            boolean found = false;
            try {
                Iterator i = this.iteratorInOrder();
                while (i.hasNext()) {
                    if (i.next().equals(targetElement)) {
                        found = true;
                    }
                }

            } catch (NotSupportComparable ex) {

            }
            return found;
        } else {
            throw new NotSupportComparable("Not support comparable.");
        }

    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }
        return (current.element);
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.element.equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }

    @Override
    public Iterator<T> iteratorInOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        preorder(root, tempList);
        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postorder(root, tempList);
        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws NotSupportComparable {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        try {
            levelorder(root, tempList);
        } catch (EmptyQueueException ex) {
        }
        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) throws NotSupportComparable {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) throws NotSupportComparable {
        if (node != null) {
            tempList.addToRear(node.element);
            preorder(node.left, tempList);
            preorder(node.right, tempList);
        }
    }

    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) throws NotSupportComparable {
        if (node != null) {
            postorder(node.left, tempList);
            postorder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    protected void levelorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) throws EmptyQueueException, NotSupportComparable {
        QueueADT<BinaryTreeNode> nodes = new LinkedQueue();
        nodes.enqueue(this.root);

        while (!nodes.isEmpty()) {
            BinaryTreeNode element = (BinaryTreeNode) nodes.dequeue();
            if (element != null) {
                tempList.addToRear(element);
                nodes.enqueue(element.left);
                nodes.enqueue(element.right);
            } else {
                //tempList.addToRear(null);
            }

        }
    }

}
