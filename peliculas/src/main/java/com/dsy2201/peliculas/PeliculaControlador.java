package main.java.com.dsy2201.peliculas;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeliculaControlador {
    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaControlador() {
        //inicio de datos
        peliculas.add(new Pelicula(0, "Relatos Salvajes", 2014, "Damián Szifron", "Antologia", "Antologia de historias en momentos de furia."));
        peliculas.add(new Pelicula(1, "Estado Electrico", 2025, "Hnos. Russo", "Ciencia-ficcion", "Estado eléctrico es una película de comedia dramática, aventuras y ciencia ficción estadounidense dirigida por Anthony y Joe Russo a partir de un guion de Christopher Markus y Stephen McFeely, basada vagamente en la novela gráfica de 2018 del mismo nombre de Simon Stålenhag."));
        peliculas.add(new Pelicula(2, "La Cosa", 1982, "John Carpenter", "Horror", "Un alienígena que se transforma en otras formas vivientes, invade una base científica de la Antártida."));
        peliculas.add(new Pelicula(3, "Conan el Barbaro", 1982, "John Millius", "Aventura", "Adaptacion de la historia de Robert E. Howard. Conan se organiza con un mongol y con una reina para quitarle la espada de su padre al rey de las serpientes."));
        peliculas.add(new Pelicula(4, "Gran Torino", 2008, "Clint Eastwood", "Supenso", "Walt Kowalski es un veterano de guerra duro e inflexible apasionado por su auto, un Gran Torino del 72. La llegada de unos inmigrantes asiáticos al barrio ablanda su carácter, pero unos pandilleros generan problemas y Walt se ve envuelto en ellos."));
    }

    @GetMapping("/peliculas")
    public List<Pelicula> getPeliculas(){
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    public Pelicula getPeliculaById(@PathVariable int id){
        for (main.java.com.dsy2201.peliculas.pelicula pelicula : peliculas){
            if (pelicula.getId() == id){
                return pelicula;
            }
        }
        return null;
    }
}