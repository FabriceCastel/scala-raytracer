package com.fcastel.raytracer.shader

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.AccelerationStructure
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light
import com.fcastel.raytracer.Material
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.algebra.Ray

abstract class Shader(){
	def apply(ray: Ray, inter: BasicIntersection, mat: Material)(scene: AccelerationStructure, lights: List[Light]): Colour
}