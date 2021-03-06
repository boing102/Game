package com.boingy.rain.level.tile.SpawnLevel;

import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.Sprite;
import com.boingy.rain.level.tile.Tile;

public class SpawnFloorTile extends Tile {

	public SpawnFloorTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
