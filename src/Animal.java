import java.util.Random;

public class Animal extends SerVivo {

    private boolean ganasDeDormir;

    public Animal(int fila, int columna) {

        super(fila, columna, TipoSerVivo.ANIMAL);

        this.ganasDeDormir = false;

    }

    public void recibirLuzSolar() {

            setEnergia(getEnergia() + 10);
    
    }

    public void recibirGanasDeDormir() {
    
        this.ganasDeDormir = true;
    
    }

    public void perderEnergiaEclipseSolar() {
    
        setEnergia(getEnergia() - 10);
    
    }

    public void ganarEnergiaEclipseLunar() {
    
        setEnergia(getEnergia() + 5);
    
    }

    public void morirTerremoto() {
    
        setEstaVivo(false);
    
    }

    public void morirMaremoto() {
    
        setEstaVivo(false);
    
    }

    public void reproducirse(Animal otro, Tablero tablero) {
    
        if (Math.abs(this.getFila() - otro.getFila()) <= 1 && Math.abs(this.getColumna() - otro.getColumna()) <= 1) {
    
            int nuevaFila = (this.getFila() + otro.getFila()) / 2;
    
            int nuevaColumna = (this.getColumna() + otro.getColumna()) / 2;
    
            Animal nuevoAnimal = new Animal(nuevaFila, nuevaColumna);
    
            tablero.agregarAnimal(nuevoAnimal);
    
        }
    
    }

    @Override
    
    public void pasarTiempo(Tablero tablero, Evento evento) {
        
        Random rand = new Random();

        /* movimiento aleatorio */
        
        int nuevaFila = getFila() + rand.nextInt(3) - 1;
        
        int nuevaColumna = getColumna() + rand.nextInt(3) - 1;
        
        if (tablero.esPosicionValida(nuevaFila, nuevaColumna) && tablero.getCelda(nuevaFila, nuevaColumna).estaVacia()) {
        
            tablero.moverSerVivo(this, nuevaFila, nuevaColumna);
        
        }

        /* switch de eventos para clase animal */
        
        switch (evento) {
            
            case ECLIPSE_SOLAR:
            
                perderEnergiaEclipseSolar();
            
                break;
            
            case ECLIPSE_LUNAR:
            
                ganarEnergiaEclipseLunar();
            
                break;
            
            case TERREMOTO:
            
                morirTerremoto();
            
                break;
            
            case MAREMOTO:
            
                morirMaremoto();
            
                break;
            
            case LLUVIA:
            
                recibirLuzSolar();
            
                break;
                
            case SEQUIA:
            
                setEnergia(getEnergia() - 5);

                break;

        }

        /* disminuir energía con el paso del tiempo */ 

        setEnergia(getEnergia() - 1);

        /* verificar si el animal ha muerto por falta de energía */ 

        if (getEnergia() <= 0) {

            setEstaVivo(false);

        }

    }

    @Override

    public String getRepresentacion() {

        return "🐾";

    }

}

/* gestionar a animal */  /* el animal es representado por (🐾) */

/* nombre: Jonathan Ahumada */

/* JDK 22 e IntelliJ IDEA Community Edition */