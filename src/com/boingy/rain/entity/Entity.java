package com.boingy.rain.entity;

import java.util.Random;

import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.Sprite;
import com.boingy.rain.level.Level;

public abstract class Entity {
	

	protected double x, y;
	private boolean removed;
	protected Level level;
	protected final Random random = new Random();
	protected Sprite sprite;
	
	public void init(Level level) {
		this.level = level;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		if(sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
	}
	
	public void remove() {
		removed = true;
	}
	

	public Sprite getSprite() {
		return sprite;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	 public boolean isRemoved() {
		 return removed;
	 }

}
