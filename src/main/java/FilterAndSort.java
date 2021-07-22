import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FilterAndSort {
    private File fileIn;
    private File fileOut;

    private List<Integer> inputIntList;
    private List<Integer> filterIntList;
    private List<Integer> squareIntList;

    //инициализация.
    public FilterAndSort() {
        inputIntList = new ArrayList<>();
        filterIntList = new ArrayList<>();
        squareIntList = new ArrayList<>();
    }

    public static void main(String[] args) {
        FilterAndSort evenSquare = new FilterAndSort();

        String pathIn = "src/main/resources/input.txt";
        String pathOut = "src/main/resources/output.txt";

        evenSquare.setFileIn(new File(pathIn).getAbsoluteFile());
        evenSquare.setFileOut(new File(pathOut).getAbsoluteFile());

        evenSquare.readFileInt();
        System.out.println(evenSquare.inputIntList);

        //фильтрация
        evenSquare.filterEven();
        System.out.println(evenSquare.filterIntList);

        //возведение
        evenSquare.listToSquare();
        System.out.println(evenSquare.squareIntList);

        //сортировка
        evenSquare.toSortList();
        System.out.println(evenSquare.squareIntList);

        evenSquare.outputInFile();
    }

    public void setFilterIntList(List<Integer> filterIntList) {
        this.filterIntList = filterIntList;
    }

    public List<Integer> getInputIntList() {
        return inputIntList;
    }

    //установка файла ввода и вывода.
    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }

    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    //чтение файла.
    public void readFileInt() {
        try (Scanner in = new Scanner(fileIn)) {
            //чтение файла и запись в массив.
            while (in.hasNext()) {    
                if (in.hasNextInt()) {
                    Integer num = in.nextInt();   
                    inputIntList.add(num);
                }
                else {
    
                    System.out.println("Во входных данных допущена ошибка. На вход получено неверное значение: " + in.next());
                }
            } 
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    //фильтрация.
    public void filterEven() {
        this.filterIntList = this.inputIntList.stream().filter(number -> (number % 2 == 0)).collect(Collectors.toList());
    }

    public void addNum(Integer element) {
        this.squareIntList.add(element);
        System.out.println(element);
    }

    //возведение в квадрат.
    public void listToSquare() {

        // this.filterIntList.forEach(number);
        this.filterIntList.forEach(number -> {
            if (number < Math.sqrt(Integer.MAX_VALUE)) {
                this.squareIntList.add(number * number);
            } else {
                System.out.println("Ошибка. На вход получено слишком большое значение: " + number);
            }
            
        });

    }

    public void toSortList() {
        Collections.sort(squareIntList);
    }

    public void outputInFile() {
        try (FileWriter writer = new FileWriter(fileOut)) {
            for (Integer number : this.squareIntList) {
                try {
                    writer.write(number + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
