package raytracer.Camera

import javax.vecmath.{Vector3d}
import javax.vecmath.{Point3d}

import scala.math._

import raytracer.Ray._

class Camera(position: Point3d, direction: Vector3d, up: Vector3d, fov: Double){
	def this(){
		this(new Point3d(0,0,-100), new Vector3d(0,0,1), new Vector3d(0,1,0), 50)
	}

	// ********** TODO *********
	//
	// direct port of the old INCOMPLETE C++ code
	// this doesn't take any of the direction or up vectors
	// into consideration and does a hack-ass job
	//

	def generateRays(width: Int, height: Int): Array[Array[Ray]] = {
		val fovx = Math.PI * fov / 360.0
		val fovy = fovx * height / width

		var rayGrid = Array.ofDim[Ray](width, height)

		for(x <- 0 to width-1; y <- 0 to height-1){
			val rdir = new Vector3d(
				(2.0*x - width)/width * tan(fovx),
				(-1 * (2.0*y - height)/height) * tan(fovy),
				1)
			rayGrid(x)(y) = new Ray(position, rdir)
		}
		rayGrid
	}

	//
	// ********* /TODO *******
	//
}