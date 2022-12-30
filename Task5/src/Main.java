import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Main {
    public static void main(String[] args) {
        int[] test = encrypt("Hello");
        for (int i = 0; i < test.length; i++)
            System.out.print(test[i] + " ");
        System.out.println();
        System.out.println(decrypt(new int [] { 72, 33, -73, 84, -12, -3, 13, -13, -68} ));
        System.out.println(canMove("�����", "A2", "A3"));
        System.out.println(canComplete("butl", "beautiful") );
        System.out.println(canComplete("butlz", "beautiful") );
        System.out.println(sumDigProd(16, 28) );
        List<String> test2 = sameVowelGroup(new String []{"toe", "ocelot", "maniac"} );
        for(String ch: test2)
            System.out.print(ch + " ");
        System.out.println();
        System.out.println(validateCard(1234567890123452l));
        System.out.println(numToEng(0) );
        System.out.println(getSha256Hash("password123") );
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(hexLattice(37));
    }
    //������� 1 ������ ��� �������, ������� ��������� ������ � ����� � ���������� �������������� � �������������� ���������
    public static int[] encrypt(String str) {
        int len = str.length(); // �������� ������
        int chCode=0; // �������� ��� ����� �� ���������
        int[] arrEncrypt = new int[len]; // ����� ������������� ��������
        for(int j=0;j<len;j++) { // ������� ������ ������� ����� �����������
            arrEncrypt[j] = str.charAt(j) - chCode; // ����������� � ����� ������ �������������� c������, ��� �������
            chCode = str.charAt(j); // �������� ��� ������ �������
        }
        return arrEncrypt;

    }

    public static String decrypt(int[] arr) {
        String str=""; // ��������� ������
        int chCode=0; // ��� �������
        for(int j=0;j<arr.length;j++) { // ������� ������
            str+=(char)(arr[j]+chCode); // ����������� ��� ������ ���� ���� ������� � �������� �������
            chCode=arr[j]+chCode; // �������� ��� ������ �������
        }
        return str;
    }
    //������� 2 ������ �������, ������� ��������� ��� ��������� ������, � ��������� � ������� �������
    public static boolean canMove(String p, String c, String t) {
        // �������� �����
        if (p.equals("�����")) {
            if (c.charAt(0) == t.charAt(0)) { // ���� ����� ����������
                if (c.charAt(1) == '2' && t.charAt(1) == '4') { // ������ 2 � 4, ����� ������, ������ ��� ����� ����� 2
                    return true;
                }
                if (c.charAt(1) == '7' && t.charAt(1) == '5') { // ������ 7 � 5, ����� ������, ������ ��� ����� ����� 2
                    return true;
                }
                if (Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 1) { // ���� ������� ����� �������� 1, �� ����� ������
                    return true;
                }
            }
        }
        // �������� ����
        if (p.equals("����")) {
            // ���� ������� �� �������� ����� 2 � �� ������� ����� 1, �� ����� ������
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == 2
                    && Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 1) {
                return true;
            }
            // ���� ������� �� ������� ����� 2 � �� �������� ����� 1, �� ����� ������
            if (Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 2
                    && Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == 1) {
                return true;
            }
        }
        // ���� ����
        if (p.equals("����")) {
            // ���� ������� ����� ��������� ���������� ������� � ������� ����� ������� ����� ��������� ����� ������� � ����������
            // ����� ����� ������
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == Math.abs((int) c.charAt(1) - (int) t.charAt(1))) {
                return true;
            }
        }
        // ���� �����
        if (p.equals("�����")) {
            // ���� �������� �� ���������� �� �������� ����� ���� ��� �� �������, ����� ����� ������
            if (c.charAt(0) == t.charAt(0)
                    || c.charAt(1) == t.charAt(1)) {
                return true;
            }
        }
        // ���� ��������
        if (p.equals("��������")) {
            // ���� ������� ����� ��������� ���������� ������� � ������� ����� ������� ����� ��������� ����� ������� � ����������
            // ����� ����� ������
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == Math.abs((int) c.charAt(1) - (int) t.charAt(1))) {
                return true;
            }
            // ���� �������� �� ���������� �� �������� ����� ���� ��� �� �������, ����� ����� ������
            if (c.charAt(0) == t.charAt(0)
                    || c.charAt(1) == t.charAt(1)) {
                return true;
            }
        }
        // ���� ������
        if (p.equals("������")) {
            // ���� ������� �� �������� � ������� �� ����� 1, �� ����� ������
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) <= 1
                    && Math.abs((int) c.charAt(1) - (int) t.charAt(1)) <= 1) {
                return true;
            }
        }
        return false;
    }
    //������� 3 ������ �������, ������� ����� ��������� ������� ������ � ����� ����������, ����� �� ����� ���� �����������
    public static boolean canComplete(String initial, String word) {
        int in = 0; // ���������� ��� ���������� ���������� ����, ������� ����������� � ������ ������� � ����������
        // ������� ������, ������� ������ ���������
        for(int w = 0; w < word.length(); w++) {
            if(word.charAt(w) == initial.charAt(in)) // ���� ����� �� ���������� ����������� � ������� ������
                in++; // �������� ���������� ��� ���������� ���������� ����
        }
        // ���� �����, ��� ����������� � ������ ���������� ����������� � � ������ �������
        // ��� ��� � ���������� ���� ����������� ��������� ����� ����� ������� ������, ����� ������ ����� ���������
        if(in == initial.length()) return true;
        else return false; // ����� ������
    }
    //������� 4 ������ �������, ������� ��������� ����� � �������� ���������� ��� ���� ��������� �� ������ � ���������� �� ��� ���, ���� ����� ������ �� ����� 1 ������
    public static int sumDigProd(int...i) { /// ��� ����� ���������� ����������, ����� ������� ����� ���������� ����������
        int s=0; // ���������� ����� ��������
        for (int n:i) s+=n; // ������� ��� ��������� � ���������� � �����
        if (s<10) return s; // ���� ����� ������ 10, ������ s
        int p=1; // ������� ���������� ��� ��������������� ������������
        while (s>0) { // ���� ����� ������ 0
            p*=s%10; // ������� ������������ �� ����� ������ ����� �����
            s/=10; // ������ ����� ������ ����� �����
        }
        return sumDigProd(p); // ������� ����������, ���� ����� �� ������ ������� � ���� �����
    }
    //������� 5 ������� �������, ������� �������� ��� �����, ������� ��� �� �� �������, ��� � ������ ����� + ��� ����� �����

    public static List<String> sameVowelGroup(String[] words) {
        // ������ ����� �����������
        List<String> result = new ArrayList<>();
        char[] arr = words[0].toCharArray(); // ����� ������ ����� � �������������� � ������ ��������
        Set<Character> v = new HashSet<>(); // ���������� ���������, ����� ��������� ���������
        for(char a: arr){ // ������� ����� �� ���������
            if(a == 'a' ||a == 'e' ||a == 'i' ||a == 'o' ||a == 'u'){ // ���� ������ �������
                v.add(a); // ������� � ��������� v
            }
        }
        for(String s: words){ // ������� ��� �����
            boolean valid = true; // ���� ������� ��������� true ��� ���������� �����
            for(char c: s.toCharArray()){ // ����������� ������ � ������ �������� � ������ ��� ��������
                if((c == 'a' ||c == 'e' ||c == 'i' ||c == 'o' ||c == 'u')){ // ���� ������ �������
                    if(!v.contains(c)){ // � ��� ���� �� ���������� � ��������� v
                        valid = false; // ����� ����� �� �������� � ������ ���� �� false
                    }
                }
            }
            // ����� ������ ����� ���������, ���� ���� �� ���������, ������ ����� ��������
            if(valid){
                result.add(s); // ������� ����� � ��������� �������
            }
        }
        return result;
    }
    //������� 6 ������ �������, ������� ��������� ����� � �������� ��������� � ���������� True, ���� ����������� ����� ��������������, ����� False
    public static boolean validateCard(long num) {

        int str_len = String.valueOf(num).length(); // �������� ����� ����� � ����������
        if ( (str_len < 14) || (str_len > 19)) // ���� ����� ������ 14 ��� ������ 19, ������ ��� �� ��� �����
            return false;
        // ������� ��������� �����
        int last_digit = (int) (num%10);
        // ������ ��������� ����� � �������� �����, � ������� StringBuilder
        StringBuilder number = new StringBuilder(String.valueOf(num/10)).reverse();

        int temp = 0;
        // ������� ������ �� �������� ��������
        for (int i=0; i < number.length(); i= i+2){
            // ��������� �����,
            temp = Integer.parseInt(number.charAt(i)+ "")*2;
            // ������������ ��� ����� ����, ���� ��������� �������� ������ 1 �����
            if (temp/10 > 0){
                temp = temp/10 + temp%10;
            }
            // ������� ����� ����������� ������ �����
            number.replace(i, i+1, String.valueOf(temp));
        }
        temp = 0;
        // ������� ��� ����� ����� � ������ ��
        for (char x : number.toString().toCharArray()){
            temp += Integer.parseInt(x + "");
        }
        // �������� ��������� ����� �� 10, ��������� ������ ��������� ����� ����������� ��������� �����
        return ((10-(temp%10)) == last_digit);

    }
    //������� 7 ������� �������, ����������� ������������� ����� ����� �� 0 �� 999 � ������������ ��������� ������������� ����� �����, �� ����������
    public static String numToEng(int n) {
        // ����� �� 1 �� 9
        String[] ones = {"zero","one","two","three","four","five","six","seven",
                "eight","nine"};
        // ����� �� 10 �� 19
        String[] ten2twenty = {"ten","eleven","twelve","thirten","fourten","fifteen",
                "sixteen","seventeen","eighten","nineteen"};
        // ����� ������� 10 �� 20 �� 90
        String[] tens = {"","twenty","thirty","forty","fifty","sixty","seventy","eighty",
                "ninety"};
        String num1 = Integer.toString(n);
        // ���������� ��������� �������� ������� ������
        switch (num1.length()) {
            case 1:
                // ���� ������ 1, �� ��� ������ ������������� �������������
                return ones[Integer.parseInt(num1)];
            case 2:
                // teens
                return ten2twenty[Integer.parseInt(num1)];
            case 3:
                // hundreds
                StringBuilder sb = new StringBuilder();
                int[] digits = new int[3];
                int k=0;
                while( n != 0 )
                {
                    digits[k]=n%10;
                    n = n/10;
                    k++;
                }
                sb.append(ones[digits[2]]).append(" hundred ");
                if (digits[1] == 1) {
                    int c = digits[1]+digits[2];
                    sb.append(ten2twenty[c]);
                    return sb.toString();
                }
                if (digits[1] > 1) sb.append(tens[digits[1]-1]).append(" ");
                if (digits[0] > 0) sb.append(ones[digits[0]]);
                return sb.toString();
            default:
                break;
        }
        return "";
    }
    //������� 8 ��� �������� �������� �������, ������� ���������� ��� SHA - 256 ��� ������ ������ � ������������� � ���� 16 - ��� �����
    public static String getSha256Hash(String str) {
        // ����� �������, � ������� ��������� �������� ����������� � ������, ������� ����� ����������
        return hash(str,"SHA-256");
    }
    static String hash(String msg, String algo){
        String result = "";
        // ����� MessageDigest ������������ ��� ������� ���������� SHA-256 � SHA-1
        MessageDigest md;
        // ���������� ����������� ���������
        try {
            md = MessageDigest.getInstance(algo); // ��������� �������� � ������� ��� ����������
            byte[] hashes = md.digest(msg.getBytes()); // �������� ����� ������������ �������� ��� ������� �������, �������������� ������������ ������, � ����� ������
            for (int i = 0; i < hashes.length; i++){ // ������� ����� �����
                String hex = Integer.toHexString(0xff & hashes[i]); // ��������� ���� � ������
                if (hex.length() == 1) result+=0; // ���� ����� �������������� ������ ������ ��������� 1, �� ������� � ������ ������ 0
                result += hex; // ����� ������� ���
            }
        } catch (NoSuchAlgorithmException e) { // ���������� ���������� ��� �������, ���� ��������� �������� �� ����� ������
            e.printStackTrace();
        }
        // ���������� ������
        return result;
    }
    //������� 9 ����� �������, ������� ��������� ������ � ���������� ������ � ���������� ��������� ��� ���������� � ����� ���� ���������
    public static String correctTitle(String str) {
        // ������ ������ ���� ����������� ���������
        String[] words = str.split(" ");
        // ������ ����������
        String output = "";
        for (int i = 0; i < words.length; i++) {// ������� �����
            if (i>0) {
                output += " "; // ���� ��� �� ������ �����, �� ������� ������ ����� ����� ������
            }
            String[] wordsN = words[i].split("-"); // ���������� - ��� ����������� �� �������� � �����
            for (int j = 0; j < wordsN.length; j++) { // ������� ��������
                if(j>0) {
                    output += "-"; // ���� �������� �� ������, �� ������� -
                }
                // ���� ����� and the of in, �� �� ������ ���������
                if (wordsN[j].equalsIgnoreCase("and")
                        || wordsN[j].equalsIgnoreCase("the")
                        || wordsN[j].equalsIgnoreCase("of")
                        || wordsN[j].equalsIgnoreCase("in")) {
                    output += wordsN[j].toLowerCase();
                }
                else { // �����
                    output += wordsN[j].substring(0,1).toUpperCase(); // ������ ������ ������ �� ������� ������� � ���������� � ������
                    output += wordsN[j].substring(1).toLowerCase(); // ��������� ������ �� ������ ������� � ���������� � ������
                }
            }
        }

        return output;
    }
    //������� 10 �������� �������, ������� ��������� ����� ����� n � ���������� ������������, ���� n �� �������� �������������� ������������� ������
    // ��� ��� ������������ � ���� ������������� ������������� ������ � ��������� ������
    public static String hexLattice(int n) {
        int i = 0; // ���������� ��� ���������� �������������� �������
        boolean isHexLattice = false; // �������� �������� �� ������ ������������ ��������
        while (3*i*(i+1)+1 <= n){ // ���� �������� ����������� �� ������� 6 �������, ������ n
            if (3*i*(i+1)+1 == n) isHexLattice = true; // ���� ����� n ����� ������ �������������� �������
            i++; // �������� ������� �� 1
        }
        String str = "";
        if (isHexLattice){ // ���� ��� �������������� �������
            int l = i; // ������ ������
            int m = i; // ������� ������
            String str2; // ������ ��� "�����" ������
            for (int j = 0; j < 2*i-1; j++){ // ������� �� ���� �������
                str += "\n"; // ��������� ������� ������
                str2 = ""; // �������� ������ ��� "�����
                for (int k = 1; k < m; k++){ // ������� �� ������ �������������� �����
                    str2 += " "; // ��������� ������� ��� ���������������� ������
                }
                str += str2; // ������� ������ � �������� ������ ������
                for (int k = 0; k < l; k++){ // ������� �������
                    str += " o"; // � ������� ������ + �
                }
                str += str2 + " "; // ������� � ����� ���������
                l += (j < i-1) ? 1 : -1; // ���� j ������ i - 1, �� �������� � l 1, ����� ������� 1
                m += (j < i-1) ? -1 : 1; // ���� j ������ i - 1, �� ������� �� m 1, ����� �������� 1
            }
            str = str.replaceFirst("\n", ""); // ������ � ������ ������ ������� ������
            return str; // ������ ������ ���������
        } else return "Invalid"; // ���� �� �������������� ������� ������ invalid
    }

}