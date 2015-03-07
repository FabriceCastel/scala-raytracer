package com.fcastel.raytracer

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.node.SceneNode
import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.utils.Parser
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.AccelerationStructure
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

	val acceleratedScene = new AccelerationStructure(rp.cameraPos, scene.flatten())
	val camera = new Camera()
	
	ImageIO.write(img, IMG_FORMAT, new File(imgFullName))

	val ui = new RenderWindow(rp, img)

	var i = 0.0
	var lastFrame = System.currentTimeMillis()
	val spf = 1000 / 30
	while(ui.alive){
		i += 0.04
		val origin = new Point3D(0,0,0)
		val camPos = new Point3D(100 * Math.sin(i),-100,100 * Math.cos(i))
		val camDir = origin - camPos
		val camUp = new Vector3D(0,1,0)
		val curCam = new Camera(camPos, camDir, camUp, 50)
		val rays = curCam.generateRays(rp.width, rp.height)
		for(x <- 0 to rp.width-1; y <- 0 to rp.height-1){
			val pixelColor = scene.intersect(rp, rays(x)(y)).shadeIntersection(acceleratedScene, lights)
			img.setRGB(x, y, pixelColor.getRGB())
		}
		ui.update()
		val newTime = System.currentTimeMillis()
		val delay = spf - (newTime - lastFrame)
		if(delay > 0) Thread.sleep(delay)
		lastFrame = System.currentTimeMillis()
	}
}