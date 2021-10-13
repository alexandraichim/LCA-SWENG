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
