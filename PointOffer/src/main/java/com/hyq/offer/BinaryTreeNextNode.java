package com.hyq.offer;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author nanke
 * @date 2020/8/21 上午10:44
 *
 * 给定一颗二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？
 * 树中节点除左、右子节点外，还有一个指向父节点的指针。
 *
 * 中序遍历序列：[1，2，3，4，5，6，7，8，9]
 * 情况一：若给到节点存在右子节点，则下一节点为其最左子节点
 * 情况二：若给到节点不存在右子节点，且该节点为其父节点的左节点，则下一个节点为其父节点
 * 情况三：若给到节点不存在右子节点，且该节点为其父节点的右节点，则下一个节点为循环遍历其父节点，知道某节点为其父节点的左节点
 *
 * @see PointOffer/src/main/resources/BinaryTreeNextNodeImage.png
 */
public class BinaryTreeNextNode {

    public static void main(String[] args) {

        BinaryTreeNode a = new BinaryTreeNode();
        a.setValue(6);
        BinaryTreeNode b = new BinaryTreeNode();
        b.setValue(2);
        BinaryTreeNode c = new BinaryTreeNode();
        c.setValue(8);
        BinaryTreeNode d = new BinaryTreeNode();
        d.setValue(1);
        BinaryTreeNode e = new BinaryTreeNode();
        e.setValue(4);
        BinaryTreeNode f = new BinaryTreeNode();
        f.setValue(7);
        BinaryTreeNode g = new BinaryTreeNode();
        g.setValue(9);
        BinaryTreeNode h = new BinaryTreeNode();
        h.setValue(3);
        BinaryTreeNode i = new BinaryTreeNode();
        i.setValue(5);

        a.setLeftNode(b);
        a.setRightNode(c);
        b.setLeftNode(d);
        b.setRightNode(e);
        c.setLeftNode(f);
        c.setRightNode(g);
        e.setLeftNode(h);
        e.setRightNode(i);

        BinaryTreeNode execute = execute(g);
        System.out.println(JSON.toJSONString(execute));
    }

    public static BinaryTreeNode execute(BinaryTreeNode node) {

        if (node == null) {
            return null;
        }
        // 该节点存在子右节点,下一节点为其最左子节点
        if (node.rightNode != null) {
            node = node.rightNode;
            while (node.leftNode != null) {
                node = node.leftNode;
            }
            return node;
        }

        // 该节点不存在子右节点时,其父节点成为关键
        while (node.parentNode != null) {
            // 1：该节点为父节点的左子节点
            if (node.parentNode.leftNode == node) {
                return node.parentNode;
            }
            // 2：该节点为父节点的右子节点
            node = node.parentNode;
        }
        return null;
    }



    @Data
    public static class BinaryTreeNode {

        /**
         * 值
         */
        private Integer value;
        /**
         * 左节点
         */
        private BinaryTreeNode leftNode;
        /**
         * 右节点
         */
        private BinaryTreeNode rightNode;
        /**
         * 父节点
         */
        private BinaryTreeNode parentNode;

    }
}
