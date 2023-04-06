import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DictionaryClass implements IDictionaryInterface {
    Map<String, String> dict = new HashMap<>();
    String key;
    String keys;
    String values;
    String value;

    public void readFileLineByLine(String path, Boolean invert) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.contains(" ")) {
                    boolean line_right = line.matches("\\w{5}\\s\\w{4}");
                    if (line_right) {
                        String[] parts = line.split(" ");
                        String key = parts[0];
                        String value = parts[1];
                        check(key, value);
                    } else {
                        System.out.println("Строка не соответствует условию " + line);
                    }
                } else {
                    System.out.println("Строка не содержит пробел " + line);
                }
            }
            System.out.println("---");
        } catch (Exception e) {
            System.out.println("Не удалось считать файл");
        }
    }

    public void check(String key, String value) {
        Boolean key_right = key.matches("\\d{5}");
        Boolean value_right = value.matches("[a-zA-Z]{4}");
        if (key_right & value_right) {
            dict.put(key, value);
        } else {
            System.out.println("Неверно введенные данные(");
        }
    }

    public void outputFromTheDictionary() {
        getKeyAndValues();
        System.out.println("---");
    }

    public void flipTheDictionary() {
        //keys = getKeys();
        Map<String, String> time = new HashMap<>();
        for (Map.Entry<String, String> entry : dict.entrySet()) {
            key = entry.getValue();
            value = entry.getKey();
            time.put(key, value);
            dict = time;
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }

    public void search(String key) {
        if (dict.get(key) == null) {
            System.out.println("Такой записи не существует");
        } else {
            System.out.print(dict.get(key));
        }
    }

    public void removal(String key) {
        if (dict.get(key) == null) {
            System.out.println("Такой записи не существует");
        } else {
            System.out.print(dict.remove(key));
        }
    }

    public void writeUsingOutputStream(String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            os.write(dictionaryToString().getBytes(), 0, dictionaryToString().length());
        } catch (Exception e) {
            System.out.println("Не удалось записать файл");
        }
    }

    public String dictionaryToString() {
        String string = dict.toString();
        string = string.substring(1, string.length() - 1);
        string = string.replace("=", " ");
        string = string.replace(", ", "\n");
        return string;
    }

    public void clear() {
        dict.clear();
    }

    public void getKeyAndValues() {
        for (Map.Entry<String, String> entry : dict.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}