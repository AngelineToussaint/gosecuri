package fr.gosecuri.utils;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.Observable;

public class Camera extends Observable {
    private VideoCapture camera;
    private BufferedImage image;

    public Camera() {
        // Load Camera
        OpenCV.loadLocally();
        camera = new VideoCapture(Videoio.CAP_DSHOW);

        // Start new Thread
        new Thread(this::runCamera).start();
    }

    public void runCamera() {
        // Start Video Capture
        while(camera.isOpened()) {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                // Format image to ImageIcon format
                image = matToBufferedImage(frame);

                // notify Observers
                notify(new ImageIcon(image));
            }
        }
    }

    public BufferedImage getPhoto() {
        return image;
    }

    public BufferedImage matToBufferedImage(Mat frame) {
        // Mat structure : (data, type (GRAY / BGR), height, width)
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        // Match Mat to BufferedImage structure
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    public void notify(ImageIcon frame) {
        setChanged();
        notifyObservers(frame);
    }

    public boolean isOpened () {
        return camera.isOpened();
    }
}
