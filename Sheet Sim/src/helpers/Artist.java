package helpers;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

public class Artist {

	public static final int WIDTH=1900, HEIGHT=1000, SHEET_XOFFSET=400, SHEET_YOFFSET=0, TEXT_XOFFSET=50, TEXT_YOFFSET=10, TEXTSIZE=24;
	
	public static void beginSession() {
		Display.setTitle("Sim");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setResizable(false);
			Display.setLocation(0, 0);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void drawRectTex(Texture tex, float x, float y, float width, float height) {
		tex.bind();
		glTranslatef(x,y,0);
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		glEnd();
		glLoadIdentity();
	}
	
	public static void drawRedRect(float x, float y, float width, float height) {
		GL11.glColor3f(255, 0, 0);
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+width);
		glEnd();
		glLoadIdentity();
	}
	
	public static void drawLine(float x1, float y1, float x2, float y2) {
		glDisable(GL_BLEND);
		glLineWidth(75);
		glBegin(GL_LINES);
		glVertex2f(x1,y1);
		glVertex2f(x2,y2);
		glEnd();
		glLoadIdentity();
		glEnable(GL_BLEND);
	}
	
	public static Texture load(String name) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream("res/"+ name + ".png");
		try {
			tex = TextureLoader.getTexture("png", in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
}
