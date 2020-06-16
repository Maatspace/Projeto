package br.com.series.organizer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import br.com.series.organizer.entity.SeriesEntity;
import br.com.series.organizer.repository.SeriesRepository;

@RestController
@RequestMapping("catalog")
public class SeriesController {

	@Autowired
	private SeriesRepository seriesRepo;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<SeriesEntity> getAllSeries() {
		return seriesRepo.findAll();
	}
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SeriesEntity> getUserById(@PathVariable(value = "id") Long serieId) throws Exception{
		
		SeriesEntity serie = seriesRepo.findById(serieId).orElseThrow(() -> 
				new ResourceAccessException("Serie not found on :: " + serieId));
		
		return ResponseEntity.ok().body(serie);
	}
	
	/**
	 * Example of a POST request
	{
		"seriesName":"Futurama",
		"seriesGenre":"Comedy; Sitcon; Sci-Fi",
		"watched":"yes"
	}
	*/
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public SeriesEntity createSerie(@Validated @RequestBody SeriesEntity serie) {
		return seriesRepo.save(serie);
	}

	
	@PutMapping(path="/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			 				  produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SeriesEntity> updateSeries(@PathVariable(value = "id") 
				Long serieId, @Validated @RequestBody SeriesEntity serieDetails) throws Exception {
		
		SeriesEntity serie = seriesRepo.findById(serieId).orElseThrow(() ->
			new ResourceAccessException("Serie not found on :: " + serieId));
		
		serie.setSeriesName(serieDetails.getSeriesName());
		serie.setSeriesGenre(serieDetails.getSeriesGenre());
		serie.setWatched(serieDetails.getWatched());
		final SeriesEntity updatedSerie = seriesRepo.save(serie);
		
		return ResponseEntity.ok(updatedSerie);
	}

	
	@DeleteMapping(path="/{id}",
				   produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Boolean> deleteSeries(@PathVariable(value ="id") Long serieId) throws Exception {
		
		SeriesEntity serie = seriesRepo.findById(serieId).orElseThrow(() ->
			new ResourceAccessException("Serie not found on :: " + serieId));
		
		seriesRepo.delete(serie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
