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
    }

    private AvlNode remove(AvlNode v, int x)//this function is overloaded
    {
    }

    private AvlNode balance(AvlNode v)
    {
    }

    private AvlNode rightRotate (AvlNode v)
    {
    }

    private AvlNode leftRotate (AvlNode v)
    {
    }

    private AvlNode doubleLeftRightRotate (AvlNode v)
    {
    }

    private AvlNode doubleRightLeftRotate (AvlNode v)
    {
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

        //print the pre-odrder traversal on the console/screen
        System.out.println("The pre-order traversal list is: ");
        tree.preOrder();

        //print the in-odrder traversal
        System.out.println("The in-order traversal list is: ");
        tree.inOrder();

        input.close();
    }
}
