package ex2;

import java.util.NoSuchElementException;

public class BST


{ private BTNode<Integer> root;
  int count;    //variables to track the values found within the class
  int nth;
  boolean foundNth;

  public BST()
  { root = null;
  }

  public boolean find(Integer i)
  { BTNode<Integer> n = root;
    boolean found = false;

    while (n!=null && !found)
    { int comp = i.compareTo(n.data);
      if (comp==0)
        found = true;
      else if (comp<0)
        n = n.left;
      else
        n = n.right;
	}

    return found;
  }

  public boolean insert(Integer i)
  { BTNode<Integer> parent = root, child = root;
    boolean goneLeft = false;

    while (child!=null && i.compareTo(child.data)!=0)
    { parent = child;
      if (i.compareTo(child.data)<0)
	  { child = child.left;
	    goneLeft = true;
	  }
	  else
	  { child = child.right;
	    goneLeft = false;
      }
	}

    if (child!=null)
      return false;  // number already present
    else
    { BTNode<Integer> leaf = new BTNode<Integer>(i);
      if (parent==null) // tree was empty
        root = leaf;
      else if (goneLeft)
        parent.left = leaf;
      else
        parent.right = leaf;
      return true;
    }
  }


  public int nth (int n)  //function to find and return the value of the Nth node
  {
    count = 0;
    foundNth = false;
    findNth(n , root); //calls the recursive function to update the variable storing the data on the Nth node
    if (!foundNth) throw new NoSuchElementException("Nth element does not exist"); //throws an exception of the Nth node isn't found
    else
    {
      return nth;
    }
  }

  private void findNth(int n, BTNode<Integer> current)  //recursive function to find the Nth node
  {
    if (current != null)
    {
      findNth(n ,current.left);
      if (count == n)
      {
        nth = current.data;  //if the value of the Nth node is found the class variable is updated
        foundNth = true;
      }
      count ++;
      findNth(n ,current.right);
    }

  }


  public int greater(int n) //function to find and return how many nodes have a value greater than n
  {
    count = 0;
    int amount = findGreater(n , root);

    return amount;
  }


  private int findGreater(int n , BTNode<Integer> current)  //recursively searches the tree for values greater than n
  {
    if (current != null)
    {
      if (current.data <= n)
      {
        findGreater(n ,current.right);
      }

      if (current.data > n)
      {
        count ++;
        findGreater(n ,current.right);
        findGreater(n ,current.left);
      }
    }
    return count;
  }

}

class BTNode<T>
{ T data;
  BTNode<T> left, right;

  BTNode(T o)
  { data = o; left = right = null;
  }
}
