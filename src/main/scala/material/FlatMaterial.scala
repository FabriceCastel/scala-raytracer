package com.fcastel.raytracer.material

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.algebra._

class FlatMaterial(kd: Colour, ks: Colour) extends Material(){
	def this(kd: Colour){
		this(kd, new Colour(255, 255, 255))
	}
	def getKD(uv: Point2D): Colour = {kd}
	def getKS(uv: Point2D): Colour = {ks}
}