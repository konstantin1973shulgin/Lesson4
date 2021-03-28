

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

    public class Lesson4 {

        private static final char DEFAULT = '_';  //имена констант содержат только заглавные буквы
        private static final char X = 'X';// значение константы задаётся сразу при объявлении и не изменяется
        private static final char O = '0';
        private static final int SIZE = 3;
        private static final char[][] MAP = new char[SIZE][SIZE];

        /**
         * _ _ X
         * _ O _
         * _ _ _
         */

        private static void initMap() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    MAP[i][j] = DEFAULT;
                }
            }
        }

        private static void printMap() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(MAP[i][j] + " ");
                }
                System.out.println();
            }
        }

        private static void game(Scanner in) {
            initMap();
            System.out.println("Игра Крестики Нолики");
            printMap();
            System.out.println("Для хода необходимо ввести номер строки и номер столбца");
            int stepCounter = 0;
            while (true) {
                System.out.println("Ваш ход: ");
                String line = in.nextLine();
                String[] args = line.split(" "); // 12 1212 -> [12, 1212]
                if (args.length != 2) {
                    System.out.println("Введите два числа");
                } else {
                    try {
                        int x = Integer.parseInt(args[0]);
                        int y = Integer.parseInt(args[1]);
                        x--;
                        y--;
                        if (isValid(x, y)) {
                            makeStep(x, y, X);
                            printMap();
                            stepCounter++;
                            if (checkVictory(X)) {
                                System.out.println("Вы победили");
                                return;
                            }
                            if (stepCounter == 9) {
                                System.out.println("Ничья");
                                return;
                            }
                            System.out.println("Ход компьютера: ");
                            // joke();
                            movePC();
                            printMap();
                            stepCounter++;
                            if (checkVictory(O)) {
                                System.out.println("Вы проиграли");
                                return;
                            }
                        } else {
                            System.out.println("Некорректный ход.\n" +
                                    "Введите два числа от 1 до 3");
                        }
                    } catch (Exception e) {
                        System.out.println("Введите два числа");
                    }
                }
            }
        }

        private static void joke() throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println("Думаю о тебе");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println("Майню биткоин");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println("Читаю твою личку в фейсбуке");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println("Ворую рубль со счета");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println("Отправляю письмо бывшей");
        }

        private static void movePC() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (MAP[i][j] == DEFAULT) {
                        makeStep(i, j, O);
                        return;
                    }
                }
            }
        }

        private static boolean checkLine(char x, int lineNumber) {
            for (int i = 0;i<SIZE;i++ ){
                if (MAP[lineNumber][i] != x) {
                    return false;
                }
            }
            return true;
        }

        private static boolean checkCol(char x, int colNumber) {
            for (int i = 0;i<SIZE;i++ ){
                if (MAP[i][colNumber] != x) {
                    return false;
                }
            }
            return true;
        }

        private static boolean checkVictory(char x) {
            // написать логику

            for (int i = 0; i<SIZE;i++){
                if (checkCol(x, i) || checkLine(x, i)) {
                    return true;
                }
            }

            return false;
        }


        private static void makeStep(int x, int y, char sym) {
            MAP[x][y] = sym;
        }

        private static boolean isValid(int x, int y) {
            return x >= 0
                    && x < SIZE
                    && y >= 0
                    && y < SIZE
                    && MAP[x][y] == DEFAULT;
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            game(in);
        }

    }

