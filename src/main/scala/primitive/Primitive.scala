package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.algebra.Point3D

abstract class Primitive(){
	def intersect(ray: Ray): BasicIntersection
	def intersectFast(ray: Ray): Boolean
	// return (lower, upper) points for xyz vals
	def getBoundingBox(): (Point3D, Point3D)
}