package com.fcastel.raytracer.acceleration

import com.fcastel.raytracer.utils._
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.RenderParameters

class KDTreeNode(axis: Char, scene: List[FlattenedGeometryNode]){
	def this(scene: List[FlattenedGeometryNode]){
		this('x', scene)
	}

	// <build> children nodes if applicable

	// </build>

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
}