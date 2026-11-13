package dev.licaza.temanahuiani.engine.tools.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.licaza.temanahuiani.engine.tools.math.Functions.Matrix;
import dev.licaza.temanahuiani.engine.tools.math.Functions.Pythagoras;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FunctionsTest {

    @Test
    void listToMatrixFillsRowByRow() {
        ArrayList<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));

        int[][] matrix = Matrix.ArrayListTo2Dint(list, 2, 3);

        assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}}, matrix);
    }

    @Test
    void matrixToArrayReadsRowByRow() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};

        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, Matrix.TwoDTo1DintArray(matrix));
    }

    @Test
    void matrixConversionsAreInverse() {
        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : Matrix.TwoDTo1DintArray(matrix)) {
            list.add(i);
        }

        assertArrayEquals(matrix, Matrix.ArrayListTo2Dint(list, 3, 2));
    }

    @Test
    void hypotenuseOfThreeFourIsFive() {
        assertEquals(5, Pythagoras.GetHypotenuseDistance(0, 0, 3, 4));
    }

    @Test
    void hypotenuseIgnoresDirection() {
        assertEquals(5, Pythagoras.GetHypotenuseDistance(3, 4, 0, 0));
    }

    @Test
    void hypotenuseIsTruncated() {
        // sqrt(2) = 1.41...
        assertEquals(1, Pythagoras.GetHypotenuseDistance(0, 0, 1, 1));
    }
}
