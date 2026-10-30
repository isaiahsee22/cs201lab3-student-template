public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

     //write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          String[] traversals = {traversal1, traversal2, traversal3};
          String[] types = {"Inorder", "Preorder", "Postorder"};

          String[] values = traversal1.split("-");

          java.util.Set<String> set = new java.util.HashSet<>(
               java.util.Arrays.asList(values)
          );

          if (set.size() != values.length) {
               return "Invalid traversals";
          }

          for (String traversal : traversals) {
               String[] current = traversal.split("-");

               if (current.length != values.length ||
                    !set.equals(new java.util.HashSet<>(
                         java.util.Arrays.asList(current)))) {
                    return "Invalid traversals";
               }
          }

          //try all possible assignments
          for (int i = 0; i < 3; i++) {
               for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {

                         if (i == j || i == k || j == k) {
                              continue;
                         }

                         //j represents preorder
                         Node root = null;

                         for (String value : traversals[j].split("-")) {
                              root = insert(root, Integer.parseInt(value));
                         }

                         StringBuilder in = new StringBuilder();
                         StringBuilder pre = new StringBuilder();
                         StringBuilder post = new StringBuilder();

                         inorder(root, in);
                         preorder(root, pre);
                         postorder(root, post);

                         //compare each input with its corresponding traversal
                         if (in.toString().equals(traversals[i]) &&
                              pre.toString().equals(traversals[j]) &&
                              post.toString().equals(traversals[k])) {

                              return "Traversal 1 - " + types[i] + ", Traversal 2 - " + types[j] + ", Traversal 3 - " + types[k];
                         }
                    }
               }
          }
          return "Invalid traversals";
     }


     static class Node {
          int value;
          Node left, right;

          Node(int value) {
               this.value = value;
          }
     }

     static Node insert(Node root, int value) {
          if (root == null) {
               return new Node(value);
          }

          if (value < root.value) {
               root.left = insert(root.left, value);
          } else if (value > root.value) {
               root.right = insert(root.right, value);
          }

          return root;
     }

     static void inorder(Node root, StringBuilder result) {
          if (root == null) return;

          inorder(root.left, result);
          append(root.value, result);
          inorder(root.right, result);
     }

     static void preorder(Node root, StringBuilder result) {
          if (root == null) return;

          append(root.value, result);
          preorder(root.left, result);
          preorder(root.right, result);
     }

     static void postorder(Node root, StringBuilder result) {
          if (root == null) return;

          postorder(root.left, result);
          postorder(root.right, result);
          append(root.value, result);
     }

     static void append(int value, StringBuilder result) {
          if (result.length() > 0) {
               result.append("-");
          }

          result.append(value);
     }
}