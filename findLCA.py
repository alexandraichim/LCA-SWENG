#References:
#https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

#BTNode class
class BTNode:
    def __init__(self, key):
        self.key =  key
        self.left = None
        self.right = None
 
def getPath(root, p, n):

    if root is None:
        return False
 
    p.append(root.key)
 
    if root.key == n :
        return True
 
    if ((root.left != None and getPath(root.left, p, n)) or
            (root.right!= None and getPath(root.right, p, n))):
        return True
      
    p.pop()
    return False
 
def findLCA(root, node1, node2):
 
    p1 = []
    p2 = []

    if (not getPath(root, p1, node1) or not getPath(root, p2, node2)):
        return -1
 
    # Compare the paths to get the first different value
    index = 0
    while(index < len(p1) and index < len(p2)):
        if p1[index] != p2[index]:
            break
        index += 1
    return p1[index-1]
 
 
# Driver program to test above function
# Let's create the Binary Tree shown in above diagram
root = BTNode(1)
root.left = BTNode(2)
root.right = BTNode(3)
root.left.left = BTNode(4)
root.left.right = BTNode(5)
root.right.left = BTNode(6)
root.right.right = BTNode(7)
 
print "LCA(4, 5) = %d" %(findLCA(root, 4, 5,))
print "LCA(4, 6) = %d" %(findLCA(root, 4, 6))
print "LCA(3, 4) = %d" %(findLCA(root,3,4))
print "LCA(2, 4) = %d" %(findLCA(root,2, 4))
