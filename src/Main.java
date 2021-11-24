
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
    }





}
