
public class client {
    public static void main(String[] args) {
        huffman control = new huffman("e(1109)d(15)ndhhshgdcbhhgg1234uudsiikmcmnvbbfddasaerwyquioplmmmbnhhgf");
        control.displayHuffmanTree();
        // System.out.println(control.decodeBinaryToUserName("11110110"));
        // System.out.println(control.generatePasswordFromUsername("abaa", 5));
        String password = control.generatePasswordFromUsername("kamalaggarwal", 4, true, true, true);
        String username = control.generateUsernameFromPassword(password);
        System.out.println(password + "\n" + username);
    }
}