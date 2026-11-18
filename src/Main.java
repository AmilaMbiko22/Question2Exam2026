import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(()->new ProductApp().setVisible(true));
    }
}