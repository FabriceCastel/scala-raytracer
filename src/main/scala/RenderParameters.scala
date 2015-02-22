package raytracer.RenderParameters

import javax.vecmath.{Vector3d}
import javax.vecmath.{Point3d}

class RenderParameters(val width: Int, val height: Int, val cameraPos: Point3d, val cameraDir: Vector3d){
	def this(){
		this(500, 500, new Point3d(0,0,0), new Vector3d(0,0,1))
	}
}