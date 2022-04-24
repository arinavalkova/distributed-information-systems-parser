package ru.nsu.fit.dis.valkova.parser.rest.controllers;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
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
public class InfoController {
    private final CRUDNodeService CRUDNodeService;
    private final SearchNodeService searchNodeService;

    @SneakyThrows
    @PostMapping(value = "/node/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NodeEntity> createNode(@RequestBody NodeEntity nodeEntity) {
        log.info("creating...");
        return ResponseEntity.ok(CRUDNodeService.create(nodeEntity));
    }

    @SneakyThrows
    @GetMapping(value = "/node/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NodeEntity> getNode(@PathVariable("id") Long nodeId) {
        log.info("Вызван метод {}", "/node/get");
        return ResponseEntity.ok(CRUDNodeService.get(nodeId));
    }

    @SneakyThrows
    @PutMapping(value = "/node/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NodeEntity> updateNode(@PathVariable("id") Long nodeId,
                                                 @RequestBody HttpEntity<NodeEntity> nodeDTORequestEntity) {
        log.info("updating...");
        return ResponseEntity.ok(CRUDNodeService.update(nodeDTORequestEntity.getBody()));
    }

    @DeleteMapping("/node/delete/{id}")
    public void delete(@PathVariable("id") Long nodeId) {
        log.info("deleting...");
        CRUDNodeService.delete(nodeId);
    }

    @GetMapping("/node/search")
    public ResponseEntity<List<NodeEntity>> search(@RequestParam("latitude") Double latitude,
                                                   @RequestParam("longitude") Double longitude,
                                                   @RequestParam("" + "") Double radius) {
        log.info("searching...");
        return ResponseEntity.ok(searchNodeService.search(latitude, longitude, radius));
    }
}