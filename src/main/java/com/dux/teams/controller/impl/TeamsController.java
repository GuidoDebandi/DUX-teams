package com.dux.teams.controller.impl;

import com.dux.teams.dto.ErrorResponse;
import com.dux.teams.dto.TeamDto;
import com.dux.teams.model.Team;
import com.dux.teams.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "TeamsController", description = "Interfaz de exposición de los recursos de negocio para la manipulación y consultas de equipos.")
@RestController
public class TeamsController {

    @Value("${teams.uri}")
    public static final String TEAMS_URI = "/equipos";
    @Value("${teams.find.url}")
    public static final String FIND_URL = "/buscar";



    @Autowired
    private TeamService teamService;

    @Operation(summary = "Devuelve la lista de todos los equipos de fútbol registrados", description = "Devuelve la lista de todos los equipos de fútbol registrados.",tags = {"TeamsController"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation",content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = TEAMS_URI)
    public List<Team> findTeamsController(){
        return teamService.findAllTeams();
    }

    @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devuelve la información del equipo correspondiente al ID proporcionado.",content = @Content(schema = @Schema(implementation = Team.class))),
        @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = TEAMS_URI+ "/{id}")
    public Team findTeamByIdController(@PathVariable Integer id){
        return teamService.findTeamById(id);
    }

    @Operation(summary = "Devuelve la lista de todos los equipos de fútbol registrados", description = "Devuelve la lista de todos los equipos de fútbol registrados.",tags = {"TeamsController"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation",content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
        @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = TEAMS_URI + FIND_URL)
    public List<Team> findTeamsByNameController(@Param("nombre") String name){
        return teamService.findTeamsByName(name);
    }

    @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Devuelve la información del equipo correspondiente al ID proporcionado.",content = @Content(schema = @Schema(implementation = Team.class))),
        @ApiResponse(responseCode = "400", description = "La solicitud es invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(path = TEAMS_URI)
    public Team createTeamController(@RequestBody TeamDto teamDto){
        return teamService.createTeam(teamDto);
    }


    @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devuelve la información actualizada del equipo.",content = @Content(schema = @Schema(implementation = Team.class))),
        @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping(path = TEAMS_URI+"/{id}")
    public Team updateTeamController(@RequestBody TeamDto teamDto,@PathVariable Integer id){
        return teamService.updateTeam(id,teamDto);
    }


    @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Equipo eliminado con éxito.",content = @Content(schema = @Schema(implementation = Team.class))),
        @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping(path = TEAMS_URI+ "/{id}")
    public void deleteTeamController(@PathVariable Integer id){
        teamService.deleteTeam(id);
    }



}
