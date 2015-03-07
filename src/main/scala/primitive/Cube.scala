package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._

class Cube(dim: Double) extends Mesh(
	List(new Point3D(0, dim, dim),
		new Point3D(0, 0, dim),
		new Point3D(dim, 0, dim),
		new Point3D(dim, dim, dim),
		new Point3D(0, dim, 0),
		new Point3D(0, 0, 0),
		new Point3D(dim, 0, 0),
		new Point3D(dim, dim, 0)),
	List(List(0, 1, 2),
		List(0, 2, 3),
		List(7, 6, 5),
		List(7, 5, 4),
		List(3, 2, 6),
		List(3, 6, 7),
		List(4, 0, 3),
		List(4, 3, 7),
		List(4, 5, 1),
		List(4, 1, 0),
		List(1, 5, 6),
		List(1, 6, 2))) {

	require(dim > 0, "Cube must have sides of size greater than 0")
}