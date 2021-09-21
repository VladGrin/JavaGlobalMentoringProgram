package com.tree.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class BinaryTree {

    private static final Logger LOG = LoggerFactory.getLogger(BinaryTree.class);

    /**
     * Структура данных узла BinaryTree
     */
    private static class TreeNode {
        private int key = 0;
        private String data = null;
        private boolean isVisited = false;
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;

        public TreeNode() {
        }

        public TreeNode(int key, String data) {
            this.key = key;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    // Получаем корневой узел
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // Определяем корневой узел
    private TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode(1, "A");
    }

    /**
     * Создать двоичное дерево
     */
    public void createBinaryTree(TreeNode root) {
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.rightChild = nodeF;
    }

    /**
     * Обход предзаказа
     */
    public void preOrder(TreeNode node) {
        if (node != null) {
            visited(node);
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    /**
     * Обход среднего порядка
     *
     * @param node
     */
    public void inOrder(TreeNode node) {
        if (node != null) {
            preOrder(node.leftChild);
            visited(node);
            preOrder(node.rightChild);
        }
    }

    /**
     * Пост-заказ обход
     *
     * @param node
     */
    public void postOrder(TreeNode node) {
        if (node != null) {
            preOrder(node.leftChild);
            preOrder(node.rightChild);
            visited(node);
        }
    }

    /**
     * Безрекурсивный обход предварительного заказа
     *
     * @param node
     */
    public void nonRecPreOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = node;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                visited(pNode);
                stack.push(pNode);
                pNode = pNode.leftChild;
            }
            if (stack.isEmpty()) {
                pNode = stack.pop();
                pNode = pNode.rightChild;
            }
        }
    }

    /**
     * Безрекурсивный обход по порядку
     *
     * @param node
     */
    public void nonRecInOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = node;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                stack.push(pNode);
                pNode = pNode.leftChild;
            }
            if (!stack.isEmpty()) {
                pNode = stack.pop();
                visited(pNode);
                pNode = pNode.rightChild;
            }
        }
    }

    /**
     * Нерекурсивный обход пост-заказа
     *
     * @param pNode
     */
    public void nonRecPostOrder(TreeNode pNode) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pNode;
        while (pNode != null) {
            // Левое поддерево помещается в стек
            while (pNode.leftChild != null) {
                stack.push(pNode);
                pNode = pNode.leftChild;
            }
            // У текущего узла нет правого поддерева или правое поддерево было выведено
            while (pNode != null && (pNode.rightChild == null || pNode.rightChild == node)) {
                visited(pNode);
                // Записываем последний выходной узел
                node = pNode;
                if (!stack.isEmpty()) {
                    pNode = stack.pop();
                } else {
                    return;
                }
            }
            // Правое поддерево помещается в стек
            stack.push(pNode);
            pNode = pNode.rightChild;
        }
    }

    private void visited(TreeNode node) {
        node.isVisited = true;
        LOG.info("{},{}\t", node.data, node.key);
    }

    /**
     * Рассчитать высоту дерева
     */
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int i = height(node.leftChild);
            int j = height(node.rightChild);
            return (i < j) ? j + 1 : i + 1;
        }
    }

    /**
     * Рассчитать количество узлов в дереве
     *
     * @param node
     * @return Узлы дерева
     */
    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.leftChild) + size(node.rightChild);
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.root;
        binaryTree.createBinaryTree(root);
        LOG.info("Высота двоичного дерева составляет:");
        LOG.info(String.valueOf(binaryTree.height(root)));
        LOG.info("Количество узлов в двоичном дереве:");
        LOG.info(String.valueOf(binaryTree.size(root)));
        LOG.info("Рекурсивный результат обхода предварительного заказа:");
        binaryTree.preOrder(root);
        LOG.info("Результат рекурсивного обхода среднего порядка:");
        binaryTree.inOrder(root);
        LOG.info("Результат рекурсивного обхода пост-заказа:");
        binaryTree.postOrder(root);
    }
}
