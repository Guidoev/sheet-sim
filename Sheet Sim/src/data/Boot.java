package data;

import org.lwjgl.opengl.Display;
import static helpers.Artist.*;

public class Boot {
	
	public Boot(int width, int height) {
		beginSession();
		TileGrid tileGrid = new TileGrid(width,height);
		Brush brush = new Brush(tileGrid);
		UI ui = new UI(brush, tileGrid);
		while(!Display.isCloseRequested()) {
			ui.draw();
			tileGrid.draw();
			brush.update();
			ui.updateLater();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
}
