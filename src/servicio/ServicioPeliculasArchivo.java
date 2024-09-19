package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo(){
    File archivo = new File(this.NOMBRE_ARCHIVO);
    try {
        if(archivo.exists()){
            System.out.println("El archivo exite!");
        } else {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        }
     } catch (Exception e) {
        System.out.println("Ocurrio un error al abrir archivo: "+ e.getMessage());
     }
    }

    @Override
    public void listarPeliculas() {
        //volvemos a abrir el archivo
        File archivo = new File(this.NOMBRE_ARCHIVO);
        try {
            System.out.println("Lista de Peliculas");
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));

            String linea;
            linea=entrada.readLine();

            while(linea !=null) {
                Pelicula pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                //Antes de terminar el ciclo volvemos a leer la siguiente linea
                linea= entrada.readLine();
            }

            entrada.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error a leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        File archivo = new File(this.NOMBRE_ARCHIVO);

        try {
            //Revisamos si existe el archivo
            anexar= archivo.exists();
            PrintWriter salida = new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(pelicula);
            salida.close();
            System.out.println("se agrego al archivo: " +pelicula);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al agregar pelicula: " + e.getMessage());
        }


    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        File archivo = new File(this.NOMBRE_ARCHIVO);
        try{
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto= entrada.readLine();
            int indice=1;
            boolean encontrada= false;
            String peliculaBuscar= pelicula.getNombre();

            while(lineaTexto !=null){
                if(peliculaBuscar !=null && peliculaBuscar.equalsIgnoreCase(lineaTexto) ){
                    encontrada = true;
                    break;
                }
                lineaTexto= entrada.readLine();
                indice++;
            }
            if(encontrada){
                System.out.println("Pelicula "+ lineaTexto +" encontrada - linea "+ indice);
            } else {
                System.out.println("No se ha encontrado la pelicula " + pelicula.getNombre());
            }

            entrada.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar en el archivo: "+ e.getMessage());
        }

    }
}
