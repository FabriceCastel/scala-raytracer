import swing._
import event._
import java.awt.{Color}
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

object Raytracer extends App {
	val OUTPUT_FOLDER = "renders/"
	val IMG_FORMAT = "png"
	val IMG_NAME = "out"
	val imgFullName = OUTPUT_FOLDER + IMG_NAME + "." + IMG_FORMAT
	val width = 700
	val height = 700
	val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
	
	for(x <- 0 to width-1; y <- 0 to height-1)
		img.setRGB(x, y, 0xff000000 + 0x0000100 * x + 0x0000001 * y)

	val panel = new Panel {
		override def paint(g : Graphics2D){
			g.drawImage(img, 0, 0, null)
		}
		preferredSize = new Dimension(width, height)
	}

	ImageIO.write(img, IMG_FORMAT, new File(imgFullName))

	val frame = new MainFrame {
		title = "Raytracer"
		contents = panel
		centerOnScreen
	}

	frame.open
}