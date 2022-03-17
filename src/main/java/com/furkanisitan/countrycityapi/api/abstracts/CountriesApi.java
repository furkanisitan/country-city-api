package com.furkanisitan.countrycityapi.api.abstracts;

import com.furkanisitan.core.results.Result;
import com.furkanisitan.core.results.ResultData;
import com.furkanisitan.countrycityapi.api.Examples;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Tag(name = "Countries", description = "The Country API")
public interface CountriesApi {

    @Operation(summary = "Returns a list of countries.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.Country.Success.GET_ALL)}))
    ResponseEntity<?> all(String[] filter, String[] sort, Integer page, Integer size);

    @Operation(summary = "Returns a specific country.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.Country.Success.GET)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Country", value = Examples.Error.NOT_FOUND_BY_ID)}))
    ResponseEntity<?> one(long id);

    @Operation(summary = "Creates a new country.")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.Country.Success.CREATE)}),
            headers = @Header(name = HttpHeaders.LOCATION, description = "Location of the newly created resource.", schema = @Schema(type = "string")))
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "blank", description = "fieldName: code | name", value = Examples.Error.BAD_REQUEST_BLANK)}))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "unique", description = "fieldName: code", value = Examples.Error.CONFLICT_NOT_UNIQUE),
            @ExampleObject(name = "foreign key", description = "key: languages.languageId", value = Examples.Error.CONFLICT_FOREIGN_KEY)}))
    ResponseEntity<?> create(CountryCreateRequest request);

    @Operation(summary = "Updates a specific country.")
    @ApiResponse(responseCode = "204", content = @Content)
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "blank", description = "fieldName: code | name", value = Examples.Error.BAD_REQUEST_BLANK),
            @ExampleObject(name = "null", description = "fieldName: id", value = Examples.Error.BAD_REQUEST_NULL),
            @ExampleObject(name = "mismatch", description = "param: id - field: id", value = Examples.Error.BAD_REQUEST_MISMATCH)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Country", value = Examples.Error.NOT_FOUND_BY_ID)}))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "unique", description = "fieldName: code", value = Examples.Error.CONFLICT_NOT_UNIQUE),
            @ExampleObject(name = "foreign key", description = "key: languages.languageId", value = Examples.Error.CONFLICT_FOREIGN_KEY)}))
    ResponseEntity<?> update(long id, CountryUpdateRequest request);

    @Operation(summary = "Deletes a specific country.")
    @ApiResponse(responseCode = "204", content = @Content)
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Country", value = Examples.Error.NOT_FOUND_BY_ID)}))
    ResponseEntity<?> delete(long id);

    @Operation(summary = "Returns a list of cities of the country.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.City.Success.GET_ALL)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Country", value = Examples.Error.NOT_FOUND_BY_ID)}))
    ResponseEntity<?> allCities(long id);

}
