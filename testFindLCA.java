//testing findLCA.java using JUnit 4 test cases
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class testFindLCA
{
    @Test 
    public void emptyTree() {
    	
    	findLCA bt = new findLCA();
 	    assertEquals("Empty tree shoudl return -1 as there are no ancestors",-1, bt.lca(1,7));
    }
    
  
	@Test
	public void perfectTree() {
		
		//constructing a perfect trees
		findLCA bt = new findLCA();
		 
        bt.root = new BTNode(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.left = new BTNode(4);
        bt.root.left.right = new BTNode(5);
        bt.root.right.left = new BTNode(6);
        bt.root.right.right = new BTNode(7);
        
        //testing two nodes on the same level with common ancestor
	    assertEquals("Nodes on the same level with common ancestor:",2, bt.lca(4,5));
	    
	    //testing two nodes on the same level however with different immediate ancestor
        assertEquals("Nodes on the same level but different immediate ancestors",1, bt.lca(4,6));
	    
	    //testing two nodes on a different level
	    assertEquals("Nodes on different levels",1, bt.lca(3,4));
	    
	    //testing two nodes where one node is the predecessor of the other
	    assertEquals("One node is the predecessor of the other",2, bt.lca(2,4));
	}
	
	@Test
	public void balancedTree() {
		
		//constructing a perfect trees
		findLCA bt = new findLCA();
			 
		bt.root = new BTNode(1);
		bt.root.left = new BTNode(2);
		bt.root.right = new BTNode(3);
		bt.root.left.left = new BTNode(4);
		bt.root.right.left = new BTNode(6);
		bt.root.right.right = new BTNode(7);
		bt.root.left.left.left = new BTNode(8);
		bt.root.left.left.right = new BTNode(9);
		        
		//testing two nodes on the same level with common ancestor
        assertEquals("Nodes on the same level with common ancestor:",1, bt.lca(2,3));
			    
	    //testing two nodes on the same level however with different immediate ancestor
		assertEquals("Nodes on the same level but different immediate ancestors",1, bt.lca(4,6));
			    
	    //testing two nodes on a different level
		assertEquals("Nodes on different levels",1, bt.lca(2,7));
			    
	    //testing two nodes where one node is the predecessor of the other
	    assertEquals("One node is the predecessor of the other:",1, bt.lca(1,3));
	}
	
	@Test
	public void leftSkewedTree(){
		
		//constructing left skewed tree
		BST bt = new BST();
				 
		bt.root = new BTNode(7);
        bt.root.left = new BTNode(6);
		bt.root.left.left = new BTNode(5);
		bt.root.left.left.left = new BTNode(4);
		bt.root.left.left.left.left = new BTNode(3);
		bt.root.left.left.left.left.left = new BTNode(2);
		bt.root.left.left.left.left.left.left = new BTNode(1);
		
		//testing two nodes where one node is the predecessor of the other
	    assertEquals("Nodes on the same level with common ancestor:",4, bt.lca(3,4));
	    
	    //testing two nodes which are several levels apart
	    assertEquals("Nodes on the same level with common ancestor:",5, bt.lca(2,5));
	}
	
	@Test
	public void rightSkewedTree(){
		
		//constructing left skewed tree
		BST bt = new BST();
				 
		bt.root = new BTNode(1);
		bt.root.right = new BTNode(2);
		bt.root.right.right = new BTNode(3);
		bt.root.right.right.right = new BTNode(4);
		bt.root.right.right.right.right = new BTNode(5);
		bt.root.right.right.right.right.right = new BTNode(6);
		bt.root.right.right.right.right.right.right = new BTNode(7);
		
		//testing two nodes where one node is the predecessor of the other
	    assertEquals("Nodes on the same level with common ancestor:",3, bt.lca(3,4));
	    
	    //testing two nodes which are several levels apart
	    assertEquals("Nodes on the same level with common ancestor:",2, bt.lca(2,5));
	}

}
