package MainKurs;

import java.util.Scanner;

public class Main {

    // Глобальные переменные X
    public static int x1, x2, x3, x4, x5, x6 , x7, x8, x9, x10;

    public static boolean xx1,xx2,xx3,xx4,xx5,xx6,xx7,xx8,xx9,xx10;

    // Текущие значения автомата
    public static StringBuilder currentY = new StringBuilder("000000000000000");
    public static StringBuilder currentW = new StringBuilder("0000");
    public static StringBuilder currentU = new StringBuilder("0000");
    public static StringBuilder currentA = new StringBuilder("0000");
    public static int currentState = 0;

    // Массив для проверки зацикливания
    public static int [] mass = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    // Троичные матрицы
    public static String [][] matrixY = new String [15][];
    public static String [][] matrixW = new String [4][];
    public static String [][] matrixU = new String [4][];

    // Коды состояний
    public static String [] states = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001",
            "1010", "1011", "1100", "1101", "1110"};

    // boolean для отлавливания финиша
    public static boolean finished = false;

    // Переменные для подсчета времени выполнения
    public static double startTime;
    public static double stopTime;
    public static double elapsedTime;
    // Валидация входных данных
    public static boolean InputValidation(){
        if (String.valueOf(x1).length() > 1 || String.valueOf(x2).length() > 1 || String.valueOf(x3).length() > 1
                || String.valueOf(x4).length() > 1 || String.valueOf(x5).length() > 1 || String.valueOf(x6).length() > 1
                || String.valueOf(x7).length() > 1 || String.valueOf(x8).length() > 1 || String.valueOf(x9).length() > 1
                || String.valueOf(x10).length() > 1) {
            System.out.println("Error: Длина входных значений не равна 1");
            return false;
        }

        if (x1 != 0 && x1 != 1 || x2 != 0 && x2 != 1 ||x3 != 0 && x3 != 1 || x4 != 0 && x4 != 1 ||x5 != 0 && x5 != 1
                || x6 != 0 && x6 != 1 || x7 != 0 && x7 != 1 || x8 != 0 && x8 != 1|| x9 != 0 && x9 != 1 || x10 != 0 && x10 != 1) {
            System.out.println("Error: Во входных значениях присутствуют запрещенные символы");
            return false;
        }

        return true;
    }

    // LOG текущего состояния
    public static void stateLOG(int currentState, String [] states, StringBuilder currentY) {
        System.out.println("Текущее состояние: S" + currentState);
        System.out.println("Код состояния: " + states[currentState]);
        System.out.println("Текущие Y (y1 ... y15): " + currentY);
        System.out.println("");
    }

    // Переворот строки
    public static String reverseString(String str) {
        char [] arr = str.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        char tmp;
        while (j > i) {
            tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
            j--;
            i++;
        }
        return new String(arr);
    }

    // Сравнение строк
    public static boolean Compare(String pattern, String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if (pattern.charAt(i) != '-')
            {
                if(str.charAt(i) != pattern.charAt(i))
                    return false;
            }

        }

        return true;
    }

    public static void wu() {
        String input = currentA + String.valueOf(x10) + String.valueOf(x9) + String.valueOf(x8) +
                String.valueOf(x7) + String.valueOf(x6) + String.valueOf(x5) + String.valueOf(x4) + String.valueOf(x3) +
                String.valueOf(x2) + String.valueOf(x1);
        for(int i = 0; i < matrixW.length; i++)
        {
            for (int j = 0; j < matrixW[i].length; j++)
            {
                if (Compare(matrixW[i][j], input)) {
                    currentW.setCharAt(matrixW.length - i - 1, '1');
                }
            }

            for (int j = 0; j < matrixU[i].length; j++)
            {
                if (Compare(matrixU[i][j], input)) {
                    currentU.setCharAt(matrixW.length - i - 1, '1');
                }
            }
        }
    }

    public static void wuLog() {
        int a3temp = Integer.parseInt(String.valueOf(currentA.charAt(0)));
        int a2temp = Integer.parseInt(String.valueOf(currentA.charAt(1)));
        int a1temp = Integer.parseInt(String.valueOf(currentA.charAt(2)));
        int a0temp = Integer.parseInt(String.valueOf(currentA.charAt(3)));

        boolean a3,a2,a1,a0,w0,w1,w2,w3,u0,u1,u2,u3;

        a3 = a3temp == 1;
        a2 = a2temp == 1;
        a1 = a1temp == 1;
        a0 = a0temp == 1;

        w0 = !a3 && !a2 && !a1 && !a0 || !a3 && !a2 && a1 && !a0 || !a3 && a2 && !a1 && !a0 && !xx5 || !a3 && a2 && a1 && !a0 || a3 && !a2 && !a1 && !a0 && xx1 && xx7 ||  a3 && !a2 && !a1 && !a0 && !xx1 && xx2 && xx8 || a3 && a2 && !a1 && !a0;
        w1 = !a3 && !a2 && !a1 && a0 && xx1 || !a3 && !a2 && !a1 && a0 && xx3 || !a3 && !a2 && !a1 && a0 && xx2 || !a3 && a2 && !a1 && !a0 && xx5 || !a3 && a2 && !a1 && a0 || a3 && !a2 && !a1 && !a0 && xx1 && !xx7 && xx9 || a3 && !a2 && !a1 && !a0 && !xx1 && !xx2 && xx9 || a3 && !a2 && !a1 && !a0 && !xx1 && xx2 && !xx8 && xx9 || a3 && !a2 && !a1 && a0 && xx9 || a3 && a2 && !a1 && a0 && xx6 && xx2 && !xx8 && xx9 || a3 && a2 && !a1 && a0 && xx6 && !xx2 && !xx4;
        w2 = !a3 && !a2 && a1 && a0 && xx4 || !a3 && !a2 && a1 && a0 && !xx4 || a3 && !a2 && a1 && a0 && !xx10 || a3 && !a2 && a1 && a0 && xx10;
        w3 = !a3 && !a2 && !a1 && a0 && xx2 || !a3 && !a2 && !a1 && a0 && xx3 || !a3 && !a2 && a1 && a0 && !xx4 || !a3 && a2 && a1 && a0 && xx6;

        u0 = !a3 && !a2 && !a1 && a0 && xx1 || !a3 && !a2 && a1 && a0 && xx4 || !a3 && !a2 && a1 && a0 && !xx4 || !a3 && a2 && a1 && a0 && !xx6 && xx5 || !a3 && a2 && a1 && a0 && xx6 || a3 && !a2 && !a1 && a0 && !xx9 || a3 && !a2 && !a1 && a0 && xx9 || a3 && !a2 && a1 && a0 && xx10 || a3 && a2 && !a1 && a0 && xx6 && xx2 && !xx8 && xx9 || a3 && a2 && !a1 && a0 && xx6 && !xx2 && xx4 || a3 && a2 && !a1 && a0 && xx6 && !xx2 && !xx4 || a3 && a2 && !a1 && a0 && xx6 && xx2 && !xx8 && !xx9 || a3 && a2 && !a1 && a0 && !xx6 && xx10;
        u1 = !a3 && !a2 && a1 && a0 && xx4 || !a3 && a2 && a1 && a0 && !xx6 && !xx5 || !a3 && a2 && a1 && a0 && xx6 || a3 && !a2 & a1 && !a0 || a3 && !a2 && a1 && a0 && !xx10 || a3 && !a2 && a1 && a0 && xx10 || a3 && a2 && a1 && !a0;
        u2 = !a3 && a2 && a1 && a0 && xx6 || a3 && a2 && !a1 && a0 && xx6 && xx2 && !xx8 && xx9 || a3 && a2 && !a1 && a0 && xx6 && !xx2 && xx4 || a3 && a2 && !a1 && a0 && xx6 && xx2 && xx8 || a3 && a2 && !a1 && a0 && xx6 && xx2 && !xx8 && !xx9 || a3 && a2 && a1 && !a0;
        u3 = a3 && !a2 && !a2 && !a0 && xx1 && !xx7 && !xx9 || a3 && !a2 && !a2 && !a0 && !xx1 && !xx2 && !xx9 || a3 && !a2 && !a1 && !a0 && !xx1 && xx2 && !xx8 && !xx9 || a3 && !a2 && !a1 && a0 && !xx9 || a3 && !a2 && a1 && !a0 || a3 && a2 && !a1 && a0 && xx6 && xx2 && !xx8 && !xx9 || a3 && a2 && a1 && !a0;

        if (w0) {
            currentW.setCharAt(3, '1');
        }
        if (w1) {
            currentW.setCharAt(2, '1');
        }
        if (w2) {
            currentW.setCharAt(1, '1');
        }
        if (w3) {
            currentW.setCharAt(0, '1');
        }

        if (u0) {
            currentU.setCharAt(3, '1');
        }
        if (u1) {
            currentU.setCharAt(2, '1');
        }
        if (u2) {
            currentU.setCharAt(1, '1');
        }
        if (u3) {
            currentU.setCharAt(0, '1');
        }
    }

    public static void newState() {
        for(int i = 0; i < currentW.length(); i++)
        {
            if (currentW.charAt(i) == '1')
                currentA.setCharAt(i, '1');
            else if (currentU.charAt(i) == '1')
                currentA.setCharAt(i, '0');
        }
        currentState = Integer.parseInt(currentA.toString(), 2);
        mass[currentState] += 1;
    }

    public static void y() {
        for (int i = 0; i < matrixY.length; i++)
        {
            for (String y : matrixY[i])
            {
                if (Compare(y, currentA.toString())) {
                    currentY.setCharAt(i, '1');
                }
            }
        }
    }

    public static void clear() {
        currentY = new StringBuilder("000000000000000");
        currentW = new StringBuilder("0000");
        currentU = new StringBuilder("0000");
    }

    public static void ys() {
        int a3temp = Integer.parseInt(String.valueOf(currentA.charAt(0)));
        int a2temp = Integer.parseInt(String.valueOf(currentA.charAt(1)));
        int a1temp = Integer.parseInt(String.valueOf(currentA.charAt(2)));
        int a0temp = Integer.parseInt(String.valueOf(currentA.charAt(3)));

        boolean a3,a2,a1,a0;

        a3 = a3temp == 1;
        a2 = a2temp == 1;
        a1 = a1temp == 1;
        a0 = a0temp == 1;

        boolean y1 = false;
        boolean y2 = false;
        boolean y3 = false;
        boolean y4 = false;
        boolean y5 = false;
        boolean y6 = false;
        boolean y7 = false;
        boolean y8 = false;
        boolean y9 = false;
        boolean y10 = false;
        boolean y11 = false;
        boolean y12 = false;
        boolean y13 = false;
        boolean y14 = false;
        boolean y15 = false;

        y1 = !a3 && !a2 && !a1 && a0;
        y2 = !a3 && !a2 && a1 && !a0 || !a3 && a2 && !a1 && a0;
        y3 = !a3 && !a2 && a1 && a0;
        y4 = !a3 && a2 && !a1 && !a0 || !a3 && a2 && a1 && a0;
        y5 = !a3 && a2 && !a1 && !a0 || a3 && !a2 && a1 && a0;
        y6 = !a3 && a2 && a1 && !a0 || a3 && a2 && !a1 && !a0;
        y7 = !a3 && a2 && a1 && a0;
        y8 = !a3 && a2 && a1 && a0 || a3 && a2 && !a1 && a0;
        y9 = a3 && !a2 && !a1 && !a0;
        y10 = a3 && !a2 && !a1 && a0;
        y11 = a3 && !a2 && a1 && !a0;
        y12 = a3 && !a2 && a1 && a0;
        y13 = a3 && a2 && !a1 && a0;
        y14 = a3 && a2 && !a1 && a0;
        y15 = a3 && a2 && a1 && !a0;

        if (y1) {
            currentY.setCharAt(0, '1');
        }
        if (y2) {
            currentY.setCharAt(1, '1');
        }
        if (y3) {
            currentY.setCharAt(2, '1');
        }
        if (y4) {
            currentY.setCharAt(3, '1');
        }
        if (y5) {
            currentY.setCharAt(4, '1');
        }
        if (y6) {
            currentY.setCharAt(5, '1');
        }
        if (y7) {
            currentY.setCharAt(6, '1');
        }
        if (y8) {
            currentY.setCharAt(7, '1');
        }
        if (y9) {
            currentY.setCharAt(8, '1');
        }
        if (y10) {
            currentY.setCharAt(9, '1');
        }
        if (y11) {
            currentY.setCharAt(10, '1');
        }
        if (y12) {
            currentY.setCharAt(11, '1');
        }
        if (y13) {
            currentY.setCharAt(12, '1');
        }
        if (y14) {
            currentY.setCharAt(13, '1');
        }
        if (y15) {
            currentY.setCharAt(14, '1');
        }
    }



    // Функция множества единичных наборов
    public static void log() {
        if (!InputValidation()) {
            return;
        }

        startTime = System.nanoTime();

        stateLOG(currentState, states, currentY);

        do
        {
            // Условия выхода из цикла
            if (mass[currentState] > 2)
            {
                System.out.println("Обнаружено зацикливание...");
                break;
            }

            // Очистка текущих значений
            clear();

            // Подсчет W и U
            wuLog();

            // Переход в новое состояние
            newState();

            // Подсчет Y
            ys();


            // Вывод LOG'а текущего состояния
            stateLOG(currentState, states, currentY);

        } while (currentState != 0);

        stopTime = System.nanoTime();

        // Время выполнения
        elapsedTime = (stopTime - startTime) * 1000000;
        System.out.println("");
        System.out.println("Execution time: " + elapsedTime + " ms");
    }

    // Функция троичных матриц
    public static void matrix()
    {
        if (!InputValidation()) {
            return;
        }

        startTime = System.nanoTime();

        stateLOG(currentState, states, currentY);

        do
        {
            // Условия выхода из цикла
            if (mass[currentState] > 2)
            {
                System.out.println("Обнаружено зацикливание...");
                break;
            }

            // Очистка текущих значений
            clear();

            // Подсчет W и U
            wu();

            // Переход в новое состояние
            newState();

            // Подсчет Y
            y();


            // Вывод LOG'а текущего состояния
            stateLOG(currentState, states, currentY);

        } while (currentState != 0);

        stopTime = System.nanoTime();

        // Время выполнения
        elapsedTime = (stopTime - startTime) * 1000000;
        System.out.println("");
        System.out.println("Execution time: " + elapsedTime + " ms");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Введите x1, x2, x3, x4, x5, x6, x7, x8, x9, x10 через пробел");

        x1 = input.nextInt();
        x2 = input.nextInt();
        x3 = input.nextInt();
        x4 = input.nextInt();
        x5 = input.nextInt();
        x6 = input.nextInt();
        x7 = input.nextInt();
        x8 = input.nextInt();
        x9 = input.nextInt();
        x10 = input.nextInt();

        xx1 = x1 == 1;
        xx2 = x2 == 1;
        xx3 = x3 == 1;
        xx4 = x4 == 1;
        xx5 = x5 == 1;
        xx6 = x6 == 1;
        xx7 = x7 == 1;
        xx8 = x8 == 1;
        xx9 = x9 == 1;
        xx10 = x10 == 1;

        System.out.println("Введите 1 для решения методом троичных матрицы или 2 для решения методом множества единичных наборов.");
        int temp;
        temp = input.nextInt();

        input.close();

        if (x2 == 1 && x1 == 1) {
            System.out.println("Error: Параллельный переход");
            return;
        }else if (x3 == 1 && x1 == 1) {
            System.out.println("Error: Параллельный переход");
            return;
        }

        // Троичные матрицы для Y
        matrixY[0] = new String [1];
        matrixY[1] = new String [2];
        matrixY[2] = new String [1];
        matrixY[3] = new String [2];
        matrixY[4] = new String [2];
        matrixY[5] = new String [2];
        matrixY[6] = new String [1];
        matrixY[7] = new String [2];
        matrixY[8] = new String [1];
        matrixY[9] = new String [1];
        matrixY[10] = new String [1];
        matrixY[11] = new String [1];
        matrixY[12] = new String [1];
        matrixY[13] = new String [1];
        matrixY[14] = new String [1];
        matrixY[0][0] = "0001";
        matrixY[1][0] = "0010";
        matrixY[2][0] = "0011";
        matrixY[3][0] = "0100";
        matrixY[4][0] = "0100";
        matrixY[5][0] = "0110";
        matrixY[6][0] = "0111";
        matrixY[7][0] = "0111";
        matrixY[8][0] = "1000";
        matrixY[9][0] = "1001";
        matrixY[10][0] = "1010";
        matrixY[11][0] = "1011";
        matrixY[12][0] = "1101";
        matrixY[13][0] = "1101";
        matrixY[14][0] = "1110";
        matrixY[1][1] = "0101";
        matrixY[3][1] = "0111";
        matrixY[4][1] = "1011";
        matrixY[5][1] = "1100";
        matrixY[7][1] = "1101";

        // Троичные матрицы для W

        matrixW[0] = new String [7];
        matrixW[1] = new String [11];
        matrixW[2] = new String [4];
        matrixW[3] = new String [4];
        matrixW[0][0] = "0000----------";
        matrixW[0][1] = "0010----------";
        matrixW[0][2] = "0100-----0----";
        matrixW[0][3] = "0110----------";
        matrixW[0][4] = "1000--1-----10";
        matrixW[0][5] = "1100----------";
        matrixW[0][6] = "1000---1-----1";
        matrixW[1][0] = "0001---------1";
        matrixW[1][1] = "0001-------1--";
        matrixW[1][2] = "0001--------1-";
        matrixW[1][3] = "0100-----1----";
        matrixW[1][4] = "0101----------";
        matrixW[1][5] = "1000-1-0-----1";
        matrixW[1][6] = "1000-1------00";
        matrixW[1][7] = "1000-10-----10";
        matrixW[1][8] = "1001-1--------";
        matrixW[1][9] = "1101-10-1---1-";
        matrixW[1][10] = "1101----1-0-0-";
        matrixW[2][0] = "0011------1---";
        matrixW[2][1] = "0011------0---";
        matrixW[2][2] = "10110---------";
        matrixW[2][3] = "10111---------";
        matrixW[3][0] = "0001--------1-";
        matrixW[3][1] = "0001-------1--";
        matrixW[3][2] = "0011------0---";
        matrixW[3][3] = "0111----1-----";

        // Троичные матрицы для U
        matrixU[0] = new String [13];
        matrixU[1] = new String [7];
        matrixU[2] = new String [6];
        matrixU[3] = new String [7];
        matrixU[0][0] = "0001---------1";
        matrixU[0][1] = "0011------1---";
        matrixU[0][2] = "0011------0---";
        matrixU[0][3] = "0111----01----";
        matrixU[0][4] = "0111----1-----";
        matrixU[0][5] = "1001-0--------";
        matrixU[0][6] = "1001-1--------";
        matrixU[0][7] = "10111---------";
        matrixU[0][8] = "1101-10-1---1-";
        matrixU[0][9] = "1101----1-1-0-";
        matrixU[0][10] = "1101----1-0-0-";
        matrixU[0][11] = "1101-00-1---1-";
        matrixU[0][12] = "11011---0-----";
        matrixU[1][0] = "0011------1---";
        matrixU[1][1] = "0111----00----";
        matrixU[1][2] = "0111----1-----";
        matrixU[1][3] = "1010----------";
        matrixU[1][4] = "10110---------";
        matrixU[1][5] = "10111---------";
        matrixU[1][6] = "1110----------";
        matrixU[2][0] = "0111----1-----";
        matrixU[2][1] = "1101-10-1---1-";
        matrixU[2][2] = "1101----1-1-0-";
        matrixU[2][3] = "1101--1-1---1-";
        matrixU[2][4] = "1101-00-1---1-";
        matrixU[2][5] = "1110----------";
        matrixU[3][0] = "1000-0-0-----1";
        matrixU[3][1] = "1000-0------00";
        matrixU[3][2] = "1000-00-----10";
        matrixU[3][3] = "1001-0--------";
        matrixU[3][4] = "1010----------";
        matrixU[3][5] = "1101-00-1---1-";
        matrixU[3][6] = "1110----------";

        if (temp == 1) {
            matrix();
        }else if (temp == 2) {
            log();
        }else {
            System.out.println("Error: Некорректный ввод данных.");
        }
    }

}
