import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        AVLTree.IAVLNode node1 = tree.new AVLNode(5, "e");
//        System.out.println(node1.getKey());
        System.out.println(tree.empty());
        System.out.println(tree.insert(5, "five"));
        System.out.println(tree.insert(3, "three"));
        System.out.println(tree.insert(2, "two"));
        tree.insert(6, "six");
        tree.insert(7, "seven");
        System.out.println(tree.delete(6));
        System.out.println(tree.empty());
        AVLTree.IAVLNode node1 = tree.new AVLNode(1, "one");
        AVLTree empty_tree = new AVLTree();
        // join test - empty tree
//        tree.join(node1, empty_tree);
        System.out.println(tree);
        //
        empty_tree.insert(9, "nine");
        empty_tree.insert(10, "ten");
        AVLTree.IAVLNode node2 = tree.new AVLNode(8, "eight");
        tree.join(node2, empty_tree);
        int chaeck = tree.delete(10);
        int[] keys = tree.keysToArray();
        String[] info = tree.infoToArray();
        AVLTree[] trees = tree.split(9);
        System.out.println(trees);
        System.out.println(trees[1].max());
        keys = trees[0].keysToArray();
        info = trees[0].infoToArray();
        keys = trees[1].keysToArray();
        info = trees[1].infoToArray();

        ArrayList<Integer> i1_ordered = new ArrayList<>();
        ArrayList<Integer> i1_shuffled = new ArrayList<>();

//        int[] test = {2,13,15,1,4,5,12,6,10,16,14,8,18,17,11,9};
        int[] test = {2,13,15,1,4,5,12,6};
        int j = 2000;

        AVLTree testTree = new AVLTree();
        for (int i=0; i< test.length;i++) {
            testTree.insert(test[i], null);
        }
        testTree.insert(10, null);
        for (int i=0; i<j;i++) {
            i1_ordered.add(j-i);
            i1_shuffled.add(j-i);
        }

        Collections.shuffle(i1_shuffled);

        AVLTree i1_ordered_tree = new AVLTree();
        AVLTree i1_shuffled_tree = new AVLTree();
        int insert_cost_i1_ordered = 0;
        int insert_cost_i1_shuffled = 0;
        for (int key : i1_ordered) {
            insert_cost_i1_ordered+= i1_ordered_tree.insert(key, null);
        }
        for (int key : i1_shuffled) {
            insert_cost_i1_shuffled+= i1_shuffled_tree.insert(key, null);
        }

        double logs = 0;
        for (int i=2; i<=8000; i++) {
            logs+=2*Math.log(i-1);
        }
        System.out.println(logs);
    }





}
