/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TropicalIsland;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Alexey
 */
public class TropicalIsland {
    public static Island MyIslands[];

    public static final int MAX_ISLAND_SIZE = 50;
    public static final int MIN_ISLAND_SIZE = 1;
    public static final int MAX_ISLAND_ALTITUDE = 1000;
    public static final int MIN_ISLAND_ALTITUDE = 1;

    public static void readData() {
        int numberOfIslands;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            numberOfIslands = StdIn.readInt(reader);
            if (numberOfIslands > 0) {
                MyIslands = new Island[numberOfIslands];
                for (int i = 0; i < numberOfIslands; i++) {
                    int[] size = StdIn.readIntArray(reader, 2, MIN_ISLAND_SIZE, MAX_ISLAND_SIZE);
                    MyIslands[i] = new Island(size[0], size[1]);
                    int[][] cells = new int[size[0]][size[1]];
                    for (int j = 0; j < size[0]; j++) {
                        int[] line = StdIn.readIntArray(reader, size[1], MIN_ISLAND_ALTITUDE, MAX_ISLAND_ALTITUDE);
                        System.arraycopy(line, 0, cells[j], 0, size[1]);
                    }
                    MyIslands[i].setCells(cells);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printResults() {
        for (Island i : MyIslands) {
            System.out.println(i.count_water());
        }
    }

    public static void main(String[] args) {
        readData();
        printResults();        
    }
}
