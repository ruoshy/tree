package cn.ruoshy.tree.fork;

public class Tree {
    // 头结点
    private TreeNode treeNode;

    public Tree() {
        this.treeNode = new TreeNode();
    }

    /* 插入数据 */
    public void insert(Integer data) {
        insert(treeNode, data);
    }

    public void insert(TreeNode node, Integer data) {
        if (node.getData() == null) { // 节点数据为空则插入
            node.setData(data);
        } else if (node.getData() > data) { // 若要插入的数据小于当前节点，将数据存储到左子树
            // 若左子树为空创建左子树
            if (node.getLeftNode() == null) {
                // 由于节点中，子节点为null，并没有被分配内存地址
                // 当将子节点传递给方法，方法收到的是一个指向空地址的对象
                // 实例化对象后赋值，子节点将指向实例化的对象的内存地址
                // 而父节点中子节点仍然为null，所以先对子节点进行实例化再传递给方法
                node.setLeftNode(new TreeNode());
            }
            // 将左子树对象进行递归直到子节点中数据为空
            insert(node.getLeftNode(), data);
        } else {
            if (node.getRightNode() == null) {
                node.setRightNode(new TreeNode());
            }
            insert(node.getRightNode(), data);
        }
    }
    /**/

    /* 先序遍历 */
    public void before() {
        before(treeNode);
    }

    public void before(TreeNode node) {
        if (node != null) {
            // 根左右
            System.out.println(node.getData());
            before(node.getLeftNode());
            before(node.getRightNode());
        }
    }
    /**/

    /* 后续遍历 */
    public void after() {
        after(treeNode);
    }

    public void after(TreeNode node) {
        if (node != null) {
            // 左右根
            after(node.getLeftNode());
            after(node.getRightNode());
            System.out.println(node.getData());
        }
    }
    /**/

    /* 中序遍历 */
    public void middle() {
        middle(treeNode);
    }

    public void middle(TreeNode node) {
        if (node != null) {
            // 左根右
            middle(node.getLeftNode());
            System.out.println(node.getData());
            middle(node.getRightNode());
        }
    }
    /**/

    public void search(Integer data) {
        search(treeNode, data, 1);
    }

    public void search(TreeNode node, Integer data, int num) {
        if (node != null) {
            System.out.println(num++);
            if (node.getData() > data) {
                search(node.getLeftNode(), data, num);
            } else if (node.getData() < data) {
                search(node.getRightNode(), data, num);
            } else {
                System.out.println(node.getData());
            }
        }
    }
}