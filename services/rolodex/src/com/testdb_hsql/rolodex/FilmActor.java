/*Generated by WaveMaker Studio*/
package com.testdb_hsql.rolodex;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FilmActor generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FILM_ACTOR`", schema = "PUBLIC")
@IdClass(FilmActorId.class)
public class FilmActor implements Serializable {

    private Integer actorId;
    private Integer filmId;
    private Actor actor;
    private Film film;

    @Id
    @Column(name = "`ACTOR_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getActorId() {
        return this.actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Id
    @Column(name = "`FILM_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getFilmId() {
        return this.filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ACTOR_ID`", referencedColumnName = "`ACTOR_ID`", insertable = false, updatable = false)
    public Actor getActor() {
        return this.actor;
    }

    public void setActor(Actor actor) {
        if(actor != null) {
            this.actorId = actor.getActorId();
        }

        this.actor = actor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FILM_ID`", referencedColumnName = "`FILM_ID`", insertable = false, updatable = false)
    public Film getFilm() {
        return this.film;
    }

    public void setFilm(Film film) {
        if(film != null) {
            this.filmId = film.getFilmId();
        }

        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmActor)) return false;
        final FilmActor filmActor = (FilmActor) o;
        return Objects.equals(getActorId(), filmActor.getActorId()) &&
                Objects.equals(getFilmId(), filmActor.getFilmId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActorId(),
                getFilmId());
    }
}

