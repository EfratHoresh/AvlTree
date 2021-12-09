import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Q2 {
    public static void main(String[] args) {
//        create tree
        AVLTree t1_random = new AVLTree();
        AVLTree t2_random = new AVLTree();
        AVLTree t3_random = new AVLTree();
        AVLTree t4_random = new AVLTree();
        AVLTree t5_random = new AVLTree();
        AVLTree t6_random = new AVLTree();
        AVLTree t7_random = new AVLTree();
        AVLTree t8_random = new AVLTree();
        AVLTree t9_random = new AVLTree();
        AVLTree t10_random = new AVLTree();
        AVLTree t1_max_left = new AVLTree();
        AVLTree t2_max_left = new AVLTree();
        AVLTree t3_max_left = new AVLTree();
        AVLTree t4_max_left = new AVLTree();
        AVLTree t5_max_left = new AVLTree();
        AVLTree t6_max_left = new AVLTree();
        AVLTree t7_max_left = new AVLTree();
        AVLTree t8_max_left = new AVLTree();
        AVLTree t9_max_left = new AVLTree();
        AVLTree t10_max_left = new AVLTree();

//        ArrayList<Integer> i1 = new ArrayList<>();
//        ArrayList<Integer> i2 = new ArrayList<>();
//        ArrayList<Integer> i3 = new ArrayList<>();
//        ArrayList<Integer> i4 = new ArrayList<>();
//        ArrayList<Integer> i5 = new ArrayList<>();
//        ArrayList<Integer> i6 = new ArrayList<>();
//        ArrayList<Integer> i7 = new ArrayList<>();
//        ArrayList<Integer> i8 = new ArrayList<>();
//        ArrayList<Integer> i9 = new ArrayList<>();
//        ArrayList<Integer> i10 = new ArrayList<>();
//
//
//        for (int i=0;i<2000;i++) {
//            i1.add(i);
//        }
//
//
//        for (int i=0;i<4000;i++) {
//            i2.add(i);
//        }
//
//        for (int i=0;i<8000;i++) {
//            i3.add(i);
//        }
//
//        for (int i=0;i<16000;i++) {
//            i4.add(i);
//        }
//
//        for (int i=0;i<32000;i++) {
//            i5.add(i);
//        }
//
//        for (int i=0;i<64000;i++) {
//            i6.add(i);
//        }
//
//        for (int i=0;i<128000;i++) {
//            i7.add(i);
//        }
//
//        for (int i=0;i<256000;i++) {
//            i8.add(i);
//        }
//
//        for (int i=0;i<512000;i++) {
//            i9.add(i);
//        }
//
//        for (int i=0;i<1024000;i++) {
//            i10.add(i);
//        }
//
//        Collections.shuffle(i1);
//        Collections.shuffle(i2);
//        Collections.shuffle(i3);
//        Collections.shuffle(i4);
//        Collections.shuffle(i5);
//        Collections.shuffle(i6);
//        Collections.shuffle(i7);
//        Collections.shuffle(i8);
//        Collections.shuffle(i9);
//        Collections.shuffle(i10);
//
//        for (Integer key: i1) {
//            t1_random.insert(key, null);
//            t1_max_left.insert(key, null);
//        }
////        t1_random.delete(t1_random.getRoot().getKey());
////        System.out.println(t1_random.size());
//
//        for (Integer key: i2) {
//            t2_random.insert(key, null);
//            t2_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i3) {
//            t3_random.insert(key, null);
//            t3_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i4) {
//            t4_random.insert(key, null);
//            t4_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i5) {
//            t5_random.insert(key, null);
//            t5_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i6) {
//            t6_random.insert(key, null);
//            t6_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i7) {
//            t7_random.insert(key, null);
//            t7_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i8) {
//            t8_random.insert(key, null);
//            t8_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i9) {
//            t9_random.insert(key, null);
//            t9_max_left.insert(key, null);
//        }
//
//
//        for (Integer key: i10) {
//            t10_random.insert(key, null);
//            t10_max_left.insert(key, null);
//        }
//
//        Random random = new Random();
//        int t1_random_key = random.nextInt(2000);
//        int t2_random_key = random.nextInt(4000);
//        int t3_random_key = random.nextInt(8000);
//        int t4_random_key = random.nextInt(16000);
//        int t5_random_key = random.nextInt(32000);
//        int t6_random_key = random.nextInt(64000);
//        int t7_random_key = random.nextInt(128000);
//        int t8_random_key = random.nextInt(256000);
//        int t9_random_key = random.nextInt(512000);
//        int t10_random_key = random.nextInt(1024000);
//
//
//        double[] t1_random_cost = t1_random.splitCost(t1_random_key);
//        double[] t2_random_cost = t2_random.splitCost(t2_random_key);
//        double[] t3_random_cost = t3_random.splitCost(t3_random_key);
//        double[] t4_random_cost = t4_random.splitCost(t4_random_key);
//        double[] t5_random_cost = t5_random.splitCost(t5_random_key);
//        double[] t6_random_cost = t6_random.splitCost(t6_random_key);
//        double[] t7_random_cost = t7_random.splitCost(t7_random_key);
//        double[] t8_random_cost = t8_random.splitCost(t8_random_key);
//        double[] t9_random_cost = t9_random.splitCost(t9_random_key);
//        double[] t10_random_cost = t10_random.splitCost(t10_random_key);
//
//
//        System.out.println("t1 random average = " +t1_random_cost[0] +" random max = "+t1_random_cost[1]);
//        System.out.println("t2 random average = " +t2_random_cost[0] +" random max = "+t2_random_cost[1]);
//        System.out.println("t3 random average = " +t3_random_cost[0] +" random max = "+t3_random_cost[1]);
//        System.out.println("t4 random average = " +t4_random_cost[0] +" random max = "+t4_random_cost[1]);
//        System.out.println("t5 random average = " +t5_random_cost[0] +" random max = "+t5_random_cost[1]);
//        System.out.println("t6 random average = " +t6_random_cost[0] +" random max = "+t6_random_cost[1]);
//        System.out.println("t7 random average = " +t7_random_cost[0] +" random max = "+t7_random_cost[1]);
//        System.out.println("t8 random average = " +t8_random_cost[0] +" random max = "+t8_random_cost[1]);
//        System.out.println("t9 random average = " +t9_random_cost[0] +" random max = "+t9_random_cost[1]);
//        System.out.println("t10 random average = " +t10_random_cost[0] +" random max = "+t10_random_cost[1]);
//
//        double[] t1_max_cost = t1_max_left.splitCost(findMaxLeft(t1_max_left));
//        double[] t2_max_cost = t2_max_left.splitCost(findMaxLeft(t2_max_left));
//        double[] t3_max_cost = t3_max_left.splitCost(findMaxLeft(t3_max_left));
//        double[] t4_max_cost = t4_max_left.splitCost(findMaxLeft(t4_max_left));
//        double[] t5_max_cost = t5_max_left.splitCost(findMaxLeft(t5_max_left));
//        double[] t6_max_cost = t6_max_left.splitCost(findMaxLeft(t6_max_left));
//        double[] t7_max_cost = t7_max_left.splitCost(findMaxLeft(t7_max_left));
//        double[] t8_max_cost = t8_max_left.splitCost(findMaxLeft(t8_max_left));
//        double[] t9_max_cost = t9_max_left.splitCost(findMaxLeft(t9_max_left));
//        double[] t10_max_cost = t10_max_left.splitCost(findMaxLeft(t10_max_left));
//
//
//
//        System.out.println("t1 max average = " + t1_max_cost[0] + " max max = " + t1_max_cost[1]);
//        System.out.println("t2 max average = " + t2_max_cost[0] + " max max = " + t2_max_cost[1]);
//        System.out.println("t3 max average = " + t3_max_cost[0] + " max max = " + t3_max_cost[1]);
//        System.out.println("t4 max average = " + t4_max_cost[0] + " max max = " + t4_max_cost[1]);
//        System.out.println("t5 max average = " + t5_max_cost[0] + " max max = " + t5_max_cost[1]);
//        System.out.println("t6 max average = " + t6_max_cost[0] + " max max = " + t6_max_cost[1]);
//        System.out.println("t7 max average = " + t7_max_cost[0] + " max max = " + t7_max_cost[1]);
//        System.out.println("t8 max average = " + t8_max_cost[0] + " max max = " + t8_max_cost[1]);
//        System.out.println("t9 max average = " + t9_max_cost[0] + " max max = " + t9_max_cost[1]);
//        System.out.println("t10 max average = " + t10_max_cost[0] + " max max = " + t10_max_cost[1]);
//
//
//
//    }
//
//    public static int findMaxLeft(AVLTree tree) {
//        AVLTree.IAVLNode node = tree.getRoot();
//        node = node.getLeft();
//        while (node.getRight().getKey()!=-1) {
//            node = node.getRight();
//        }
//        return node.getKey();
    }

}
