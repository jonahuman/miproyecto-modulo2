public abstract class SerVivo {
    private int fila;
    private int columna;
    private int energia;
    private boolean estaVivo;
    private TipoSerVivo tipo;

    public SerVivo(int fila, int columna, TipoSerVivo tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.energia = 100;
        this.estaVivo = true;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public TipoSerVivo getTipo() {
        return tipo;
    }

    public abstract void pasarTiempo(Tablero tablero, Evento evento);
    public abstract String getRepresentacion();
}




/* esta clase ser vivo va a gestionar cada comportamiento, representación y tiempo */

