package com.fcastel.raytracer.acceleration

import com.fcastel.raytracer.utils._
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.node.FlattenedGeometryNode
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive.Box

class KDTreeNode(axis: Char, scene: List[FlattenedGeometryNode]){
	def this(scene: List[FlattenedGeometryNode]){
		this('x', scene)
	}

	// <build> children nodes if applicable
	// sort flat geom nodes by smallest value on given axis
	// 
	// bounding boxes
	
	// sort flat geom nodes by largest value on given axis

	// iterate from smallest to largest on second sorted list,
	// pulling out the corresponding geom node out of the first
	// sorted list
	// </build>

	private val bbox = scene.map(e => new Box(e.getBoundingBox()))

	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val col = new Colour(0xff000000)
		val sinter = new ShadeableIntersection(0, false, (s, l) => {col})
		if(scene.length > 0 && bbox(0).intersectFast(ray)){
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