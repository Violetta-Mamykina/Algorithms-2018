package lesson6;

import kotlin.NotImplementedError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //Трудоёмкость O(n^2)
    //Ресурсоёмкость O(n)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int listSize = list.size();
        if (listSize == 0 || listSize == 1) return list;
        int[] start = IntStream.generate(() -> -1).limit(listSize).toArray();
        int[] sizeOfSubsequence = Arrays.copyOf(start, listSize);
        int position = 0;
        int maxLength = sizeOfSubsequence[0];
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) > list.get(j) && sizeOfSubsequence[i] <= sizeOfSubsequence[j]) {
                    start[i] = j;
                    sizeOfSubsequence[i] = sizeOfSubsequence[j] + 1;
                    if (sizeOfSubsequence[i] > maxLength) {
                        position = i;
                        maxLength = sizeOfSubsequence[i];
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (position != -1) {
            result.add(list.get(position));
            position = start[position];
        }
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    //Трудоёмкость O(m*n)
    //Ресурсоёмкость O(m*n)
    public static int shortestPathOnField(String inputName) throws IOException {
        List<String> inputFile = Files.readAllLines(Paths.get(inputName));
        List<String> input = new ArrayList<>();
        for (String line : inputFile) input.add(line.replaceAll("\\s+", ""));
        final int CONST = 48;
        int width = input.size();
        int length = input.get(0).length();
        int field[][] = new int[width][length];
        field[0][0] = input.get(0).charAt(0) - CONST;
        for (int i = 1; i < width; i++) field[i][0] = field[i - 1][0] + input.get(i).charAt(0) - CONST;
        for (int i = 1; i < length; i++) field[0][i] = field[0][i - 1] + input.get(0).charAt(i) - CONST;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < width; j++) {
                int min = min(min(field[j - 1][i], field[j][i - 1]), field[j - 1][i - 1]);
                field[j][i] = min + input.get(j).charAt(i) - CONST;
            }
        }
        return field[width - 1][length - 1];
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
