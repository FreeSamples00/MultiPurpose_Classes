import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static final File userDir = new File(System.getProperty("user.home"));
    private static final Scanner reader = new Scanner(System.in);
    private static class EmptyInput extends InputMismatchException {
        private EmptyInput() {
            super();
        }
    }
    private static void printErr(String message) {
        System.out.format("Input err: %s\n", message);
    }


    public static int getInteger(String message) {
        int input = 0;
        boolean isValid = false;
        do {
            try {
                System.out.print(message);
                String rawInput = reader.nextLine();
                if (rawInput.isEmpty()) {
                    throw new EmptyInput();}
                input = Integer.parseInt(rawInput);
                isValid = true;
            } catch (EmptyInput e) {
                printErr("Empty input");
            } catch (NumberFormatException e) {
                printErr("Not an integer");
            }
        } while (!isValid);
        return input;
    }

    public static String getString(String message) {
        String input = "";
        boolean isValid = false;
        do {
            try {
                System.out.print(message);
                input = reader.nextLine();
                if (input.isEmpty()) {throw new EmptyInput();}
                isValid = true;
            } catch (EmptyInput e) {
                printErr("Empty input");
            }
        } while (!isValid);
        return input;
    }

    public static Double getDouble(String message) {
        double input = 0.0;
        boolean isValid = false;
        do {
            try {
                System.out.print(message);
                String rawInput = reader.nextLine();
                if (rawInput.isEmpty()) {
                    throw new EmptyInput();
                }
                input = Double.parseDouble(rawInput);
                isValid = true;
            } catch (EmptyInput e) {
                printErr("Empty input");
            } catch (NumberFormatException e) {
                printErr("Not a double");
            }
        } while (!isValid);
        return input;
    }

    public static boolean getBoolean(String message, boolean showTF) {
        boolean input = false;
        boolean isValid = false;
        if (showTF) {
            message = message.substring(0, message.indexOf(":")) + " (T/F): ";
        }
        do {
            try {
                System.out.print(message);
                String rawInput = reader.nextLine().toLowerCase();
                if (rawInput.isEmpty()) {
                    throw new EmptyInput();
                }
                if (rawInput.equals("t")) {
                    input = true;
                    isValid = true;
                } else if (rawInput.equals("f")) {
                    input = false;
                    isValid = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (EmptyInput e) {
                printErr("Empty input");
            } catch (InputMismatchException e) {
                printErr("Not a 't' or 'f'");
            }
        } while (!isValid);
        return input;
    }

    public static File getFile(String title, JFrame frame) {
        JFileChooser chooser = new JFileChooser(userDir);
        chooser.setDialogTitle(title);
        int saveChoice;
        do {
            saveChoice = chooser.showOpenDialog(frame);
        } while (saveChoice != JFileChooser.APPROVE_OPTION);
        return chooser.getSelectedFile().getAbsoluteFile();
    }

    public static File getImage(String title, JFrame frame) {
        JFileChooser chooser = new JFileChooser(userDir);
        chooser.setFileFilter(new FileNameExtensionFilter("Images","JPG", "PNG"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle(title);
        int saveChoice;
        do {
            saveChoice = chooser.showOpenDialog(frame);
        } while (saveChoice != JFileChooser.APPROVE_OPTION);
        return chooser.getSelectedFile().getAbsoluteFile();
    }

    public static File getFolder(String title, JFrame frame) {
        JFileChooser chooser = new JFileChooser(userDir);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle(title);
        int saveChoice;
        do {
            saveChoice = chooser.showOpenDialog(frame);
        } while (saveChoice != JFileChooser.APPROVE_OPTION);
        return chooser.getSelectedFile().getAbsoluteFile();
    }

    public static String getTXTFromFile(String title, JFrame frame) {
        JFileChooser chooser = new JFileChooser(userDir);
        chooser.setFileFilter(new FileNameExtensionFilter("text", "TXT"));
        chooser.setDialogTitle(title);
        int saveChoice;
        StringBuilder output = new StringBuilder();
        do {
            saveChoice = chooser.showOpenDialog(frame);
        } while (saveChoice != JFileChooser.APPROVE_OPTION);
        File file = chooser.getSelectedFile().getAbsoluteFile();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                output.append(fileReader.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + file.getAbsoluteFile() + "\" not found.");
            return getTXTFromFile(title, frame);
        }
        return output.toString();
    }
}
