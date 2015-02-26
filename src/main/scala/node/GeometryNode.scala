package raytracer.node.GeometryNode

import java.awt.{Color}

import raytracer.node.SceneNode._
import raytracer.primitive.Primitive._
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._
import raytracer.node.FlattenedGeometryNode._
import raytracer.Ray._
import raytracer.vecmath._

class GeometryNode(name: String, primitive: Primitive) extends SceneNode(name){
	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val primitiveIntersection = primitive.intersect(ray)
		if(primitiveIntersection.isValid){
			val col = new Color(0xfff220b0)
			val sinter = new ShadeableIntersection(0, true, () => {col})
			sinter
		} else {
			val col = new Color(0xffff0000)
			val sinter = new ShadeableIntersection(0, false, () => {col})
			sinter
		}
	}

	override def flatten(): List[FlattenedGeometryNode] = {
		val id = new Matrix4D()
		id.setIdentity()
		val flatNode = new FlattenedGeometryNode(name, primitive, id)
		List(flatNode)
	}
}