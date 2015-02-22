package raytracer.node.GeometryNode

import java.awt.{Color}
import javax.vecmath.{Matrix4d}

import raytracer.node.SceneNode._
import raytracer.primitive.Primitive._
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._
import raytracer.node.FlattenedGeometryNode._
import raytracer.Ray._

class GeometryNode(name: String, primitive: Primitive) extends SceneNode(name){
	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val col = new Color(0xfff04ea9)
		val sinter = new ShadeableIntersection(0, true, () => {
			val col2 = new Color(0xff0f44f2)
			col2
			})
		sinter
	}

	override def flatten(): List[FlattenedGeometryNode] = {
		val id = new Matrix4d()
		id.setIdentity()
		val flatNode = new FlattenedGeometryNode(name, primitive, id)
		List(flatNode)
	}
}