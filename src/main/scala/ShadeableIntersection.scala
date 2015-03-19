package com.fcastel.raytracer

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.acceleration.AccelerationStructure
import com.fcastel.raytracer.Light
import com.fcastel.raytracer.algebra._

// Intersection returned by GeometryNode && its subclasses

class ShadeableIntersection(val t: Double, val isValid: Boolean, shadeFunction: (AccelerationStructure, List[Light]) => Colour){
	def shadeIntersection(scene: AccelerationStructure, lights: List[Light]) = shadeFunction(scene, lights)
}