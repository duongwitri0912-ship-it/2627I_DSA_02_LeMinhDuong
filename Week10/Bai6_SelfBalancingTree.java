import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class AVLNode {
    int val;
    int ht;
    AVLNode left;
    AVLNode right;

    static AVLNode newNode(int data) {
        AVLNode temp = new AVLNode();
        temp.val = data;
        temp.left = null;
        temp.right = null;
        return temp;
    }

    static int ht_hidden(AVLNode T)
    {
        int lh, rh;
        if (T == null)
        {
            return(0);
        }
        if (T.left == null)
        {
            lh = 0;
        }
        else
        {
            lh = 1 + T.left.ht;
        }
        if (T.right == null)
        {
            rh = 0;
        }
        else
        {
            rh = 1 + T.right.ht;
        }
        if (lh > rh)
        {
            return(lh);
        }
        return(rh);
    }
    static AVLNode rotate_right_hidden(AVLNode x)
    {
        AVLNode y;
        y = x.left;
        x.left = y.right;
        y.right = x;
        x.ht = ht_hidden(x);
        y.ht = ht_hidden(y);
        return(y);
    }
    static AVLNode rotate_left_hidden(AVLNode x)
    {
        AVLNode y;
        y = x.right;
        x.right = y.left;
        y.left = x;
        x.ht = ht_hidden(x);
        y.ht = ht_hidden(y);
        return(y);
    }
    static AVLNode RR_hidden(AVLNode T)
    {
        T = rotate_left_hidden(T);
        return(T);
    }
    static AVLNode LL_hidden(AVLNode T)
    {
        T = rotate_right_hidden(T);
        return(T);
    }
    static AVLNode LR_hidden(AVLNode T)
    {
        T.left = rotate_left_hidden(T.left);
        T = rotate_right_hidden(T);
        return(T);
    }
    static AVLNode RL_hidden(AVLNode T)
    {
        T.right = rotate_right_hidden(T.right);
        T = rotate_left_hidden(T);
        return(T);
    }
    static int BF_hidden(AVLNode T)
    {
        int lh, rh;
        if (T == null)
        {
            return(0);
        }
        if (T.left == null)
        {
            lh = 0;
        }
        else
        {
            lh = 1 + T.left.ht;
        }
        if (T.right == null)
        {
            rh = 0;
        }
        else
        {
            rh = 1 + T.right.ht;
        }
        return(lh - rh);
    }
    static void inorder_hidden(AVLNode T)
    {
        if (T != null)
        {
            inorder_hidden(T.left);
            System.out.print(T.val+"(BF="+BF_hidden(T)+") ");
            inorder_hidden(T.right);
        }
    }

    static void preorder_hidden(AVLNode T)
    {
        if (T != null)
        {
            System.out.print(T.val+"(BF="+BF_hidden(T)+") ");
            preorder_hidden(T.left);
            preorder_hidden(T.right);
        }
    }

    static AVLNode hiddenInsert(AVLNode T, int x)
    {
        if (T == null)
        {
            T = newNode(x);
        }
        else if (x > T.val)
        {
            T.right = hiddenInsert(T.right, x);
            if (BF_hidden(T) == -2)
            {
                if (x > T.right.val)
                {
                    T = RR_hidden(T);
                }
                else
                {
                    T = RL_hidden(T);
                }
            }
        }
        else if (x < T.val)
        {
            T.left = hiddenInsert(T.left, x);
            if (BF_hidden(T) == 2){
                if (x < T.left.val)
                {
                    T = LL_hidden(T);
                }
                else
                {
                    T = LR_hidden(T);
                }
            }
        }
        T.ht = ht_hidden(T);
        return(T);
    }

    static AVLNode insert(AVLNode root, int val) {
        return hiddenInsert(root, val);
    }
}

public class Bai6_SelfBalancingTree {
    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        AVLNode root = null;

        for(int i = 0; i < n; i++)
        {
            int val;
            val = in.nextInt();
            root = AVLNode.hiddenInsert(root, val);
        }
        int nv;
        nv = in.nextInt();
        root = AVLNode.insert(root, nv);
        AVLNode.inorder_hidden(root);
        System.out.println("");
        AVLNode.preorder_hidden(root);
        System.out.println("");
    }
}