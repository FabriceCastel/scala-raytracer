package raytracer.primitive.Triangle

import raytracer.vecmath._
import raytracer.Ray._
import raytracer.BasicIntersection._
import raytracer.primitive.Primitive._
import raytracer.Utils._

class Triangle(vertices: List[Point3D], uv: List[Point2D], hasUV: Boolean) extends Primitive(){
	require(vertices.length == 3, "Triangles must have three vertices")

	def this(vertices: List[Point3D]){
		this(vertices, null, false)
	}

	def this(vertices: List[Point3D], uv: List[Point2D]){
		this(vertices, uv, true)
		require(uv.length == 3, "Invalid UV parameters for triangle")
	}

	override def intersect(ray: Ray): BasicIntersection = {
		val edge1 = vertices(1) - vertices(0)
		val edge2 = vertices(2) - vertices(0)
		var P = new Vector3D(0,0,0)
		P.cross(ray.v, edge2) // assigns val to P
		val determinant = edge1 dot P
		if(determinant > -U.EPSILON && determinant < U.EPSILON) new BasicIntersection()
		else {
			val T = ray.p - vertices(0)
			new BasicIntersection()
		}
	}

	override def intersectFast(ray: Ray): Boolean = {
		false
	} 
}