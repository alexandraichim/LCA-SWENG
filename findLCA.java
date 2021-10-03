//References:
//https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

import java.util.*;

//Node class
class BTNode{
    int value;
    BTNode left, right;

    BTNode(int val){
        value = val;
        left = null;
        right = null;
    }
}

//Finding the path from the root node to the given root of the binary tree
public class findLCA{
    BTNode root;
    private List<Integer> p1 = new ArrayList<>();
    private List<Integer> p2 = new ArrayList<>();

    public int lca(int node1, int node2){
        p1.clear();
        p2.clear();
        return findlcainternal(root,node1,node2);
    }

    private int findlcainternal(BTNode root, int node1,int node2){
        if(!getPath(root,node1,p1) || !getPath(root,node2,p2)){
            System.out.println((p1.size() > 0) ? "node1 is present" : "node1 is missing");
            System.out.println((p2.size() > 0) ? "node2 is present" : "node2 is missing");
            return -1;
        }

        int index;
        for(index = 0; index < p1.size() && index < p2.size(); index++){
            if(!p1.get(index).equals(p2.get(index))) break;
        }
        return p1.get(index - 1);
    }

    private boolean getPath(BTNode root, int n, List<Integer> p){
        //base case
        if(root == null)
            return false;

        p.add(root.value);

        if(root.value == n)
            return true;
            
        if(root.left != null && getPath(root.left,n,p))
            return true;

        if(root.right != null && getPath(root.right,n,p))
            return true;

        p.remove(p.size() - 1);
        return false;
    }

    public static void main(String[] args){
        findLCA bt = new findLCA();
        bt.root = new BTNode(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.left = new BTNode(4);
        bt.root.left.right = new BTNode(5);
        bt.root.right.left = new BTNode(6);
        bt.root.right.right = new BTNode(7);

        System.out.println(bt.lca(4,5));
        System.out.println(bt.lca(4,6));
        System.out.println(bt.lca(3,4));
        System.out.println(bt.lca(2,4));
    }
}