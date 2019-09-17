package cn.ruoshy.tree.redblack;

import java.util.Stack;

public class RBTree {

    class TreeNode {
        private TreeNode leftNode;
        private TreeNode parentNode;
        private TreeNode rightNode;
        private Integer color;
        private Integer data;
    }

    // 根节点
    private TreeNode treeNode;

    public RBTree() {
        treeNode = new TreeNode();
    }

    public void insert(Integer data) {
        if (treeNode.data == null) {
            treeNode.data = data;
            treeNode.color = 0;
        } else {
            insert(treeNode, data);
        }
    }

    public void insert(TreeNode node, Integer data) {
        TreeNode temp = new TreeNode();
        temp.color = 1;
        temp.data = data;
        while (node != null) {
            if (node.data > data) {
                if (node.leftNode == null) {
                    temp.parentNode = node;
                    node.leftNode = temp;
                    break;
                } else {
                    node = node.leftNode;
                }
            } else {
                if (node.rightNode == null) {
                    temp.parentNode = node;
                    node.rightNode = temp;
                    break;
                } else {
                    node = node.rightNode;
                }
            }
        }
        // 修正
//		revise(temp);
    }

    public void revise(TreeNode node) {
        TreeNode parentNode = node.parentNode;
        if (parentNode != null && parentNode.color == 1) {
            TreeNode grandfatherNode = parentNode.parentNode;
            TreeNode uncleNode = null;
            // 获取叔节点
            if (grandfatherNode != null) {
                uncleNode = grandfatherNode.leftNode == parentNode ? grandfatherNode.rightNode
                        : grandfatherNode.leftNode;
            } else {
                parentNode.color = 0;
                return;
            }
            // 若叔节点为黑（null也算黑）
            if (uncleNode != null && uncleNode.color == 1) {
                // 将父节点、叔节点变黑
                parentNode.color = 0;
                uncleNode.color = 0;
                // 将祖父节点变红，再次修正
                grandfatherNode.color = 1;
                revise(grandfatherNode);
            } else {
                // 当前节点在父节点的左边
                if (parentNode.leftNode == node) {
                    // 父节点在祖父节点的左边（Left-Left）
                    // 		 g				  f
                    //     f		-->	   n     g
                    //   n
                    if (grandfatherNode.leftNode == parentNode) {
                        // 对祖父节点进行右旋，并变色
                        //       黑
                        //	 红       红
                        rotateRight(grandfatherNode);
                        parentNode.color = 0;
                        node.color = 1;
                        grandfatherNode.color = 1;
                    }
                    // 父节点在祖父节点的右边（Left-Right）
                    // 	 g				g
                    //     f	-->		  n
                    //   n					f
                    else {
                        // 父节点右旋，使其符合（Left-Left），再次修正
                        rotateRight(parentNode);
                        revise(parentNode);
                    }
                }
                // 当前节点在父节点的右边
                else {
                    // 父节点在祖父节点的左边（Right-Left）
                    // 	 	 g			   g
                    //     f	  -->	 n
                    //  	 n  	   f
                    if (grandfatherNode.leftNode == parentNode) {
                        // 父节点左旋，使其符合（Right-Right），再次修正
                        rotateLeft(parentNode);
                        revise(parentNode);
                    }
                    // 父节点在祖父节点的右边（Right-Right）
                    // 	 g				   f
                    //     f	 -->	g     n
                    //  	 n
                    else {
                        // 对祖父节点进行左旋，并变色
                        //       黑
                        //	 红       红
                        rotateLeft(grandfatherNode);
                        parentNode.color = 0;
                        node.color = 1;
                        grandfatherNode.color = 1;
                    }
                }
            }
        }
    }

    public void rotateLeft(TreeNode node) {
        TreeNode parentNode = node.parentNode;
        TreeNode rightNode = node.rightNode;
        // 判断当前节点位于父节点的方向,并将右节点位置移到当前节点位置
        if (parentNode != null) {
            // 当前节点位于父节点左边
            if (parentNode.leftNode == node) {
                parentNode.leftNode = rightNode;
            }
            // 当前节点位于父节点右边
            else {
                parentNode.rightNode = rightNode;
            }
        } else {
            // 若父节点为空必定是根节点，设置根节点
            this.treeNode = rightNode;
        }
        rightNode.parentNode = parentNode;
        // 将右节点的左节点交给当前节点的右节点
        node.rightNode = rightNode.leftNode;
        if (rightNode.leftNode != null) {
            rightNode.leftNode.parentNode = node;
        }
        // 将当前节点交给右节点的左节点
        rightNode.leftNode = node;
        node.parentNode = rightNode;
    }

    public void rotateRight(TreeNode node) {
        TreeNode parentNode = node.parentNode;
        TreeNode leftNode = node.leftNode;
        // 判断当前节点位于父节点的方向,并将左节点位置移到当前节点位置
        if (parentNode != null) {
            // 当前节点位于父节点左边
            if (parentNode.leftNode == node) {
                parentNode.leftNode = leftNode;
            }
            // 当前节点位于父节点右边
            else {
                parentNode.rightNode = leftNode;
            }
        } else {
            // 若父节点为空必定是根节点，设置根节点
            this.treeNode = leftNode;
        }
        leftNode.parentNode = parentNode;
        // 将左节点的右节点交给当前节点的左节点
        node.leftNode = leftNode.rightNode;
        if (leftNode.rightNode != null) {
            leftNode.rightNode.parentNode = node;
        }
        // 将当前节点交给左节点的右节点
        leftNode.rightNode = node;
        node.parentNode = leftNode;

    }

    /**
     * 中序遍历
     */
    public void middle() {
        middle(treeNode);
    }

    public void middle(TreeNode node) {
        // 定义栈
        Stack<TreeNode> stack = new Stack<>();
        // 直到节点为空且栈为空停止循环
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                // 将左节点顺序入栈
                stack.push(node);
                node = node.leftNode;
            }
            // 取出栈顶元素
            node = stack.pop();
            // 输出节点元素
            System.out.println(node.data + "  " + node.color);
            // 获取该栈顶元素的右节点并继续将其全部左节点入栈
            node = node.rightNode;
        }
    }

    public void search(Integer data) {
        search(treeNode, data);
    }

    public void search(TreeNode node, Integer data) {
        int num = 0;
        while (node != null) {
            num++;
            if (node.data.equals(data)) {
                System.out.println("查询结果：" + node.data);
                break;
            } else if (node.data > data) {
                node = node.leftNode;
            } else {
                node = node.rightNode;
            }
        }
        System.out.println("查询比较次数: " + num);
    }

}