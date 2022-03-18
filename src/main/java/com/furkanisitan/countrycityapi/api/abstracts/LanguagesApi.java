package com.furkanisitan.countrycityapi.api.abstracts;

import com.furkanisitan.core.results.Result;
import com.furkanisitan.core.results.ResultData;
import com.furkanisitan.countrycityapi.api.Examples;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Tag(name = "Languages", description = "The Language API")
public interface LanguagesApi {

    @Operation(summary = "Returns a list of languages.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.Language.Success.GET_ALL)}))
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "invalid field", value = Examples.Error.BAD_REQUEST_INVALID_FIELD),
            @ExampleObject(name = "invalid filter", value = Examples.Error.BAD_REQUEST_INVALID_FILTER),
            @ExampleObject(name = "invalid value", value = Examples.Error.BAD_REQUEST_INVALID_VALUE),
            @ExampleObject(name = "unsupported filter", value = Examples.Error.BAD_REQUEST_UNSUPPORTED_FILTER)}))
    ResponseEntity<?> all(String[] filter, String[] sort, Integer page, Integer size);

    @Operation(summary = "Returns a specific language.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.Language.Success.GET)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Language", value = Examples.Error.NOT_FOUND_BY_ID)}))
    ResponseEntity<?> one(long id);

    @Operation(summary = "Creates a new language.")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResultData.class), examples = {
            @ExampleObject(Examples.Language.Success.CREATE)}),
            headers = @Header(name = HttpHeaders.LOCATION, description = "Location of the newly created resource.", schema = @Schema(type = "string")))
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "blank", description = "fieldName: code | name", value = Examples.Error.BAD_REQUEST_BLANK)}))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "unique", description = "fieldName: code", value = Examples.Error.CONFLICT_NOT_UNIQUE)}))
    ResponseEntity<?> create(LanguageCreateRequest request);

    @Operation(summary = "Updates a specific language.")
    @ApiResponse(responseCode = "204", content = @Content)
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "blank", description = "fieldName: code | name", value = Examples.Error.BAD_REQUEST_BLANK),
            @ExampleObject(name = "null", description = "fieldName: id", value = Examples.Error.BAD_REQUEST_NULL),
            @ExampleObject(name = "mismatch", description = "param: id - field: id", value = Examples.Error.BAD_REQUEST_MISMATCH)}))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Language", value = Examples.Error.NOT_FOUND_BY_ID)}))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "unique", description = "fieldName: code", value = Examples.Error.CONFLICT_NOT_UNIQUE)}))
    ResponseEntity<?> update(long id, LanguageUpdateRequest request);

    @Operation(summary = "Deletes a specific language.")
    @ApiResponse(responseCode = "204", content = @Content)
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Result.class), examples = {
            @ExampleObject(name = "no record", description = "recordName: Language", value = Examples.Error.NOT_FOUND_BY_ID)}))
    ResponseEntity<?> delete(long id);

}
