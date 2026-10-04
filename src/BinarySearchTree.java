import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    //Узел и вставка

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    private TreeNode root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }

        return node;
    }

    //Поиск

    public boolean contains(int value) {
        return containsRec(root, value);
    }

    private boolean containsRec(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }
        if (value < node.value) {
            return containsRec(node.left, value);
        } else {
            return containsRec(node.right, value);
        }
    }

    //Удаление

    public void remove(int value) {
        root = removeRec(root, value);
    }

    private TreeNode removeRec(TreeNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = removeRec(node.left, value);
        } else if (value > node.value) {
            node.right = removeRec(node.right, value);
        } else {

            //Лист
            if (node.left == null && node.right == null) {
                return null;
            }

            //Один потомок
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            //Два потомка
            TreeNode successor = findMin(node.right);
            node.value = successor.value;
            node.right = removeRec(node.right, successor.value);
        }

        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //Обходы

    public List<Integer> inOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inOrderRec(node.left, result);
        result.add(node.value);
        inOrderRec(node.right, result);
    }

    public List<Integer> preOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        preOrderRec(root, result);
        return result;
    }

    private void preOrderRec(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.value);
        preOrderRec(node.left, result);
        preOrderRec(node.right, result);
    }

    public List<Integer> postOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        postOrderRec(root, result);
        return result;
    }

    private void postOrderRec(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postOrderRec(node.left, result);
        postOrderRec(node.right, result);
        result.add(node.value);
    }

    //Самопроверка

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        int[] values = {5, 3, 8, 1, 4, 7, 9};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("In-order:   " + tree.inOrderTraversal());
        System.out.println("Pre-order:  " + tree.preOrderTraversal());
        System.out.println("Post-order: " + tree.postOrderTraversal());

        System.out.println("contains(7):  " + tree.contains(7));
        System.out.println("contains(5):  " + tree.contains(5));
        System.out.println("contains(6):  " + tree.contains(6));
        System.out.println("contains(10): " + tree.contains(10));

        //Удаление листа
        tree.remove(1);
        System.out.println("После remove(1): " + tree.inOrderTraversal());

        //Удаление узла с одним потомком
        tree.remove(3);
        System.out.println("После remove(3): " + tree.inOrderTraversal());

        //Удаление узла с двумя потомками — повторное строение
        BinarySearchTree tree2 = new BinarySearchTree();
        for (int v : values) {
            tree2.insert(v);
        }
        tree2.remove(8);
        System.out.println("После remove(8): " + tree2.inOrderTraversal());
    }
}