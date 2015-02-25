package raytracer.ShadeableIntersection

import java.awt.{Color}

// Intersection returned by GeometryNode && its subclasses

class ShadeableIntersection(val t: Double, val isValid: Boolean, shadeFunction: () => Color){
	def shadeIntersection() = shadeFunction()
}