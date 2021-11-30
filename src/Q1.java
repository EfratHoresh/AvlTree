import java.util.ArrayList;
import java.util.Collections;

public class Q1 {
    public static void main(String[] args) {
        ArrayList<Integer> i1_ordered = new ArrayList<>();
        ArrayList<Integer> i1_shuffled = new ArrayList<>();
        ArrayList<Integer> i2_ordered = new ArrayList<>();
        ArrayList<Integer> i2_shuffled = new ArrayList<>();
        ArrayList<Integer> i3_ordered = new ArrayList<>();
        ArrayList<Integer> i3_shuffled = new ArrayList<>();
        ArrayList<Integer> i4_ordered = new ArrayList<>();
        ArrayList<Integer> i4_shuffled = new ArrayList<>();
        ArrayList<Integer> i5_ordered = new ArrayList<>();
        ArrayList<Integer> i5_shuffled = new ArrayList<>();

        for (int i=0; i<2000;i++) {
            i1_ordered.add(2000-i);
            i1_shuffled.add(2000-i);
        }

        for (int i=0; i<4000;i++) {
            i2_ordered.add(4000-i);
            i2_shuffled.add(4000-i);
        }

        for (int i=0; i<8000;i++) {
            i3_ordered.add(8000-i);
            i3_shuffled.add(8000-i);
        }

        for (int i=0; i<16000;i++) {
            i4_ordered.add(16000-i);
            i4_shuffled.add(16000-i);
        }

        for (int i=0; i<32000;i++) {
            i5_ordered.add(32000-i);
            i5_shuffled.add(32000-i);
        }

        Collections.shuffle(i1_shuffled);
        Collections.shuffle(i2_shuffled);
        Collections.shuffle(i3_shuffled);
        Collections.shuffle(i4_shuffled);
        Collections.shuffle(i5_shuffled);


        AVLTree i1_ordered_tree = new AVLTree();
        AVLTree i1_shuffled_tree = new AVLTree();

        AVLTree i2_ordered_tree = new AVLTree();
        AVLTree i2_shuffled_tree = new AVLTree();


        AVLTree i3_ordered_tree = new AVLTree();
        AVLTree i3_shuffled_tree = new AVLTree();


        AVLTree i4_ordered_tree = new AVLTree();
        AVLTree i4_shuffled_tree = new AVLTree();


        AVLTree i5_ordered_tree = new AVLTree();
        AVLTree i5_shuffled_tree = new AVLTree();

        int insert_cost_i1_ordered = 0;
        int insert_cost_i1_shuffled = 0;

        int insert_cost_i2_ordered = 0;
        int insert_cost_i2_shuffled = 0;

        int insert_cost_i3_ordered = 0;
        int insert_cost_i3_shuffled = 0;

        int insert_cost_i4_ordered = 0;
        int insert_cost_i4_shuffled = 0;

        int insert_cost_i5_ordered = 0;
        int insert_cost_i5_shuffled = 0;


        for (int key : i1_ordered) {
            insert_cost_i1_ordered+= i1_ordered_tree.insert_from_max(key, null);
        }
        for (int key : i1_shuffled) {
            insert_cost_i1_shuffled+= i1_shuffled_tree.insert(key, null);
        }

        for (int key : i2_ordered) {
            insert_cost_i2_ordered+= i2_ordered_tree.insert_from_max(key, null);
        }
        for (int key : i2_shuffled) {
            insert_cost_i2_shuffled+= i2_shuffled_tree.insert(key, null);
        }
        for (int key : i3_ordered) {
            insert_cost_i3_ordered+= i3_ordered_tree.insert_from_max(key, null);
        }
        for (int key : i3_shuffled) {
            insert_cost_i3_shuffled+= i3_shuffled_tree.insert(key, null);
        }
        for (int key : i4_ordered) {
            insert_cost_i4_ordered+= i4_ordered_tree.insert_from_max(key, null);
        }
        for (int key : i4_shuffled) {
            insert_cost_i4_shuffled+= i4_shuffled_tree.insert(key, null);
        }
        for (int key : i5_ordered) {
            insert_cost_i5_ordered+= i5_ordered_tree.insert_from_max(key, null);
        }
        for (int key : i5_shuffled) {
            insert_cost_i5_shuffled+= i5_shuffled_tree.insert(key, null);
        }

        int[] i1_ordered_from_bottom = i1_ordered_tree.keysToArray();
        int[] i2_ordered_from_bottom = i2_ordered_tree.keysToArray();
        int[] i3_ordered_from_bottom = i3_ordered_tree.keysToArray();
        int[] i4_ordered_from_bottom = i4_ordered_tree.keysToArray();
        int[] i5_ordered_from_bottom = i5_ordered_tree.keysToArray();

        int i1_changes_shuffle = 0;
        int i2_changes_shuffle = 0;
        int i3_changes_shuffle = 0;
        int i4_changes_shuffle = 0;
        int i5_changes_shuffle = 0;


        int i1_changes_ordered = 0;
        int i2_changes_ordered = 0;
        int i3_changes_ordered = 0;
        int i4_changes_ordered = 0;
        int i5_changes_ordered = 0;

        for (int i=0;i<i1_ordered_from_bottom.length;i++) {
            for (int j=i+1;j<i1_ordered_from_bottom.length;j++) {
                if (i1_ordered_from_bottom[i]!=i1_shuffled.get(j)) {
                    i1_changes_shuffle++;
                }
                if (i1_ordered_from_bottom[i]!=i1_ordered.get(j)) {
                    i1_changes_ordered++;
                }
            }
        }

        for (int i=0;i<i2_ordered_from_bottom.length;i++) {
            for (int j=i+1;j<i2_ordered_from_bottom.length;j++) {
                if (i2_ordered_from_bottom[i]!=i2_shuffled.get(j)) {
                    i2_changes_shuffle++;
                }
                if (i2_ordered_from_bottom[i]!=i2_ordered.get(j)) {
                    i2_changes_ordered++;
                }
            }
        }

        for (int i=0;i<i3_ordered_from_bottom.length;i++) {
            for (int j=i+1;j<i3_ordered_from_bottom.length;j++) {
                if (i3_ordered_from_bottom[i]!=i3_shuffled.get(j)) {
                    i3_changes_shuffle++;
                }
                if (i3_ordered_from_bottom[i]!=i3_ordered.get(j)) {
                    i3_changes_ordered++;
                }
            }
        }

        for (int i=0;i<i4_ordered_from_bottom.length;i++) {
            for (int j=i+1;j<i4_ordered_from_bottom.length;j++) {
                if (i4_ordered_from_bottom[i]!=i4_shuffled.get(j)) {
                    i4_changes_shuffle++;
                }
                if (i4_ordered_from_bottom[i]!=i4_ordered.get(j)) {
                    i4_changes_ordered++;
                }
            }
        }

        for (int i=0;i<i5_ordered_from_bottom.length;i++) {
            for (int j=i+1;j<i5_ordered_from_bottom.length;j++) {
                if (i5_ordered_from_bottom[i]!=i5_shuffled.get(j)) {
                    i5_changes_shuffle++;
                }
                if (i5_ordered_from_bottom[i]!=i5_ordered.get(j)) {
                    i5_changes_ordered++;
                }
            }
        }

        System.out.println("i1 insert cost in ordered list: " + insert_cost_i1_ordered);
        System.out.println("i1 insert cost in shuffled list: " + insert_cost_i1_shuffled);

        System.out.println("i2 insert cost in ordered list: " + insert_cost_i2_ordered);
        System.out.println("i2 insert cost in shuffled list: " + insert_cost_i2_shuffled);

        System.out.println("i3 insert cost in ordered list: " + insert_cost_i3_ordered);
        System.out.println("i3 insert cost in shuffled list: " + insert_cost_i3_shuffled);

        System.out.println("i4 insert cost in ordered list: " + insert_cost_i4_ordered);
        System.out.println("i4 insert cost in shuffled list: " + insert_cost_i4_shuffled);

        System.out.println("i5 insert cost in ordered list: " + insert_cost_i5_ordered);
        System.out.println("i5 insert cost in shuffled list: " + insert_cost_i5_shuffled);

        System.out.println("num of ordered changes in i1 " + i1_changes_ordered);
        System.out.println("num of ordered changes in i2 " + i2_changes_ordered);
        System.out.println("num of ordered changes in i3 " + i3_changes_ordered);
        System.out.println("num of ordered changes in i4 " + i4_changes_ordered);
        System.out.println("num of ordered changes in i5 " + i5_changes_ordered);

        System.out.println("num of shuffle changes in i1 " + i1_changes_shuffle);
        System.out.println("num of shuffle changes in i2 " + i2_changes_shuffle);
        System.out.println("num of shuffle changes in i3 " + i3_changes_shuffle);
        System.out.println("num of shuffle changes in i4 " + i4_changes_shuffle);
        System.out.println("num of shuffle changes in i5 " + i5_changes_shuffle);

    }
    }

