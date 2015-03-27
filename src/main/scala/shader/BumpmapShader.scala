package com.fcastel.raytracer.shader

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light
import com.fcastel.raytracer.acceleration.AccelerationStructure
import com.fcastel.raytracer.material.Material
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.algebra.Ray

//
// !! work in progress !!
//

class BumpmapShader() extends Shader{
	private val map = ImageIO.read(new File("data/textures/waterbump.jpg"))
	private val blinnPhongShader = new BlinnPhongShader(30)

	def apply(ray: Ray, inter: BasicIntersection, mat: Material)(scene: AccelerationStructure, lights: List[Light]): Colour = {
		val altn = perturbate(inter.normal, inter.uv)
		val altinter = new BasicIntersection(inter.point, altn, inter.t, inter.uv)
		blinnPhongShader.apply(ray, altinter, mat)(scene, lights)
	}

	private def perturbate(v: Vector3D, uv: Point2D): Vector3D = {
		//new Vector3D(v.x, v.y, v.z)
		v
	}
}