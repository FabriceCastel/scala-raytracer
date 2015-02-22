package raytracer.node.FlattenedGeometryNode

import javax.vecmath.{Matrix4d}
import java.awt.{Color}

import raytracer.node.GeometryNode._
import raytracer.ShadeableIntersection._
import raytracer.RenderParameters._
import raytracer.primitive.Primitive._
import raytracer.Ray._

class FlattenedGeometryNode(name: String, primitive: Primitive, trans: Matrix4d) extends GeometryNode(name, primitive){
	
	// intersect method needs to be overriden to calculate intersection WITH its trans
	// matrix taken into account, unlike parent GeometryNode class

	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		super.intersect(params, ray)
	}

	def intersectFast(ray: Ray): Boolean = {
		true
	}

	def applyTransform(trans: Matrix4d): FlattenedGeometryNode = {
		var updatedMatrix = new Matrix4d(trans)
		updatedMatrix.mul(trans)
		val transformedNode = new FlattenedGeometryNode(name, primitive, updatedMatrix)
		transformedNode
	}
}