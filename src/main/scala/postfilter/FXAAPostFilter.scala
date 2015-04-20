package com.fcastel.raytracer.postfilter

import java.awt.image.BufferedImage

import com.fcastel.raytracer.utils.Colour

class FXAAFilter() extends PostFilter(){
	def apply(image: BufferedImage): Unit = {
		val tmpBuffer = new BufferedImage(image.getWidth, image.getHeight, image.getType)
		for(x <- 0 to image.getWidth()-1; y <- 0 to image.getHeight()-1){
			val xb_+ = Math.min(x+1, image.getWidth-1)
			val xb_- = Math.max(x-1, 0)
			val yb_+ = Math.min(y+1, image.getHeight-1)
			val yb_- = Math.max(y-1, 0)
			val centre = new Colour(image.getRGB(x, y))
			val up = new Colour(image.getRGB(x, yb_+))
			val down = new Colour(image.getRGB(x, yb_-))
			val left = new Colour(image.getRGB(xb_-, y))
			val right = new Colour(image.getRGB(xb_+, y))
			val edge = detectEdge(centre, up, down, left, right, 2000.0)
			tmpBuffer.setRGB(x, y, (if(edge) blur(centre, up, down, left, right) else centre).getRGB)
		}
		for(x <- 0 to image.getWidth()-1; y <- 0 to image.getHeight()-1){
			image.setRGB(x, y, tmpBuffer.getRGB(x, y))
		}
	}

	private def detectEdge(centre: Colour, up: Colour, down: Colour, left: Colour, right: Colour, threshold: Double): Boolean = {
		def sq(a: Int): Int = {
			a * a
		}
		val rdiff =
			sq(centre.getRed() - up.getRed()) + 
			sq(centre.getRed() - down.getRed()) + 
			sq(centre.getRed() - left.getRed()) + 
			sq(centre.getRed() - right.getRed())
		val gdiff =
			sq(centre.getGreen() - up.getGreen()) + 
			sq(centre.getGreen() - down.getGreen()) + 
			sq(centre.getGreen() - left.getGreen()) + 
			sq(centre.getGreen() - right.getGreen())
		val bdiff =
			sq(centre.getBlue() - up.getBlue()) + 
			sq(centre.getBlue() - down.getBlue()) + 
			sq(centre.getBlue() - left.getBlue()) + 
			sq(centre.getBlue() - right.getBlue())
		if((rdiff + gdiff + bdiff) > threshold) true
		else false
	}

	private def blur(centre: Colour, up: Colour, down: Colour, left: Colour, right: Colour): Colour = {
		val red = (centre.getRed + up.getRed + down.getRed + left.getRed + right.getRed) / 5
		val green = (centre.getGreen + up.getGreen + down.getGreen + left.getGreen + right.getGreen) / 5
		val blue = (centre.getBlue + up.getBlue + down.getBlue + left.getBlue + right.getBlue) / 5
		new Colour(red, green, blue)
	}
}