package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.converter.GroupConverter;
import com.aca_disqo.generactive.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/group")
public class GroupController {

    private final GroupService groupService;
    private final GroupConverter groupConverter;

    public GroupController(GroupService groupService, GroupConverter groupConverter) {
        this.groupService = groupService;
        this.groupConverter = groupConverter;
    }

    @GetMapping()
    public ResponseEntity<List<? extends GroupDTO>> getAll() {
        return ResponseEntity.ok(groupConverter.convert(groupService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id){
        return ResponseEntity.ok(groupConverter.convert(groupService.get(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<GroupDTO> getGroupById(@RequestParam String name){
        return ResponseEntity.ok(groupConverter.convert(groupService.findGroupByName(name)));
    }

    @PostMapping()
    public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO groupDTO){
        return ResponseEntity.ok(groupConverter.convert(groupService.create(groupDTO)));
    }

    @PutMapping()
    public ResponseEntity<GroupDTO> update(@PathVariable Long id, @RequestBody GroupDTO groupDTO){
        return ResponseEntity.ok(groupConverter.convert(groupService.update(id,groupDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(groupService.deleteById(id));
    }
}
