import swing._
import event._
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import raytracer.Colour._
import raytracer.node.SceneNode._
import raytracer.node.FlattenedGeometryNode._
import raytracer.Parser._
import raytracer.Ray._
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._
import raytracer.AccelerationStructure._
import raytracer.Camera._
import raytracer.vecmath._
import raytracer.Light._

object Raytracer extends App {
	val OUTPUT_FOLDER = "renders/"
	val IMG_FORMAT = "png"
	val IMG_NAME = "out"
	val imgFullName = OUTPUT_FOLDER + IMG_NAME + "." + IMG_FORMAT

	val parser = new Parser("data/dummy_scene_file_name")
	val scene = parser.getScene()
	val rp = parser.getRenderParameters()
	println(scene.toString)

	val lights = parser.getLights()
	
	val img = new BufferedImage(rp.width, rp.height, BufferedImage.TYPE_INT_ARGB)

	val acceleratedScene = new AccelerationStructure(rp.cameraPos, scene.flatten())
	val camera = new Camera()
	

	val panel = new Panel {
		override def paint(g : Graphics2D){
			super.paint(g)
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

	var i = 0.0
	var lastFrame = System.currentTimeMillis()
	val spf = 1000 / 30
	while(frame.showing){
		i += 0.04
		val origin = new Point3D(0,0,0)
		val camPos = new Point3D(100 * Math.sin(i),30,100 * Math.cos(i))
		val camDir = origin - camPos
		val camUp = new Vector3D(0,1,0)
		val curCam = new Camera(camPos, camDir, camUp, 50)
		val rays = curCam.generateRays(rp.width, rp.height)
		for(x <- 0 to rp.width-1; y <- 0 to rp.height-1){
			val pixelColor = scene.intersect(rp, rays(x)(y)).shadeIntersection(acceleratedScene, lights)
			img.setRGB(x, y, pixelColor.getRGB())
		}
		panel.repaint()
		val newTime = System.currentTimeMillis()
		val delay = spf - (newTime - lastFrame)
		if(delay > 0) Thread.sleep(delay)
		lastFrame = System.currentTimeMillis()
	}
}