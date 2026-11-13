package dev.licaza.temanahuiani.engine.tools.helpers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import dev.licaza.temanahuiani.engine.commons.objects.PathPoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

class LoadSaveTest {

    // Levels are always 20x20 tiles
    private static final int SIZE = 20;

    // LoadSave appends ".txt" to the level name, so names are paths without it
    @TempDir Path dir;

    private String levelName() {
        return dir.resolve("level").toString();
    }

    @Test
    void newLevelHasGivenTilesAndZeroPathPoints() {
        int[] tiles = new int[SIZE * SIZE];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = i % 3;
        }

        LoadSave.CreateLevel(levelName(), tiles);

        int[][] level = LoadSave.GetLevelData(levelName());
        assertEquals(1, level[0][1]);
        assertEquals(2, level[0][2]);
        // Second row starts at index 20
        assertEquals(20 % 3, level[1][0]);
        assertEquals(
                List.of(new PathPoint(0, 0), new PathPoint(0, 0)),
                LoadSave.GetLevelPathPoints(levelName()));
    }

    @Test
    void createLevelKeepsAnExistingLevel() {
        LoadSave.CreateLevel(levelName(), new int[SIZE * SIZE]);

        int[] otherTiles = new int[SIZE * SIZE];
        otherTiles[0] = 7;
        LoadSave.CreateLevel(levelName(), otherTiles);

        assertEquals(0, LoadSave.GetLevelData(levelName())[0][0]);
    }

    @Test
    void savedLevelIsLoadedBack() {
        LoadSave.CreateLevel(levelName(), new int[SIZE * SIZE]);

        int[][] tiles = new int[SIZE][SIZE];
        tiles[3][5] = 2;
        tiles[19][19] = 1;
        PathPoint start = new PathPoint(1, 2);
        PathPoint end = new PathPoint(18, 19);

        LoadSave.SaveLevel(levelName(), tiles, start, end);

        assertArrayEquals(tiles, LoadSave.GetLevelData(levelName()));
        assertEquals(List.of(start, end), LoadSave.GetLevelPathPoints(levelName()));
    }

    @Test
    void missingLevelReturnsNull() {
        assertNull(LoadSave.GetLevelData(levelName()));
        assertNull(LoadSave.GetLevelPathPoints(levelName()));
    }
}
