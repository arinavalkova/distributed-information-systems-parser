package ru.nsu.fit.dis.valkova.parser.rest.controllers;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;
import ru.nsu.fit.dis.valkova.parser.rest.services.search.SearchNodeService;
import ru.nsu.fit.dis.valkova.parser.rest.services.crud.CRUDNodeService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/info/")
@RequiredArgsConstructor
public class NodeController {
    private final CRUDNodeService CRUDNodeService;
    private final SearchNodeService searchNodeService;

    @SneakyThrows
    @PostMapping(value = "/node", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNode(@RequestBody NodeEntity nodeEntity) {
        log.info("creating node...");
        NodeEntity savedNode = CRUDNodeService.create(nodeEntity);
        if (savedNode == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Node creation error occurred");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedNode);
    }

    @SneakyThrows
    @GetMapping(value = "/node/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNode(@PathVariable("id") Long nodeId) {
        log.info("getting node by id...");
        NodeEntity foundNode = CRUDNodeService.get(nodeId);
        if (foundNode == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Node getting error occurred");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundNode);
    }

    @SneakyThrows
    @PutMapping(value = "/node", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateNode(@RequestBody NodeEntity nodeEntity) {
        log.info("updating node...");
        NodeEntity updatedEntity = CRUDNodeService.update(nodeEntity);
        if (updatedEntity == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Node update error occurred");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedEntity);
    }

    @DeleteMapping("/node/{id}")
    public ResponseEntity<Object> deleteNode(@PathVariable("id") Long nodeId) {
        log.info("deleting node...");
        NodeEntity foundNode = CRUDNodeService.get(nodeId);
        if (foundNode == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Node update error occurred");
        }
        else {
            CRUDNodeService.delete(nodeId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(foundNode);
        }
    }

    @GetMapping("/node/search")
    public ResponseEntity<Object> search(@RequestParam("latitude") Double latitude,
                                                   @RequestParam("longitude") Double longitude,
                                                   @RequestParam("" + "") Double radius) {
        log.info("searching node...");
        List<NodeEntity> foundNodes = searchNodeService.search(latitude, longitude, radius);
        if (foundNodes.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Nodes not found in database!");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(foundNodes);
        }
    }
}