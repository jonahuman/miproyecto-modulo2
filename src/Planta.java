import java.util.Random;

public class Planta extends SerVivo {

    public Planta(int fila, int columna) {

        super(fila, columna, TipoSerVivo.PLANTA);

    }

    public void recibirEnergiaSolar() {

        setEnergia(getEnergia() + 10);

    }

    public void recibirAguaDeLluvia() {

        setEnergia(getEnergia() + 5);

    }

    public void perderEnergiaTerremoto() {

        setEnergia(getEnergia() - 15);

    }

    @Override

    public void pasarTiempo(Tablero tablero, Evento evento) {

        Random rand = new Random();

        /* if e int con movimientos aleatorios */

        int nuevaFila = getFila() + rand.nextInt(3) - 1;

        int nuevaColumna = getColumna() + rand.nextInt(3) - 1;

        if (tablero.esPosicionValida(nuevaFila, nuevaColumna) && tablero.getCelda(nuevaFila, nuevaColumna).estaVacia()) {

            tablero.moverSerVivo(this, nuevaFila, nuevaColumna);

        }

        /* switch de eventos eventos */

        switch (evento) {

            case ECLIPSE_SOLAR:

                perderEnergiaTerremoto();

                break;

            case ECLIPSE_LUNAR:

                recibirEnergiaSolar();

                break;

            case TERREMOTO:

                perderEnergiaTerremoto();

                break;

            case MAREMOTO:

                perderEnergiaTerremoto();

                break;

            case LLUVIA:

                recibirAguaDeLluvia();

                break;

            case SEQUIA:

                setEnergia(getEnergia() - 5);

                break;

        }

        /* disminuir energía con el paso del tiempo */

        setEnergia(getEnergia() - 1);

        /* verificar si la planta ha muerto por falta de energía */

        if (getEnergia() <= 0) {

            setEstaVivo(false);

        }

    }

    @Override

    public String getRepresentacion() {

        return "🌱";

    }

}

/* la planta es representada por (🌱) */

/* en lo personal siento que ponerle emojis en vez de letras lo hace más generación z y no tan millenial o boomer xd */


