package com.fcastel.raytracer.acceleration

import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.RenderParameters

abstract class AccelerationStructure(scene: List[FlattenedGeometryNode]){
	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection
	def intersectFast(ray: Ray): Boolean
}