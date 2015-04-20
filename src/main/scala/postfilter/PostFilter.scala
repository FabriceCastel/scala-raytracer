package com.fcastel.raytracer.postfilter

import java.awt.image.BufferedImage

abstract class PostFilter() {
	def apply(image: BufferedImage): Unit
}