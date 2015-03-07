package com.fcastel.raytracer

import scala.math._

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.algebra.Ray

class Camera(position: Point3D, direction: Vector3D, up: Vector3D, fov: Double){
	def this(){
		this(new Point3D(0,0,-100), new Vector3D(0,0,1), new Vector3D(0,1,0), 50)
	}


	def generateRays(width: Int, height: Int): Array[Array[Ray]] = {
		// Adapted from
		// http://stackoverflow.com/questions/14094042/calculating-camera-ray-direction-to-3d-world-pixel
		val fovx = Math.PI * fov / 360.0
		val fovy = fovx * height / width

		var rayGrid = Array.ofDim[Ray](width, height)

		var nright = up cross direction; nright.normalize
		var nup = nright cross direction; nup.normalize
		var dir = direction; dir.normalize

		val ulen = tan(fovx)
		val vlen = tan(fovy)

		for(x <- 0 to width - 1; y <- 0 to height - 1){
			val adjx = (2.0*x - width) / width
			val adjy = (2.0*y - height) / height
			val pixel = position + nright*adjx*ulen + nup*adjy*vlen + dir
			rayGrid(x)(y) = new Ray(position, pixel - position)
		}
		rayGrid
	}
}