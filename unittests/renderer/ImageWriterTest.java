package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for image writer
 */
class ImageWriterTest {


    /**
     * Test method for
     * {@link ImageWriter#writeToImage()},
     * and {@link  ImageWriter#writePixel(int, int, Color)}
     */
    @Test
    void writeToImageAndWritePixelTest() {
        //TC01: making a grid of 2 colors
        ImageWriter gridWriter = new ImageWriter("test", 500, 800);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                if ((i % 10 == 0 || j % 10 == 0))
                    gridWriter.writePixel(i, j, new Color(200, 40, 30));
                else gridWriter.writePixel(i, j, new Color(155, 7, 73));
            }
        }
        gridWriter.writeToImage();
    }
}