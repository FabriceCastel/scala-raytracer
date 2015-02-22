import swing._
import event._
import java.awt.{Color}
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import raytracer.SceneNode._
import raytracer.Parser._
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._

object Raytracer extends App {
	val OUTPUT_FOLDER = "renders/"
	val IMG_FORMAT = "png"
	val IMG_NAME = "out"
	val imgFullName = OUTPUT_FOLDER + IMG_NAME + "." + IMG_FORMAT

	val parser = new Parser("data/dummy_scene_file_name")
	val scene = parser.getScene()
	val rp = parser.getRenderParameters()

	val img = new BufferedImage(rp.width, rp.height, BufferedImage.TYPE_INT_ARGB)
	
	for(x <- 0 to rp.width-1; y <- 0 to rp.height-1){
		val pixelColor = scene.intersect(rp).shadeIntersection()
		img.setRGB(x, y, pixelColor.getRGB())
	}

	val panel = new Panel {
		override def paint(g : Graphics2D){
			g.drawImage(img, 0, 0, null)
		}
		preferredSize = new Dimension(rp.width, rp.height)
	}

	ImageIO.write(img, IMG_FORMAT, new File(imgFullName))

	val frame = new MainFrame {
		title = "Raytracer"
		contents = panel
		centerOnScreen
	}

	frame.open
}