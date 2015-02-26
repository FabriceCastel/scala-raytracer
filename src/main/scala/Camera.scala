package raytracer.Camera

import scala.math._

import raytracer.vecmath._
import raytracer.Ray._

class Camera(position: Point3D, direction: Vector3D, up: Vector3D, fov: Double){
	def this(){
		this(new Point3D(0,0,-100), new Vector3D(0,0,1), new Vector3D(0,1,0), 50)
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
			val rdir = new Vector3D(
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