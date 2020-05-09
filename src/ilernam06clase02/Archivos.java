package ilernam06clase02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Archivos {

    // campos de clase ---------------------------------------------------------
    File pac1 = null;
    File pac2 = null;
    File ilerna = null;
    File copiaIlerna = null;
    List<Integer> bytesImagen = null;

    // constructor -------------------------------------------------------------
    public Archivos() {

        try {
            /* la clase File, crea una instancia a ese archivo, si no exite
            el archivo lo crea, pero no crea el directorio
             */

            // creo una instancia de los archivos para trabajar con texto
            pac1 = new File("assets/pac1.txt");
            pac2 = new File("assets/pac2.txt");

            // creo una instancia de los archivos para trabajar en binario
            ilerna = new File("assets/ilerna.jpg");
            copiaIlerna = new File("assets/copiailerna.jpg");
            
        } catch (NullPointerException e) {

            System.out.println("Error. Problema con el directorio");
        }

        // creo una lista para los datos en binario que estará 
        // entre el metodo de lectura al de escritura
        bytesImagen = new ArrayList<>();
    }

    // metodos -----------------------------------------------------------------
    
    // Inicio la llamada a los procedimientos
    private void inicio(){
        
        /* ------   Ficheros secuenciales de TEXTO -----------------------*/
        // llamo a los metodos de lectura/escritura secuenciol de texto
        System.out.println("\n --- Lectura secuencial de texto.\n");
        lecturaSecuencialTexto(pac1);

        System.out.println(" \n--- Escritura secuencial de texto.\n");
        escrituraSecuencialTexto(pac1);

        /* ------   Ficheros aleatorias de TEXTO -------------------------*/
        // llamo a los metodos de lectura/escritura aleatorios de texto
        System.out.println("\n --- Lectura aleatoria de texto.\n");
        lecturaAleatoriaTexto(pac2);

        System.out.println("\n --- Escritura aleatoria de texto.\n");
        escrituraAleatoriaTexto(pac2);

        /* ------   Ficheros secuenciales BINARIOS -----------------------*/
        // llamo a los metodos de lectura/escritura secuenciales binarios
        System.out.println("\n --- Lectura secuencia binaria.\n");
        lecturaSecuencialBinaria(ilerna);

        System.out.println("\n --- Escritura secuencia binaria.\n");
        escrituraSecuencialBinario(copiaIlerna);
        
    }

    // Lectura secuenciol de texto
    private void lecturaSecuencialTexto(File archivo) {

        try {
            // creo un objeto FileWriter. 
            FileReader fileReader = new FileReader(archivo);
            int letra;

            while ((letra = fileReader.read()) != -1) {
                System.out.print((char) letra);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer en el fichero secuencial: " 
                    + archivo.getName());
            System.exit(-1);
        }

    }

    // Escritura secuenciol de texto
    private void escrituraSecuencialTexto(File archivo) {

        try {
            // creo un objeto FileWriter
            FileWriter fileWriter = new FileWriter(archivo, true);
            fileWriter.write("Estoy añadiendo al archivo pac1.txt\n");
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException ex) {
            System.out.println("Error al escribir en el fichero secuenciol: " 
                    + archivo.getName());
            System.exit(-1);
        }

    }

    // Lectura aleatoria de texto
    private void lecturaAleatoriaTexto(File archivo) {
        try {
            // creo un objeto RandomAccessFile
            RandomAccessFile randomAccessReader = new RandomAccessFile(archivo, "rw");
            int letra;

            while ((letra = randomAccessReader.read()) != -1) {
                System.out.print((char) letra);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer en el fichero aleatoria: " 
                    + archivo.getName());
        }

    }

    // Escritura aleatoria de texto
    private void escrituraAleatoriaTexto(File archivo) {
        try {
            // creo un objeto RandomAccessFile
            RandomAccessFile randomAccessReader = new RandomAccessFile(archivo, "rw");

            // muestra la posicion actual del cursor
            System.out.println("La posicion del puntero es :" 
                    + randomAccessReader.getFilePointer());

            // movemos el puntero al final
            randomAccessReader.seek(archivo.length());

            // escribimos con uno de los muchos metodos que hay
            randomAccessReader.writeBytes("Estoy añadiendo al archivo pac2.txt\n");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero aleatoria: " 
                    + archivo.getName());
        }

    }

    // Lectura secuencial binaria
    private void lecturaSecuencialBinaria(File archivo) {

        try {
            // creo un objeto FileInputStream
            FileInputStream fileInputStream = new FileInputStream(archivo);
            int letra;

            while ((letra = fileInputStream.read()) != -1) {
                // guardo en el array la imagen que voy leyendo
                bytesImagen.add(letra);
            }
            fileInputStream.close();

        } catch (IOException ex) {
            System.out.println("Error al leer en el archivo secuencial binario");

        }
    }

    // Escritura secuencial binaria
    private void escrituraSecuencialBinario(File archivo) {
        try {
            // creo un objeto FileOutputStream
            FileOutputStream fileOutputStream = new FileOutputStream(archivo, true);
            // Feddback 
            System.out.println("Tamaño del archivo: " + bytesImagen.size() + " bytes");
            
            // creo un bucle y voy escribiendo lo que guarde en la lista
            for (Integer datos : bytesImagen) {
                fileOutputStream.write(datos);
            }

        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo secuencial binario");
        }
    }

    // metodo main  ------------------------------------------------------------
    public static void main(String[] args) {

        Archivos archivo = new Archivos();
        archivo.inicio();
    }

}
