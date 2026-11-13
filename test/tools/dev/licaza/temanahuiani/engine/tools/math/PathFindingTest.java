package dev.licaza.temanahuiani.engine.tools.math;

import static dev.licaza.temanahuiani.engine.commons.misc.Constants.Directions.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import dev.licaza.temanahuiani.engine.commons.objects.PathPoint;

import org.junit.jupiter.api.Test;

class PathFindingTest {

    // Tile types
    private static final int G = 1; // Grass
    private static final int R = 2; // Road

    // No direction: tiles that aren't part of the road
    private static final int __ = 0;

    @Test
    void followsStraightRoad() {
        int[][] level = {
            {G, G, G},
            {R, R, R},
            {G, G, G},
        };

        int[][] directions =
                PathFinding.GetRoadDirectionArray(level, new PathPoint(0, 1), new PathPoint(2, 1));

        assertArrayEquals(
                new int[][] {
                    {__, __, __},
                    {RIGHT, RIGHT, RIGHT},
                    {__, __, __},
                },
                directions);
    }

    @Test
    void followsRoadAroundCorners() {
        int[][] level = {
            {R, G, G},
            {R, R, R},
            {G, G, R},
        };

        int[][] directions =
                PathFinding.GetRoadDirectionArray(level, new PathPoint(0, 0), new PathPoint(2, 2));

        assertArrayEquals(
                new int[][] {
                    {DOWN, __, __},
                    {RIGHT, RIGHT, DOWN},
                    {__, __, DOWN},
                },
                directions);
    }

    @Test
    void neverWalksBackwards() {
        // At (1, 1), right and down aren't road, so the next direction to
        // test is left: the road tile we came from. It must be skipped.
        int[][] level = {
            {G, R, G},
            {R, R, G},
            {G, G, G},
        };

        int[][] directions =
                PathFinding.GetRoadDirectionArray(level, new PathPoint(0, 1), new PathPoint(1, 0));

        assertArrayEquals(
                new int[][] {
                    {__, UP, __},
                    {RIGHT, UP, __},
                    {__, __, __},
                },
                directions);
    }
}
