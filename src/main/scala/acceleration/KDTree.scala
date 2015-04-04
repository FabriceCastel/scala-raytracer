package com.fcastel.raytracer.acceleration

import com.fcastel.raytracer.utils._
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.RenderParameters

class KDTree(scene: List[FlattenedGeometryNode]) extends AccelerationStructure(scene){
	private val tree = new KDTreeNode(scene)

	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		tree.intersect(params, ray)
	}

	def intersectFast(ray: Ray): Boolean = {
		throw(new UnsupportedOperationException("intersectFast() has not been implemented"))
	}
}