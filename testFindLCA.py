import unittest

from findLCA import findLCA
from findLCA import BTNode

class testFindLCA(unittest.TestCase):

    #test for empty root
    def emptyTree(self):
        emptyRoot = None

        #The result of a root containing no value(null), should be None
        self.assertEqual(None,findLCA(emptyRoot,2,7))


    def testPerfectTree(self):
        root = BTNode(1)
        root.left = BTNode(2)
        root.right = BTNode(3)
        root.left.left = BTNode(4)
        root.left.right = BTNode(5)
        root.right.left = BTNode(6)
        root.right.right = BTNode(7)

        #testing two nodes on the same level with common ancestor
        self.assertEqual(2,findLCA(root,4,5).key)

        #testing two nodes on the same level however with different immediate ancestor
        self.assertEqual(1,findLCA(root,4,6).key)

        #testing two nodes on a different level
        self.assertEqual(1,findLCA(root,3,4).key)

        #testing two nodes where one node is the predecessor of the other
        self.assertEqual(2,findLCA(root,2,4).key)

    def testBalancedTree(self):
        root = BTNode(1)
        root.left = BTNode(2)
        root.right = BTNode(3)
        root.left.left = BTNode(4)
        root.right.left = BTNode(6)
        root.right.right = BTNode(7)
        root.left.left.left = BTNode(8)
        root.left.left.right = BTNode(9)

        #testing two nodes on the same level with common ancestor
        self.assertEqual(1,findLCA(root,2,3).key)

        #testing two nodes on the same level however with different immediate ancestor
        self.assertEqual(1,findLCA(root,4,6).key)

        #testing two nodes on a different level
        self.assertEqual(1,findLCA(root,2,7).key)

        #testing two nodes where one node is the predecessor of the other
        self.assertEqual(1,findLCA(root,1,3).key)

    def testLeftSkewedTree(self):
        root = BTNode(7)
        root.left = BTNode(6)
        root.left.left = BTNode(5)
        root.left.left.left = BTNode(4)
        root.left.left.left.left = BTNode(3)
        root.left.left.left.left.left = BTNode(2)
        root.left.left.left.left.left.left = BTNode(1)

        #testing two nodes on a different level
        self.assertEqual(5,findLCA(root,2,5).key)

        #testing two nodes where one node is the predecessor of the other
        self.assertEqual(4,findLCA(root,4,3).key)

    def testRightSkewedTree(self):
        root = BTNode(1)
        root.right = BTNode(2)
        root.right.right = BTNode(3)
        root.right.right.right = BTNode(4)
        root.right.right.right.right= BTNode(5)
        root.right.right.right.right.right = BTNode(6)
        root.right.right.right.right.right.right = BTNode(7)

        #testing two nodes on a different level
        self.assertEqual(2,findLCA(root,2,5).key)

        #testing two nodes where one node is the predecessor of the other
        self.assertEqual(3,findLCA(root,4,3).key)

if _name_ == "_main_":
    unittest.main()
    


