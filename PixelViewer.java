import java.io.IOException;

public class PixelViewer {
    public static void main(String[] args) throws IOException {
        Image image = new Image(Input.getImage("Select image", null));
        while (true) {
            String rawInput = Input.getString("Enter coordinates in \"x, y\" form: ");
            int commaIndex = rawInput.indexOf(",");
            int x = Integer.parseInt(rawInput.substring(0, commaIndex));
            int y = Integer.parseInt(rawInput.substring(commaIndex + 2));
            int[] RGB = image.getRGB(x, y);
            System.out.println("RGB Int: " + image.getIntRGB(x, y));
            System.out.format("Red: %d\nGreen: %d\nBlue: %d\n\n", RGB[0], RGB[1], RGB[2]);
        }
    }
}
