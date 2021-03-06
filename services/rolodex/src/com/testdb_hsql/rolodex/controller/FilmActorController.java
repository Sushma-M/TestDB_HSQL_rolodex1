/*Generated by WaveMaker Studio*/
package com.testdb_hsql.rolodex.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.testdb_hsql.rolodex.FilmActor;
import com.testdb_hsql.rolodex.FilmActorId;
import com.testdb_hsql.rolodex.service.FilmActorService;

/**
 * Controller object for domain model class FilmActor.
 * @see FilmActor
 */
@RestController("rolodex.FilmActorController")
@Api(value = "FilmActorController", description = "Exposes APIs to work with FilmActor resource.")
@RequestMapping("/rolodex/FilmActor")
public class FilmActorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmActorController.class);

    @Autowired
    @Qualifier("rolodex.FilmActorService")
    private FilmActorService filmActorService;

    @ApiOperation(value = "Creates a new FilmActor instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FilmActor createFilmActor(@RequestBody FilmActor filmActor) {
        LOGGER.debug("Create FilmActor with information: {}", filmActor);
        filmActor = filmActorService.create(filmActor);
        LOGGER.debug("Created FilmActor with information: {}", filmActor);
        return filmActor;
    }

    @ApiOperation(value = "Returns the FilmActor instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FilmActor getFilmActor(@RequestParam(value = "actorId", required = true) Integer actorId, @RequestParam(value = "filmId", required = true) Integer filmId) throws EntityNotFoundException {
        FilmActorId filmactorId = new FilmActorId();
        filmactorId.setActorId(actorId);
        filmactorId.setFilmId(filmId);
        LOGGER.debug("Getting FilmActor with id: {}", filmactorId);
        FilmActor filmActor = filmActorService.getById(filmactorId);
        LOGGER.debug("FilmActor details with id: {}", filmActor);
        return filmActor;
    }

    @ApiOperation(value = "Updates the FilmActor instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FilmActor editFilmActor(@RequestParam(value = "actorId", required = true) Integer actorId, @RequestParam(value = "filmId", required = true) Integer filmId, @RequestBody FilmActor filmActor) throws EntityNotFoundException {
        filmActor.setActorId(actorId);
        filmActor.setFilmId(filmId);
        LOGGER.debug("FilmActor details with id is updated with: {}", filmActor);
        return filmActorService.update(filmActor);
    }

    @ApiOperation(value = "Deletes the FilmActor instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFilmActor(@RequestParam(value = "actorId", required = true) Integer actorId, @RequestParam(value = "filmId", required = true) Integer filmId) throws EntityNotFoundException {
        FilmActorId filmactorId = new FilmActorId();
        filmactorId.setActorId(actorId);
        filmactorId.setFilmId(filmId);
        LOGGER.debug("Deleting FilmActor with id: {}", filmactorId);
        FilmActor filmActor = filmActorService.delete(filmactorId);
        return filmActor != null;
    }

    /**
     * @deprecated Use {@link #findFilmActors(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FilmActor instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FilmActor> findFilmActors(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FilmActors list");
        return filmActorService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of FilmActor instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FilmActor> findFilmActors(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FilmActors list");
        return filmActorService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFilmActors(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return filmActorService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of FilmActor instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countFilmActors(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting FilmActors");
        return filmActorService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FilmActorService instance
	 */
    protected void setFilmActorService(FilmActorService service) {
        this.filmActorService = service;
    }
}
