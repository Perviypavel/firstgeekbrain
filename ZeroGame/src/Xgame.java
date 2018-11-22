

import java.text.MessageFormat;
import java.util.Random;
import java.util.Scanner;

public class Xgame {

    public static String[][]map;
    public static final int SIZE = 3;
    public static final int DOT_TO_WIN = 3;

    public static final String DOT_EMPTY ="*";
    public static final String DOT_X ="X";
    public static final String DOT_O ="O";

    public static void main(String[] args){
        initMap();
        printMap();
        while (true)
            humanTurn();
            printMap();
        if (checkWin(DOT_X)){
            System.out.println("Победил человеке!");
            break;
        }
        if (isMapFull()){
            System.out.println("Ничья");
            break;
        }
        computerTurn();
        printMap();
        if (checkWin(DOT_O)) {
            System.out.println("Выйграл компьютер!");
            break;
        }
        if (isMapFull()){
            System.out.println("Ничья");
            break;
        }
        System.out.println("Игра закончена!");
    }
    

    /**
     * Метод инециализирует игровое поле
     */
    public static void initMap(){
        map = new String[SIZE][SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Метод печает игровое поле в консоль
     */
    public static void printMap(){
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    /**
     * Метод обрабатывает ход человека
     */
    public static void humanTurn(){
        Scanner scanner = new Scanner(System.in);
        int row;
        int column;
        do {
            System.out.println("Введите координаты хода в формате Х и Y, /n" +
                    "где Х - номер строки, а Y - номер колонки");
            row = scanner.nextInt();
            column = scanner.nextInt();
        }while (!isCellValid(row, column));
        map[row][column] = DOT_X;

    }

    /**
     * Метод обрабатывает ход компьютера
     */
    public static void computerTurn() {
        Random random = new Random();
        int row;
        int column;
        do {
            row = random.nextInt(SIZE);
            column = random.nextInt(SIZE);
        } while (!isCellValid(row, column));
        System.out.println(MessageFormat.format("Компьютер походил в точку {0}{1}    ", row + 1, column + 1));
        map[row][column] = DOT_O;

    }
    public static boolean isCellValid(int row, int column) {
        if (row < 0 || row >= SIZE || column < 0 || column >= SIZE) {
            return false;
        }
        if (map[row][column].equals(DOT_EMPTY)) {
            return true;
        }
        return false;

    }

    /**
     * Методпроверяет поле на наличие пустых клеток
     * @return {@code true} если пустых клеток нет, иначе (@code false)
     */
    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j].equals(DOT_EMPTY)){
                    return false;
                }

            }
            
        }
        return true;
    }
    public static boolean checkWin(String symb){
        if (map[0][0].equals(symb) && map[0][1].equals(symb) && map[0][2].equals(symb)) return true;
        if (map[1][0].equals(symb) && map[1][1].equals(symb) && map[1][2].equals(symb)) return true;
        if (map[2][0].equals(symb) && map[2][1].equals(symb) && map[2][2].equals(symb)) return true;
        if (map[0][0].equals(symb) && map[1][0].equals(symb) && map[2][0].equals(symb)) return true;
        if (map[0][1].equals(symb) && map[1][1].equals(symb) && map[2][1].equals(symb)) return true;
        if (map[0][2].equals(symb) && map[1][2].equals(symb) && map[2][2].equals(symb)) return true;
        if (map[0][0].equals(symb) && map[1][1].equals(symb) && map[2][2].equals(symb)) return true;
        if (map[2][0].equals(symb) && map[1][1].equals(symb) && map[0][2].equals(symb)) return true;
        return false;
    }

}
