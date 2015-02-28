import org.scalatest.FlatSpec

import raytracer.primitive.Triangle._
import raytracer.Ray._
import raytracer.BasicIntersection._
import raytracer.vecmath._

class TriangleSpec extends FlatSpec {
	val o = new Point3D(0,0,0)
	val i = new Point3D(1,0,0)
	val j = new Point3D(0,1,0)
	val k = new Point3D(0,0,1)

	val v1 = new Vector3D(0,0,1)
	val ray1 = new Ray(o - v1, v1)
	val ray2 = new Ray(i - v1, v1)
	val ray3 = new Ray(j - v1, v1)
	val ray4 = new Ray(new Point3D(0.2, 0.2, -1), v1)
	val ray5 = new Ray(new Point3D(-10, -10, 0.00001), new Vector3D(1, 1, 0))
	val ray1n = new Ray(o + v1, v1)

	val tri1 = new Triangle(List(o,i,j))
	val tri1n = new Triangle(List(o,j,i))


	"A Triangle" should "return a valid intersection at its vertices" in {
		assert(tri1.intersect(ray1).isValid)
		assert(tri1n.intersect(ray1).isValid)
		assert(tri1.intersect(ray2).isValid)
		assert(tri1n.intersect(ray2).isValid)
		assert(tri1.intersect(ray3).isValid)
		assert(tri1n.intersect(ray3).isValid)
	}

	it should "compute accurate invalid intersections" in {
		assert(!tri1.intersect(ray1n).isValid)
		assert(!tri1.intersect(ray5).isValid)
	}

	it should "return reasonable normals" in {
		val b1 = tri1.intersect(ray4)
		val b2 = tri1n.intersect(ray4)
		assert(b1.isValid)
		assert(b1.normal == new Vector3D(0,0,1))
		assert(b2.isValid)
		assert(b2.normal == new Vector3D(0,0,-1))
	}
}