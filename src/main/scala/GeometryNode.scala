package raytracer.GeometryNode

import java.awt.{Color}

import raytracer.SceneNode._
import raytracer.Primitive._
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._

class GeometryNode(name: String, primitive: Primitive) extends SceneNode(name){
	override def intersect(params: RenderParameters): ShadeableIntersection = {
		val col = new Color(0xfff04ea9)
		val sinter = new ShadeableIntersection(0, true, () => {
			val col2 = new Color(0xff0f44f2)
			col2
			})
		sinter
	}
}