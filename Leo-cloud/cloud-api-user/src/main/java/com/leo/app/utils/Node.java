package com.leo.app.utils;

public class Node {

    int data;
    Node left,right;
    Node(int item){
        data=item;
        left=right=null;
    }

    static class Tree{
        Node root;
         int getLength(Node node){
            if(node==null){
                return 0;
            }else {
                int leftLength = getLength(node.left);
                int rightLength = getLength(node.right);
                if(leftLength>rightLength){
                    return leftLength+1;
                }else{
                    return rightLength+1;
                }
            }
        }

        public static void main(String[] args) {
            Tree tree =new Tree();
            tree.root = new Node(1);
            tree.root.left = new Node(2);
            tree.root.right = new Node(3);
            tree.root.left.left = new Node(4);
            tree.root.left.right = new Node(5);
            System.out.println("Height of tree is : " +
                    tree.getLength(tree.root));
        }
    }



}
