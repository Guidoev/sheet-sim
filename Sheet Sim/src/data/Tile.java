package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;

public class Tile {

	private float x, y, width, height;
	private Texture texture;
	private TileType type;
	private int value;
	
	public Tile(float x, float y, float width, float height, int value) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.value=value;
		texture = load(TileGrid.tileVector[value][0].name); 
	}
	
	
	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public void Draw() {
		drawRectTex(texture, x, y, width, height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
	public void textureMode(int mode) {
		this.texture=null;
	}
	

}
