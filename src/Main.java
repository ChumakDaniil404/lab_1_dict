import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String key;
        String value;
        DictionaryClass d1 = new DictionaryClass();
        DictionaryClassSecond d2 = new DictionaryClassSecond();
        boolean flags = true; //для первого словаря (правда)
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        boolean exit2 = false;
        String path = ""; //путь
        try {
            System.out.println("Введите путь к файлу ");
            path = sc.next();
            //System.out.println("Содержимое файла: ");
            while (!exit2) {
                System.out.println("Ваши действия: \n1. Выбрать 1 словарь\n2. Выбрать 2 словарь\n3. Выход ");
                String number2 = checkingForASymbol();
                switch (number2) {
                    case "1"://считывает блокнот и записывает в словарь
                        System.out.println("Вы нажали 1");
                        System.out.println("Вы работаете со первым словарем");
                        exit2 = true;
                        break;
                    case "2":
                        flags = false;
                        System.out.println("Вы нажали 2");
                        System.out.println("Вы работаете со вторым словарем");
                        d2.readFileLineByLine(path, false);
                        d2.outputFromTheDictionary();
                        exit2 = true;
                        break;
                    case "3":
                        System.out.println("Выбран 3 пункт меню.");
                        exit2 = true;
                        break;
                    default:
                        System.out.println("Введена неверная команда");
                }
            }
            while (!exit) {
                System.out.println("Ваши действия: \n1. Считать блокнот и записывать в словарь\n2. Вывод(словаря) \n3. Добавление записи\n4. Поиск записи по ключу\n5. Удаление записи по ключу\n" +
                        "6. Поменять словари местами\n7. Выход ");
                String number = checkingForASymbol();
                switch (number) {
                    case "1"://считывает блокнот и записывает в словарь
                        System.out.println("Выбран 1 пункт меню.");
                        if (flags) {
                            d1.readFileLineByLine(path, true);
                        } else {
                            d2.readFileLineByLine(path, true);
                        }
                        break;
                    case "2"://вывод того что в словаре
                        System.out.println("Выбран 2 пункт меню.");
                        if (flags) {
                            d1.outputFromTheDictionary();
                        } else {
                            d2.outputFromTheDictionary();
                        }
                        break;
                    case "3"://добавление записи
                        System.out.println("Выбран 3 пункт меню.");
                        try {
                            System.out.println("Введите ключ");
                            key = sc.next();
                            System.out.println("Введите значение");
                            value = sc.next();
                            if (flags) {
                                d1.check(key, value);
                            } else {
                                d2.check(key, value);
                            }
                        } catch (Exception e) {
                            System.out.println("Не удалось добавить запись");
                        }
                        break;
                    case "4"://поиск записи по ключу
                        System.out.println("Выбран 4 пункт меню.");
                        System.out.println("Введите ключ");
                        key = sc.next();
                        if (flags) {
                            d1.search(key);
                        } else {
                            d2.search(key);
                        }
                        System.out.println("\n");
                        break;
                    case "5":// удаление записи по ключу
                        System.out.println("Выбран 5 пункт меню.");
                        System.out.println("Введите ключ");
                        key = sc.next();
                        if (flags) {
                            d1.removal(key);
                        } else {
                            d2.removal(key);
                        }
                        System.out.println("\n");
                        break;
                    case "6": //Перевернуть словарь
                        System.out.println("Выбран 6 пункт меню.");
                        flags = !flags;
                        if (flags) {
                            d1.clear();
                            d2.flipTheDictionary();
                            d2.writeUsingOutputStream(path);
                            d1.readFileLineByLine(path, true);
                            d1.outputFromTheDictionary();
                            System.out.println("Вы работаете с первым словарем");
                        } else {
                            d2.clear();
                            d1.writeUsingOutputStream(path);
                            d2.readFileLineByLine(path, false);
                            d2.outputFromTheDictionary();
                            System.out.println("Вы работаете со вторым словарем");
                        }
                        break;
                    case "7"://выход
                        System.out.println("Выбран 7 пункт меню.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Введена неверная команда");
                }
            }
        } catch (Exception e) {
            System.out.println("Данные введены неверно");
        }
    }

    public static String checkingForASymbol() {
        System.out.println("Выберите пункт меню");
        try {
            Scanner sc2 = new Scanner(System.in);
            return sc2.nextLine();
        } catch (Exception e) {
            System.out.println("Данные введены неверно. Пожалуйста повторите ввод команды из пунктов меню.");
            return "";
        }
    }
}