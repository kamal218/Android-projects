import java.util.*;

public class huffman {
    public class TreeNode implements Comparable<TreeNode> {
        TreeNode left = null;
        TreeNode right = null;
        int freq = 0;
        String lossy = "";
        String bit = "";

        public TreeNode(String lossy, int freq, TreeNode left, TreeNode right, String bit) {
            this.lossy = lossy;
            this.freq = freq;
            this.left = left;
            this.right = right;
            this.bit = bit;
        }

        @Override
        public String toString() {
            return lossy;
        }

        @Override
        public int compareTo(TreeNode o) {
            return this.freq - o.freq;
        }
    }

    private TreeNode root = null;
    private HashMap<String, String> encode;
    private HashMap<String, String> decode;

    public huffman(String ref) {
        encode = new HashMap<>();
        decode = new HashMap<>();
        int[] map = trimGivenRef(ref);
        this.root = init(map);
    }

    public int[] trimGivenRef(String ref) {
        // create freq map
        int[] map = new int[128];
        int i = 0;
        while (i < ref.length()) {
            char ch = ref.charAt(i);
            if (i + 1 < ref.length() && ref.charAt(i + 1) == '(') {
                // find closing bracket
                int j = i + 2;
                while (ref.charAt(j) != ')') {
                    j++;
                }
                if (j != i + 2)// a(123)
                    map[ch] += Integer.parseInt(ref.substring(i + 2, j));
                else // a()
                    map[ch]++;
                i = j + 1;
            } else {
                map[ch]++;
                i++;
            }
        }
        return map;
    }

    public TreeNode init(int[] map) {

        // init priority queue
        PriorityQueue<TreeNode> pq = new PriorityQueue<>();
        for (int i = 0; i < map.length; i++) {
            TreeNode node = new TreeNode(((char) (i)) + "", map[i], null, null, "");
            if (map[i] != 0)
                pq.add(node);
        }
        // System.out.println(pq.size());
        // create tree
        while (pq.size() > 1) {
            TreeNode node1 = pq.poll();
            TreeNode node2 = pq.poll();
            TreeNode combine = new TreeNode(node1.lossy + node2.lossy, node1.freq + node2.freq, node1, node2, "");
            pq.add(combine);
        }
        // init root
        root = pq.poll();
        // call for assign bit
        assignBit(root, "");
        // System.out.println(root);
        return root;
    }

    public void assignBit(TreeNode root, String bit) {
        if (root.left == null && root.right == null) {
            root.bit = bit;
            encode.put(root.lossy, bit);
            decode.put(bit, root.lossy);
            return;
        }
        assignBit(root.left, bit + "0");
        assignBit(root.right, bit + "1");
    }

    public String encodeUserNameToBinary(String username) {// we will get username as a12b13c15.......
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < username.length()) {
            char ch = username.charAt(i);
            sb.append(encode.get(ch + ""));
            i++;
        }
        return sb.toString();
    }

    public String decodeBinaryToUserName(String binary) {
        StringBuilder username = new StringBuilder();
        int i = 0;
        StringBuilder temp = new StringBuilder();
        while (i < binary.length()) {
            temp.append(binary.charAt(i));
            if (decode.containsKey(temp.toString())) {
                username.append(decode.get(temp.toString()));
                temp = new StringBuilder();
            }
            i++;
        }
        return username.toString();
    }

    public void displayHuffmanTree() {
        displayHuffmanTree(root);
    }

    // public String generatePasswordFromUsername(String username, int minLength) {
    // String binary = encodeUserNameToBinary(username);
    // StringBuilder password = new StringBuilder();
    // // i will take max of 4 length character from binary string and use base 3 to
    // // save 40 diff chars
    // int fact = (binary.length() / minLength) > 4 ? 4 : (binary.length() /
    // minLength);
    // int i = 0;
    // while (i < binary.length()) {
    // if (i + fact <= binary.length()) {
    // int ascii = asciiWithBase3(binary.substring(i, i + fact));
    // // check if ascii is in range of 0-25 i.e smaller character
    // if (ascii < 26) {
    // // append smaller character
    // password.append((char) (ascii + 'a') + "");
    // } else {
    // // append capitalcharacter
    // password.append((char) ((ascii - 26) + 'A') + "");
    // }
    // } else {
    // int ascii = asciiWithBase3(binary.substring(i, binary.length()));
    // if (ascii < 26) {
    // // append smaller character
    // password.append((char) (ascii + 'a') + "");
    // } else {
    // // append capitalcharacter
    // password.append((char) ((ascii - 26) + 'A') + "");
    // }
    // }
    // i += fact;
    // }
    // password.append("a");
    // return password.toString();
    // }

    public String generatePasswordFromUsername(String username, int minLength, boolean cap, boolean digit,
            boolean specialchar) {
        String binary = encodeUserNameToBinary(username);
        StringBuilder password = new StringBuilder();
        // i will take max of 4 length character from binary string and use base 3 to
        // save 40 diff chars
        int fact = (binary.length() / minLength) > 4 ? 4 : (binary.length() / minLength);
        int i = 0;
        while (i < binary.length()) {
            if (i + fact <= binary.length()) {
                int[] asciiWithZero = asciiWithBase3AndStartingZero(binary.substring(i, i + fact));
                int ascii = asciiWithZero[0];
                int zero = asciiWithZero[1];
                // check if ascii is in range of 0-25 i.e smaller character
                if (ascii < 26) {
                    // append smaller character
                    password.append((char) (ascii + 'a') + "");
                } else {
                    // append capitalcharacter
                    password.append((char) ((ascii - 26) + 'A') + "");
                }
                if (zero != 0) {
                    password.append((char) (zero + '0') + "");
                }
            } else {
                int[] asciiWithZero = asciiWithBase3AndStartingZero(binary.substring(i, binary.length()));
                int ascii = asciiWithZero[0];
                int zero = asciiWithZero[1];
                if (ascii < 26) {
                    // append smaller character
                    password.append((char) (ascii + 'a') + "");
                } else {
                    // append capitalcharacter
                    password.append((char) ((ascii - 26) + 'A') + "");
                }
                if (zero != 0) {
                    password.append((char) (zero + '0') + "");
                }
            }
            i += fact;
        }
        password.append("a");
        if (cap) {
            int v = (int) Math.random() * 25;
            password.append((char) (v + 'A') + "");
        }
        if (digit) {
            int v = (int) Math.random() * 10;
            password.append((char) ('0' + v) + "");
        }
        if (specialchar) {
            String[] special = { "@", "&", "!", "$" };
            int v = (int) Math.random() * 4;
            password.append(special[v]);
        }
        return password.toString();
    }

    public int[] asciiWithBase3AndStartingZero(String binary) {
        int[] ans = new int[2];
        int mul = 1;
        boolean one = false;
        for (int i = binary.length() - 1; i >= 0; i--) {
            ans[0] += (binary.charAt(i) - '0') * mul;
            mul *= 3;
        }
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1')
                break;
            ans[1]++;
        }
        return ans;
    }

    public String generateUsernameFromPassword(String password) {
        int i = password.length() - 1;
        while (i >=0 && password.charAt(i) != 'a')
            i--;
        password = password.substring(0, i);
        // System.out.println(password);
        i = 0;
        StringBuilder username = new StringBuilder();
        while (i < password.length()) {
            char ch = password.charAt(i);
            int zero = 0;
            int j = i + 1;
            while (j < password.length() && password.charAt(j) - '0' >= 0 && password.charAt(j) - '9' <= 0) {
                j++;
            }
            if (j != i + 1) {
                zero = Integer.parseInt(password.substring(i + 1, j));
            }
            if (ch - 'a' >= 0 && ch - 'z' <= 0) {// smaller character
                int ascii = ch - 'a';
                String binary = binaryWithBase3AndZero(ascii, zero);
                username.append(binary);
            } else {// capital character
                int ascii = (ch - 'A') + 26;
                String binary = binaryWithBase3AndZero(ascii, zero);
                username.append(binary);
            }
            i=j;
        }
        return decodeBinaryToUserName(username.toString());
    }

    public String binaryWithBase3AndZero(int ascii, int zero) {
        StringBuilder binary = new StringBuilder();
        while (zero-- > 0) {
            binary.append("0");
        }
        StringBuilder temp = new StringBuilder();
        while (ascii > 0) {
            int rem = ascii % 3;
            if (rem == 0) {
                temp.append("0");
            } else {
                temp.append("1");
            }
            ascii = ascii / 3;
        }
        return binary.toString() + (temp.reverse().toString());
    }

    public void displayHuffmanTree(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null)
            System.out.print(" lossy: " + root.left.lossy + " bit: " + root.left.bit + " -> ");
        System.out.print(" lossy: " + root.lossy + " bit: " + root.bit);
        if (root.right != null)
            System.out.print(" <- " + " lossy: " + root.right.lossy + " bit: " + root.right.bit);
        System.out.println();
        displayHuffmanTree(root.left);
        displayHuffmanTree(root.right);
    }
}