package raytracer.RenderParameters

import raytracer.vecmath._

class RenderParameters(val width: Int, val height: Int, val cameraPos: Point3D, val cameraDir: Vector3D){
	def this(){
		this(500, 500, new Point3D(0,0,0), new Vector3D(0,0,1))
	}
}