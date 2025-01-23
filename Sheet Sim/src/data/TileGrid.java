package data;

import static helpers.Artist.*;

public class TileGrid {
	
	public static final TileType[][] tileVector = {
			{TileType.Green, TileType.Concept_Green, TileType.Real_Green},
			{TileType.Black, TileType.Concept_Black, TileType.Real_Black},
			{TileType.Grey, TileType.Grid_Grey, TileType.Grey},
			{TileType.Bounds, TileType.Bounds, TileType.Bounds}
	};
	
	public Tile[][] map;
	public int[] squares = {0,0};
	public int tileSize;
	public int startX, startY, endX, endY, previousMode, mode;
	private boolean gridActivated=false, previousGridState=false;
	
	
	public TileGrid(int width, int height) {

		int nx, ny;
		
		squares[0]=width;
		squares[1]=height;
		
		tileSize=getDimension(width+2, 64, WIDTH-SHEET_XOFFSET);
		tileSize=Math.min(tileSize, getDimension(height+2, 64, HEIGHT-SHEET_YOFFSET));
		
		nx=(int)Math.ceil((WIDTH-SHEET_XOFFSET)/(float)tileSize);
		ny=(int)Math.ceil((HEIGHT-SHEET_YOFFSET)/(float)tileSize);
		
		startX=(int)Math.floor((nx-width)/2f);
		startY=(int)Math.floor((ny-height)/2f);
		
		endX=startX+width;
		endY=startY+height;
			
		map = new Tile[ny][nx];
		for(int i=0; i<ny; i++) {
			for(int j=0; j<nx; j++) {
				if(j>=startX && j<endX && i>=startY && i<endY) {
					map[i][j] = new Tile(j*tileSize + SHEET_XOFFSET, i*tileSize + SHEET_YOFFSET, tileSize, tileSize, tileVector[2][0].value);
				}else {
					map[i][j] = new Tile(j*tileSize + SHEET_XOFFSET, i*tileSize + SHEET_YOFFSET, tileSize, tileSize, tileVector[3][0].value);				}
			}
		}
	}
	
	
	public int getDimension(int dimension, int size, int target) {
		if(size*dimension<=target) {
			return size;
		} else {
			return getDimension(dimension, size-1, target);
		}
	}
	
	public void clear() {
		for(int i=startY; i<endY; i++) {
			for(int j=startX; j<endX; j++) {
				Tile t = map[i][j];
				t.setValue(2);
				t.setTexture(load((gridActivated)? tileVector[2][1].name : tileVector[2][0].name));
			}
		}
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	
	public void setValue(int xCoord, int yCoord, int value) {
		map[yCoord+startY][xCoord+startX].setValue(value);
		map[yCoord+startY][xCoord+startX].setTexture(load(tileVector[value][(value==2) ? booleanConvert(gridActivated) : mode].name));
	}
	
	
	public Tile getTile(int xCoord, int yCoord) {
		return map[xCoord][yCoord];
	}

	
	public void draw() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				Tile t = map[i][j];
				if(previousMode!=mode || (previousGridState!=gridActivated)) {
					t.setTexture(load(tileVector[t.getValue()][(t.getValue()==2) ? booleanConvert(gridActivated) : mode].name));
				}
				drawRectTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
			}
		}
		previousMode=mode;
		previousGridState=gridActivated;
	}
	
	
	public int[] squares() {
		return this.squares;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void toggleGrid() {
		if(!gridActivated) {
			gridActivated=true;
		}
		else {
			gridActivated=false;
		}
	}
	
	public int booleanConvert(boolean b) {
		return (b)?1:0;
	}
	
	public Boolean getGridActivated() {
		return this.gridActivated;
	}
	
}
