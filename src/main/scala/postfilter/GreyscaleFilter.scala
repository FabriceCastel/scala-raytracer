package com.fcastel.raytracer.postfilter

import java.awt.image.BufferedImage

import com.fcastel.raytracer.utils.Colour

class GreyscaleFilter() {
	def apply(image: BufferedImage): Unit = {
		for(x <- 0 to image.getWidth()-1; y <- 0 to image.getHeight()-1){
			val baseColour = new Colour(image.getRGB(x, y))
			val grey = (baseColour.getRed() + baseColour.getGreen() + baseColour.getBlue()) / 3
			image.setRGB(x, y, new Colour(grey, grey, grey).getRGB())
		}
	}
}