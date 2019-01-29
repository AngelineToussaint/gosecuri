package fr.gosecuri.utils;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class Camera {
    private ImageIcon frame;
    private VideoCapture camera;

    public Camera() {
        // Load Camera
        OpenCV.loadLocally();
        camera = new VideoCapture(0);

        new Thread(this::runCamera).start();
    }

    public void runCamera() {
        // Start Video Capture
        BufferedImage bufferedImage;
        while(camera.isOpened()) {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                // Format image to ImageIcon format
                bufferedImage = MatToBufferedImage(frame);
                setFrame(new ImageIcon(bufferedImage));
            }
        }
    }

    public BufferedImage MatToBufferedImage(Mat frame) {
        //Mat() to BufferedImage
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    public ImageIcon getFrame() {
        return frame;
    }
    public void setFrame(ImageIcon frame) {
        this.frame = frame;
    }

    public boolean isOpen() {
        return camera.isOpened();
    }
}
