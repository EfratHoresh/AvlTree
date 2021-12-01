/**
 *
 * AVLTree
 *
 * An implementation of aמ AVL Tree with
 * distinct integer keys and info.
 *
 */

public class AVLTree {

    IAVLNode root;
    IAVLNode min;
    IAVLNode max;

    public AVLTree() {
        this.root = null;
        this.min = null;
        this.max = null;
    }

    /**
     * public boolean empty()
     * <p>
     * Returns true if and only if the tree is empty.
     */
    public boolean empty() {
        return (this.getRoot() == null);
    }

    /**
     * public String search(int k)
     * <p>
     * Returns the info of an item with key k if it exists in the tree.
     * otherwise, returns null.
     */
    public String search(int k) {
        IAVLNode node = search_node(k);
        if (node==null) {
            return null;
        }
        return node.getValue();  // to be replaced by student code
    }


    public IAVLNode search_node(int k) {
        IAVLNode node = this.getRoot();
        while (node != null) {
            int node_key = node.getKey();
            if (node_key == k) {
                return node;
            }
            if (node_key > k) {
                node = node.getLeft();
            }
            if (node_key < k) {
                node = node.getRight();
            }
        }
        return null;  // to be replaced by student code
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * Inserts an item with key k and info i to the AVL tree.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        if (this.search(k)!=null) {
            return -1;
        }
//        creating new node and virtual sons
        IAVLNode virtual_node_left = new AVLNode(-1, null);
        IAVLNode virtual_node_right = new AVLNode(-1, null);
        IAVLNode added_node = new AVLNode(k, i);
//        update min amd max
        if (this.max == null || k > this.max.getKey()) {
            this.max = added_node;
        }
        if (this.min == null || k < this.min.getKey()) {
            this.min = added_node;
        }
        int num_of_height_changes = 0;
        added_node.setLeft(virtual_node_left);
        added_node.setRight(virtual_node_right);
        if (this.empty()) {
            this.setRoot(added_node);
            return 0;
        }
//        finding k's place
        IAVLNode node = this.getRoot();
        while (node.getKey() != -1) {
            int node_key = node.getKey();
            if (node_key > k) {
                node = node.getLeft();
            }
            if (node_key < k) {
                node = node.getRight();
            }
        }
//        adding k
        node = node.getParent();
        if (k < node.getKey()) {
            node.setLeft(added_node);
        } else {
            node.setRight(added_node);
        }
//        starting rebalance
        int num_of_rotations = 0;
        while (node != null) {
            int BF = balanceFactor(node);
            if (BF==0) {
                break;
            }
            else if (Math.abs(BF)==1) {
                node.setHeight(node.getHeight()+1); //update height
                num_of_height_changes++;
                node = node.getParent();
            }
            else if (BF == 2) {
                if (balanceFactor(node.getLeft()) == 1) {
                    num_of_height_changes += right(node);
                    num_of_rotations++;
                } else { // node.left.BF == -1
                    num_of_height_changes += left(node.getLeft());
                    num_of_height_changes += right(node);
                    num_of_rotations += 2;
                }
                break;
                }
            else {  // Bf == -2
                if (balanceFactor(node.getRight()) == -1) {
                    num_of_height_changes += left(node);
                    num_of_rotations++;
                } else { // node.right.BF == +1
                    num_of_height_changes += right(node.getRight());
                    num_of_height_changes += left(node);
                    num_of_rotations += 2;
                }
                break;
                }
            }
        updateSizeFromBottom(added_node);
        return num_of_rotations + num_of_height_changes;
    }

    public int updateHeightFromBottom(IAVLNode node) {
        int num_of_height_changes = 0;
        while (node!=null) {
            num_of_height_changes += updateHeight(node);
            node = node.getParent();
        }
        return num_of_height_changes;
    }


    public void updateSizeFromBottom(IAVLNode node) {
        while (node != null) {
            node.updateSize();
            node = node.getParent();
            }
        }


    public void setRoot(IAVLNode new_root) {
        this.root = new_root;
        if (new_root!=null) {
            new_root.setParent(null);
        }
        updateMinMax();
    }

    public int updateHeight(IAVLNode node) {
        int old_height = node.getHeight();
        node.setHeight(1 + Math.max(node.getRight().getHeight(), node.getLeft().getHeight()));
        if (old_height== node.getHeight()) {
            return 0;
        }
        return 1;
    }

    public void updateMinMax() {
        if (this.empty()) {
            this.min = null;
            this.max = null;
            return;
        }
        IAVLNode node = this.getRoot();
        while (node.getLeft().getKey()!=-1) {
            node = node.getLeft();
        }
        this.min = node;
        IAVLNode other_node = this.getRoot();
        while (other_node.getRight().getKey()!=-1) {
            other_node = other_node.getRight();
        }
        this.max = other_node;
    }



    public int balanceFactor(IAVLNode node) {
        int left_height = node.getLeft().getHeight();
        int right_height = node.getRight().getHeight();
        return left_height - right_height;
    }


    public int right(IAVLNode B) {   // right rotation; if B.BF +2, B.left.Bf = +1
        IAVLNode A = B.getLeft();
        IAVLNode B_Parent = B.getParent();
        int num_of_height_changes = 0;
//        A.setParent(B.getParent());
//        B.setParent(A);
//        A.getRight().setParent(B);
        B.setLeft(A.getRight());
        A.setRight(B);
        num_of_height_changes += updateHeight(B);
        num_of_height_changes += updateHeight(A);
        B.updateSize();
        A.updateSize();
        if (B_Parent == null) {
            this.root = A;
            A.setParent(null);
        }
        else if (isRightSon(B_Parent,B)) {
            B_Parent.setRight(A);
        }
        else {  //(B_Parent.getLeft()==B)
            B_Parent.setLeft(A);
        }
        return num_of_height_changes;
    }

    public int left(IAVLNode B) {   // left rotation; if B.BF +2, B.left.Bf = -1
        IAVLNode A = B.getRight();
        IAVLNode B_Parent = B.getParent();
        int num_of_height_changes = 0;
//        A.setParent(B.getParent());
//        B.setParent(A);
//        A.getLeft().setParent(B);
        B.setRight(A.getLeft());
        A.setLeft(B);
        num_of_height_changes += updateHeight(B);
        num_of_height_changes += updateHeight(A);
        B.updateSize();
        A.updateSize();
        if (B_Parent == null) {
            this.root = A;
            A.setParent(null);
        } else if (isRightSon(B_Parent,B)) {
            B_Parent.setRight(A);
        } else {  //(B_Parent.getLeft()==B)
            B_Parent.setLeft(A);
        }
        return num_of_height_changes;
    }


    /**
     * public int delete(int k)
     * <p>
     * Deletes an item with key k from the binary tree, if it is there.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        IAVLNode deleted = search_node(k);
        IAVLNode fix_balance = deleted;
        int num_of_rotations = 0;
        int num_of_height_changes = 0;
        if (deleted == null) {
            return -1;
        }
//        delete node
        IAVLNode virtual_node = new AVLNode(-1, null);
        IAVLNode deleted_parent = deleted.getParent();
        if (deleted.getRight().getKey() == -1 && deleted.getLeft().getKey() == -1) { // deleted is a leaf
            if (deleted_parent == null) { //deleted is root
                this.setRoot(null);
                return 0;
            }
            fix_balance = deleted_parent;
            if (isRightSon(deleted_parent, deleted)) { //deleted is right son
                deleted_parent.setRight(virtual_node);
            } else { //deleted is left son
                deleted_parent.setLeft(virtual_node);
            }
        }
        else if (deleted.getRight().getKey() == -1) { // only left son
            if (deleted_parent == null) {
                deleted.getLeft().setParent(null);
                this.root = deleted.getLeft(); // take care of height
            } else {
                fix_balance = deleted_parent;
                if (isRightSon(deleted_parent, deleted)) { //deleted is a right son
                    deleted_parent.setRight(deleted.getLeft());
                } else { //deleted is left son
                    deleted_parent.setLeft(deleted.getLeft());
                }
            }
        }
        else if (deleted.getLeft().getKey() == -1) { // only right son
            if (deleted_parent == null) {
                deleted.getRight().setParent(null);
                this.root = deleted.getRight(); //
            } else {
                fix_balance = deleted_parent;
                if (isRightSon(deleted_parent, deleted)) { //deleted is a right son
                    deleted_parent.setRight(deleted.getRight());
                } else { //deleted is left son
                    deleted_parent.setLeft(deleted.getRight());
                }
            }
        }
        else { //has 2 sons
            IAVLNode successor = findSuccessor(deleted);
            if (isRightSon(deleted, successor)) {  // successor is deleted right son
                successor.setLeft(deleted.getLeft());
                fix_balance = deleted_parent;
                if (this.getRoot().getKey()==deleted.getKey()) { //deleted was root
                    this.root = successor;
                    successor.setParent(null);
                }
                if (isRightSon(deleted_parent, deleted)) {
                    deleted_parent.setRight(successor);
                }
                else { // deleted is a left son
                    deleted_parent.setLeft(successor);
                }
                num_of_height_changes += updateHeight(successor);
                successor.updateSize();
            }
            else {
                fix_balance = successor.getParent();
                successor.getParent().setLeft(successor.getRight());
                successor.setLeft(deleted.getLeft());
                successor.setRight(deleted.getRight());
                if (deleted_parent==null) { //deleted is root
                    this.root = successor;
                    successor.setParent(null);
                }
                else if (isRightSon(deleted_parent, deleted)) {
                    deleted_parent.setRight(successor);
                }
                else {
                    deleted_parent.setLeft(successor);
                }
            }
        }
        IAVLNode size_update = fix_balance;
        while (fix_balance != null) {
            int old_height = fix_balance.getHeight();
            num_of_height_changes += updateHeight(fix_balance);
            int BF = balanceFactor(fix_balance);
            if (Math.abs(BF)<2 && old_height==fix_balance.getHeight()) {
                break;
            }
            else if (Math.abs(BF)<2) {
                fix_balance = fix_balance.getParent();
            }
            else {
                if (BF == 2) {
                    if (balanceFactor(fix_balance.getLeft()) == 1 || balanceFactor(fix_balance.getLeft()) == 0) {
                        num_of_height_changes += right(fix_balance);
                        num_of_rotations++;
                    } else { // node.left.BF == -1
                        num_of_height_changes += left(fix_balance.getLeft());
                        num_of_height_changes += right(fix_balance);
                        num_of_rotations += 2;
                    }
                }
                else {  // Bf == -2
                    if (balanceFactor(fix_balance.getRight()) == -1 || balanceFactor(fix_balance.getRight()) == 0) {
                        num_of_height_changes += left(fix_balance);
                        num_of_rotations++;
                    } else { // node.right.BF == +1
                        num_of_height_changes += right(fix_balance.getRight());
                        num_of_height_changes += left(fix_balance);
                        num_of_rotations += 2;
                    }
                }
        }
        }
        num_of_height_changes += updateHeightFromBottom(fix_balance);
        updateSizeFromBottom(fix_balance);
        if (k==this.min.getKey() || k==this.max.getKey()) {
            updateMinMax();
        }
        return num_of_rotations + num_of_height_changes;
    }

// node.getRight!=null;
    public IAVLNode findSuccessor(IAVLNode node) {
        node = node.getRight();
        while (node.getLeft().getKey()!=-1) {
            node = node.getLeft();
        }
        return node;
    }

    public boolean isRightSon(IAVLNode parent, IAVLNode son) {
        if (parent==null) {
            return false;
        }
        return equals(son, parent.getRight());
    }


    public static boolean equals(IAVLNode first, IAVLNode second) {
        if (first==null && second == null) {
            return true;
        }
        else if (first==null || second == null) {
            return false;
        }
        return first.getKey()==second.getKey();
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty.
     */
    public String min() {
        if (this.empty()) {
            return null;
        }
        return this.min.getValue();
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty.
     */
    public String max() {
        if (this.empty()) {
            return null;
        }
        return this.max.getValue();
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        int[] arrayOfKeys = new int[this.size()];
        int[][] answer = EnvelopeKeysToArray(arrayOfKeys, 0, this.getRoot());
        return answer[0]; // to be replaced by student code
    }

    public int[][] EnvelopeKeysToArray(int[] array, int index, IAVLNode node) {
        if (node.getKey() == -1) {
            int[][] for_return = {array, {index}};
            return for_return;
        }
        int[][] left = EnvelopeKeysToArray(array, index, node.getLeft());
        array = left[0];
        index = left[1][0];
        array[index] = node.getKey();
        int[][] right = EnvelopeKeysToArray(array, index + 1, node.getRight());
        int[][] for_return = {right[0], right[1]};
        return for_return;

    }

    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public String[] infoToArray() {
        String[] arrayOfInfo = new String[this.size()];
        String[][] answer = EnvelopeInfoToArray(arrayOfInfo, "0", this.getRoot());
        return answer[0]; // to be replaced by student code
    }

    public String[][] EnvelopeInfoToArray(String[] array, String index, IAVLNode node) {
        if (node.getKey() == -1) {
            String[][] for_return = {array, {index}};
            return for_return;
        }
        String[][] left = EnvelopeInfoToArray(array, index, node.getLeft());
        array = left[0];
        index = left[1][0];
        int int_index = Integer.parseInt(index);
        array[int_index] = node.getValue();
        int_index++;
        String[][] right = EnvelopeInfoToArray(array, String.valueOf(int_index), node.getRight());
        String[][] for_return = {right[0], right[1]};
        return for_return;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        if (this.empty()) {
            return 0;
        }
        return this.getRoot().getSize(); // to be replaced by student code
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public IAVLNode getRoot() {
        return this.root;
    }

    public int getTreeHeight() {
        return this.getRoot().getHeight();
    }


        /**
         * public AVLTree[] split(int x)
         * <p>
         * splits the tree into 2 trees according to the key x.
         * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
         * <p>
         * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
         * postcondition: none
         */
    public AVLTree[] split(int x) {
        IAVLNode node = search_node(x);
        AVLTree T1 = new AVLTree();
        AVLTree T2 = new AVLTree();
        AVLTree[] trees = {T1, T2};
        if (node.getLeft().getKey()!=-1) {
            T1.root = node.getLeft();
            T1.root.setParent(null);
        }
        if (node.getRight().getKey()!=-1) {
            T2.root = node.getRight();
            T2.root.setParent(null);
        }
        if (node.equals(this.root)) {
            T1.updateMinMax();
            T2.updateMinMax();
            return trees;
        }
        IAVLNode prev_node = node;
        node = node.getParent();
        IAVLNode next_node = node.getParent();
        AVLTree added_tree = new AVLTree();
        while (node!=null) {
            if (this.isRightSon(node, prev_node)) {
                if (node.getLeft().getKey()!=-1) {
                    added_tree.setRoot(node.getLeft());
                }
                else {
                    added_tree.setRoot(null);
                }
                T1.join(node, added_tree);
            }
            else {
                if (node.getRight().getKey()!=-1) {
                    added_tree.setRoot(node.getRight());
                }
                else {
                    added_tree.setRoot(null);
                }
                    T2.join(node, added_tree);
            }
            prev_node = node;
            node = next_node;
            if (next_node!=null) {
                next_node = next_node.getParent();
            }
        }
        T1.updateMinMax();
        T2.updateMinMax();
        return trees;
    }

    public int splitCost(int x) {
        IAVLNode node = search_node(x);
        AVLTree T1 = new AVLTree();
        AVLTree T2 = new AVLTree();
        int cost = 0;
        AVLTree[] trees = {T1, T2};
        if (node.getLeft().getKey()!=-1) {
            T1.root = node.getLeft();
            T1.root.setParent(null);
        }
        if (node.getRight().getKey()!=-1) {
            T2.root = node.getRight();
            T2.root.setParent(null);
        }
        if (node.equals(this.root)) {
            T1.updateMinMax();
            T2.updateMinMax();
            return 0;
        }
        IAVLNode prev_node = node;
        node = node.getParent();
        IAVLNode next_node = node.getParent();
        AVLTree added_tree = new AVLTree();
        while (node!=null) {
            if (this.isRightSon(node, prev_node)) {
                if (node.getLeft().getKey()!=-1) {
                    added_tree.setRoot(node.getLeft());
                }
                else {
                    added_tree.setRoot(null);
                }
                cost+= T1.join(node, added_tree);
            }
            else {
                if (node.getRight().getKey()!=-1) {
                    added_tree.setRoot(node.getRight());
                }
                else {
                    added_tree.setRoot(null);
                }
                cost+=T2.join(node, added_tree);
            }
            prev_node = node;
            node = next_node;
            if (next_node!=null) {
                next_node = next_node.getParent();
            }
        }
        T1.updateMinMax();
        T2.updateMinMax();
        return cost;
    }


    /**
     * public int join(IAVLNode x, AVLTree t)
     * <p>
     * joins t and x with the tree.
     * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
     * <p>
     * precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
     * postcondition: none
     */


    public int join(IAVLNode x, AVLTree t) {
        // save original heights
        IAVLNode virtual_node_left = new AVLNode(-1, null);
        IAVLNode virtual_node_right = new AVLNode(-1, null);
        x.setRight(virtual_node_right);
        x.setLeft(virtual_node_left);
        x.setParent(null);
        if (t.empty() && this.empty()) {
            this.setRoot(x);
            return 1;
        }
        int t_height;
        int this_height;
        if (t.empty()) {
            t.root = virtual_node_left;
        }
        if (this.empty()) {
            this.root = virtual_node_right;
        }
        t_height = t.getTreeHeight();
        this_height = this.getTreeHeight();
        AVLTree tall_tree;
        AVLTree short_tree;
        if (t_height <= this_height) {
            short_tree = t;
            tall_tree = this;
        }
        else {
            short_tree = this;
            tall_tree = t;
        }
        IAVLNode h_node = tall_tree.getRoot();
//        find min max
        if (tall_tree.getRoot().getKey() > x.getKey()) {
            this.max = tall_tree.max;
            if (short_tree.empty()) {
                this.min = x;
            }
            else {
                this.min = short_tree.min;
            }
        }
        else {
            this.min = tall_tree.min;
            if (short_tree.empty()) {
                this.max = x;
            }
            else {
                this.max = short_tree.max;
            }
        }
        if (tall_tree.getRoot().getKey() > x.getKey()) {
            while (h_node.getHeight() > short_tree.getTreeHeight()) {
                h_node = h_node.getLeft();
            }
            if (h_node.getParent()!=null) {
                h_node.getParent().setLeft(x);
            }
            x.setRight(h_node);
            x.setLeft(short_tree.getRoot());
        }
        else { //tall tree smaller than x
            while (h_node.getHeight() > short_tree.getTreeHeight()) {
                h_node = h_node.getRight();
            }
            if (h_node.getParent()!=null) {
                h_node.getParent().setRight(x);
            }
            x.setLeft(h_node);
            x.setRight(short_tree.getRoot());
        }
        while (x!=null) {
            this.updateHeight(x);
            x.updateSize();
            int BF = balanceFactor(x);
            if (BF==2) {
                if (balanceFactor(x.getLeft()) == 1 || balanceFactor(x.getLeft()) == 0) {
                    right(x);
                }
                else {
                    left(x.getLeft());
                    right(x);
                }
            }
            else if (BF==-2) {
                if (balanceFactor(x.getRight()) == -1 || balanceFactor(x.getRight()) == 0) {
                    left(x);
                }
                else {
                    right(x.getRight());
                }
            }
            if (x.getParent()==null) {
                break;
            }
            x = x.getParent();
        }
        this.root = x;
        x.updateSize();
        this.updateHeight(x);
        this.updateMinMax();
        return Math.abs(this_height-t_height) + 1;
    }

    public int insert_from_max(int k, String i) {
//        creating new node and virtual sons
        IAVLNode virtual_node = new AVLNode(-1, null);
        IAVLNode added_node = new AVLNode(k, i);
//        update min amd max
        if (this.max == null || k > this.max.getKey()) {
            this.max = added_node;
        }
        if (this.min == null || k < this.min.getKey()) {
            this.min = added_node;
        }
        int num_of_edges = 0;
        int num_of_height_changes = 0;
        added_node.setLeft(virtual_node);
        added_node.setRight(virtual_node);
        if (this.empty()) {
            this.setRoot(added_node);
            return 0;
        }
//        finger search - finding k's place
        IAVLNode node = this.max;
        while (node.getParent()!=null) {
            if (node.getKey() > k && node.getParent().getKey() < k) {
                break;
            }
            node = node.getParent();
            num_of_edges++;
        }
        node = node.getLeft();
        num_of_edges++;
        while (node.getKey() != -1) {
            int node_key = node.getKey();
            if (node_key > k) {
                node = node.getLeft();
                num_of_edges++;
            }
            if (node_key < k) {
                node = node.getRight();
                num_of_edges++;
            }
        }
//        adding k
        node = node.getParent();
        num_of_edges++;
        if (k < node.getKey()) {
            node.setLeft(added_node);
        } else {
            node.setRight(added_node);
        }
//        starting rebalance
        int num_of_rotations = 0;
        while (node != null) {
            int BF = balanceFactor(node);
            if (BF==0) {
                break;
            }
            else if (Math.abs(BF)==1) {
                node.setHeight(node.getHeight()+1); //update height
                num_of_height_changes++;
                node = node.getParent();
            }
            else if (BF == 2) {
                if (balanceFactor(node.getLeft()) == 1) {
                    num_of_height_changes += right(node);
                    num_of_rotations++;
                } else { // node.left.BF == -1
                    num_of_height_changes += left(node.getLeft());
                    num_of_height_changes += right(node);
                    num_of_rotations += 2;
                }
                break;
            }
            else {  // Bf == -2
                if (balanceFactor(node.getRight()) == -1) {
                    num_of_height_changes += left(node);
                    num_of_rotations++;
                } else { // node.right.BF == +1
                    num_of_height_changes += right(node.getRight());
                    num_of_height_changes += left(node);
                    num_of_rotations += 2;
                }
                break;
            }
        }
        updateSizeFromBottom(added_node);
        return num_of_edges;
    }



    /**
     * public interface IAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IAVLNode {
        public int getKey(); // Returns node's key (for virtual node return -1).

        public String getValue(); // Returns node's value [info], for virtual node returns null.

        public void setLeft(IAVLNode node); // Sets left child.

        public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.

        public void setRight(IAVLNode node); // Sets right child.

        public IAVLNode getRight(); // Returns right child, if there is no right child return null.

        public void setParent(IAVLNode node); // Sets parent.

        public IAVLNode getParent(); // Returns the parent, if there is no parent return null.

        public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.

        public void setHeight(int height); // Sets the height of the node.

        public int getHeight(); // Returns the height of the node (-1 for virtual nodes).

        public int getSize();

        public void updateSize();

    }

    /**
     * public class AVLNode
     * <p>
     * If you wish to implement classes other than AVLTree
     * (for example AVLNode), do it in this file, not in another file.
     * <p>
     * This class can and MUST be modified (It must implement IAVLNode).
     */
    public class AVLNode implements IAVLNode {

        int key;
        String info;
        IAVLNode parent;
        IAVLNode left;
        IAVLNode right;
        int height;
        int size;

        public AVLNode(int key, String info) {
            this.key = key;
            this.info = info;
            this.parent = null;
            this.left = null;
            this.right = null;
            if (key == -1) {
                this.height = -1;
                this.size = 0;
            } else {
                this.height = 0;
                this.size = 1;
            }
        }


        public int getKey() {
            return this.key;
        }

        public String getValue() {
            return this.info;
        }

        public void setLeft(IAVLNode node) {
            IAVLNode virtal_node = new AVLNode(-1, null);
            if (node == null) {
                node = virtal_node;
            }
            this.left = node;
            node.setParent(this);
        }

        public IAVLNode getLeft() {
            return this.left; // to be replaced by student code
        }

        public void setRight(IAVLNode node) {
            IAVLNode virtal_node = new AVLNode(-1, null);
            if (node == null) {
                node = virtal_node;
            }
            this.right = node;
            node.setParent(this);
        }

        public IAVLNode getRight() {
            return this.right; // to be replaced by student code
        }

        public void setParent(IAVLNode node) {
            this.parent = node; // to be replaced by student code
        }

        public IAVLNode getParent() {
            return this.parent; // to be replaced by student code
        }

        public boolean isRealNode() {
            return (this.key != -1); // to be replaced by student code
        }

        public void setHeight(int height) {
            this.height = height; // to be replaced by student code
        }

        public int getHeight() {
            return this.height; // to be replaced by student code
        }

        public int getSize() {return this.size;}

        public void updateSize() {this.size = this.getLeft().getSize()+this.getRight().getSize()+1;}

    }
    }



