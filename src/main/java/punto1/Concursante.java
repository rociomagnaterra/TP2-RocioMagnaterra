package punto1;

public class Concursante {
    private String nombre;
    private int puntos;
    private static int contadorId = 1;
    private final int id;

    public Concursante(String nombre) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.puntos = 0;
    }
    public int getPuntos() {
        return puntos;
    }
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    //Sobreescribi para comprarar el equalss
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concursante that = (Concursante) o;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }
}