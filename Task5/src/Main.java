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
        System.out.println(canMove("Пешка", "A2", "A3"));
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
    //Задание 1 создаём две функции, которые принимают строку и масив и возвращают закодированные и декодированные сообщения
    public static int[] encrypt(String str) {
        int len = str.length(); // Получаем длинна
        int chCode=0; // Получить код буквы из кодировки
        int[] arrEncrypt = new int[len]; // масив зашифрованных значений
        for(int j=0;j<len;j++) { // Обходим строку которую нужно зашифровать
            arrEncrypt[j] = str.charAt(j) - chCode; // Подставляем в новый массив закодированные cимволы, как разница
            chCode = str.charAt(j); // Получаем код нового символа
        }
        return arrEncrypt;

    }

    public static String decrypt(int[] arr) {
        String str=""; // Результат строка
        int chCode=0; // Код символа
        for(int j=0;j<arr.length;j++) { // Обходим строку
            str+=(char)(arr[j]+chCode); // Раскодируем как массив сумм кода символа и значения символа
            chCode=arr[j]+chCode; // Получаем код нового символа
        }
        return str;
    }
    //Задание 2 Создаём функцию, которая принимает имя шахматной фигуры, её положение и целевую позицию
    public static boolean canMove(String p, String c, String t) {
        // Проверка пешки
        if (p.equals("Пешка")) {
            if (c.charAt(0) == t.charAt(0)) { // Если буквы одинаковые
                if (c.charAt(1) == '2' && t.charAt(1) == '4') { // Строки 2 и 4, можем ходить, первый ход можно через 2
                    return true;
                }
                if (c.charAt(1) == '7' && t.charAt(1) == '5') { // Строки 7 и 5, можем ходить, первый ход можно через 2
                    return true;
                }
                if (Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 1) { // Если разница между строками 1, то можем ходить
                    return true;
                }
            }
        }
        // Проверка коня
        if (p.equals("Конь")) {
            // Если разница по столбцам равна 2 и по строкам равна 1, то можем ходить
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == 2
                    && Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 1) {
                return true;
            }
            // Если разница по строкам равна 2 и по столбцам равна 1, то можем ходить
            if (Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 2
                    && Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == 1) {
                return true;
            }
        }
        // Если слон
        if (p.equals("Слон")) {
            // Если разница между столбцами предыдущей позиции и текущей равна разнице между столбцами между текущей и предыдущей
            // Тогда можем ходить
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == Math.abs((int) c.charAt(1) - (int) t.charAt(1))) {
                return true;
            }
        }
        // Если ладья
        if (p.equals("Ладья")) {
            // Если значение не изменилось по столбцам после хода или по строкам, тогда можем ходить
            if (c.charAt(0) == t.charAt(0)
                    || c.charAt(1) == t.charAt(1)) {
                return true;
            }
        }
        // Если королева
        if (p.equals("Королева")) {
            // Если разница между столбцами предыдущей позиции и текущей равна разнице между столбцами между текущей и предыдущей
            // Тогда можем ходить
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == Math.abs((int) c.charAt(1) - (int) t.charAt(1))) {
                return true;
            }
            // Если значение не изменилось по столбцам после хода или по строкам, тогда можем ходить
            if (c.charAt(0) == t.charAt(0)
                    || c.charAt(1) == t.charAt(1)) {
                return true;
            }
        }
        // Если король
        if (p.equals("Король")) {
            // Если разница по столбцам и строкам не более 1, то можем ходить
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) <= 1
                    && Math.abs((int) c.charAt(1) - (int) t.charAt(1)) <= 1) {
                return true;
            }
        }
        return false;
    }
    //Задание 3 Создаём функцию, которая будет учитывать входную строку и будет определять, может ли слово быть завершенным
    public static boolean canComplete(String initial, String word) {
        int in = 0; // Переменная для сохранения количества букв, которые встречается в строке базовой и результате
        // Обходим строку, которая должна получится
        for(int w = 0; w < word.length(); w++) {
            if(word.charAt(w) == initial.charAt(in)) // Если буква из результата встретилась в базовой строке
                in++; // Увеличим переменную для сохранения количества букв
        }
        // Если буквы, что встречались в строке результате встречались и в строке базовой
        // Так что в количество букв встреченных оказалось равно длине базовой строки, тогда строку можно достроить
        if(in == initial.length()) return true;
        else return false; // Иначе нельзя
    }
    //Задание 4 Создаём функцию, которая принимает числа в качестве аргументов при этом скалдывая их вместе и возвращает до тех пор, пока длина ответа не будет 1 цифрой
    public static int sumDigProd(int...i) { /// Три точки используем специально, чтобы принять любое количество переменных
        int s=0; // переменная сумма значений
        for (int n:i) s+=n; // Обходим все аргументы и складываем в сумму
        if (s<10) return s; // Если сумма меньше 10, вернем s
        int p=1; // Объявим переменную для алгебраического произведения
        while (s>0) { // Пока сумма больше 0
            p*=s%10; // Умножим произведение на самую правую цифру суммы
            s/=10; // Уберем самую правую цифру суммы
        }
        return sumDigProd(p); // Вызовем рекурисвно, пока ответ не станет длинной в одну цифру
    }
    //Задание 5 Напишем функцию, которая выберает все слова, имеющие все те же гласные, что и первое слово + это самое слово

    public static List<String> sameVowelGroup(String[] words) {
        // Список строк результатов
        List<String> result = new ArrayList<>();
        char[] arr = words[0].toCharArray(); // Берем первое слово и преобразовывем в массив символов
        Set<Character> v = new HashSet<>(); // Используем множество, чтобы отбросить дубликаты
        for(char a: arr){ // обойдем слово по символьно
            if(a == 'a' ||a == 'e' ||a == 'i' ||a == 'o' ||a == 'u'){ // если символ гласная
                v.add(a); // Добавим в множество v
            }
        }
        for(String s: words){ // Обойдем все слова
            boolean valid = true; // Флаг который останется true для подходящим строк
            for(char c: s.toCharArray()){ // Преобразуем строку в массив символов и начнем его обходить
                if((c == 'a' ||c == 'e' ||c == 'i' ||c == 'o' ||c == 'u')){ // Если символ гласная
                    if(!v.contains(c)){ // И при этом не содержится в множестве v
                        valid = false; // Тогда слово не подходим и меняем флаг на false
                    }
                }
            }
            // После обхода слова проверяем, если флаг не поменялся, значит слово подходит
            if(valid){
                result.add(s); // Добавим слово в результат ответов
            }
        }
        return result;
    }
    //Задание 6 Создаём функцию, которая принимает число в качестве аргумента и возвращает True, если крединатная карта действительная, иначе False
    public static boolean validateCard(long num) {

        int str_len = String.valueOf(num).length(); // Сохраним длину числа в переменной
        if ( (str_len < 14) || (str_len > 19)) // Если длина меньше 14 или больше 19, значит это не код карты
            return false;
        // Получим последнюю цифру
        int last_digit = (int) (num%10);
        // Удалим последнюю цифру и перевнем число, с помощью StringBuilder
        StringBuilder number = new StringBuilder(String.valueOf(num/10)).reverse();

        int temp = 0;
        // Обойдем строку по нечетным позициям
        for (int i=0; i < number.length(); i= i+2){
            // Удваиваем цифру,
            temp = Integer.parseInt(number.charAt(i)+ "")*2;
            // Конвертируем как сумму цифр, если удвоенное значение больше 1 цифры
            if (temp/10 > 0){
                temp = temp/10 + temp%10;
            }
            // заменем новым результатом старую цифру
            number.replace(i, i+1, String.valueOf(temp));
        }
        temp = 0;
        // Обойдем все новые цифры и сложим их
        for (char x : number.toString().toCharArray()){
            temp += Integer.parseInt(x + "");
        }
        // Вычитаем последнюю цифру из 10, результат должен оказаться равен запомненной последней цифры
        return ((10-(temp%10)) == last_digit);

    }
    //Задание 7 напишем функцию, принимающая положительные целые числа от 0 до 999 и возвращающее строковое представление этого числа, на английском
    public static String numToEng(int n) {
        // Цифры от 1 до 9
        String[] ones = {"zero","one","two","three","four","five","six","seven",
                "eight","nine"};
        // Цифры от 10 до 19
        String[] ten2twenty = {"ten","eleven","twelve","thirten","fourten","fifteen",
                "sixteen","seventeen","eighten","nineteen"};
        // Цифры кратные 10 от 20 до 90
        String[] tens = {"","twenty","thirty","forty","fifty","sixty","seventy","eighty",
                "ninety"};
        String num1 = Integer.toString(n);
        // Рассмотрим различные варианты длинные строки
        switch (num1.length()) {
            case 1:
                // Если длинна 1, то это значит одноразрядное представление
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
    //Задание 8 Хеш алгоритм Создадим функцию, которая возвращает хеш SHA - 256 для данной строки с форматировкай в виде 16 - ной цифры
    public static String getSha256Hash(String str) {
        // Метод обертка, в которой указываем алгоритм хеширования и строку, которую нужно хешировать
        return hash(str,"SHA-256");
    }
    static String hash(String msg, String algo){
        String result = "";
        // Класс MessageDigest используется для классов алгоритмов SHA-256 и SHA-1
        MessageDigest md;
        // Попытаемся зашифровать сообщение
        try {
            md = MessageDigest.getInstance(algo); // Применяем алгоритм к клаассу для шифрования
            byte[] hashes = md.digest(msg.getBytes()); // Получаем новые хешированные значения для каждого символа, предварительно преобразовав строку, в набор байтов
            for (int i = 0; i < hashes.length; i++){ // Обходим набор хешей
                String hex = Integer.toHexString(0xff & hashes[i]); // Переводим хеши к строке
                if (hex.length() == 1) result+=0; // Если после преобразования длинна строки оказалась 1, то добавим к строке символ 0
                result += hex; // Далее добавим хещ
            }
        } catch (NoSuchAlgorithmException e) { // Обработчик исключений для случаев, если названный алгоритм не будет найден
            e.printStackTrace();
        }
        // Возвращаем строку
        return result;
    }
    //Задание 9 Пишем функцию, которая принимает строку и возвращает строку с правильным регистром для заголовков в серии Игра Престолов
    public static String correctTitle(String str) {
        // Делаем массив слов разделенных пробелами
        String[] words = str.split(" ");
        // Строка результата
        String output = "";
        for (int i = 0; i < words.length; i++) {// Обходим слова
            if (i>0) {
                output += " "; // Если это не первое слово, то добавим пробел перед новым словом
            }
            String[] wordsN = words[i].split("-"); // Используем - как разделитель на подслова в слове
            for (int j = 0; j < wordsN.length; j++) { // обходим подслова
                if(j>0) {
                    output += "-"; // Если подслово не первое, то постави -
                }
                // Если слово and the of in, мы их делаем строчными
                if (wordsN[j].equalsIgnoreCase("and")
                        || wordsN[j].equalsIgnoreCase("the")
                        || wordsN[j].equalsIgnoreCase("of")
                        || wordsN[j].equalsIgnoreCase("in")) {
                    output += wordsN[j].toLowerCase();
                }
                else { // Иначе
                    output += wordsN[j].substring(0,1).toUpperCase(); // Меняем первый символ на верхний регистр и прибавляем к строке
                    output += wordsN[j].substring(1).toLowerCase(); // Остальные меняем на нижний регистр и прибавляем к строке
                }
            }
        }

        return output;
    }
    //Задание 10 Напишите функцию, которая принимает целое число n и возвращает недопустимое, если n не является центрированным шестиугольным числом
    // или его иллюстрацией в виде многострочной прямоугольной строки в противном случае
    public static String hexLattice(int n) {
        int i = 0; // Переменная для построение гексагональной решетки
        boolean isHexLattice = false; // Проверка является ли фигура шестигранной решеткой
        while (3*i*(i+1)+1 <= n){ // Если значение расчитанное по формуле 6 соседей, меньше n
            if (3*i*(i+1)+1 == n) isHexLattice = true; // если равно n значи фигура гексагональная решетка
            i++; // Увеличим уровень на 1
        }
        String str = "";
        if (isHexLattice){ // Если это гексагональная решетка
            int l = i; // строка вывода
            int m = i; // столбец вывода
            String str2; // Строка для "строк" вывода
            for (int j = 0; j < 2*i-1; j++){ // обходим по всем строкам
                str += "\n"; // добавляем перевод строки
                str2 = ""; // обнуляем строку для "строк
                for (int k = 1; k < m; k++){ // Обходим до начала гексагональной сетки
                    str2 += " "; // Добавляем пробелы для форматированного вывода
                }
                str += str2; // добавим строку к основной строке вывода
                for (int k = 0; k < l; k++){ // Обойдем решетку
                    str += " o"; // И добавим пробел + о
                }
                str += str2 + " "; // добавим в конец результат
                l += (j < i-1) ? 1 : -1; // если j меньше i - 1, то прибавим к l 1, иначе отнимим 1
                m += (j < i-1) ? -1 : 1; // если j меньше i - 1, то отнимим от m 1, иначе прибавим 1
            }
            str = str.replaceFirst("\n", ""); // Удалим в строке первый перевод строки
            return str; // Вернем строку результат
        } else return "Invalid"; // Если не гексагональная решетка вернем invalid
    }

}