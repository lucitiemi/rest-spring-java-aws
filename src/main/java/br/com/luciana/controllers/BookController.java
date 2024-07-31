package br.com.luciana.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.luciana.services.BookService;
import br.com.luciana.vo.v1.BookVO;
//import br.com.luciana.vo.v2.BookVOv2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController {

	@Autowired
	private BookService service;
	
	
	// BUSCA TODOS
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // precisa manter o "produces" por causa do swagger
	@Operation(summary = "Finds all Books", description = "Finds all Books", 
		tags = {"Books"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public List<BookVO> findAll() {
		return service.findAll();
	}
	
	
	// BUSCA POR ID
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds a Book", description = "Finds a Book", 
		tags = {"Books"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public BookVO findById(@PathVariable(value= "id") Long id) {
		return service.findById(id);
	}
	
	
	// CRIA
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Adds a new Book", description = "Adds a new Book", 
		tags = {"Books"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public BookVO create(@RequestBody BookVO book) {
		return service.create(book);
	}
	/*@PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookVOv2 createV2(@RequestBody BookVOv2 book) {
		return service.createV2(book);
	}*/
	
	
	// ATUALIZA
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Updates a Book", description = "Updates a Book", 
		tags = {"Books"}, 
		responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public BookVO update(@RequestBody BookVO book) {
		return service.update(book);
	}
	
	
	// DELETA
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a Book", description = "Deletes a Book", 
		tags = {"Books"}, 
		responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public ResponseEntity<?> delete(@PathVariable(value= "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
