/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_09;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NotSupportComparable;
import SortedArrays.ArrayUnorderedList;
import java.util.Iterator;

/**
 *
 * @author Navega
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> {

    private int height;
    private int maxIndex;

    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    public void addElement(T element) {
        if (tree.length < maxIndex * 2 + 3) {
            //expandCapacity();
        }
        Comparable<T> tempElement = (Comparable<T>) element;
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;

            while (!added) {
                if (tempElement.compareTo((tree[currentIndex])) < 0) {
                    // go left
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    // go right
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    public T removeElement(T targetElement) throws ElementNotFoundException, NotSupportComparable {
        T result = null;
        boolean found = false;
        if (isEmpty()) {
            throw new ElementNotFoundException("binary search tree");
        }
        Comparable<T> tempElement = (Comparable<T>) targetElement;
        int targetIndex = findIndex(tempElement, 0);
        result = tree[targetIndex];
        replace(targetIndex);
        count--;
        int temp = maxIndex;
        maxIndex = -1;
        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        return result;
    }

    protected int findIndex(Comparable<T> targetElement, int n) throws
            ElementNotFoundException {
        int result = 0;
        if (n > tree.length) {
            throw new ElementNotFoundException("binary search tree");
        }
        if (tree[n] == null) {
            throw new ElementNotFoundException("binary search tree");
        }
        if (targetElement.compareTo(tree[n]) == 0) {
            result = n;
        } else if (targetElement.compareTo(tree[n]) > 0) {
            result = findIndex(targetElement, (2 * (n + 1)));
        } else {
            result = findIndex(targetElement, (2 * n + 1));
        }
        return result;
    }

    protected void replace(int targetIndex) throws NotSupportComparable {
        int currentIndex, parentIndex, temp, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<Integer>();
        ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<Integer>();
        ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<Integer>();
        Iterator<Integer> oldIt, newIt;

        // if target node has no children
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) {
            tree[targetIndex] = null;
        } // if target node has no children
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) {
            tree[targetIndex] = null;
        } // if target node only has a left child
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {
            // fill newlist with indices of nodes that will replace
            // the corresponding indices in oldlist
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }
            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // if target node only has a right child
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {
            // fill newlist with indices of nodes that will replace
            // the corresponding indices in oldlist
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }
            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }
            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // if target node has two children
        else {
            currentIndex = targetIndex * 2 + 2;
            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }
            tree[targetIndex] = tree[currentIndex];
            // the index of the root of the subtree to be replaced
            int currentRoot = currentIndex;
            // if currentIndex has a right child
            if (tree[currentRoot * 2 + 2] != null) {
                // fill newlist with indices of nodes that will replace
                // the corresponding indices in oldlist
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    newlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }
                // fill oldlist
                currentIndex = currentRoot;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    oldlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }
                // do replacement
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else {
                tree[currentRoot] = null;
            }
        }
    }

    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException, NotSupportComparable {
        removeElement(targetElement);
        try {
            while (contains((T) targetElement)) {
                removeElement(targetElement);
            }
        } catch (Exception ElementNotFoundException) {
        }
    }

    public T removeMin() throws EmptyCollectionException, NotSupportComparable {
        T result = null;
        if (isEmpty()) {
            throw new EmptyCollectionException("binary search tree");
        } else {
            int currentIndex = 1;
            int previousIndex = 0;
            while (tree[currentIndex] != null && currentIndex <= tree.length) {
                previousIndex = currentIndex;
                currentIndex = currentIndex * 2 + 1;
            }
            result = tree[previousIndex];
            replace(previousIndex);
        }
        count--;
        return result;
    }
}
