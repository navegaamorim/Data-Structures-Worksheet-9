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
        
        LinkedBinarySearchTree<String> binary = new LinkedBinarySearchTree("1");
        
        binary.addElement("2");
        binary.addElement("2");
        binary.addElement("4");
        
        Iterator i = binary.iteratorInOrder();
        
        binary.removeAllOccurrences("2");
        
        while(i.hasNext()) {
            System.out.println(i.next());
        }
        
        System.out.println(binary.contains("6"));
        
        
        
    }
    
}
