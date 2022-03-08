package com.furkanisitan.countrycityapi.api.abstracts;

import com.furkanisitan.core.results.Result;
import com.furkanisitan.core.results.ResultData;
import com.furkanisitan.countrycityapi.api.Examples;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cities", description = "The City API")
public interface CitiesApi {

    @Operation(summary = "Returns a list of cities.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.City.Success.GET_ALL)}))
    ResponseEntity<?> all();

    @Operation(summary = "Returns a specific city.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.City.Success.GET)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: City", value = Examples.Error.NOT_FOUND_BY_ID)}))
    ResponseEntity<?> one(long id);

    @Operation(summary = "Creates a new city.")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.City.Success.CREATE)}),
            headers = @Header(name = HttpHeaders.LOCATION, description = "Location of the newly created resource.", schema = @Schema(type = "string")))
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "blank", description = "fieldName: name | countryCode", value = Examples.Error.BAD_REQUEST_BLANK),
            @ExampleObject(name = "positive", description = "fieldName: population", value = Examples.Error.BAD_REQUEST_POSITIVE)}))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "foreign key", description = "key: countryCode", value = Examples.Error.CONFLICT_FOREIGN_KEY)}))
    ResponseEntity<?> create(CityCreateRequest request);

    @Operation(summary = "Updates a specific city.")
    @ApiResponse(responseCode = "204", content = @Content)
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "blank", description = "fieldName: name | countryCode", value = Examples.Error.BAD_REQUEST_BLANK),
            @ExampleObject(name = "positive", description = "fieldName: population", value = Examples.Error.BAD_REQUEST_POSITIVE),
            @ExampleObject(name = "null", description = "fieldName: id", value = Examples.Error.BAD_REQUEST_NULL),
            @ExampleObject(name = "mismatch", description = "param: id - field: id", value = Examples.Error.BAD_REQUEST_MISMATCH)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class),
            examples = @ExampleObject(name = "no record", description = "recordName: City", value = Examples.Error.NOT_FOUND_BY_ID)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "foreign key", description = "key: countryCode", value = Examples.Error.CONFLICT_FOREIGN_KEY)}))
    ResponseEntity<?> update(long id, CityUpdateRequest request);

    @Operation(summary = "Deletes a specific city.")
    @ApiResponse(responseCode = "204", content = @Content)
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class),
            examples = @ExampleObject(name = "no record", description = "recordName: City", value = Examples.Error.NOT_FOUND_BY_ID)))
    ResponseEntity<?> delete(long id);

}
