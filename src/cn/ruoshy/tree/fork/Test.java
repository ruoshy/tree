package cn.ruoshy.tree.fork;

public class Test {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(3);
        tree.insert(5);
        tree.insert(1);
        tree.insert(2);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(3);
        tree.insert(3);
        System.out.println("中序遍历");
        tree.middle();
        System.out.println("前序遍历");
        tree.before();
        System.out.println("后序遍历");
        tree.after();
    }

}