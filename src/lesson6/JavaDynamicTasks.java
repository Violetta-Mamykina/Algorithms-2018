package lesson6;

import kotlin.NotImplementedError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        List<String> lines = Files.readAllLines(Paths.get(inputName));
        int height = lines.size();
        int width = lines.get(0).split("\\s+").length;
        int[][] field = IntStream.range(0, height).mapToObj(i ->
                Stream.of(lines.get(i).split("\\s+")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        IntStream.range(1, height).forEachOrdered(i -> field[i][0] = field[i - 1][0] + field[i][0]);
        IntStream.range(1, width).forEachOrdered(i -> field[0][i] = field[0][i - 1] + field[0][i]);
        for (int y = 1; y < height; y++) {
            for (int x = 1; x < width; x++) {
                int minimal = min(Math.min(field[y - 1][x], field[y][x - 1]), field[y - 1][x - 1]);
                field[y][x] += minimal;
            }
        }
        return field[height - 1][width - 1];
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
