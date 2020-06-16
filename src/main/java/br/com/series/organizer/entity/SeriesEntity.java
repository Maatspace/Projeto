package br.com.series.organizer.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "series_catalog")
@EntityListeners(AuditingEntityListener.class)
public class SeriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, length = 50)
	private String seriesName;

	@Column(nullable = false, length = 150)
	private String seriesGenre;

	@Column(nullable = false, length = 10)
	private String watched;

	public String getWatched() {
		return watched;
	}

	public void setWatched(String watched) {
		this.watched = watched;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSeriesGenre() {
		return seriesGenre;
	}

	public void setSeriesGenre(String seriesGenre) {
		this.seriesGenre = seriesGenre;
	}

}
