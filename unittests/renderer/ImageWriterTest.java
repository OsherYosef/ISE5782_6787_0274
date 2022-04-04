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

        //Just a fun test- drawing a smile :)
        ImageWriter smile = new ImageWriter("Smile", 500, 500);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                smile.writePixel(j,i,Color.BLACK);
                //Head
                if((i-250)*(i-250)+(j-250)*(j-250)<=250*250)
                    smile.writePixel(j, i, new Color(java.awt.Color.YELLOW));
                //Eyes
                if ((i>100&&i<200)&&((j>175)&&(j<200)||(j>300)&&(j<325)))
                    smile.writePixel(j,i,Color.BLACK);
                //Teeth
                if ((i>350&&i<375)&&((j>125)&&(j<375)))
                    smile.writePixel(j,i,new Color(java.awt.Color.WHITE));
                if ((i>=325&&i<=350)&&((j>=100)&&(j<=400)))
                    smile.writePixel(j,i,new Color(java.awt.Color.WHITE));
                if ((i>=300&&i<=325)&&((j>=75)&&(j<=425)))
                    smile.writePixel(j,i,new Color(java.awt.Color.WHITE));
            }
        }
        smile.writeToImage();
    }
}