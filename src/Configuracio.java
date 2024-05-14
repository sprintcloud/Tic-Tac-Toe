import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author chong
 */
public class Configuracio {
    private int midataulell;
    private int tempp;
    
    Configuracio(int midataulell, int tempp) {
        this.midataulell = midataulell;
        this.tempp = tempp;
    }
    
    public void guardarConfiguracio() {
        try (FileReader reader = new FileReader("config.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea = bufferedReader.readLine();
            if (linea == null) {
                escribirConfiguracio();
                return;
            }
            String[] data = linea.split("\\*");
            if (data.length < 2) {
                escribirConfiguracio();
            } else {
                if (midataulell == 0) {
                    midataulell = Integer.parseInt(data[0]);
                }
                if (tempp == 0) {
                    tempp = Integer.parseInt(data[1]);
                }
                escribirConfiguracio();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al guardar la configuració: " + e.getMessage());
        }
    }
    
    private void escribirConfiguracio() {
        try (FileWriter writer = new FileWriter("config.txt")) {
            writer.write(String.format("%s*%s", midataulell, tempp));
            System.out.println("Configuració guardada correctament.");
        } catch (IOException e) {
            System.out.println("Error al guardar la configuració: " + e.getMessage());
        }
    }
}
