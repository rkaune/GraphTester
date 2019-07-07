//*******************************************************************
//  BinaryTreeNode.java                Authors:  Lewis/Chase
//
//  Represents a node in a binary tree with a left and right child.
//*******************************************************************
package jsjf;


public class BinaryTreeNode<T>
{
   protected T element;
   protected BinaryTreeNode<T> left, right;

   /*****************************************************************
     Creates a new tree node with the specified data.
   *****************************************************************/
   BinaryTreeNode (T obj) 
   {
      element = obj;
      left = null;
      right = null;
   }

   /*****************************************************************
     Returns the number of non-null children of this node.
     This method may be able to be written more efficiently.
   *****************************************************************/
   public int numChildren() 
   {
      int children = 0;

      if (left != null)
         children = 1 + left.numChildren();

      if (right != null)
         children = children + 1 + right.numChildren();

      return children;
   }
}
