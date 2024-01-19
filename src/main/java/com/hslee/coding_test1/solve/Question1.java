package com.hslee.coding_test1.solve;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Question1 {
    public String solve() throws IOException {
        int totalLine = 0;
        int calculatedLine = 0;
//        StringBuilder errorValue = new StringBuilder();
        StringBuilder result = new StringBuilder();

        InputStream is = getClass().getResourceAsStream("/static/file/sample.csv");
        if (is == null) {
            throw new FileNotFoundException("Cannot find resource file");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                .setHeader()
                .build()
                .parse(reader);

        for (CSVRecord record : records) {
            boolean canCalculate = true;
            List<Integer> rowValueList = new ArrayList<>();
            totalLine++;

            for (int i = 0; i < 10; i++) {
                String stringValue = "";
                try {
                    stringValue = i == 0 ? record.get("p" + i) : record.get(" p" + i);
                    int cellValue = Integer.parseInt(stringValue);
                    rowValueList.add(cellValue);
                } catch (NumberFormatException numberFormatException) {
//                    errorValue.append(stringValue);
                    canCalculate = false;
                } catch (IllegalArgumentException illegalArgumentException) {
                    canCalculate = false;
                }
            }

            if (canCalculate && !rowValueList.isEmpty()) {
                calculatedLine++;
                result.append(min(rowValueList)).append(" ")
                        .append(max(rowValueList)).append(" ")
                        .append(sum(rowValueList)).append(" ")
                        .append(average(rowValueList)).append(" ")
                        .append(standardDeviation(rowValueList)).append(" ")
                        .append(median(rowValueList)).append("\n");
            }
        }

        result.append("The total number of lines: ").append(totalLine).append("\n");
        result.append("The calculated lines: ").append(calculatedLine).append("\n");
        result.append("The error values: ").append("a");

        return result.toString();
    }

    /**
     * 최솟값 구하기
     *
     * @param rowValueList
     * @return
     * @throws NoSuchElementException
     */
    private int min(List<Integer> rowValueList) throws NoSuchElementException {
        return rowValueList.stream()
                .min(Integer::compareTo)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 최댓값 구하기
     *
     * @param rowValueList
     * @return
     * @throws NoSuchElementException
     */
    private double max(List<Integer> rowValueList) throws NoSuchElementException {
        return rowValueList.stream()
                .max(Integer::compareTo)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 합계 구하기
     *
     * @param rowValueList
     * @return
     */
    private double sum(List<Integer> rowValueList) {
        return rowValueList.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * 평균 구하기
     *
     * @param rowValueList
     * @return
     * @throws NoSuchElementException
     */
    private double average(List<Integer> rowValueList) throws NoSuchElementException {
        return rowValueList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 표준편차 구하기
     *
     * @param rowValueList
     * @return
     */
    private double standardDeviation(List<Integer> rowValueList) {
        double _sum = rowValueList.stream()
                .mapToDouble(value -> Math.pow(value - average(rowValueList), 2))
                .sum();
        return Math.sqrt(_sum / rowValueList.size());
    }

    /**
     * 중간값 구하기
     *
     * @param rowValueList
     * @return
     * @throws NoSuchElementException
     */
    private double median(List<Integer> rowValueList) throws NoSuchElementException {
        return rowValueList.stream()
                .sorted()
                .skip(rowValueList.size() / 2)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
