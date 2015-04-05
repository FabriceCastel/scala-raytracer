package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._

class Box(posX: Double, posY: Double, posZ: Double, dimX: Double, dimY: Double, dimZ: Double) extends Mesh(
	List(new Point3D(posX, posY + dimY, posZ + dimZ),
		new Point3D(posX, posY, posZ + dimZ),
		new Point3D(posX + dimX, posY, posZ + dimZ),
		new Point3D(posX + dimX, posY + dimY, posZ + dimZ),
		new Point3D(posX, posY + dimY, posZ),
		new Point3D(posX, posY, posZ),
		new Point3D(posX + dimX, posY, posZ),
		new Point3D(posX + dimX, posY + dimY, posZ)),
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

	def this(lower: Point3D, upper: Point3D){
		this(lower.x, lower.y, lower.z, upper.x-lower.x, upper.y-lower.y, upper.x-lower.z)
	}

	def this(tuple: (Point3D, Point3D)){
		this(tuple._1, tuple._2)
	}

	require(dimX > 0, "Cube must have sides of size greater than 0")
	require(dimY > 0, "Cube must have sides of size greater than 0")
	require(dimZ > 0, "Cube must have sides of size greater than 0")
}