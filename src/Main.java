import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

/**
 * @author chong
 */
public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static char[][] tauler;
    private static Partida partida;
    private  static Configuracio config;
    
    public static void main(String[] args) {
        mostrarMenu();
    }
    
    public static void mostrarMenu() {
        while (true) {
            System.out.println("Menu Principal:");
            System.out.println("1. Configuración");
            System.out.println("2. Nueva partida");
            System.out.println("3. Salir");
            int option = SC.nextInt();
            
            switch (option) {
                case 1:
                    mostrarMenuConfiguracion();
                    return;
                case 2:
                    NuevaPartida();
                    return;
                case 3:
                    System.out.println("Saliendo");
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    
    public static void mostrarMenuConfiguracion() {
        while (true) {
            System.out.println("1. La mida de taulell");
            System.out.println("2. Tiempo por turno");
            System.out.println("3. Tornar enere");
            
            int option = SC.nextInt();
            
            switch (option) {
                case 1:
                    midaTaulell();
                    return;
                case 2:
                    conftiempo();
                    return;
                case 3:
                    System.out.println("Tornant enrere al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    
    private static void conftiempo() {
        int TempP;
            System.out.println("El temp per partida:");
            System.out.println("El temp predeterminada és entre 30 segons.");
            System.out.println("Introdueixi el nou temp per partida:");
            
            SC.nextLine();
            String input = SC.nextLine();
            if (input.trim().isEmpty()) {
                TempP = 30;
            } else {
                TempP = Integer.parseInt(input);
            }
            config =new Configuracio(0,TempP);
            config.guardarConfiguracio();
    }
    
    public static void midaTaulell() {
        int nuevaMida;
            System.out.println("Mida del taulell:");
            System.out.println("La mida predeterminada és 3.");
            System.out.println("Introdueixi la nova mida del taulell (mínim 3, màxim 10):");
            
            SC.nextLine();
            String input = SC.nextLine();
            if (input.trim().isEmpty()) {
                nuevaMida = 3;
            } else {
                nuevaMida = Integer.parseInt(input);
            }
            if (nuevaMida < 3 || nuevaMida > 10) {
                System.out.println("La mida del taulell ha de ser un valor entre 3 i 10.");
            } else {
                config =new Configuracio(nuevaMida,0);
                config.guardarConfiguracio();
            }
    }
    
    public static void NuevaPartida() {
        System.out.println("Introdueixi el nombre del partida: ");
        String NomPartida = SC.next();
        File folder = new File(NomPartida);
        if (folder.exists()) {
            System.out.println("La partida creada error, la parrtida ya existe.");
        } else {
            if (folder.mkdir()) {
                try {
                    System.out.println("Introdueixi el nombre del Jugador1: ");
                    String Jnm1 = SC.next();
                    System.out.println("Introdueixi el tipo de pieza del Jugador1 (0 o 1): ");
                    int Tipo1 = SC.nextInt();
                    if (Tipo1 != 0 && Tipo1 != 1) {
                        throw new InputMismatchException();
                    }
                    System.out.println("Introdueixi el nombre del Jugador2: ");
                    String Jnm2 = SC.next();
                    int Tipo2 = (Tipo1 == 0) ? 1 : 0;
                    partida = new Partida(Jnm1, Tipo1, Jnm2, Tipo2, NomPartida);
                    try (Scanner sc = new Scanner(new File("config.txt"));
                         FileWriter writer = new FileWriter(String.format("./%s/config.txt", NomPartida))) {
                        writer.write(String.format("%s*%d**%s*%d-%s", Jnm1, Tipo1, Jnm2, Tipo2, NomPartida));
                        int size = sc.nextInt();
                        tauler = new char[size][size];
                        sc.close();
                        writer.close();
                        System.out.println("La partida creada correctamente.");
                        crear_taulell();
                        mostrar_taulell();
                    } catch (FileNotFoundException e) {
                        System.out.println("No troba el fichero de config：" + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("El contenido del archivo de configuración no es válido: " + e.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (InputMismatchException e) {
                    folder.delete();
                    System.out.println("Error: El tipo de pieza debe ser 0 o 1.");
                }
            } else {
                System.out.println("La partida creada error.");
            }
        }
    }
    
    public static void crear_taulell() {
        for (char[] chars : tauler) {
            Arrays.fill(chars, '-');
        }
    }
    
    public static void mostrar_taulell() {
        for (char[] chars : tauler) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }
}