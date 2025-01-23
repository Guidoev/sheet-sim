package helpers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import data.Launcher;
import data.TileGrid;

public class FileCreator {

	private TileGrid grid;
	
	public FileCreator(TileGrid grid) {
		this.grid=grid;
	}
	
	public void Save() throws IOException {
		BufferedImage image = new BufferedImage(grid.squares[0]*grid.tileSize, grid.squares[1]*grid.tileSize, BufferedImage.TYPE_INT_RGB);
		BufferedImage image2 = new BufferedImage(grid.squares[0]*grid.tileSize, grid.squares[1]*grid.tileSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		
		for(int i=grid.startY; i<grid.endY; i++) {
			for(int j=grid.startX; j<grid.endX; j++) {
				switch(grid.map[i][j].getValue()) {
				case 0:
					g2d.setColor(Color.decode("#284c08"));
					break;
				case 1:
					g2d.setColor(Color.black);
					break;
				case 2:
					g2d.setColor(Color.gray);
					break;
				default:
					g2d.setColor(Color.red);
					break;
				}
				g2d.fillRect((j-grid.startX)*grid.tileSize, (i-grid.startY)*grid.tileSize, grid.tileSize, grid.tileSize);
			}
		}
		
		g2d = image2.createGraphics();
		
		for(int i=grid.startY; i<grid.endY; i++) {
			for(int j=grid.startX; j<grid.endX; j++) {
				switch(grid.map[i][j].getValue()) {
				case 0:
					g2d.setColor(Color.decode("#284c08"));
					break;
				case 1:
					g2d.setColor(Color.black);
					break;
				case 2:
					g2d.setColor(Color.gray);
					break;
				default:
					g2d.setColor(Color.red);
					break;
				}
				g2d.fillRect((j-grid.startX)*grid.tileSize, (i-grid.startY)*grid.tileSize, grid.tileSize, grid.tileSize);
			}
		}
		g2d.setColor(Color.CYAN);
		for(int i=1; i<grid.squares[0]; i++) {
			g2d.drawLine(i*grid.tileSize, 0, i*grid.tileSize, (grid.endY+1)*grid.tileSize);
		}
		for(int j=1; j<grid.squares[1]; j++) {
			g2d.drawLine(0, j*grid.tileSize, (grid.endX+1)*grid.tileSize, j*grid.tileSize);
		}
		g2d.dispose();
		
		File file=new File("src/exports/" + Launcher.fileName + ".png");
		File file2=new File("src/exports/blueprints/" + Launcher.fileName + "_bp.png");
		
		Launcher.fileName = (Integer.parseInt(Launcher.fileName)+1) + "";
		
		ImageIO.write(image, "png", file);
		ImageIO.write(image2, "png", file2);
	}
}
