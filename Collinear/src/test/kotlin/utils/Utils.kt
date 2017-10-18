package utils

import edu.princeton.cs.algs4.StdDraw
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner
import java.util.regex.Pattern

import Point
import BruteCollinearPoints
import FastCollinearPoints
import edu.princeton.cs.algs4.StdOut

private val WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+")

class Utils {
    fun createScannerForInputFile(file: String): Scanner {
        val path = Paths.get(javaClass.classLoader.getResource(file)!!.toURI())
        val scanner = Scanner(Files.newBufferedReader(path))
        scanner.useDelimiter(WHITESPACE_PATTERN)
        return scanner
    }
}

fun readPointsFromFileToList(filename: String): List<Point> {
    val scanner = Utils().createScannerForInputFile(filename)
    val n = scanner.nextInt()
    val points = mutableListOf<Point>()
    (1..n).forEach {
        val x = scanner.nextInt()
        val y = scanner.nextInt()
        points.add(Point(x, y))
    }
    return points.toList()
}

fun drawBruteSegmentsFromFile(filename: String) {
    val points = drawPonts(filename)
    val collinear = BruteCollinearPoints(points.toTypedArray())
    for (segment in collinear.segments()) {
        StdOut.println(segment)
        segment.draw()
    }
    StdDraw.show()
}

fun drawFastSegmentsFromFile(filename: String) {
    val points = drawPonts(filename)
    val collinear = FastCollinearPoints(points.toTypedArray())
    for (segment in collinear.segments()) {
        StdOut.println(segment)
        segment.draw()
    }
    StdDraw.show()
}

private fun drawPonts(filename: String): List<Point> {
    StdDraw.enableDoubleBuffering()
    StdDraw.setXscale(0.0, 32768.0)
    StdDraw.setYscale(0.0, 32768.0)
    val points = readPointsFromFileToList(filename)
    for (p in points) {
        p.draw()
    }
    StdDraw.show()
    return points
}