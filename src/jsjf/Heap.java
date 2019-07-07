//********************************************************************
//  Heap.java               Authors: Lewis/Chase
//
//  Represents an implementation of a heap.
//********************************************************************
package jsjf;


import jsjf.exceptions.*;

public class Heap<T> extends LinkedBinaryTree<T> implements HeapADT<T> 
{
   public HeapNode<T> lastNode;  

   /******************************************************************
     Constructs an empty heap.
   ******************************************************************/
   public Heap() 
   {
      super();
   }

   /******************************************************************
     Adds the specified element to the heap in the appropriate
     position according to its key value.  Note that equal elements
     are added to the right.
   ******************************************************************/
   public void addElement (T obj) 
   {
      HeapNode<T> node = new HeapNode<T>(obj);

      if (root == null)
         root=node;
      else
      {
         HeapNode<T> next_parent = getNextParentAdd(); 
         if (next_parent.left == null)
            next_parent.left = node;
         else
            next_parent.right = node;
         node.parent = next_parent;
      }
      lastNode = node;
      count++;
      if (count>1)
         heapifyAdd();
   }

   /******************************************************************
     Returns the node that will be the parent of the new node.
   ******************************************************************/
   private HeapNode<T> getNextParentAdd()
   {
      HeapNode<T> result = lastNode;
      while ((result != root) && (result.parent.left != result))
         result = result.parent;

      if (result != root)
         if (result.parent.right == null)
            result = result.parent;
         else
         {
            result = (HeapNode<T>)result.parent.right;
            while (result.left != null)
               result = (HeapNode<T>)result.left;
         }
      else
         while (result.left != null)
            result = (HeapNode<T>)result.left;
        
      return result;
   }
   
   /******************************************************************
     Reorders the heap after adding a node.
   ******************************************************************/
   private void heapifyAdd()
   {
      T temp;
      HeapNode<T> next = lastNode;
      
      while ((next != root) && (((Comparable)
              next.element).compareTo(next.parent.element) < 0))
      {
         temp = next.element;
         next.element = next.parent.element;
         next.parent.element = temp;
         next = next.parent;
      }
   }
   
   /******************************************************************
     Remove the element with the lowest value in the heap and
     returns a reference to it.  Throws an EmptyCollectionException
     if the heap is empty.
   ******************************************************************/
   public T removeMin() throws EmptyCollectionException 
   {
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");

      T minElement =  root.element;

      if (count == 1)
      {
         root = null;
         lastNode = null;
      }
      else
      {
         HeapNode<T> next_last = getNewLastNode();
         if (lastNode.parent.left == lastNode)
            lastNode.parent.left = null;
         else
            lastNode.parent.right = null;

         root.element = lastNode.element;
         lastNode = next_last;
         heapifyRemove();
      }

      count--;
      return minElement;
   }
   
   /******************************************************************
     Reorders the heap after removing the root element.
   ******************************************************************/
   private void heapifyRemove()
   {
      T temp;
      HeapNode<T> node = (HeapNode<T>)root;
      HeapNode<T> left = (HeapNode<T>)node.left;
      HeapNode<T> right = (HeapNode<T>)node.right;
      HeapNode<T> next;
      
      if ((left == null) && (right == null))
         next = null;
      else if (left == null)
         next = right;
      else if (right == null)
         next = left;
      else if (((Comparable)left.element).compareTo(right.element) < 0)
         next = left;
      else
         next = right;

      while ((next != null) && (((Comparable)
              next.element).compareTo(node.element) < 0))
      {
         temp = node.element;
         node.element = next.element;
         next.element = temp;
         node = next;
         left = (HeapNode<T>)node.left;
         right = (HeapNode<T>)node.right;
         if ((left == null) && (right == null))
            next = null;
         else if (left == null)
            next = right;
         else if (right == null)
            next = left;
         else if (((Comparable)left.element).compareTo
                   (right.element) < 0)
            next = left;
         else
            next = right;
      }
   }

   /******************************************************************
     Returns the node that will be the new last node after a remove.
   ******************************************************************/
   private HeapNode<T> getNewLastNode()
   {
      HeapNode<T> result = lastNode;
      
      while ((result != root) && (result.parent.left == result))
         result = result.parent;
      
      if (result != root)
         result = (HeapNode<T>)result.parent.left;

      while (result.right != null)
         result = (HeapNode<T>)result.right;

      return result;
   }
   
   /******************************************************************
     Returns the element with the lowest value in the heap.
     Throws an EmptyCollectionException if the heap is empty.
   ******************************************************************/
   public T findMin () throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");

      return root.element;
   }
}
