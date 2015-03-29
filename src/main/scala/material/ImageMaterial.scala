package com.fcastel.raytracer.material

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.algebra._

class ImageMaterial(file: String, ks: Colour) extends Material(){
	def this(file: String){
		this(file, new Colour(255, 255, 255))
	}

	private val img = ImageIO.read(new File(file))

	def getKD(uv: Point2D): Colour = {
		val uidx = Math.min((img.getWidth() * uv.x).floor.toInt, img.getWidth() - 1)
		val vidx = img.getHeight() - (img.getHeight() * uv.y).floor.toInt - 1
		new Colour(img.getRGB(uidx, vidx))
	}

	def getKS(uv: Point2D): Colour = {ks}
}