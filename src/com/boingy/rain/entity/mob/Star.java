package com.boingy.rain.entity.mob;

import java.util.List;

import com.boingy.rain.graphics.AnimatedSprite;
import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.Sprite;
import com.boingy.rain.graphics.SpriteSheet;
import com.boingy.rain.level.Node;
import com.boingy.rain.util.Vector2i;

public class Star extends Mob{

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down,
			32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32,
			32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left,
			32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right,
			32, 32, 3);

	private AnimatedSprite animSprite = down;

	private double xa = 0, ya = 0;
	private double speed = 0.5;
	private List<Node> path;
	private int time = 0;

	public Star(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	private void move() {
		xa = 0;
		ya = 0;
		int px = (int) level.getPlayerAt(0).getX();
		int py = (int) level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);
		if(time % 3 == 0) path = level.findPath(start, destination);
		if(path != null) {
			if(path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
				if(x < vec.getX() << 4) xa++;
				if(x > vec.getX() << 4) xa--;
				if(y < vec.getY() << 4) ya++;
				if(y > vec.getY() << 4) ya--;				
			}
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
	}

	public void update() {
		time++;
		move();
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		}
		if (ya > 0) {
			System.out.println("Chaser X possition: " + this.x);
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			//System.out.println("xa left value: " + xa);
			animSprite = left;
			dir = Direction.LEFT;
		}
		if (xa > 0) {
			//System.out.println("X possition: " + xa);
			animSprite = right;
			dir = Direction.RIGHT;
		}

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}
}
