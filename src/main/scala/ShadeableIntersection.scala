package raytracer.ShadeableIntersection

import raytracer.Colour._
import raytracer.AccelerationStructure._
import raytracer.Light._

// Intersection returned by GeometryNode && its subclasses

class ShadeableIntersection(val t: Double, val isValid: Boolean, shadeFunction: (AccelerationStructure, List[Light]) => Colour){
	def shadeIntersection(scene: AccelerationStructure, lights: List[Light]) = shadeFunction(scene, lights)
}