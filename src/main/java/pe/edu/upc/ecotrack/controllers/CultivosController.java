package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.BuscarCultivosDTO;
import pe.edu.upc.ecotrack.dtos.CultivosDTO;
import pe.edu.upc.ecotrack.entities.Cultivos;
import pe.edu.upc.ecotrack.serviceinterfaces.ICultivosService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cultivos")
public class CultivosController {
    @Autowired
    private ICultivosService cS;
    @GetMapping
    public List<CultivosDTO> listar(){
        return cS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CultivosDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody CultivosDTO dto) {
        ModelMapper m = new ModelMapper();
        Cultivos c = m.map(dto, Cultivos.class);
        cS.insert(c);
    }
    @GetMapping("/{id}")
    public CultivosDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        CultivosDTO dto = m.map(cS.listId(id), CultivosDTO.class);
        return dto;
    }
    @PutMapping
    public void modificar(@RequestBody CultivosDTO dto) {
        ModelMapper m = new ModelMapper();
        Cultivos c = m.map(dto, Cultivos.class);
        cS.update(c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }

    @GetMapping("/buscarCultivos")
    public List<BuscarCultivosDTO>buscarCultivos(@RequestParam String nombre) {
        List<String[]> lista = cS.buscarNombre(nombre);
        List<BuscarCultivosDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            BuscarCultivosDTO dto = new BuscarCultivosDTO();
            dto.setIdCultivo(Integer.parseInt(columna[0]));
            dto.setTipoCultivo(columna[1]);
            listaDTO.add(dto);
        }
        return listaDTO;
    }

}