package com.practica.Heladas.controller;

import com.practica.Heladas.repository.PaletaRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import com.practica.Heladas.documentos.Paleta;

//permitimos que los endpoints puedan ser consumidos o llamados desde cualquier lugar
@CrossOrigin(origins = "http://localhost:5173")

//marcamos la clase como controlador rest (maneja solicitudes HTTP y retorna una respuesta en JSON o XML)
@RestController

//indicamos la ruta base de la app
@RequestMapping("paletas")
public class PaletaController {
    //crea un objeto de manera automatica del tipo especificado
    @Autowired
    private PaletaRepository paletaRepository;


    /**
     * ! endpoint que muestra las paletas
     *
     * @return una lista de paletas
     */
    @GetMapping("/mostrar")
    public List<Paleta> obtenerPaletas() {
        return paletaRepository.findAll();
    }


    /**
     * ! endpoint que inserta una paleta en la base de datos
     *
     * @param paleta a insertar en la base de datos
     * @return respuesta de tipo HTTP
     * @RequestBody indica que debe de convertir el cuerpo de la solicitud HTTP en un objeto de tipo Paleta
     */
    @PostMapping("/agregar")
    public ResponseEntity<Paleta> agregarPaleta(@RequestBody Paleta paleta) {
        //genera un UUID aleatorio
        UUID id = UUID.randomUUID();

        //le asignamos el UUID generado
        paleta.setId(id.toString());

        //guardamos la paleta en la base de datos
        paletaRepository.save(paleta);

        //retorna una respuesta http con el estatus "creado" y en el cuerpo el propio documento insertado
        return ResponseEntity.status(HttpStatus.CREATED).body(paleta);
    }

    /**
     * !endpoint que busca un documento por su id
     *
     * @param id de documento a buscar
     * @return respuesta de tipo HTTP
     * @PathVariable indica que se debe recuperar la variable de ruta llamada id
     */
    @GetMapping("buscar/{id}")
    public ResponseEntity<Paleta> buscarPaleta(@PathVariable String id) {
        Paleta paleta = paletaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la paleta" + id));

        return ResponseEntity.status(HttpStatus.OK).body(paleta);
    }

    /**
     * !endpoint que actualiza un documento en la base de datos
     *
     * @param id     de documento a actualizar
     * @param paleta objeto a actualizar
     * @return respuesta de tipo HTTP
     * @PathVariable indica que se debe recuperar la variable de ruta llamada id
     * @RequestBody indica que debe de convertir el cuerpo de la solicitud HTTP en un objeto de tipo Paleta
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Paleta> actualizarPaleta(@PathVariable String id, @RequestBody Paleta paleta) {

        //busca la paleta, en caso de que no la encuentre lanza una excepcion con el texto
        Paleta paleta1 = paletaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la paleta" + id));

        //manipula mediante los metodos de acceso los valores en base al objeto recibido
        paleta1.setNombre(paleta.getNombre());
        paleta1.setDescripcion(paleta.getDescripcion());
        paleta1.setCantidad(paleta.getCantidad());
        paleta1.setPrecio(paleta.getPrecio());

        //actualiza la paleta
        Paleta paletaActualizada = paletaRepository.save(paleta1);

        //retorna una respuesta http con el estatus "ok" y en el cuerpo el propio documento actualizado
        return ResponseEntity.status(HttpStatus.OK).body(paleta1);
    }

    /**
     * ! endpoint que permite eliminar un documento de la base de datos
     *
     * @param id del documento a eliminar
     * @return respuesta de tipo HTTP
     * @PathVariable indica que se debe recuperar la variable de ruta llamada id
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Paleta> eliminarPaleta(@PathVariable String id) {

        //busca la paleta, en caso de que no la encuentre lanza una excepcion con el texto
        Paleta paleta = paletaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la paleta" + id));

        //elimina la paleta
        paletaRepository.delete(paleta);

        //retorna una respuesta http con el estatus "ok" y en el cuerpo el propio documento eliminado
        return ResponseEntity.status(HttpStatus.OK).body(paleta);
    }
}
