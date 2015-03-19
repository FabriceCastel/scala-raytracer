package com.fcastel.raytracer

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.node.SceneNode
import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.parser.Parser
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.acceleration.AccelerationStructure
import com.fcastel.raytracer.Camera
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light
import com.fcastel.raytracer.ui._

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

	val camera = new Camera()
	val flatScene = scene.flatten()

	val ui = new RenderWindow(rp, img)

	var i = 0.0
	var lastFrame = System.currentTimeMillis()
	var frame = 0
	var avgtime = 0.0
	while(ui.alive){
		i += 0.04
		val origin = new Point3D(0,0,0)
		val dist = 10
		val camPos = new Point3D(dist * Math.sin(i),2,dist * Math.cos(i))
		val acceleratedScene = new AccelerationStructure(camPos, flatScene)
		val camDir = origin - camPos
		val camUp = new Vector3D(0,1,0)
		val curCam = new Camera(camPos, camDir, camUp, 50)
		val rays = curCam.generateRays(rp.width, rp.height)
		for(x <- 0 to rp.width-1; y <- 0 to rp.height-1){
			val pixelColor = acceleratedScene.intersect(rp, rays(x)(y)).shadeIntersection(acceleratedScene, lights)
			img.setRGB(x, y, pixelColor.getRGB())
			ui.update()
		}
		val newTime = System.currentTimeMillis()
		avgtime = ((avgtime * frame) + (newTime - lastFrame)) / (frame + 1)
		frame = frame + 1
		print("Avg frame render time: " + "%.2f".format(avgtime) + "ms                   \r")
		ImageIO.write(img, IMG_FORMAT, new File(OUTPUT_FOLDER + frame + "." + IMG_FORMAT))
		lastFrame = System.currentTimeMillis()
	}
}