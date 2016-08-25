/*Generated by WaveMaker Studio*/
package com.testdb_hsql.rolodex;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Arrays;
import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.persistence.UniqueConstraint;

/**
 * FilmActor generated by hbm2java
 */
@Entity
@Table(name = "FILM_ACTOR", schema = "PUBLIC")
public class FilmActor implements java.io.Serializable {

    private FilmActorId id;

    private Actor actor;

    private Film film;

    private int filmId;

    private int actorId;

    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "actorId", column = @Column(name = "ACTOR_ID", nullable = false)), @AttributeOverride(name = "filmId", column = @Column(name = "FILM_ID", nullable = false)) })
    public FilmActorId getId() {
        return this.id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTOR_ID", nullable = false, insertable = false, updatable = false)
    public Actor getActor() {
        return this.actor;
    }

    public void setActor(Actor actor) {
        if (actor != null) {
            this.setActorId(actor.getActorId());
        }
        this.actor = actor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FILM_ID", nullable = false, insertable = false, updatable = false)
    public Film getFilm() {
        return this.film;
    }

    public void setFilm(Film film) {
        if (film != null) {
            this.setFilmId(film.getFilmId());
        }
        this.film = film;
    }

    @Column(name = "`FILM_ID`", nullable = false, precision = 19, scale = 0)
    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Column(name = "`ACTOR_ID`", nullable = false, precision = 19, scale = 0)
    public int getActorId() {
        return this.actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public FilmActor() {
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof FilmActor))
            return false;
        FilmActor that = (FilmActor) o;
        return ((this.getId() == that.getId()) || (this.getId() != null && that.getId() != null && this.getId().equals(that.getId())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        return result;
    }
}