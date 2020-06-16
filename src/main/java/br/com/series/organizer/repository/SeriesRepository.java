package br.com.series.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.series.organizer.entity.SeriesEntity;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> { 
	
	
	
}
