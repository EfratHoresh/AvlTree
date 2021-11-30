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

        for (int i=0;i<2000;i++) {
            t1_random.insert(i, null);
            t1_max_left.insert(i, null);
        }

        for (int i=0;i<4000;i++) {
            t2_random.insert(i, null);
            t2_max_left.insert(i, null);
        }

        for (int i=0;i<8000;i++) {
            t3_random.insert(i, null);
            t3_max_left.insert(i, null);
        }

        for (int i=0;i<16000;i++) {
            t4_random.insert(i, null);
            t4_max_left.insert(i, null);
        }

        for (int i=0;i<32000;i++) {
            t5_random.insert(i, null);
            t5_max_left.insert(i, null);
        }

        for (int i=0;i<64000;i++) {
            t6_random.insert(i, null);
            t6_max_left.insert(i, null);
        }

        for (int i=0;i<128000;i++) {
            t7_random.insert(i, null);
            t7_max_left.insert(i, null);
        }

        for (int i=0;i<256000;i++) {
            t8_random.insert(i, null);
            t8_max_left.insert(i, null);
        }

        for (int i=0;i<512000;i++) {
            t9_random.insert(i, null);
            t9_max_left.insert(i, null);
        }

        for (int i=0;i<1024000;i++) {
            t10_random.insert(i, null);
            t10_max_left.insert(i, null);
        }


    }
}
