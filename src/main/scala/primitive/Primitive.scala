package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.BasicIntersection

abstract class Primitive(){
	def intersect(ray: Ray): BasicIntersection
	def intersectFast(ray: Ray): Boolean
}