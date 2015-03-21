package com.fcastel.raytracer.material

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.algebra._

class FlatMaterial(kd: Colour, ks: Colour) extends Material(){
	def getKD(uv: Point2D): Colour = {//kd}
		val r = (uv.x * 255).floor.toInt
		val g = (uv.y * 255).floor.toInt
		val b = 0
		new Colour(r, g, b)
	}
	def getKS(uv: Point2D): Colour = {ks}
}