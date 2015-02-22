package raytracer.ShadeableIntersection

import java.awt.{Color}

class ShadeableIntersection(val t: Double, val valid: Boolean, shadeFunction: () => Color){
	def shadeIntersection() = shadeFunction()
}