package com.hyq.offer;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author nanke
 * @date 2020/8/20 下午5:32
 *
 * 输入某二叉树的前序遍历和中序遍历结果，请重建此二叉树。
 * 假设输入的前序遍历和中序遍历结果都不含重复的数字。
 * 例如：输入前序遍历序列[1,2,4,7,3,5,6,8] 中序遍历序列[4,7,2,1,5,3,8,6]
 *
 * @see PointOffer/src/main/resources/BuildBinaryTreeImage.png
 */
public class BuildBinaryTree {

    public static void main(String[] args) {

        List<Integer> preList = Arrays.asList(1,2,4,7,3,5,6,8);
        List<Integer> midList = Arrays.asList(4,7,2,1,5,3,8,6);

        TreeNode construct = construct(preList, midList);
        System.out.println(construct);
    }


    public static TreeNode construct(List<Integer> preList, List<Integer> midList) {

        if (preList == null || midList == null ||
                preList.size() < 1 || preList.size() != midList.size()) {
            return null;
        }

        for (Integer preNum : preList) {
            if (!midList.contains(preNum)) {
                return null;
            }
        }

        TreeNode rootNode = new TreeNode();
        rootNode.setValue(preList.get(0));
        int rootMidIndex = midList.indexOf(preList.get(0));

        // 左子树中序列
        List<Integer> leftTreeMidList = midList.subList(0,rootMidIndex);
        // 左子树前序列
        List<Integer> leftTreePreList = preList.subList(1, leftTreeMidList.size() + 1);
        // 构造左树
        rootNode.setLeftNode(construct(leftTreePreList,leftTreeMidList));

        // 右子树中序列
        List<Integer> rightTreeMidList = midList.subList(rootMidIndex + 1,midList.size());
        // 右子树前序列
        List<Integer> rightTreePreList = preList.subList(leftTreeMidList.size() + 1, preList.size());
        // 构造右树
        rootNode.setRightNode(construct(rightTreePreList,rightTreeMidList));

        return rootNode;
    }

    @Data
    public static class TreeNode {

        /**
         * 值
         */
        private Integer value;
        /**
         * 左节点
         */
        private TreeNode leftNode;
        /**
         * 右节点
         */
        private TreeNode rightNode;

    }
}
