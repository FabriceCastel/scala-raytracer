package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.algebra.Ray

class Mesh(vertices: List[Point3D], faces: List[List[Int]], UVMapping: List[Point2D], hasUVMapping: Boolean) extends Primitive(){
	require(faces.foldLeft(true)((base, cur) => base && (cur.length == 3)), "Mesh faces must specify exactly three vectors")

	def this(vertices: List[Point3D], faces: List[List[Int]]){
		this(vertices, faces, Nil, false)
	}

	def this(vertices: List[Point3D], faces: List[List[Int]], UVMapping: List[Point2D]){
		this(vertices: List[Point3D], faces: List[List[Int]], UVMapping: List[Point2D], true)
		require(vertices.length == UVMapping.length, "UV mapping does not match vertex count in Mesh")
	}

	val triangles = faces.map(face =>
		if(hasUVMapping) new Triangle(
			List(vertices(face(0)), vertices(face(1)), vertices(face(2))), 
			List(UVMapping(face(0)), UVMapping(face(1)), UVMapping(face(2))))
		else new Triangle(List(vertices(face(0)), vertices(face(1)), vertices(face(2)))))

	override def intersect(ray: Ray): BasicIntersection = {
		triangles.foldLeft(new BasicIntersection())((base, cur) => {
			val curHit = cur.intersect(ray)
			if(!base.isValid || (curHit.isValid && curHit.t < base.t)) curHit
			else base
			})
	}

	override def intersectFast(ray: Ray): Boolean = {
		false
	}
}