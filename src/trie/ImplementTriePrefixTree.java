package trie;

/**
 * description Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-14 15:14
 **/
public class ImplementTriePrefixTree {

    static class Trie {

        /**
         * 构造字典树
         * (执行用时：38 ms, 在所有 Java 提交中击败了96.74%的用户)
         * (内存消耗：49.4 MB, 在所有 Java 提交中击败了18.58%的用户)
         */
        private static class Node{
            // children 孩子节点数组
            private final Node[] children;
            // isLeaf 当前节点是否是叶子节点
            private boolean isLeaf;

            public Node(){
                children = new Node[26];
                isLeaf = false;
            }
        }

        private final Node root;

        public Trie() {
            root = new Node();
        }

        /**
         * 挨个字符插入字典树
         *
         * @param word 待插入字符串
         */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            Node temp = root;

            for (char c : chars) {
                int index = c - 'a';
                // 没有才创建
                if (temp.children[index] == null) {
                    temp.children[index] = new Node();
                }
                temp = temp.children[index];
            }

            temp.isLeaf = true;
        }

        /**
         * 搜索字符串并返回最后一个节点（如果找到该字符串的话）
         *
         * @param root 根节点
         * @param word 待搜索字符串
         * @return     最后一个节点（如果找到该字符串的话）
         */
        private Node searchString(Node root, String word) {
            char[] chars = word.toCharArray();

            for (char c : chars) {
                int index = c - 'a';
                if (root.children[index] == null) {
                    return null;
                }
                root = root.children[index];
            }

            return root;
        }

        /**
         * 搜索字符串，判断最后一个是否是叶子节点
         *
         * @param word 待搜索字符串
         */
        public boolean search(String word) {
            Node temp = root;
            temp = searchString(temp, word);
            return temp != null && temp.isLeaf;
        }

        /**
         * 只要树中存在一条从根结点开始为 prefix 的路径，就算结果为 true
         *
         * @param prefix 待搜索前缀字符串
         */
        public boolean startsWith(String prefix) {
            return searchString(root, prefix) != null;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.search("apple"));
    }

}