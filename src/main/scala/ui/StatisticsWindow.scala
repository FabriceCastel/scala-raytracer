package com.fcastel.raytracer.ui

import swing._
import event._
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import com.fcastel.raytracer.RenderParameters

class StatisticsWindow(elems: Int) {
	require(elems > 0, "Can't create an empty statistics window")
	private val labels = List.fill(elems)(new Label(""))

	val frame = new Frame {
		title = "StatisticsWindow"
		centerOnScreen
		contents = new BoxPanel(Orientation.Vertical) {
			for(i <- 1 to elems) contents += labels(i-1)
		}
		size = new Dimension(400, 300)
	}

	frame.open

	def update(stats: List[String]): Unit = {
		for(i <- 0 to Math.min(stats.length - 1, elems)) labels(i).text = stats(i)
		frame.repaint()
	}

	def alive(): Boolean = {
		frame.showing
	}
}