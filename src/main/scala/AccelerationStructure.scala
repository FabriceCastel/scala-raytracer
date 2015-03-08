package com.fcastel.raytracer

import com.fcastel.raytracer.utils._
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.algebra.Ray

class AccelerationStructure(locus: Point3D, scene: List[FlattenedGeometryNode]){
	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val col = new Colour(0xff000000)
		val sinter = new ShadeableIntersection(0, false, (s, l) => {col})
		if(scene.length > 0){
			val hit = scene.map{e => e.intersect(params, ray)}.reduceLeft {
				(base, cur) => {
					if(cur.isValid && (!base.isValid || cur.t < base.t)) cur else base
				}
			}
			if(!hit.isValid){
				sinter
			} else hit
		} else sinter
	}

	// for shadow computations
	def intersectFast(ray: Ray): Boolean = {
		true
	}
}