package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.FileCreator;

import static helpers.Artist.*;

import java.io.IOException;


public class Brush {
	
	private TileGrid grid;
	private FileCreator fc;
	private int tileSize, mode, startX, startY;
	public int brushMode=0;
	private Counter c;
	private boolean radialSymmetry=false, horzSymmetry=false, vertSymmetry=false, noCap=false;
	private int[] values = {0,0};
	private boolean[] symmetries = {false, false, false}; //{radial, vert, horz}
	
	public Brush(TileGrid grid) {
		this.grid = grid;
		this.tileSize=grid.getTileSize();
		startX=grid.startX;
		startY=grid.startY;
		c = new Counter();
		fc = new FileCreator(grid);
	}
	
	public void setValue(int value) {
		int x = Mouse.getX();
		int y = Mouse.getY();
		if((x>TEXT_XOFFSET + 50) && (x<TEXT_XOFFSET + 50 + 64) && y>(HEIGHT-910-64) && y<(HEIGHT-910)) {
			brushMode=0;
		}
		if((x>TEXT_XOFFSET + 145) && (x<TEXT_XOFFSET + 145 + 64) && y>(HEIGHT-910-64) && y<(HEIGHT-910)) {
			brushMode=1;
		}
		int[] squareIndex = getSquare(x,y);
		int xIndex = squareIndex[0];
		int yIndex = squareIndex[1];
		int[] size = grid.squares();
		int endXIndex = size[0]-1;
		int endYIndex = size[1]-1;
		
		if(validSquare(squareIndex)) {
			if(value==2) {
				grid.setValue(xIndex, yIndex, value);
				if(radialSymmetry)
					grid.setValue(endXIndex - xIndex, endYIndex - yIndex, value);
				if(horzSymmetry)
					grid.setValue(xIndex, endYIndex - yIndex, value);
				if(vertSymmetry)
					grid.setValue(endXIndex - xIndex, yIndex, value);
				c.updateValues(grid.map);
			} else {
				if(!noCap)
					values=c.countValues(grid.map);
				if(noCap || values[value]<Counter.VALUES[value])
					grid.setValue(xIndex, yIndex, value);
				if(radialSymmetry && (noCap || ((values[value]+1)<Counter.VALUES[value])))
					grid.setValue(endXIndex - xIndex, endYIndex - yIndex, value);
				if(horzSymmetry && (noCap || ((values[value]+1 + booleanConvert(radialSymmetry))<Counter.VALUES[value])))
					grid.setValue(xIndex, endYIndex - yIndex, value);
				if(vertSymmetry && (noCap || ((values[value]+1 + booleanConvert(radialSymmetry) + booleanConvert(horzSymmetry))<Counter.VALUES[value])))
					grid.setValue(endXIndex - xIndex, yIndex, value);
				c.updateValues(grid.map);
			}
		}
	}
	
	public void update() {
		switch(brushMode) {
		case 0:
			if(Mouse.isButtonDown(0)) {
				setValue(0);
			} else if(Mouse.isButtonDown(1)) {
				setValue(1);
			}
			break;
		case 1:
			if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
				setValue(2);
			break;
		default:
			brushMode=0;
			break;
		}
		
		while(Keyboard.next()) {
			switch(Keyboard.getEventKey()) {
			case Keyboard.KEY_1:
				mode=0;
				break;
			case Keyboard.KEY_2:
				mode=1;
				break;
			case Keyboard.KEY_3:
				mode=2;
				break;
			case Keyboard.KEY_0:
				grid.clear();
				c.resetValues();
				break;
			case Keyboard.KEY_SPACE:
				if(!Keyboard.getEventKeyState()) {
					toggleBrush();
				}
				break;
			case Keyboard.KEY_Q:
				if(!Keyboard.getEventKeyState()) {
					toggleRadial();
				}
				break;
			case Keyboard.KEY_W:
				if(!Keyboard.getEventKeyState()) {
					toggleVert();
				}
				break;
			case Keyboard.KEY_E:
				if(!Keyboard.getEventKeyState()) {
					toggleHorz();
				}
				break;
			case Keyboard.KEY_TAB:
				if(!Keyboard.getEventKeyState()) {
					toggleCap();
				}
				break;
			case Keyboard.KEY_G:
				if(!Keyboard.getEventKeyState()) {
					grid.toggleGrid();
				}
				break;
			case Keyboard.KEY_S:
				if(!Keyboard.getEventKeyState()) {
					UI.savedImpulse();
					try {
						fc.Save();
					} catch (IOException e) {
						e.printStackTrace();
					}		
				}
				break;
			}
		}
		grid.setMode(mode);
	}

	public void toggleCap() {
		if(!noCap) {
			noCap=true;
		}
		else {
			noCap=false;
		}
	}
	
	public void toggleBrush() {
		if(brushMode==0)
			brushMode=1;
		else
			brushMode=0;
	}
	
	public void toggleRadial() {
		if(!radialSymmetry) {
			radialSymmetry=true;
			symmetries[0]=true;
		}
		else {
			radialSymmetry=false;
			symmetries[0]=false;
		}
	}
	
	public void toggleVert() {
		if(!vertSymmetry) {
			vertSymmetry=true;
			symmetries[1]=true;
		}
		else {
			vertSymmetry=false;
			symmetries[1]=false;
		}
	}
	
	public void toggleHorz() {
		if(!horzSymmetry) {
			horzSymmetry=true;
			symmetries[2]=true;
		}
		else {
			horzSymmetry=false;
			symmetries[2]=false;
		}
	}
	
	public int[] getValues() {
		return this.values;
	}
	
	public boolean[] getSymmetries() {
		return this.symmetries;
	}
	
	private int[] getSquare(int mouseX, int mouseY) {
		int[] result = {0,0};
		int[] squares = grid.squares();
		result[0] = (int)Math.floor((mouseX-1-SHEET_XOFFSET)/tileSize)-startX;
		result[1] = (int)Math.floor(((HEIGHT-1-mouseY)-SHEET_YOFFSET)/tileSize)-startY;
		if(result[0]<0 || result[0]>=squares[0] || result[1]<0 || result[1]>=squares[1]) {
			result[0] = -1;
			result[1] = -1;
		}
		return result;
	}
	
	private boolean validSquare(int[] square) {
		return square[0]!=-1;
	}

	public boolean isNoCap() {
		return noCap;
	}
	
	public int booleanConvert(boolean b) {
		return (b)?1:0;
	}
	
}
