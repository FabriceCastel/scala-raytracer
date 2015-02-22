package raytracer.ShadeableIntersection

import java.awt.{Color}

// Intersection returned by GeometryNode && its subclasses

class ShadeableIntersection(val t: Double, val valid: Boolean, shadeFunction: () => Color){
	def shadeIntersection() = shadeFunction()
}