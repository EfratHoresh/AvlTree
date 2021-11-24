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

    public AVLTree() {
        this.root = null;
    }

    /**
     * public boolean empty()
     * <p>
     * Returns true if and only if the tree is empty.
     */
    public boolean empty() {
        return (this.root == null);
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
        IAVLNode node = this.root;
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
        IAVLNode virtual_node = new AVLNode(-1, null);
        IAVLNode added_node = new AVLNode(k, i);
        int num_of_height_changes = 0;
        virtual_node.setParent(added_node);
        added_node.setLeft(virtual_node);
        added_node.setRight(virtual_node);
        if (this.empty()) {
            this.root = added_node;
            return 0;
        }
//        finding k's place
        IAVLNode node = this.root;
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
                return 0;
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


        public int updateHeight(IAVLNode node) {
        int old_height = node.getHeight();
        node.setHeight(1 + Math.max(node.getRight().getHeight(), node.getLeft().getHeight()));
        if (old_height== node.getHeight()) {
            return 0;
        }
        return 1;
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
        A.setParent(B.getParent());
        B.setParent(A);
        A.getRight().setParent(B);
        B.setLeft(A.getRight());
        A.setRight(B);
        num_of_height_changes += updateHeight(B);
        num_of_height_changes += updateHeight(A);
        B.updateSize();
        A.updateSize();
        if (B_Parent == null) {
            this.root = A;
        }
        else if (B_Parent.getRight()==B) {
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
        A.setParent(B.getParent());
        B.setParent(A);
        A.getLeft().setParent(B);
        B.setRight(A.getLeft());
        A.setLeft(B);
        num_of_height_changes += updateHeight(B);
        num_of_height_changes += updateHeight(A);
        B.updateSize();
        A.updateSize();
        if (B_Parent == null) {
            this.root = A;
        } else if (B_Parent.getRight() == B) {
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
                this.root = null;
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
                this.root = deleted.getRight(); // return? or take care of height?
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
        IAVLNode node = this.root;
        while (node.getLeft() != null && node.getLeft().getKey() != -1) {
            node = node.getLeft();
        }
        return node.getValue();
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
        IAVLNode node = this.root;
        while (node.getRight() != null && node.getRight().getKey() != -1) {
            node = node.getRight();
        }
        return node.getValue();
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
        AVLNode root = (AVLNode) this.getRoot();
        return root.size; // to be replaced by student code
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public IAVLNode getRoot() {
        return this.root;
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
        return null;
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
        return -1;
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
            this.left = node;
            node.setParent(this);
        }

        public IAVLNode getLeft() {
            return this.left; // to be replaced by student code
        }

        public void setRight(IAVLNode node) {
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



