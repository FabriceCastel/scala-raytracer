package raytracer.node.GeometryNode

import raytracer.Colour._
import raytracer.node.SceneNode._
import raytracer.primitive.Primitive._
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._
import raytracer.node.FlattenedGeometryNode._
import raytracer.Ray._
import raytracer.vecmath._
import raytracer.Material._
import raytracer.shader.Shader._
import raytracer.shader.BlinnPhongShader._
import raytracer.AccelerationStructure._
import raytracer.Light._

class GeometryNode(name: String, primitive: Primitive, mat: Material, shader: Shader) extends SceneNode(name){
	def this(name: String, primitive: Primitive){
		this(name, primitive, new Material(new Colour(0xffff3377), new Colour(0xffffffff), 50), new BlinnPhongShader())
	}

	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val primitiveIntersection = primitive.intersect(ray)
		if(primitiveIntersection.isValid){
			val col = new Colour(0xfff220b0)
			val sinter = new ShadeableIntersection(0, true, (s, l) => {
				shader.apply(ray, primitiveIntersection, mat)(s, l)
				})
			sinter
		} else {
			val col = new Colour(0xffff0000)
			val sinter = new ShadeableIntersection(0, false, (s, l) => {col})
			sinter
		}
	}

	override def flatten(): List[FlattenedGeometryNode] = {
		var id = new Matrix4D()
		id.setIdentity()
		val flatNode = new FlattenedGeometryNode(name, primitive, id)
		List(flatNode)
	}

	override def addChild(node: SceneNode): SceneNode = {
		throw new UnsupportedOperationException("Cannot add child to Geometry Node object")
	}
}