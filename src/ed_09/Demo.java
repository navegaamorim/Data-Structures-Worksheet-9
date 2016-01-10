/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_09;

import Exceptions.ElementNotFoundException;
import Exceptions.NotSupportComparable;
import java.util.Iterator;

/**
 *
 * @author navega
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotSupportComparable, ElementNotFoundException {

//        LinkedBinarySearchTree<String> binary_search = new LinkedBinarySearchTree("1");
//
//        binary_search.addElement("2");
//        binary_search.addElement("2");
//        binary_search.addElement("4");
//
//        binary_search.removeElement("2");
        BinaryTreeNode<String> a = new BinaryTreeNode("A");
        BinaryTreeNode<String> b = new BinaryTreeNode("B");
        BinaryTreeNode<String> c = new BinaryTreeNode("C");
        BinaryTreeNode<String> d = new BinaryTreeNode("D");
        BinaryTreeNode<String> e = new BinaryTreeNode("E");
        BinaryTreeNode<String> f = new BinaryTreeNode("F");
        BinaryTreeNode<String> g = new BinaryTreeNode("G");
        b.left = d;
        b.right = e;

        c.left = f;
        c.right = g;

        a.left=b;
        a.right=c;
//        LinkedBinaryTree<String> binary = new LinkedBinaryTree(a);
//
//        Iterator i = binary.iteratorLevelOrder();
//
//        while (i.hasNext()) {
//            System.out.println(i.next().toString());
//        }
//        System.out.println(binary.contains("A"));
//        LinkedBinarySearchTree<Integer> binary_search = new LinkedBinarySearchTree();
//
//        binary_search.addElement(10);
//        binary_search.addElement(5);
//        binary_search.addElement(13);
//        binary_search.addElement(3);
//        binary_search.addElement(7);
//        binary_search.addElement(15);
//        binary_search.addElement(15);
//
//        binary_search.removeAllOccurrences(15);
//
//        
//        binary_search.removeMin();

        ArrayBinaryTree<String> array = new ArrayBinaryTree(a);

        Iterator i = array.iteratorLevelOrder();

        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
        
    }

}
