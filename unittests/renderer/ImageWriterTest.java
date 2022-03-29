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
     * {@link ImageWriter#writeToImage()}.
     */
    @Test
    void writeToImage() {
        ImageWriter gridWriter = new ImageWriter("test", 500, 800);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                if (i%10==0||j%10==0)
                    gridWriter.writePixel(i, j,new Color(200,200,30) );
                else gridWriter.writePixel(i, j, new Color(155, 7, 73));
            }
        }
        gridWriter.writeToImage();
    }

    /**
     * Test method for
     * {@link ImageWriter#writePixel(int, int, Color)}.
     */
    @Test
    void writePixel() {
    }
}