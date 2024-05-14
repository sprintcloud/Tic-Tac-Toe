import java.util.ArrayList;

public class Partida {
    private String nombre;
    private ArrayList<jugador> jgd;
    
    Partida(String Jnm1, int Jtp_pz1, String Jnm2, int Jtp_pz2, String nmP) {
        this.jgd = new ArrayList<>();
        this.jgd.add(new jugador(Jnm1, Jtp_pz1)) ;
        this.jgd.add(new jugador(Jnm2, Jtp_pz2));
        this.nombre = nmP;
    }
    
    public ArrayList<jugador> getJugadores() {
        return this.jgd;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
}
