package com.fcastel.raytracer

import javax.vecmath.{Point3d}
import java.awt.{Color}

import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.algebra.Ray

class AccelerationStructure(locus: Point3d, scene: List[FlattenedGeometryNode]){
	def intersect(ray: Ray): Color = {
		val col = new Color(0xff0f78f9)
		col
	}

	// for shadow computations
	def intersectFast(ray: Ray): Boolean = {
		true
	}
}