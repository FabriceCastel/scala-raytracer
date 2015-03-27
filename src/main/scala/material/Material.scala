package com.fcastel.raytracer.material

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.algebra.Point2D

abstract class Material(){
	def getKD(uv: Point2D): Colour
	def getKS(uv: Point2D): Colour
}