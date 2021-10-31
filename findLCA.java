//I decided to put LCA and LCA2 under the same file.
//First class is the graph class which is part of the LCA2 asssignment
//Second class is the binary tree class which is part of the LCA asssignment

//LCA2
//Resources:
//https://www.geeksforgeeks.org/find-paths-given-source-destination/
//https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
import java.util.ArrayList;
import java.util.List;
import java.util.*;

// A directed graph using adjacency list representation
public class Graph {

	// Number of vertices in DAG
	private int v;

	// adjacency list of DAG
	private ArrayList<Integer>[] adjList;

	public Graph(int vertices)
	{
		this.v = vertices;
		adjList = new ArrayList[v];

		for (int i = 0; i < v; i++) {
			adjList[i] = new ArrayList<>();
		}
	}

	// add edge from souce node to destination node
	public void addEdge(int u, int v)
	{
		adjList[u].add(v);
	}

	public void printAllPaths(int s, int d,ArrayList<List<Integer>> arrList)
	{
		boolean[] isVisited = new boolean[v];
		ArrayList<Integer> pathList = new ArrayList<>();

		pathList.add(s);
		printAllPathsUtil(s, d, isVisited, pathList,arrList);
	}

	// A recursive function to obtain all paths from the root to the destination
    //isVisted[] keeps track of vertices that were visited in the path 
    //localPathList stores the values of the nodes of a particular path
	private void printAllPathsUtil(Integer u, Integer d,
								boolean[] isVisited,
								List<Integer> localPathList,ArrayList<List<Integer>> arrList)
	{

		if (u.equals(d)) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int i = 0; i < localPathList.size(); i++){
				temp.add(localPathList.get(i));
			}
			arrList.add(temp);
			//System.out.print(localPathList);
			// if match found then no need to traverse more till depth
			return;
		}

		isVisited[u] = true;

		for (Integer i : adjList[u]) {
			if (!isVisited[i]) {
				localPathList.add(i);
				printAllPathsUtil(i, d, isVisited, localPathList,arrList);
				localPathList.remove(i);
			}
		}
		isVisited[u] = false;
	}

	//A function taht loops through out outdegree hashmap and returns values of nodes with 0 outdegree
	public static ArrayList<Integer> iterateHashMap(HashMap <Integer,Integer> outdegree){
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Map.Entry<Integer,Integer>> hmIterator = outdegree.entrySet().iterator();

		while (hmIterator.hasNext()) {
			Map.Entry<Integer,Integer> mapElement = (Map.Entry<Integer,Integer>)hmIterator.next();
			if((int)mapElement.getValue() == 0){
				result.add((int)mapElement.getKey());
			}
		}
		
		if(result.size() == 0){
			result.add(0);
		}

		return result;
	}

	//a function that finds the lca of two nodes by iterating through all the common paths
	//We try to identify the intersection between the paths of source node and node1 
	//and the paths between the source node and node 2 to form a subgraph
	public static ArrayList<Integer> findGraphLCAUtil(ArrayList<List<Integer>> arr, ArrayList<List<Integer>> arr1 ){
		HashMap<Integer,Integer> outdegree = new HashMap<>(); 

		for(int i = 0; i < arr.size(); i++){
			for(int j = 0; j < arr1.size(); j++){
				int smallerArr = Math.min(arr.get(i).size(),arr1.get(j).size());
				for(int k = 0; k < smallerArr -1; k++){
					if(arr.get(i).get(k) == arr1.get(j).get(k)
					   && arr.get(i).get(k + 1) == arr1.get(j).get(k + 1)){
						if(!outdegree.containsKey(arr.get(i).get(k)))
							outdegree.put(arr.get(i).get(k),1);
						else
							outdegree.put(arr.get(i).get(k),outdegree.get(arr.get(i).get(k)) + 1);
						if(!outdegree.containsKey(arr.get(i).get(k+1)))
							outdegree.put(arr.get(i).get(k + 1), 0);
					}
				}
			}
		}
		return iterateHashMap(outdegree);
	}

	//Driver function of all tests 
	public static ArrayList<Integer> findGraphLCA(int node1, int node2,Graph g){

		ArrayList<List<Integer>> arr = new ArrayList<List<Integer>>();
		ArrayList<List<Integer>> arr1 = new ArrayList<List<Integer>>();

		g.printAllPaths(0, node1,arr);
		g.printAllPaths(0, node2,arr1);

		//make subgraph 
		if(arr.size() != 0 && arr1.size() != 0){
			return findGraphLCAUtil(arr,arr1);
		}

		return new ArrayList<>();
	}
}

//__________________________________________________________________________________
//LCA
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
}
