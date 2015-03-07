package com.fcastel.raytracer

import com.fcastel.raytracer.algebra._

class RenderParameters(val width: Int, val height: Int, val cameraPos: Point3D, val cameraDir: Vector3D){
	def this(){
		this(500, 500, new Point3D(0,0,0), new Vector3D(0,0,1))
	}
}