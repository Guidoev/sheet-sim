package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.WIDTH;
import static helpers.Artist.SHEET_XOFFSET;
import static helpers.Artist.TEXT_XOFFSET;
import static helpers.Artist.TEXT_YOFFSET;
import static helpers.Artist.TEXTSIZE;
import static helpers.Artist.drawRectTex;
import static helpers.Artist.drawRedRect;
import static helpers.Artist.drawLine;
import static helpers.Artist.load;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class UI {

	private TrueTypeFont font;
	private Font awtFont;
	private int offset, verdi, neri;
	private static int counter=0;
	private Brush brush;
	private TileGrid grid;
	private static boolean saved = false;
	
	public UI(Brush brush, TileGrid grid) {
		awtFont = new Font("Consolas", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
		this.brush=brush;
		this.grid=grid;
	}
	
	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
		
	}
	
	
	public void draw() {
		if(brush.getValues()[0]>Counter.VALUES[0] || brush.getValues()[1]>Counter.VALUES[1])
			drawRedRect(TEXT_XOFFSET, TEXT_YOFFSET, 50, 200);
		offset=0;
		drawRectTex(load(TileType.Bounds.name), 0, 0, WIDTH, HEIGHT); //to avoid text rectangle appearance
		bufferWrite(TEXT_YOFFSET-20, "Quadrati totali:");
		verdi = brush.getValues()[0];
		neri = brush.getValues()[1];
		bufferWrite(15, "Verdi: " + verdi + " - " + "Riman.: " + (Counter.VALUES[0] - verdi) + ((verdi==Counter.VALUES[0]) ? " (!)" : ""));
		bufferWrite(15, "Neri: " + neri + " - " + "Riman.: " + (Counter.VALUES[1] - neri) + ((neri==Counter.VALUES[1]) ? " (!)" : ""));
		drawLine(SHEET_XOFFSET-5,0,SHEET_XOFFSET-5,HEIGHT);
		drawLine(0,offset+=50,400,offset);
		bufferWrite(0, "Dimensioni: ");
		bufferWrite(20, grid.squares[0] +  " x " + grid.squares[1]);
		bufferWrite(15, grid.squares[0]*12.5 + "cm" +  " x " + grid.squares[1]*12.5 + "cm");
		drawLine(0,offset+=45,400,offset);
		bufferWriteShifted(0, 80, "COMANDI:");
		bufferWriteShifted(10, -20, "Stile:");
		bufferWriteShifted(15, -40, ((grid.mode==0) ? " > " : "   ") + "1 - Semplice");
		bufferWriteShifted(10, -40, ((grid.mode==1) ? " > " : "   ") + "2 - Concept");
		bufferWriteShifted(10, -40, ((grid.mode==2) ? " > " : "   ") + "3 - Realistico");
		bufferWriteShifted(20, -20, "Simmetrie:");
		bufferWrite(15, "Q - Simmetria Radiale");
		bufferWrite(15, "W - Asse Verticale");
		bufferWrite(15, "E - Asse Orizzontale");
		bufferWriteShifted(20, -20, "Generali:");
		bufferWrite(15, "0 - Cancella Tutto");
		bufferWrite(15, "S - Salva Configurazione");
		bufferWrite(15, "G - Griglia (ON/OFF)");
		bufferWrite(15, "TAB - Soglia Max. (ON/OFF)");
		bufferWrite(15, "SPAZIO - Pennello/Gomma");
		
		drawRectTex(load("selected"), ((brush.brushMode==0) ? TEXT_XOFFSET + 45 : TEXT_XOFFSET + 140), offset+45, 74, 74);
		drawRectTex(load("brush"), TEXT_XOFFSET + 50, offset+50, 64, 64);
		drawRectTex(load("eraser"), TEXT_XOFFSET + 145, offset+50, 64, 64);
		}
	
	public void updateLater() {
		if(brush.getSymmetries()[0])
			drawRectTex(load("radial_icon"), SHEET_XOFFSET, 0, 75, 75);
		else
			drawRectTex(load("radial_off"), SHEET_XOFFSET, 0, 75, 75);
		
		if(brush.getSymmetries()[1])
			drawRectTex(load("vert_icon"),SHEET_XOFFSET+75+3, 0, 75, 75);
		else
			drawRectTex(load("vert_off"),SHEET_XOFFSET+75+3, 0, 75, 75);
		
		if(brush.getSymmetries()[2])
			drawRectTex(load("horz_icon"), SHEET_XOFFSET+150+6, 0, 75, 75);
		else
			drawRectTex(load("horz_off"), SHEET_XOFFSET+150+6, 0, 75, 75);
		
		if(brush.isNoCap())
			drawRectTex(load("cap_icon"), SHEET_XOFFSET, 75+3, 75, 75);
		else
			drawRectTex(load("cap_off"), SHEET_XOFFSET, 75+3, 75, 75);
		
		if(grid.getGridActivated())
			drawRectTex(load("grid_icon"), SHEET_XOFFSET+75+3, 75+3, 75, 75);
		else
			drawRectTex(load("grid_off"), SHEET_XOFFSET+75+3, 75+3, 75, 75);
		
		if(saved && counter>=0) {
			drawString(SHEET_XOFFSET + 20, 175, "Salvato!");
			counter--;
		}
		if(counter==0)
			saved=false;
	}
	
	public void bufferWrite(int spacing, String s) {
		if((offset+TEXTSIZE+spacing)<HEIGHT) {
			offset+=(TEXTSIZE+spacing);
			drawString(TEXT_XOFFSET, offset, s);
		}
	}
	
	public void bufferWriteShifted(int spacing, int xSpacing, String s) {
		if((offset+TEXTSIZE+spacing)<HEIGHT) {
			offset+=(TEXTSIZE+spacing);
			drawString(TEXT_XOFFSET + xSpacing, offset, s);
		}
	}
	
	public static void savedImpulse() {
		saved=true;
		counter=60;
	}
	
}
