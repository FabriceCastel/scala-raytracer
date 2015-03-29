package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.algebra.Ray

class Mesh(triangles: List[Triangle]) extends Primitive(){
	def this(vertices: List[Point3D], faces: List[List[Int]], normals: List[Vector3D], UVMapping: List[Point2D]){
		this({
			faces.map(face => {
				val id0 = face(0)
				val id1 = face(1)
				val id2 = face(2)
				val verts = List(vertices(id0), vertices(id1), vertices(id2))
				val norms = (if (normals == Nil) Nil else List(normals(id0), normals(id1), normals(id2)))
				val uvs = (if (UVMapping == Nil) Nil else List(UVMapping(id0), UVMapping(id1), UVMapping(id2)))
			new Triangle(verts, uvs, norms)})
			})
		require(faces.foldLeft(true)((base, cur) => base && (cur.length == 3)), "Mesh faces must specify exactly three points")
		require(faces.foldLeft(true)((base, cur) => base &&
			cur.foldLeft(true)((b, f) => b && (f < vertices.size))),
			"Mesh face points to non-existent vertex")
	}

	def this(vertices: List[Point3D], faces: List[List[Int]]){
		this(vertices, faces, Nil, Nil)
	}

	override def intersect(ray: Ray): BasicIntersection = {
		triangles.foldLeft(new BasicIntersection())((base, cur) => {
			val curHit = cur.intersect(ray)
			if(!base.isValid || (curHit.isValid && curHit.t < base.t)) curHit
			else base })
	}

	override def intersectFast(ray: Ray): Boolean = {
		false
	}
}