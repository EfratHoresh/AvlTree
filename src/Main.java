
public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        AVLTree.IAVLNode node1 = tree.new AVLNode(5, "e");
//        System.out.println(node1.getKey());
        System.out.println(tree.empty());
        System.out.println(tree.insert(5, "five"));
        System.out.println(tree.insert(3, "five"));
        System.out.println(tree.insert(2, "five"));
        tree.insert(6, "six");
        tree.insert(7, "seven");
        System.out.println(tree.delete(6));
        System.out.println(tree.empty());
        AVLTree.IAVLNode node1 = tree.new AVLNode(1, "e");
        AVLTree empty_tree = new AVLTree();
        // join test - empty tree
//        tree.join(node1, empty_tree);
        System.out.println(tree);
        //
        empty_tree.insert(9, "");
        empty_tree.insert(10, "");
        AVLTree.IAVLNode node2 = tree.new AVLNode(8, "e");
        tree.join(node2, empty_tree);
        AVLTree[] trees = tree.split(9);
        System.out.println(trees);
    }





}
