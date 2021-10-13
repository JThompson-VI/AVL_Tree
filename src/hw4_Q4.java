import java.util.Scanner;
import java.io.*;

class AvlNode {
    public int key;
    public AvlNode left;
    public AvlNode right;
    public int height;

    public AvlNode(int key_input)
    {
        key = key_input;
        height = 0;
        left = null;
        right = null;
    }

}

class AvlTree{
    private AvlNode root;
    public AvlTree(){root = null;}

    public void inOrder()
    {
        inOrder(root);
        System.out.println();
    }
    private void inOrder(AvlNode v)
    {
        if(v == null)
            return;
        inOrder(v.left);
        System.out.print(v.key + " ");
        inOrder(v.right);
    }

    public void preOrder()
    {
        preOrder(root);
        System.out.println();
    }
    private void preOrder(AvlNode v)
    {
        if(v == null)
            return;
        System.out.print(v.key + " ");
        preOrder(v.left);
        preOrder(v.right);
    }

    private int getHeight(AvlNode v){
        if(v == null)
            return -1;
        else
            return v.height;
    }

    public void insert(int x) { root = insert(root, x); }

    public void remove(int x) { root = remove(root, x); }
    
    //please complete the following seven functions
	private AvlNode insert(AvlNode v, int x)//this function is overloaded
	{
        if (v == null) {
            return new AvlNode(x);
        }
        else if (x == v.key) {
            return v;
        }
        else if (x < v.key) {
            v.left = insert(v.left, x);
        }
        else {
            v.right = insert(v.right, x);
        }

        return balance(v);
    }

    private AvlNode remove(AvlNode v, int x)//this function is overloaded
    {
        if (v == null) return null;
        // navigate to desired subtree
        if (x > v.key) {
            v.right = remove(v.right, x);
            return balance(v);
        }
        else if (x < v.key) {
            v.left = remove(v.left, x);
            return balance(v);
        }
        else {
            // if v has no children/ one child
            if (v.left == null) {
                return v.right;
            }
            else if (v.right == null) {
                return v.left;
            }
            // case if v has two children
            AvlNode subTree = v.right;
            // navigate to the smallest node in the right subtree of v
            while (subTree.left != null) {
                subTree = subTree.left;
            }
            v.key = subTree.key;
            v.right = remove(v.right, v.key);
            return balance(v);
        }

    }


    private AvlNode balance(AvlNode v)
    {
        if (v == null) return null;

        if (getHeight(v.left) - getHeight(v.right) > 1) {
            if (getHeight(v.left.left) >= getHeight(v.left.right)) {
                v = rightRotate(v);
            }else {
                v = doubleLeftRightRotate(v);
            }
        }
        else if (getHeight(v.right) - getHeight(v.left) > 1) {
            if (getHeight(v.right.right) >= getHeight(v.right.left)) {
                v = leftRotate(v);
            }else {
                v = doubleRightLeftRotate(v);
            }
        }
        if (getHeight(v.left) >= getHeight(v.right)) {
            v.height = 1 + getHeight(v.left);
        }else {
            v.height = 1 + getHeight(v.right);
        }
        return v;
    }

    private AvlNode rightRotate (AvlNode v)
    {
        AvlNode newRoot = v.left;
        v.left = newRoot.right;
        newRoot.right = v;
        v.height--;
        return newRoot;
    }

    private AvlNode leftRotate (AvlNode v)
    {

        AvlNode newRoot = v.right;
        v.right = newRoot.left;
        newRoot.left = v;
        v.height--;
        return newRoot;
    }

    private AvlNode doubleLeftRightRotate (AvlNode v)
    {
        v.left = leftRotate(v.left);
        v = rightRotate(v);
        balance(v.right);
        return v;
    }

    private AvlNode doubleRightLeftRotate (AvlNode v)
    {
        v.right = rightRotate(v.right);
        v = leftRotate(v);
        balance(v.left);
        return v;
    }
}

public class hw4_Q4 {
    public static void main(String[] args) throws IOException
    {

        AvlTree tree = new AvlTree();

        String inputFile = "hw4_Q4_input.txt"; // input file with operations

        //open the input file
        File myFile = new File(inputFile);
        Scanner input = new Scanner(myFile);

        //read operations from the input file
        String op;
        int x;
        while(input.hasNext())
        {
            Scanner nextLine = new Scanner(input.nextLine());
            op = nextLine.next();

            if (op.equals("insert"))
            {
                x = nextLine.nextInt(); // read the value x for insert
                tree.insert(x);
            }
            if (op.equals("remove"))
            {
                x = nextLine.nextInt(); // read the value x for remove
                tree.remove(x);
            }
        }

        //print the pre-order traversal on the console/screen
        System.out.println("The pre-order traversal list is: ");
        tree.preOrder();

        //print the in-order traversal
        System.out.println("The in-order traversal list is: ");
        tree.inOrder();

        input.close();
    }
}
