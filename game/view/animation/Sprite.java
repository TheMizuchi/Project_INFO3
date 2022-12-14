package view.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Sprite {

	public int m_width;
	public int m_height;

	public BufferedImage[] m_images;


	public Sprite (BufferedImage[] images) {
		m_images = images;
		m_width = images[0].getWidth();
		m_height = images[0].getHeight();
	}

	public Sprite (InputStream is, int nrows, int ncolumns) throws IOException {
		BufferedImage bi;
		bi = ImageIO.read(is);
		m_width = bi.getWidth(null) / ncolumns;
		m_height = bi.getHeight(null) / nrows;

		m_images = new BufferedImage[nrows * ncolumns];

		for (int i = 0; i < nrows; i++) {

			for (int j = 0; j < ncolumns; j++) {
				int x = j * m_width;
				int y = i * m_height;
				m_images[(i * ncolumns) + j] = bi.getSubimage(x, y, m_width, m_height);
			}
		}
	}

	public static Sprite loadSprite (String filename, int nrows, int ncols) {
		File imageFile = new File(filename);

		if (imageFile.exists()) {
			BufferedImage image = null;

			try {
				image = ImageIO.read(imageFile);
			}
			catch (IOException e) {
				System.out.println("Impossible d'ouvrir le fichier " + filename);
				e.printStackTrace();
			}
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];

			for (int i = 0; i < nrows; i++) {

				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return new Sprite(images);
		}
		return null;
	}

}
