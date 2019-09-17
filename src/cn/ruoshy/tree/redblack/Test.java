package cn.ruoshy.tree.redblack;

public class Test {

    public static void main(String[] args) {
        RBTree tree = new RBTree();
        long start = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            tree.insert((int) (Math.random() * 100000));
        }
        tree.insert(932145);
        System.out.println("插入十万条数据，耗时：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        tree.search(932145);
        System.out.println("查询耗时：" + (System.currentTimeMillis() - start));
    }

}