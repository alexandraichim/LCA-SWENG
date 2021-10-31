//testing findLCA.java using JUnit 4 test cases for assignment LCA2
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class testGraph
{
    @Test
    public void testEmptyGraph(){
        Graph g = new Graph(0);
        int[] result = new int[0];
       
        assertEquals("LCA between 7 and 11 is [] as the nodes don't exist in graph",result,findGraphLCA(7,11,g));
    }

    @Test
    public void testMissingNodes(){
        int[] result = new int[0];
        Graph g = new Graph(4);
		g.addEdge(0,2);
		g.addEdge(0,1);

        //test one missing node
        assertEquals("one common lca between 2 and 4",result,findGraphLCA(2,4,g));

        //test two missing nodes
        assertEquals("one common lca between 4 and 5",result,findGraphLCA(4,5,g));

    }

    @Test
    public void testParentLCA(){
        Graph g = new Graph(9);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 4);
		g.addEdge(1, 6);
		g.addEdge(2, 4);
		g.addEdge(2, 6);
		g.addEdge(2, 3);
		g.addEdge(3, 6);
        g.addEdge(6, 5);
        g.addEdge(6, 7);
        g.addEdge(7, 8);

        int[] result = {7};

        //test when one node is the ancestor of the other node
        assertEquals("one common lca between 7 and 8",result, findGraphLCA(7,8,g));

        //test when one node is the source node and the other is not
        assertEquals("one common lca between 0 and 7",result, findGraphLCA(0,8,g));

    }

    @Test
    public void testLcaButNotParent(){
        int[] result = {0};
        
        Graph g = new Graph(4);
		g.addEdge(0,2);
		g.addEdge(0,1);
		g.addEdge(2,3);

        //test one parent of the other
        assertEquals("one common lca between 3 and 1",result, findGraphLCA(1,3,g));

    }
		
    @Test 
    public void testDAG() {
    	int[] result = {6};
        int[] result2 = {1,2};
    	
        Graph g = new Graph(9);
        g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 4);
		g.addEdge(1, 6);
		g.addEdge(2, 4);
		g.addEdge(2, 6);
		g.addEdge(2, 3);
		g.addEdge(3, 6);
        g.addEdge(6, 5);
        g.addEdge(6, 7);
        g.addEdge(7, 8);

        //one common lca between two nodes
        assertEquals("one common lca between 5 and 7",result, findGraphLCA(5,7,g));

        //two common lcas between two nodes
        assertEquals("two common lcas between 4 and 7",result2, findGraphLCA(4,7,g));
    }
}

//testing findLCA.java using JUnit 4 test cases for assignment LCA 
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
